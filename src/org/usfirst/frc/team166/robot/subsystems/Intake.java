package org.usfirst.frc.team166.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {

	DoubleSolenoid Actuator;
	Victor IntakeSIM;

	public Intake() {

	}

	public void ToggleIntakeMotor() {

	}

	public void ToggleIntakeSolenoid() {

	}

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	@Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
