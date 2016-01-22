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

	Victor shooterLeftSide;
	Victor shooterRightSide;
	Victor shooterAngle;
	Encoder encoderLeft;
	Encoder encoderRight;
	AnalogInput pot;

	public Shooter() {
		shooterLeftSide = new Victor(RobotMap.LeftShooterMotor);
		shooterRightSide = new Victor(RobotMap.RightShooterMotor);
		shooterAngle = new Victor(RobotMap.ShooterAngleMotor);
		encoderLeft = new Encoder(null, null);
		encoderRight = new Encoder(null, null);
	}

	public double getPot() {
		// math to get to angle
		return pot.getVoltage();
	}

	// launch the ball
	public void Fire(double power) {
		shooterLeftSide.set(power);
		shooterRightSide.set(power);
	}

	public void angleShooter(double power) {
		shooterAngle.set(power);
	}

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	@Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
