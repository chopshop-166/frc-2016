package org.usfirst.frc.team166.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team166.robot.Robot;

/**
 *
 */
public class CancelShot extends Command {

	public CancelShot() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.aimShooter);
		requires(Robot.intakeRoller);
		requires(Robot.shooter);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		Robot.aimShooter.stop();
		Robot.intakeRoller.stopRoller();
		Robot.shooter.setSpeedOpenLoop(0.0);
		Robot.intake.intakeMotorStop();
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {

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
