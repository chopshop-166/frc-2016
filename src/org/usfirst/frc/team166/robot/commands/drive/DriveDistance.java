package org.usfirst.frc.team166.robot.commands.drive;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team166.robot.Robot;

/**
 *
 */
public class DriveDistance extends Command {
	double mySpeed;
	double myDistance;

	// Input/Real = 25/32 It goes 32" when you put 25" into the command

	public DriveDistance(double speed, double distance) {
		mySpeed = -speed; // negative because speed is reversed for these motors
		myDistance = distance;
		requires(Robot.drive);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		Robot.drive.resetEncoders();
		Robot.drive.resetGyro();
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		SmartDashboard.putNumber("Encoder Distance", Robot.drive.getEncoderDistance());
		Robot.drive.driveWithGyro(mySpeed, mySpeed);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return (Math.abs(Robot.drive.getEncoderDistance()) >= Math.abs(myDistance));
		// return false;
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
