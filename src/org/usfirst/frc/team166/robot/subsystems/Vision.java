package org.usfirst.frc.team166.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 *
 */
public class Vision extends Subsystem {
	double screenCenter = 160;
	double xOffsetMultiplier = 1.0 / 160.0;
	double defaultShooterAngle = 45.0;
	double xOffset = 0;
	double xPos = 0;
	NetworkTable visionTable;

	public Vision() {
		visionTable = NetworkTable.getTable("visionDataTable");
	}

	public double getDesiredShooterAngle() {
		// returns the desired shooter angle as calculated in the python code

		return (visionTable.getNumber("shooterAngle", defaultShooterAngle));
	}

	public double getXOffset() {
		// returns the offset from the center of the largest vision target (a value between -1 and 1)
		xPos = visionTable.getNumber("xPos", screenCenter);
		xOffset = (xPos - screenCenter) * xOffsetMultiplier;
		return (xOffset);
	}

	public double getXPos() {
		return visionTable.getNumber("xPos", screenCenter);
	}

	@Override
	public void initDefaultCommand() {

	}
}
