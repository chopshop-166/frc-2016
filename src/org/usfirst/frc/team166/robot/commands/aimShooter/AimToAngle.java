package org.usfirst.frc.team166.robot.commands.aimShooter;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team166.robot.Robot;

/**
 *
 */
public class AimToAngle extends Command {

	int desiredAngle;

	public AimToAngle(int angle) {
		requires(Robot.aimShooter);
		desiredAngle = angle;
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {

	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		if (Robot.vision.getDesiredShooterAngle() >= 45) {
			Robot.intake.lowerRake();
		}
		Robot.aimShooter.moveToAngle(desiredAngle);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		// return ((Math.abs(Robot.vision.getDesiredShooterAngle() - Robot.aimShooter.getShooterAngle())) < 5.0);
		return ((Math.abs(desiredAngle - Robot.aimShooter.getShooterAngle())) < .25);
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.aimShooter.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		// Robot.aimShooter.disable();
	}
}
