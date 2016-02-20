package org.usfirst.frc.team166.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team166.robot.PIDSpeedController;
import org.usfirst.frc.team166.robot.RobotMap;

/**
 *
 */
// public class AimShooter extends PIDSubsystem {
public class AimShooter extends Subsystem {

	Victor motor;
	AnalogInput pot;
	double degreesPerVolt = 1 / .0927;
	double voltsPerDegree = .0927;
	double zeroDegreeVoltage = .558;
	double minAngle = 45.0;
	double midAngle = 90.0;

	PIDSpeedController anglePID;

	// Initialize your subsystem here
	public AimShooter() {

		pot = new AnalogInput(RobotMap.Analog.ShooterPotAngle);
		pot.setPIDSourceType(PIDSourceType.kDisplacement);
		motor = new Victor(RobotMap.Pwm.ShooterAngleMotor);

		anglePID = new PIDSpeedController(pot, motor, "anglePID", "AimShooter");

		updatePIDConstants();
		anglePID.set(0);
	}

	private void updatePIDConstants() {

		double AngleP = Preferences.getInstance().getDouble(RobotMap.Prefs.ShooterAngleP, 0);
		double AngleI = Preferences.getInstance().getDouble(RobotMap.Prefs.ShooterAngleI, 0);
		double AngleD = Preferences.getInstance().getDouble(RobotMap.Prefs.ShooterAngleD, 0);
		double AngleF = Preferences.getInstance().getDouble(RobotMap.Prefs.ShooterAngleF, 0);
		anglePID.setConstants(AngleP, AngleI, AngleD, AngleF);
	}

	private double convertAngleToDisplacement(double angle) {
		double displacement = ((angle - 45) * voltsPerDegree) - zeroDegreeVoltage;
		return (displacement);
	}

	public void setAngle(double angle) {
		anglePID.set(convertAngleToDisplacement(Math.max(angle, minAngle)));
	}

	public double getShooterAngle() {
		return (45 + ((pot.getVoltage() - zeroDegreeVoltage) * degreesPerVolt));
	}

	public void moveToAngle(double angle) {
		if (angle > getShooterAngle()) {
			motor.set(.3);
		} else {
			motor.set(-.75);
		}
	}

	public void stop() {
		motor.set(0.0);
	}

	@Override
	public void initDefaultCommand() {

	}

}
