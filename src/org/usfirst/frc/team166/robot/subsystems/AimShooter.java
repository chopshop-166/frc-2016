package org.usfirst.frc.team166.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

import org.usfirst.frc.team166.robot.PIDSpeedController;
import org.usfirst.frc.team166.robot.RobotMap;

/**
 *
 */
// public class AimShooter extends PIDSubsystem {
public class AimShooter extends Subsystem {

	Victor motor;
	AnalogInput pot;
	double angleToDisplacementConstant = 4096.0 / (180.0 * 4.66); // derived from gear ratio of 4.66 and max pot val of
	double displacementToAngleConstant = (180.0 * 4.66) / 4096.0; // 1000
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
		double displacement = angle * angleToDisplacementConstant;
		return (displacement);
	}

	public void setAngle(double angle) {
		if (NetworkTable.getTable("Vision").getBoolean("isLargeTargetFound", false)) {
			if (angle <= minAngle) {
				anglePID.set(convertAngleToDisplacement(minAngle));
			} else {
				anglePID.set(convertAngleToDisplacement(angle));
			}
		} else {
			anglePID.set(convertAngleToDisplacement(minAngle));
		}
	}

	public double getPotVal() {
		return ((pot.getValue()) * displacementToAngleConstant);
	}

	@Override
	public void initDefaultCommand() {

	}

}
