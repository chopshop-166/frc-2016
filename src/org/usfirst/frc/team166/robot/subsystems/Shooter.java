package org.usfirst.frc.team166.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team166.robot.RobotMap;

/**
 *
 */
public class Shooter extends Subsystem {

	Victor ShooterLeftSide;
	Victor ShooterRightSide;
	Victor ShooterAngle;
	Encoder EncoderLeft;
	Encoder EncoderRight;
	AnalogInput pot;

	public Shooter() {
		ShooterLeftSide = new Victor(RobotMap.LeftShooterMotor);
		ShooterRightSide = new Victor(RobotMap.RightShooterMotor);
		ShooterAngle = new Victor(RobotMap.ShooterAngle);
		EncoderLeft = new Encoder(null, null);
		EncoderRight = new Encoder(null, null);
	}

	// launch the ball
	public void Fire(double power) {
		ShooterLeftSide.set(power);
		ShooterRightSide.set(power);
	}

	public void AngleShooter(double power) {
		ShooterAngle.set(power);
	}

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	@Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
