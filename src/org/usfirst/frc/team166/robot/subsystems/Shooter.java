package org.usfirst.frc.team166.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team166.robot.PIDSpeedController;
import org.usfirst.frc.team166.robot.RobotMap;

/**
 *
 */
public class Shooter extends Subsystem {

	Victor shooterLeftSide;
	Victor shooterRightSide;
	Victor shooterAngle;
	Encoder encoderLeft;
	Encoder encoderRight;
	PIDController shooterAnglePID;
	PIDSpeedController leftPID;
	PIDSpeedController rightPID;
	AnalogInput pot;

	public Shooter() {
		pot = new AnalogInput(RobotMap.Analog.ShooterPotAngle);
		pot.setPIDSourceType(PIDSourceType.kDisplacement);

		shooterLeftSide = new Victor(RobotMap.Pwm.LeftShooterMotor);
		shooterRightSide = new Victor(RobotMap.Pwm.RightShooterMotor);
		shooterAngle = new Victor(RobotMap.Pwm.ShooterAngleMotor);

		encoderLeft = new Encoder(RobotMap.Digital.ShooterLeftChannelA, RobotMap.Digital.ShooterLeftChannelB);
		encoderRight = new Encoder(RobotMap.Digital.ShooterRightChannelA, RobotMap.Digital.ShooterRightChannelB);

		encoderLeft.setPIDSourceType(PIDSourceType.kRate);
		encoderRight.setPIDSourceType(PIDSourceType.kRate);

		shooterAnglePID = new PIDController(0, 0, 0, pot, shooterAngle);
		// leftPID = new PIDSpeedController(encoderLeft, shooterLeftSide, "Shooter", "Left Wheel");
		// rightPID = new PIDSpeedController(encoderRight, shooterRightSide, "Shooter", "Right Wheel");

	}

	public double convertAngleToDisplacement(double angle) {
		double displacement = angle
				* Preferences.getInstance().getDouble(RobotMap.Prefs.angleToDisplacementConstant, 0);
		return (displacement);
	}

	// launch the ball
	public void setSpeed(double power) {
		// leftPID.set(power);
		// rightPID.set(power);
		shooterLeftSide.set(power);
		shooterRightSide.set(-power);
	}

	public void setAngle(double angle) {
		shooterAnglePID.setSetpoint(convertAngleToDisplacement(angle));
	}

	public void updatePIDConstants() {
		double ShooterP = Preferences.getInstance().getDouble(RobotMap.Prefs.ShooterP, 0);
		double ShooterI = Preferences.getInstance().getDouble(RobotMap.Prefs.ShooterI, 0);
		double ShooterD = Preferences.getInstance().getDouble(RobotMap.Prefs.ShooterD, 0);
		double ShooterF = Preferences.getInstance().getDouble(RobotMap.Prefs.ShooterF, 0);

		double AngleP = Preferences.getInstance().getDouble(RobotMap.Prefs.ShooterAngleP, 0);
		double AngleI = Preferences.getInstance().getDouble(RobotMap.Prefs.ShooterAngleI, 0);
		double AngleD = Preferences.getInstance().getDouble(RobotMap.Prefs.ShooterAngleD, 0);
		double AngleF = Preferences.getInstance().getDouble(RobotMap.Prefs.ShooterAngleF, 0);

		shooterAnglePID.setPID(AngleP, AngleI, AngleD, AngleF);
		leftPID.setConstants(ShooterP, ShooterI, ShooterD, ShooterF);
		rightPID.setConstants(ShooterP, ShooterI, ShooterD, ShooterF);

	}

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	@Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
