package org.usfirst.frc.team166.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
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
	Encoder encoderLeft;
	Encoder encoderRight;
	PIDSpeedController leftPID;
	PIDSpeedController rightPID;

	public Shooter() {

		shooterLeftSide = new Victor(RobotMap.Pwm.LeftShooterMotor);
		shooterRightSide = new Victor(RobotMap.Pwm.RightShooterMotor);

		encoderLeft = new Encoder(RobotMap.Digital.ShooterLeftChannelA, RobotMap.Digital.ShooterLeftChannelB);
		encoderRight = new Encoder(RobotMap.Digital.ShooterRightChannelA, RobotMap.Digital.ShooterRightChannelB);

		encoderLeft.setPIDSourceType(PIDSourceType.kRate);
		encoderRight.setPIDSourceType(PIDSourceType.kRate);

		// leftPID = new PIDSpeedController(encoderLeft, shooterLeftSide, "Shooter", "Left Wheel");
		// rightPID = new PIDSpeedController(encoderRight, shooterRightSide, "Shooter", "Right Wheel");

	}

	// launch the ball
	public void setSpeed(double power) {
		// leftPID.set(power);
		// rightPID.set(power);
		shooterLeftSide.set(power);
		shooterRightSide.set(-power);
	}

	public void updatePIDConstants() {
		double ShooterP = Preferences.getInstance().getDouble(RobotMap.Prefs.ShooterP, 0);
		double ShooterI = Preferences.getInstance().getDouble(RobotMap.Prefs.ShooterI, 0);
		double ShooterD = Preferences.getInstance().getDouble(RobotMap.Prefs.ShooterD, 0);
		double ShooterF = Preferences.getInstance().getDouble(RobotMap.Prefs.ShooterF, 0);

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
