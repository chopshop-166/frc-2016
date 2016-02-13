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
public class ShooterFeeder extends Subsystem {
	static final double TicksperRotation = 1024.0;
	Encoder feederEncoder;
	Victor feederVictor;
	AnalogInput feederIRSensor;

	double rotationAmount;

	public ShooterFeeder() {
		feederEncoder = new Encoder(RobotMap.Digital.feederEncoderA, RobotMap.Digital.feederEncoderB);
		feederVictor = new Victor(RobotMap.Pwm.feederVictor);
		feederIRSensor = new AnalogInput(RobotMap.Analog.feederIRSensor);
		feederEncoder.setDistancePerPulse(1 / TicksperRotation);// we only care about rotation

	}

	public void setDesiredRotation(double rotations) {
		rotationAmount = Math.abs(rotations);

	}

	public boolean hasRotatedDesiredRotations() {
		return Math.abs(feederEncoder.getDistance()) >= rotationAmount;
	}

	public void startFeeder() {
		double intakeVal = feederIRSensor.getVoltage();
		if (intakeVal >= 1.0) {
			feederVictor.set(Preferences.getInstance().getDouble(RobotMap.Prefs.IntakeRollerMotorSpeed, .4));
		}

	}

	public void ResetEncoder() {
		feederEncoder.reset();
	}

	public void feederStop() {
		feederVictor.stopMotor();

	}

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	@Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
