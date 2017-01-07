package org.usfirst.frc.team166.robot.subsystems;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team166.robot.MultiSpeedController;
import org.usfirst.frc.team166.robot.PIDSpeedController;
import org.usfirst.frc.team166.robot.Robot;
import org.usfirst.frc.team166.robot.RobotMap;
import org.usfirst.frc.team166.robot.commands.drive.DriveWithJoysticks;

/**
 *
 */
public class Drive extends Subsystem {

	final double distancePerPulse = (Math.PI * 10.25) / 1024.0; // calculated based on wheel diameter and encoder specs
	// final double distancePerPulse = (12 / 56320.0) * (Math.PI * 10.25);
	// final double distancePerPulse = 1 / 1000.0;
	final double gyroConstant = -0.3 / 10.0;
	final double driveSpeedModifierConstant = .7;

	public double turnToGoalAngle = 0;
	double referenceAngle = 0;
	public boolean isReversed = false;
	double joyDeadZone = 0.1;
	double gyroVal = 0;
	double joystickTurnOffset;
	double autoTurnValue;
	double turnSpeedScalar = 0.3; // was .35
	double turnRate = 0;
	double shotZone = .05;

	boolean highGear;
	boolean neutral;
	boolean isShiftingOK;

	double alignSpeedDeadzone = 1.0;
	double brakeSpeed = .1;
	double driveLeftMotorsForwardSpeed = 1.0;
	double alignForwardSpeed = .17;
	double alignForwardScaler = 0.0;
	double alignBackwardScaler = 0.0;
	double alignBackwardSpeed = .17;

	boolean isGyroReset = false;

	double highGearValue = 0.0;
	double lowGearValue = 1.0;
	double spinSpeed = .15;

	CANTalon leftTopMotor = new CANTalon(RobotMap.CAN.leftTopDrive);
	CANTalon leftBotMotor = new CANTalon(RobotMap.CAN.leftBotDrive);
	CANTalon rightTopMotor = new CANTalon(RobotMap.CAN.rightTopDrive);
	CANTalon rightBotMotor = new CANTalon(RobotMap.CAN.rightBotDrive);

	MultiSpeedController leftDrive = new MultiSpeedController(new SpeedController[] { leftTopMotor, leftBotMotor },
			"Drive", "Left Multi Drive");

	MultiSpeedController rightDrive = new MultiSpeedController(new SpeedController[] { rightTopMotor, rightBotMotor },
			"Drive", "Right Multi Drive");

	Servo leftTransmissionServo = new Servo(RobotMap.Pwm.leftTransmissionServoPort);
	Servo rightTransmissionServo = new Servo(RobotMap.Pwm.rightTransmissionServoPort);

	Encoder leftEncoder = new Encoder(RobotMap.Digital.leftEncoderA, RobotMap.Digital.leftEncoderB);// more
	Encoder rightEncoder = new Encoder(RobotMap.Digital.rightEncoderA, RobotMap.Digital.rightEncoderB);

	PIDSpeedController leftPID = new PIDSpeedController(leftEncoder, leftDrive, "Drive", "Left PID");
	PIDSpeedController rightPID = new PIDSpeedController(rightEncoder, rightDrive, "Drive", "Right PID");

	Gyro gyro = new AnalogGyro(RobotMap.Analog.gyroPort);
	AnalogInput frontUltrasonic = new AnalogInput(RobotMap.Analog.frontUltrasonic);

	RobotDrive tankDrive = new RobotDrive(leftDrive, rightDrive);
	// RobotDrive tankDrive = new RobotDrive(leftPID, rightPID);

	public Drive() {
		initializeGear();
		// setPIDConstants();
		// leftEncoder.setDistancePerPulse(distancePerPulse);
		// rightEncoder.setDistancePerPulse(distancePerPulse);
		leftEncoder.setPIDSourceType(PIDSourceType.kRate);
		rightEncoder.setPIDSourceType(PIDSourceType.kRate);
	}

	public void driveWithJoysticks(double left, double right) {

		SmartDashboard.putBoolean("isReversed", isReversed);

		if (((right > joyDeadZone) && (left > joyDeadZone)) || ((right < -joyDeadZone) && (left < -joyDeadZone))) {
			// The joysticks have the same sign and are out of the deadzone
			if (isReversed) {
				// if the reverse mode is enabled
				tankDrive.tankDrive((-(right + left) / 2.0), (-(right + left) / 2.0), false);
				// drives straight in reverse
			} else {
				// if the reverse mode is NOT enabled
				tankDrive.tankDrive(((right + left) / 2.0), ((right + left) / 2.0), false);
				// drives straight forward
			}
			SmartDashboard.putString("Drive State", "Straight");
			isShiftingOK = true;
			// it's okay to shift
		} else if ((Math.abs(right) > joyDeadZone) || (Math.abs(left) > joyDeadZone)) {
			// the joysticks have opposite signs and are out of the deadzone
			if (isReversed) {
				// if reverse mode is enabled
				joystickTurnOffset = (left - right) * turnSpeedScalar;
				tankDrive.tankDrive(joystickTurnOffset, -joystickTurnOffset, false);
				// turns at the value of the left joystick minus the right joystick
			} else {
				joystickTurnOffset = (right - left) * turnSpeedScalar;
				tankDrive.tankDrive(-joystickTurnOffset, joystickTurnOffset, false);
				// turns at the value of the right joystick minus the left joystick

			}
			SmartDashboard.putString("Drive State", "Turning");
			isGyroReset = false;
			isShiftingOK = true;
		} else {
			SmartDashboard.putString("Drive State", "Stopped");
			// the joysticks are within the deadzone
		}
	}

	public void initializeGear() {
		if (leftTransmissionServo.get() > .6) {
			leftEncoder.setDistancePerPulse(distancePerPulse);
			rightEncoder.setDistancePerPulse(distancePerPulse);
		} else {
			leftEncoder.setDistancePerPulse(distancePerPulse * 2.5);
			rightEncoder.setDistancePerPulse(distancePerPulse * 2.5);
		}
	}

	public void highGear() {
		// if (isShiftingOK == true) {
		leftTransmissionServo.set(highGearValue);
		rightTransmissionServo.set(highGearValue);
		highGear = true;
		neutral = false;
		leftEncoder.setDistancePerPulse(distancePerPulse);
		rightEncoder.setDistancePerPulse(distancePerPulse);
		SmartDashboard.putBoolean("isHighGear", highGear);
		// }
	}

	public void lowGear() {
		// if (isShiftingOK == true) {
		leftTransmissionServo.set(lowGearValue);
		rightTransmissionServo.set(lowGearValue);
		highGear = false;
		neutral = false;
		leftEncoder.setDistancePerPulse(distancePerPulse * 2.5);
		rightEncoder.setDistancePerPulse(distancePerPulse * 2.5);
		// }
	}

	public void neutral() {
		leftTransmissionServo.set(0.5);
		rightTransmissionServo.set(0.5);
		neutral = true;
	}

	public void spinRight(double speed) {
		leftTopMotor.set(-speed);
		leftBotMotor.set(-speed);
		rightTopMotor.set(-speed);
		rightBotMotor.set(-speed);
	}

	public void spinLeft(double speed) {
		leftTopMotor.set(speed);
		leftBotMotor.set(speed);
		rightTopMotor.set(speed);
		rightBotMotor.set(speed);
	}

	public void turnToGoalWhileDrivingForward(double offset) {
		alignForwardScaler = Math.min(offset * 2, .23);
		if (offset > shotZone) {
			tankDrive.tankDrive(alignForwardSpeed + alignForwardScaler, -alignForwardSpeed);
		} else if (offset < -shotZone) {
			tankDrive.tankDrive(alignForwardSpeed, -alignForwardSpeed - alignForwardScaler);
		} else {
			stop();
		}
	}

	public void turnToGoalWhileDrivingBackward(double offset) {
		alignBackwardScaler = Math.min(offset * 2, .23);
		if (offset > shotZone) {
			tankDrive.tankDrive(-alignBackwardSpeed - alignBackwardScaler, alignBackwardSpeed);
		} else if (offset < -shotZone) {
			tankDrive.tankDrive(-alignBackwardSpeed, alignBackwardSpeed + alignBackwardScaler);
		} else {
			stop();
		}
	}

	public void turnToGoal(double offset) {
		// double turnToGoalSpeed = (Math.max(Math.abs((offset / 3.2)), .2)); // was .25 at 3:16pm
		double turnToGoalSpeed = .18; // Trying constant to avoid overshooting
		// double turnToGoalGain = Robot.vision.getDistanceToTarget() * Robot.vision.distanceToGoalMultiplier;

		// double turnToGoalSpeed = spinSpeed;
		if (offset > 0) {
			leftTopMotor.set(-turnToGoalSpeed);
			leftBotMotor.set(-turnToGoalSpeed);
			rightTopMotor.set(-turnToGoalSpeed);
			rightBotMotor.set(-turnToGoalSpeed);
		} else {
			leftTopMotor.set(turnToGoalSpeed);
			leftBotMotor.set(turnToGoalSpeed);
			rightTopMotor.set(turnToGoalSpeed);
			rightBotMotor.set(turnToGoalSpeed);
		}
	}

	public void turnToGoalParallel(double offset) {
		double turnToGoalSpeed = .135; // used to be .16
		// double turnToGoalGain = Robot.vision.getDistanceToTarget() * Robot.vision.distanceToGoalMultiplier;

		// double turnToGoalSpeed = spinSpeed;
		if (offset > 0) {
			leftTopMotor.set(-turnToGoalSpeed);
			leftBotMotor.set(-turnToGoalSpeed);
			rightTopMotor.set(-turnToGoalSpeed);
			rightBotMotor.set(-turnToGoalSpeed);
		} else {
			leftTopMotor.set(turnToGoalSpeed);
			leftBotMotor.set(turnToGoalSpeed);
			rightTopMotor.set(turnToGoalSpeed);
			rightBotMotor.set(turnToGoalSpeed);
		}
	}

	public void driveWithGyro(double left, double right) {
		double rightPower = right * driveSpeedModifierConstant;
		double leftPower = left * driveSpeedModifierConstant;
		double power = 0;
		power = (rightPower + leftPower) / 1.5;
		if (Math.abs(right) > .05 || Math.abs(left) > .05) {
			if (isReversed) {
				tankDrive.tankDrive(-power - getGyroOffset(), -power + getGyroOffset(), false);
			} else {
				tankDrive.tankDrive(power - getGyroOffset(), power + getGyroOffset(), false);
			}
		}
	}

	public double getGyroOffset() {
		gyroVal = Robot.drive.getGyro() * gyroConstant;
		if (Math.abs(gyroVal) > (1.0 - driveSpeedModifierConstant)) {
			gyroVal = (1.0 - driveSpeedModifierConstant) * Math.abs(gyroVal) / gyroVal; // sets gyroVal to either 1 or
			// -1
		}
		return gyroVal;
	}

	public void printGyroRate() {
		SmartDashboard.putNumber("Gyro Rate", gyro.getRate());
	}

	public double getGyroRate() {
		return gyro.getRate();
	}

	public void brake() {
		turnRate = gyro.getRate(); // Get the gyro rate in degrees/second
		if (turnRate > alignSpeedDeadzone) {
			brakeToLeft(); // Spin with low power to the left
		} else if (turnRate < -alignSpeedDeadzone) {
			brakeToRight(); // Spin with low power to the right
		} else {
			stop(); // Send 0 power to MCs
		}
	}

	public boolean isRobotSpinning() { // Determine if the robot is spinning
		if (Math.abs(gyro.getRate()) < alignSpeedDeadzone) {
			return false;
		} else {
			return true;
		}
	}

	public void brakeToRight() {
		leftTopMotor.set(-brakeSpeed);
		leftBotMotor.set(-brakeSpeed);
		// rightTopMotor.set(0.0);
		// rightBotMotor.set(0.0);
		rightTopMotor.set(-brakeSpeed);
		rightBotMotor.set(-brakeSpeed);
	}

	public void brakeToLeft() {
		leftTopMotor.set(brakeSpeed);
		leftBotMotor.set(brakeSpeed);
		// leftTopMotor.set(0.0);
		// leftBotMotor.set(0.0);
		rightTopMotor.set(brakeSpeed);
		rightBotMotor.set(brakeSpeed);
	}

	public void stop() {
		leftTopMotor.set(0);
		leftBotMotor.set(0);
		rightTopMotor.set(0);
		rightBotMotor.set(0);
	}

	public void driveLeftMotorsForward() {
		leftTopMotor.set(-driveLeftMotorsForwardSpeed);
		leftBotMotor.set(-driveLeftMotorsForwardSpeed);
	}

	public void driveRightMotorsForward() {
		rightTopMotor.set(driveLeftMotorsForwardSpeed);
		rightBotMotor.set(driveLeftMotorsForwardSpeed);
	}

	public void setPIDConstants() {
		double p = 0.025;
		double i = 0.0;
		double d = 0.0;
		double f = 1.0;

		rightPID.setConstants(p, i, d, f);
		leftPID.setConstants(p, i, d, f);
	}

	public void resetEncoders() {
		// resets the encoders
		leftEncoder.reset();
		rightEncoder.reset();
	}

	public double getLeftEncoder() {
		// returns the rate of the left encoder
		SmartDashboard.putNumber("Left Encoder", leftEncoder.getRate());
		return leftEncoder.getRate();
	}

	public double getRightEncoder() {
		// returns the rate of the right encoder
		SmartDashboard.putNumber("Right Encoder", rightEncoder.getRate());
		return rightEncoder.getRate();
	}

	public double getEncoderDistance() {
		// returns the distance traveled by the right encoder
		return (-1 * (leftEncoder.getDistance()));
	}

	public double getGyro() {
		// gets the gyro angle
		return gyro.getAngle() + referenceAngle;
	}

	public void resetGyro() {
		// resets the gyro
		gyro.reset();
		referenceAngle = 0;
		isGyroReset = true;
	}

	public double getFrontUltrasonicVoltage() {
		return frontUltrasonic.getVoltage();
	}

	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new DriveWithJoysticks());
	}
}
