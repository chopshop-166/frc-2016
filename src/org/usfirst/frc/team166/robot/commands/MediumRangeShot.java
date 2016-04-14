package org.usfirst.frc.team166.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

import org.usfirst.frc.team166.robot.commands.aimShooter.Aim;
import org.usfirst.frc.team166.robot.commands.drive.DriveWithJoysticks;
import org.usfirst.frc.team166.robot.commands.drive.TurnToGoalWithGyro;
import org.usfirst.frc.team166.robot.commands.roller.MoveBallIntoShooter;
import org.usfirst.frc.team166.robot.commands.shooter.SetShooterSpeed;
import org.usfirst.frc.team166.robot.commands.shooterLock.UnlockShooter;

/**
 *
 */
public class MediumRangeShot extends CommandGroup {

	public MediumRangeShot() {

		// addSequential(new UnlockShooter());
		// addSequential(new SetShooterSpeed(.9));
		// addSequential(new TurnToGoalFast()); // First fast pass (%7)
		// addSequential(new WaitCommand(.25)); // Wait a little bit for it to settle
		// addSequential(new TurnToGoalSlow()); // Second slow pass (%3)
		// addParallel(new TurnToGoalSlowParallel()); // Neverending third pass (doesn't move < %3)
		// addSequential(new Aim()); // Adjust shooter angle
		// addSequential(new WaitCommand(.5)); // Wait for shooter to settle and parallel to do work
		// addSequential(new MoveBallIntoShooter()); // FIRE!
		// addSequential(new WaitCommand(.5));
		// addSequential(new SetShooterSpeed(0.0));
		// addSequential(new CancelShot());
		// addSequential(new DriveWithJoysticks());

		addSequential(new UnlockShooter());
		addSequential(new SetShooterSpeed(.9));
		addSequential(new TurnToGoalWithGyro());
		addSequential(new Aim());
		addSequential(new WaitCommand(.5));
		addSequential(new MoveBallIntoShooter());
		addSequential(new WaitCommand(.25));
		addSequential(new SetShooterSpeed(0.0));
		addSequential(new CancelShot());
		addSequential(new DriveWithJoysticks());
	}
}
