package org.usfirst.frc.team166.robot.commands.roller;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team166.robot.Robot;
import org.usfirst.frc.team166.robot.RobotMap;

/**
 *
 */
public class RunRollerSystem extends Command {

	public RunRollerSystem() {
		requires(Robot.intakeRoller);
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		double value = Preferences.getInstance().getDouble(RobotMap.Prefs.IntakeRollerRotations, 5);
		Robot.intakeRoller.setDesiredRotation(value);
		setTimeout(value);// Get rid of this when we get an encoder
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		Robot.intakeRoller.StartRoller();
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return Robot.intakeRoller.hasRotatedDesiredRotations() || isTimedOut();// get rid of || is Timed out once we get
																				// and encoder
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.intakeRoller.ResetEncoder();
		Robot.intakeRoller.RollerStop();

	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}
