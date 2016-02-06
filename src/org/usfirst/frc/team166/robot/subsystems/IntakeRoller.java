package org.usfirst.frc.team166.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team166.robot.RobotMap;

/**
 *
 */
public class IntakeRoller extends Subsystem {
	Encoder RollerEncoder;
	Victor RollerCIM;

	public IntakeRoller() {
		RollerEncoder = new Encoder(RobotMap.Digital.RollerEncoderA, RobotMap.Digital.RollerEncoderB);
		RollerCIM = new Victor(RobotMap.Pwm.RollerVictor);

		RollerEncoder.setDistancePerPulse(1);// we only care about rotation
	}

	public void ResetEncoder() {
		RollerEncoder.reset();
	}

	public boolean RotateRoller() {
		double TicksperRotation = 1024.0;
		return RollerEncoder.get() == TicksperRotation / 3;
	}

	public void StartRoller() {
		RollerCIM.set(.5);
	}

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	@Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
