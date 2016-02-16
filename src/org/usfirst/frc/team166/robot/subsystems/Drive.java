package org.usfirst.frc.team166.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.CANTalon;
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

	// final double distancePerPulse = 12 / 56320.0; // this makes perfect cents // no it doesn't it makes
	// 2.1306818181...
	final double distancePerPulse = 1.0 / 2500;
	final double gyroConstant = -0.3 / 10.0;
	final double driveSpeedModifierConstant = .7;

	double gyroVal = 0;

	boolean highGear;
	boolean neutral;
	boolean isShiftingOK;

	double highGearValue = 0.0;
	double lowGearValue = 1.0;

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

	// RobotDrive tankDrive = new RobotDrive(leftDrive, rightDrive);
	RobotDrive tankDrive = new RobotDrive(leftPID, rightPID);

	public Drive() {
		leftEncoder.setDistancePerPulse(distancePerPulse);
		rightEncoder.setDistancePerPulse(distancePerPulse);
		leftEncoder.setPIDSourceType(PIDSourceType.kRate);
		rightEncoder.setPIDSourceType(PIDSourceType.kRate);
		setPIDConstants();
	}

	public double getGyroOffset() {
		gyroVal = Robot.drive.getGyro() * gyroConstant;
		if (Math.abs(gyroVal) > (1.0 - driveSpeedModifierConstant)) {
			gyroVal = (1.0 - driveSpeedModifierConstant) * Math.abs(gyroVal) / gyroVal; // sets gyroVal to either 1 or
			// -1
		}
		return gyroVal;
	}

	public void driveWithGyro(double left, double right) {
		double rightPower = right * driveSpeedModifierConstant;
		double leftPower = left * driveSpeedModifierConstant;
		double power = 0;
		power = (rightPower + leftPower) / 2;
		if (Math.abs(right) > .1 || Math.abs(left) > .1) {
			tankDrive.tankDrive(power + getGyroOffset(), power - getGyroOffset());
		}
	}

	public void highGear() {
		if (isShiftingOK == true) {
			leftTransmissionServo.set(highGearValue);
			rightTransmissionServo.set(highGearValue);
			highGear = true;
			neutral = false;
			leftEncoder.setDistancePerPulse(3 * distancePerPulse);
			rightEncoder.setDistancePerPulse(3 * distancePerPulse);
		}
	}

	public void lowGear() {
		if (isShiftingOK == true) {
			leftTransmissionServo.set(lowGearValue);
			rightTransmissionServo.set(lowGearValue);
			highGear = false;
			neutral = false;
			leftEncoder.setDistancePerPulse(distancePerPulse);
			rightEncoder.setDistancePerPulse(distancePerPulse);
		}
	}

	public void neutral() {
		leftTransmissionServo.set(0.5);
		rightTransmissionServo.set(0.5);
		neutral = true;
	}

	public void driveWithJoysticks(double left, double right) {
		// integrate gyro into drive. i.e. correct for imperfect forward motion
		// with a proportional controller

		if ((Math.abs(left) > .1) || (Math.abs(right) > .1)) {
			isShiftingOK = true;
			SmartDashboard.putNumber("Gyro Offset", getGyroOffset());
			tankDrive.tankDrive(left, right);
		} else {
			isShiftingOK = false;
			stop();
		}
	}

	public void stop() {
		tankDrive.tankDrive(0, 0);
	}

	public void resetGyro() {
		gyro.reset();
	}

	public void resetEncoders() {
		leftEncoder.reset();
		rightEncoder.reset();
	}

	public double getLeftEncoder() {
		SmartDashboard.putNumber("Left Encoder", leftEncoder.getRate());
		return leftEncoder.getRate();
	}

	public double getRightEncoder() {
		SmartDashboard.putNumber("Right Encoder", rightEncoder.getRate());
		return rightEncoder.getRate();
	}

	public double getDistance() {
		return (((getLeftEncoder() + getRightEncoder()) / 2.0) / 1024.0) / 31.4;
	}

	public double getGyro() {
		return gyro.getAngle();
	}

	public void turnAngle(double angle) {
		double power = (angle - getGyro()) / angle;
		if (getGyro() < angle - 7.0) {
			tankDrive.tankDrive(power, -power);

			// rightMotor(-power);
			// leftMotor(power);
		} else if (getGyro() > angle + 7) {
			tankDrive.tankDrive(-power, power);
			// rightMotor(power);
			// leftMotor(-power);
		} else if (getGyro() >= angle - 7 && getGyro() <= angle + 7) {
			tankDrive.tankDrive(0, 0);
		}
	}

	public void driveDistance(double distance) { // inches
		double power = (distance - getDistance()) / distance;
		if (getDistance() <= (Math.PI * distance) - 4) {
			tankDrive.tankDrive(power, power);
		} else {
			tankDrive.tankDrive(0, 0);
		}
	}

	public void driveDirection(double angle, double distance) {
		turnAngle(angle);
		driveDistance(distance);
	}

	public void turn(double leftVal, double rightVal) {
		tankDrive.tankDrive(leftVal, rightVal);

	}

	public void setPIDConstants() {
		// double p = 1;
		// double i = 2;
		// double d = 0;
		// double f = 1;

		double p = .3;
		double i = 0.000001;
		double d = 0.000001;
		double f = 1;

		leftPID.setConstants(p, i, d, f);
		rightPID.setConstants(p, i, d, f);
	}

	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new DriveWithJoysticks());
	}
}
