package org.usfirst.frc.team166.robot.commands.drive;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team166.robot.Robot;

/**
 *
 */
public class TurnToGoalWithGyro extends Command {
	double xOffset = 0.0;
	double shotZone = .03;
	double spinSpeed = .2;

	public TurnToGoalWithGyro() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.drive);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		Robot.drive.resetGyro();
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		xOffset = Robot.vision.getXOffset();
		if (xOffset > shotZone) {
			Robot.drive.spinRight(spinSpeed);
		} else if (xOffset < -shotZone) {
			Robot.drive.spinLeft(spinSpeed);
		} else {
			Robot.drive.brake();
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return ((!Robot.drive.isRobotSpinning()) && (Math.abs(xOffset) < shotZone));
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
