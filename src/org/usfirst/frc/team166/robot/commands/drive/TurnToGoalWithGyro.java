package org.usfirst.frc.team166.robot.commands.drive;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team166.robot.Robot;

/**
 *
 */
public class TurnToGoalWithGyro extends Command {
	double xOffset = 0.0;
	double shotZone = .045; // was .05
	double spinSpeed = .26; // was .3
	double fastSpinSpeed = .17;

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
		xOffset = Robot.vision.getXOffset(); // Obtain offset from dataTables

		if (Math.abs(xOffset) > .8) { // If more than 30% away, drive fast
			// spinSpeed = .18;
		} else {
			// spinSpeed = .16;
		}

		if (xOffset > shotZone) { // If the target is to the right, spin right
			if (!Robot.drive.isRobotSpinning()) {
				Robot.drive.spinRight(fastSpinSpeed); // If we are stuck, give it a little more power
			} else {
				Robot.drive.spinRight(spinSpeed);
			}
		} else if (xOffset < -shotZone) { // If the target is to the left, spin left
			if (!Robot.drive.isRobotSpinning()) {
				Robot.drive.spinLeft(fastSpinSpeed);
			} else {
				Robot.drive.spinLeft(spinSpeed);
			}
		} else {
			Robot.drive.brake(); // See Robot.drive.brake();
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
