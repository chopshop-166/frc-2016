package org.usfirst.frc.team166.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Encoder;
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
	Victor RollerCIM;
	Encoder RollerEncoder;
	DigitalInput IntakeSensor;

	public Intake() {
		Actuator = new DoubleSolenoid(RobotMap.Solenoid.IntakeSolenoidForwards,
				RobotMap.Solenoid.IntakeSolenoidBackwards);
		IntakeCIM = new Victor(RobotMap.Pwm.IntakeVictor);
		IntakeCIM2 = new Victor(RobotMap.Pwm.IntakeVictor2);
		RollerCIM = new Victor(RobotMap.Pwm.RollerVictor);
		RollerEncoder = new Encoder(RobotMap.Digital.RollerEncoderA, RobotMap.Digital.RollerEncoderB);
		IntakeSensor = new DigitalInput(RobotMap.Digital.IntakeSensor);

	}

	// Sensor Methods
	public void getSensorBoolean() {
		boolean SensorVal = IntakeSensor.get();
		if (SensorVal == false) {
			intakeMotorStop();
		}
	}

	public void EncoderVal() {
		boolean SensorVal = IntakeSensor.get();
		if (SensorVal == false) {

		}

	}

	// Motor Methods
	public void intakeMotorForward() {
		IntakeCIM.set(Preferences.getInstance().getDouble("Forward", .4));
		IntakeCIM2.set(Preferences.getInstance().getDouble("Forward2", .4));
		getSensorBoolean();
	}

	public void intakeMotorReverse() {
		IntakeCIM.set(Preferences.getInstance().getDouble("Reverse", -.4));
		IntakeCIM2.set(Preferences.getInstance().getDouble("Reverse2", -.4));
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

	public void intakeSolenoidForward() {
		Actuator.set(DoubleSolenoid.Value.kForward);

	}

	public void intakeSolenoidReverse() {
		Actuator.set(DoubleSolenoid.Value.kReverse);
	}

	public void toggleIntakeSolenoid() {
		Value SolenoidVal = Actuator.get();
		if (SolenoidVal == Value.kForward) {
			intakeSolenoidReverse();
		} else {
			intakeSolenoidForward();

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
