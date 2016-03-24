package org.usfirst.frc.team166.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team166.robot.Robot;

/**
 *
 */
public class ManipulateBall extends CommandGroup {

	public ManipulateBall() {
		// Add Commands here:
		// e.g. addSequential(new Command1());
		// addSequential(new Command2());
		// these will run in order.

		// To run multiple commands at the same time,
		// use addParallel()
		// e.g. addParallel(new Command1());
		// addSequential(new Command2());
		// Command1 and Command2 will run in parallel.

		if (Robot.intakeRoller.isBallLoaded()) {
			addSequential(new EjectBall());
		} else {
			addSequential(new LoadingProcess());
		}
	}
}
