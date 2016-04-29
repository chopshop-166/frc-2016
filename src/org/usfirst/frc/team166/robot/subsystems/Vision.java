package org.usfirst.frc.team166.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team166.robot.Robot;

/**
 *
 */
public class Vision extends Subsystem {
	double distanceToGoalMultiplier = 0.2 / 10.0;
	double screenCenter = 143;
	double xOffsetMultiplier = 1.0 / 160.0;
	double defaultShooterAngle = 45.0;
	double cameraLag = .14; // seconds // used to be .13
	double xOffset = 0;
	double xPos = 0;
	NetworkTable visionTable;

	public Vision() {
		visionTable = NetworkTable.getTable("VisionDataTable");
	}

	public double getDesiredShooterAngle() {
		// returns the desired shooter angle as calculated in the python code
		double angle = visionTable.getNumber("shooterAngle", defaultShooterAngle);
		if (angle < 45) {
			SmartDashboard.putString("Shot Distance", "Too Far");
		} else {
			SmartDashboard.putString("Shot Distance", "Good");
		}

		return (Math.max(angle, 41));
	}

	public double correctForLag(double offset) {
		double lagAngle = 0.0;
		double offsetAngle = 0.0;
		double realAngle = 0.0;
		double correctedAngle = 0.0;
		lagAngle = cameraLag * Robot.drive.getGyroRate();
		SmartDashboard.putNumber("Correction Angle", lagAngle);
		offsetAngle = offset * 160 * (45.0 / 320.0);
		realAngle = offsetAngle - lagAngle;
		correctedAngle = (realAngle * (320.0 / 45.0) * xOffsetMultiplier);
		return correctedAngle;
	}

	public double getXOffset() {
		if (isValidTarget()) {
			xPos = visionTable.getNumber("xPosition", screenCenter);
			xOffset = (xPos - screenCenter) * xOffsetMultiplier;
			// return (xOffset);
			return (correctForLag(xOffset));
		} else {
			return (1.0); // SPIN (RIGHT) TO WINNNNN!!!!! :D xD -_-
		}
	}

	public boolean isValidTarget() {
		return (visionTable.getBoolean("isValidTarget", false));
	}

	public double getXPos() {
		return visionTable.getNumber("xPosition", screenCenter);
	}

	public double getDistanceToTarget() {
		return visionTable.getNumber("distanceToTarget", 0);
	}

	@Override
	public void initDefaultCommand() {

	}
}
