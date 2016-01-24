package org.usfirst.frc.team166.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team166.robot.Robot;

/**
 *
 */
public class AngleShooter extends Command {

	private static final double angle = 0;

	public AngleShooter() {
		requires(Robot.shooter);
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {

		// moves the motor that angles the shooter
		// number will come from vision
		if (Robot.shooter.getPot() < angle && Robot.shooter.getPot() > angle - 1) {
			Robot.shooter.angleShooter(.75);
		} else if (Robot.shooter.getPot() > angle && Robot.shooter.getPot() < angle - 1) {
			Robot.shooter.angleShooter(-.75);
		}

	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return Robot.shooter.getPot() == angle && Robot.shooter.getPot() > angle - 1;

	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.shooter.angleShooter(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		Robot.shooter.angleShooter(0);
	}
}
