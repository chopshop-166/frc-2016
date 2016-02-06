package org.usfirst.frc.team166.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
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
	Victor IntakeSIM2;

	public Intake() {
		Actuator = new DoubleSolenoid(RobotMap.Solenoid.IntakeSolenoidForwards,
				RobotMap.Solenoid.IntakeSolenoidBackwards);
		IntakeSIM = new Victor(RobotMap.Pwm.IntakeVictor);
		IntakeSIM2 = new Victor(RobotMap.Pwm.IntakeVictor2);

	}

	// Motor Methods

	public void IntakeMotorForward() {
		IntakeSIM.set(Preferences.getInstance().getDouble("Forward", .4));
		IntakeSIM2.set(Preferences.getInstance().getDouble("Forward2", .4));
	}

	public void IntakeMotorReverse() {
		IntakeSIM.set(Preferences.getInstance().getDouble("Reverse", -.4));
		IntakeSIM2.set(Preferences.getInstance().getDouble("Reverse2", -.4));
	}

	public void IntakeMotorStop() {
		IntakeSIM.set(Preferences.getInstance().getDouble("Stop", 0));
		IntakeSIM2.set(Preferences.getInstance().getDouble("Stop2", 0));
	}

	// public void ToggleIntakeMotor() {
	// double MotorVal = IntakeSIM.get();
	// if (MotorVal >= .4) {
	// IntakeMotorReverse();
	// }
	//
	// else if (MotorVal <= -.4) {
	// IntakeMotorForward();
	// }
	//
	// else
	// IntakeMotorStop();
	//
	// }

	// Solenoid Methods

	public void IntakeSolenoidForward() {
		Actuator.set(DoubleSolenoid.Value.kForward);

	}

	public void IntakeSolenoidReverse() {
		Actuator.set(DoubleSolenoid.Value.kReverse);
	}

	public void ToggleIntakeSolenoid() {
		Value SolenoidVal = Actuator.get();
		if (SolenoidVal == Value.kForward) {
			IntakeSolenoidReverse();
		} else {
			IntakeSolenoidForward();

		}
	}

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	@Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
