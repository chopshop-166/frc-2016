package org.usfirst.frc.team166.robot.commands;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

import org.usfirst.frc.team166.robot.commands.aimShooter.Aim;
import org.usfirst.frc.team166.robot.commands.drive.DriveWithJoysticks;
import org.usfirst.frc.team166.robot.commands.drive.TurnToGoal;
import org.usfirst.frc.team166.robot.commands.drive.TurnToGoalParallel;
import org.usfirst.frc.team166.robot.commands.roller.MoveBallIntoShooter;
import org.usfirst.frc.team166.robot.commands.shooter.SetShooterSpeed;

/**
 *
 */
public class MediumRangeShot extends CommandGroup {

	public MediumRangeShot() {

		addSequential(new SetShooterSpeed(Preferences.getInstance().getDouble("ShooterSpeed", 0.0)));
		addSequential(new TurnToGoal());
		addParallel(new TurnToGoalParallel());
		addSequential(new Aim());
		addSequential(new WaitCommand(.5));
		addSequential(new MoveBallIntoShooter());
		addSequential(new WaitCommand(.5));
		addSequential(new SetShooterSpeed(0.0));
		addSequential(new CancelShot());
		addSequential(new DriveWithJoysticks());
	}
}
