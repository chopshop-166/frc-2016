package org.usfirst.frc.team166.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Gyro;

import org.usfirst.frc.team166.robot.PIDSpeedController;
import org.usfirst.frc.team166.robot.Robot;
import org.usfirst.frc.team166.robot.RobotMap;

/**
 *
 */
public class Drive extends Subsystem {

	Victor leftTopVictor = new Victor(RobotMap.Pwm.leftTopDrive);
	Victor leftBotVictor = new Victor(RobotMap.Pwm.leftBotDrive);
	Victor rightTopVictor = new Victor(RobotMap.Pwm.rightTopDrive);
	Victor rightBotVictor = new Victor(RobotMap.Pwm.rightBotDrive);

	Encoder leftEncoder = new Encoder(RobotMap.Digital.leftEncoderA, RobotMap.Digital.leftEncoderB);// more
	// specificity,
	// "left"
	// is
	// not
	// descriptive
	Encoder rightEncoder = new Encoder(RobotMap.Digital.rightEncoderA, RobotMap.Digital.rightEncoderB);

	PIDSpeedController leftTopPID = new PIDSpeedController(leftEncoder, leftTopVictor, "Six Wheel Drive", "LeftTopPID");// again
																														// specify
	PIDSpeedController leftBotPID = new PIDSpeedController(leftEncoder, leftBotVictor, "Six Wheel Drive", "LeftBotPID");// whether
																														// these
	PIDSpeedController rightTopPID = new PIDSpeedController(rightEncoder, rightTopVictor, "Six Wheel Drive",
			"RightTopPID");// are for top
	PIDSpeedController rightBotPID = new PIDSpeedController(rightEncoder, rightBotVictor, "Six Wheel Drive",
			"RightBotPID");// or bot motors

	Gyro gyro = new AnalogGyro(RobotMap.Analog.gyroPort);

	RobotDrive tankDrive = new RobotDrive(leftTopPID, leftBotPID, rightTopPID, rightBotPID);

	// public void leftMotor(double powerLeft) {
	//// leftPID1.set(powerLeft);
	//// leftPID2.set(powerLeft);
	// }
	//
	// public void rightMotor(double powerRight) {
	//// rightPID1.set(powerRight);
	//// rightPID2.set(powerRight);
	// }
	public void drive() {
		// integrate gyro into drive. i.e. correct for imperfect forward motion
		// with a proportional controller
		double leftPower = Robot.oi.getLeftYAxis();
		double rightPower = Robot.oi.getRightYAxis();
		double encoderConstant = 1 / 90;
		double modPower = Math.abs(leftEncoder.getRate() - rightEncoder.getRate()) * encoderConstant;

		leftEncoder.setDistancePerPulse(10 / 1024);
		rightEncoder.setDistancePerPulse(10 / 1024);

		if ((rightEncoder.getRate() - leftEncoder.getRate()) > 0) {
			tankDrive.tankDrive(leftPower + modPower, rightPower - modPower);

		} else if ((rightEncoder.getRate() - leftEncoder.getRate()) < 0) {
			tankDrive.tankDrive(leftPower - modPower, rightPower + modPower);

		} else {
			tankDrive.tankDrive(leftPower, rightPower);
		}

		if (Math.abs(leftPower - rightPower) > 0.1) {
			resetGyro();
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

	public int getLeftEncoder() {
		return leftEncoder.get();
	}

	public int getRightEncoder() {
		return rightEncoder.get();
	}

	public double getDistance() {
		return (((getLeftEncoder() + getRightEncoder()) / 2) / 1024) / 31.4;
	}

	public double getGyro() {
		return gyro.getAngle();
	}

	public void turnAngle(double angle) {
		double power = (angle - getGyro()) / angle;
		if (getGyro() < angle - 7) {
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

	@Override
	public void initDefaultCommand() {

	}
}
