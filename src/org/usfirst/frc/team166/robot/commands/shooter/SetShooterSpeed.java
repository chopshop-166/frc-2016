package org.usfirst.frc.team166.robot.commands.shooter;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team166.robot.Robot;

/**
 *
 */
public class SetShooterSpeed extends Command {
	double mySpeed = 0.9;

	public SetShooterSpeed(double speed) {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.shooter);
		mySpeed = speed;
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		Robot.shooter.updatePIDConstants();
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		Robot.shooter.setSpeedOpenLoop(mySpeed);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return true;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}
