package org.usfirst.frc.team166.robot.commands.drive;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team166.robot.Robot;

/**
 *
 */
public class DriveWithJoysticks extends Command {

	public DriveWithJoysticks() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.drive);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		Robot.drive.driveWithJoysticks(Robot.oi.getLeftYAxis(), Robot.oi.getRightYAxis());
		Robot.aimShooter.maintainAngle(45);

		SmartDashboard.putNumber("POT Angle", Robot.aimShooter.getShooterAngle());
		SmartDashboard.putNumber("X Offset", Robot.vision.getXOffset());
		SmartDashboard.putNumber("X Position", Robot.vision.getXPos());

		SmartDashboard.putNumber("Front Ultrasonic Distance", Robot.drive.getFrontUltrasonicVoltage());
		SmartDashboard.putNumber("Distance Traveled", Robot.drive.getEncoderDistance());

	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
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
