package org.usfirst.frc.team166.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

import org.usfirst.frc.team166.robot.RobotMap;
import org.usfirst.frc.team166.robot.commands.aimShooter.Aim;

/**
 *
 */
public class AimShooter extends PIDSubsystem {

	Victor motor;
	AnalogInput pot;
	double minAngle = 45.0;

	// Initialize your subsystem here
	public AimShooter() {

		super(0, 0, 0, 0);

		pot = new AnalogInput(RobotMap.Analog.ShooterPotAngle);
		pot.setPIDSourceType(PIDSourceType.kDisplacement);

		motor = new Victor(RobotMap.Pwm.ShooterAngleMotor);
		updatePIDConstants();
		setSetpoint(0);
		// enable();
	}

	private void updatePIDConstants() {

		double AngleP = Preferences.getInstance().getDouble(RobotMap.Prefs.ShooterAngleP, 0);
		double AngleI = Preferences.getInstance().getDouble(RobotMap.Prefs.ShooterAngleI, 0);
		double AngleD = Preferences.getInstance().getDouble(RobotMap.Prefs.ShooterAngleD, 0);
		double AngleF = Preferences.getInstance().getDouble(RobotMap.Prefs.ShooterAngleF, 0);
		getPIDController().setPID(AngleP, AngleI, AngleD, AngleF);
	}

	private double convertAngleToDisplacement(double angle) {
		double displacement = angle
				* Preferences.getInstance().getDouble(RobotMap.Prefs.angleToDisplacementConstant, 0);
		return (displacement);
	}

	public void setAngle(double angle) {
		if (angle <= minAngle) {
			setSetpoint(convertAngleToDisplacement(minAngle));
		} else {
			setSetpoint(convertAngleToDisplacement(angle));
		}
	}

	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new Aim());
	}

	@Override
	protected double returnPIDInput() {
		// Return your input value for the PID loop
		// e.g. a sensor, like a potentiometer:
		// yourPot.getAverageVoltage() / kYourMaxVoltage;
		return 0.0;
	}

	@Override
	protected void usePIDOutput(double output) {
		motor.set(output);
	}
}
