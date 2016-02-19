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
	Victor IntakeCIM;
	Victor IntakeCIM2;

	public Intake() {
		Actuator = new DoubleSolenoid(RobotMap.Solenoid.IntakeSolenoidForwards,
				RobotMap.Solenoid.IntakeSolenoidBackwards);
		IntakeCIM = new Victor(RobotMap.Pwm.MainIntakeVictor);
		IntakeCIM2 = new Victor(RobotMap.Pwm.CrossIntakeVictor);

	}

	// Motor Methods
	public void intakeMotorForward() {
		IntakeCIM.set(Preferences.getInstance().getDouble("Forward", 1.0));
		IntakeCIM2.set(Preferences.getInstance().getDouble("Forward2", -1.0));

	}

	public void intakeMotorReverse() {
		IntakeCIM.set(Preferences.getInstance().getDouble("Reverse", -1.0));
		IntakeCIM2.set(Preferences.getInstance().getDouble("Reverse2", 1.0));
	}

	public void intakeMotorStop() {
		IntakeCIM.set(Preferences.getInstance().getDouble("Stop", 0));
		IntakeCIM2.set(Preferences.getInstance().getDouble("Stop2", 0));
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

	public void lowerRake() {
		Actuator.set(DoubleSolenoid.Value.kReverse);

	}

	public void raiseRake() {
		Actuator.set(DoubleSolenoid.Value.kForward);
	}

	public void toggleIntakeSolenoid() {
		Value SolenoidVal = Actuator.get();
		if (SolenoidVal == Value.kForward) {
			raiseRake();
		} else {
			lowerRake();

		}
	}

	// Intake Final Commands

	public void startLoadProcess() {
		lowerRake();
		intakeMotorForward();

	}

	@Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
