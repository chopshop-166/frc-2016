package org.usfirst.frc.team166.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Preferences;
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
		Actuator = new DoubleSolenoid(RobotMap.Solenoid.IntakeSolenoid, 1);
		IntakeSIM = new Victor(RobotMap.Pwm.IntakeVictor);

	}

	// Motor Methods

	public void IntakeMotorForward() {
		IntakeSIM.set(Preferences.getInstance().getDouble("Forward", .4));
	}

	public void IntakeMotorReverse() {
		IntakeSIM.set(Preferences.getInstance().getDouble("reverse", -.4));
	}

	public void IntsakeMotorStop() {
		IntakeSIM.set(Preferences.getInstance().getDouble("Stop", 0));
	}

	public void ToggleIntakeMotor() {

	}

	// Solenoid Methods

	public void IntakeSolenoidForward() {
		Actuator.set(DoubleSolenoid.Value.kForward);

	}

	public void IntakeSolenoidReverse() {
		Actuator.set(DoubleSolenoid.Value.kReverse);
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
