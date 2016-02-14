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
	static final double TicksPerRotation = 1024.0;
	Encoder encoder;
	Victor motor;
	AnalogInput intakeSensor;

	double rotationAmount;

	public IntakeRoller() {
		encoder = new Encoder(RobotMap.Digital.RollerEncoderA, RobotMap.Digital.RollerEncoderB);
		motor = new Victor(RobotMap.Pwm.RollerVictor);
		intakeSensor = new AnalogInput(RobotMap.Analog.IntakeSensor);
		encoder.setDistancePerPulse(1 / TicksPerRotation);// we only care about rotation
	}

	public void setDesiredRotation(double rotations) {
		rotationAmount = Math.abs(rotations);
	}

	public boolean hasRotatedDesiredRotations() {
		return Math.abs(encoder.getDistance()) >= rotationAmount;
	}

	public void start() {
		motor.set(Preferences.getInstance().getDouble(RobotMap.Prefs.IntakeRollerMotorSpeed, .4));
	}

	public boolean isBallAtSensor() {
		return (intakeSensor.getVoltage() >= (Preferences.getInstance().getDouble(RobotMap.Prefs.IntakeSensorThreshold,
				1.0)));
	}

	public void stop() {
		motor.stopMotor();
		encoder.reset();
	}

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	@Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
