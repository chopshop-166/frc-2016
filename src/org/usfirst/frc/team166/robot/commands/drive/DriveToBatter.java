package org.usfirst.frc.team166.robot.commands.drive;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team166.robot.Robot;

/**
 *
 */
public class DriveToBatter extends Command {

	public DriveToBatter() {
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
		Robot.drive.driveWithGyro(Preferences.getInstance().getDouble("DriveToBatterSpeed", .7),
				Preferences.getInstance().getDouble("DriveToBatterSpeed", .7));
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return (Robot.drive.getFrontUltrasonicVoltage() < Preferences.getInstance().getDouble("BatterDistanceConstant",
				.73));
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.drive.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}
