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

	public double getDesiredShooterAngle() {
		// read from networktables and stuff
		return (NetworkTable.getTable("visionDataTable").getNumber("shooterAngle", defaultShooterAngle));
	}

	public double getXOffset() {
		xPos = NetworkTable.getTable("Vision").getNumber("xPos", screenCenter);
		xOffset = (xPos - screenCenter) * xOffsetMultiplier;
		return (0);
	}

	@Override
	public void initDefaultCommand() {

	}
}
