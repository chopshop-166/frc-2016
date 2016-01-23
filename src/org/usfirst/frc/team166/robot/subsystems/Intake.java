package org.usfirst.frc.team166.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team166.robot.RobotMap;

/**
 *
 */
public class Intake extends Subsystem {

	DoubleSolenoid Actuator;
	Victor IntakeSIM;

	public Intake() {
		Actuator = new DoubleSolenoid(RobotMap.IntakeSolenoid, 1);
		IntakeSIM = new Victor(RobotMap.IntakeVictor);

	}

	// Motor Methods

	public void IntakeMotorForward() {

	}

	public void IntakeMotorReverse() {

	}

	public void IntsakeMotorStop() {
		IntakeSIM.set(0);
	}

	public void ToggleIntakeMotor() {

	}

	// Solenoid Methods

	public void IntakeSolenoidForward() {

	}

	public void IntakeSolenoidReverse() {

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
