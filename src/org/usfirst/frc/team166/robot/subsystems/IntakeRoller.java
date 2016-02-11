package org.usfirst.frc.team166.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team166.robot.RobotMap;

/**
 *
 */
public class IntakeRoller extends Subsystem {
	static final double TicksperRotation = 1024.0;
	Encoder RollerEncoder;
	Victor RollerCIM;
	AnalogInput IntakeSensor;

	double rotationAmount;

	public IntakeRoller() {
		RollerEncoder = new Encoder(RobotMap.Digital.RollerEncoderA, RobotMap.Digital.RollerEncoderB);
		RollerCIM = new Victor(RobotMap.Pwm.RollerVictor);
		IntakeSensor = new AnalogInput(RobotMap.Analog.IntakeSensor);
		RollerEncoder.setDistancePerPulse(1 / TicksperRotation);// we only care about rotation

	}

	public void setDesiredRotation(double rotations) {
		rotationAmount = Math.abs(rotations);

	}

	public boolean hasRotatedDesiredRotations() {
		return Math.abs(RollerEncoder.getDistance()) >= rotationAmount;
	}

	public void StartRoller() {
		double intakeVal = IntakeSensor.getVoltage();
		if (intakeVal >= 1.0) {
			RollerCIM.set(Preferences.getInstance().getDouble(RobotMap.Prefs.IntakeRollerMotorSpeed, .4));
		}

	}

	public void ResetEncoder() {
		RollerEncoder.reset();
	}

	public void RollerStop() {
		RollerCIM.stopMotor();

	}

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	@Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
