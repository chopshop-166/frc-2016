package org.usfirst.frc.team166.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Vision extends Subsystem {
	double distanceToGoalMultiplier = 0.2 / 10.0;
	double screenCenter = 143;
	double xOffsetMultiplier = 1.0 / 160.0;
	double defaultShooterAngle = 45.0;
	double xOffset = 0;
	double xPos = 0;
	NetworkTable visionTable;

	public Vision() {
		visionTable = NetworkTable.getTable("VisionDataTable");
	}

	public int getDesiredShooterAngle() {
		// returns the desired shooter angle as calculated in the python code
		double angle = Math.round(visionTable.getNumber("shooterAngle", defaultShooterAngle));
		if (angle < 45) {
			SmartDashboard.putString("Shot Distance", "Too Far");
		} else {
			SmartDashboard.putString("Shot Distance", "Good");
		}

		return (int) (Math.max(angle, 41));
	}

	public double getXOffset() {
		if (isValidTarget()) {
			xPos = visionTable.getNumber("xPosition", screenCenter);
			xOffset = (xPos - screenCenter) * xOffsetMultiplier;
			return (xOffset);
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
