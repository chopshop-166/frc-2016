package org.usfirst.frc.team166.robot.commands.aimShooter;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team166.robot.Robot;

/**
 *
 */
public class AimParallel extends Command {
	double desiredAngle = Preferences.getInstance().getDouble("testAngle", 46);

	public AimParallel() {
		requires(Robot.aimShooter);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {

	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		// Robot.aimShooter.moveToAngle(desiredAngle);
		if (!((Math.abs(Robot.vision.getDesiredShooterAngle() - Robot.aimShooter.getShooterAngle())) < .4)) {
			Robot.aimShooter.moveToAngleParallel(Robot.vision.getDesiredShooterAngle());
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		// return ((Math.abs(Robot.vision.getDesiredShooterAngle() - Robot.aimShooter.getShooterAngle())) < 5.0);
		// return ((Math.abs(Robot.vision.getDesiredShooterAngle() - Robot.aimShooter.getShooterAngle())) < .25);
		// return ((Math.abs(desiredAngle - Robot.aimShooter.getShooterAngle())) < .25);
		return false;
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
