package org.usfirst.frc.team166.robot.commands.roller;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team166.robot.Robot;

/**
 *
 */
public class RollerSequence extends Command {

	public RollerSequence() {
		requires(Robot.intakeRoller);
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		Robot.intakeRoller.startRoller();
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		SmartDashboard.putNumber("Intake IR Voltage", Robot.intakeRoller.getIRVoltage());
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return Robot.intakeRoller.isBallLoaded();
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.intakeRoller.stopRoller();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}
