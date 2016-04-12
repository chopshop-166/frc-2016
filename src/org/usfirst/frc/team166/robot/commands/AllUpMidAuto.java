package org.usfirst.frc.team166.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team166.robot.commands.drive.DriveDistance;
import org.usfirst.frc.team166.robot.commands.shooter.SetShooterSpeed;
import org.usfirst.frc.team166.robot.commands.shooterLock.UnlockShooter;

/**
 *
 */
public class AllUpMidAuto extends CommandGroup {

	public AllUpMidAuto() {

		addSequential(new SetShooterSpeed(.9));
		addSequential(new MoveActuatorsUp());
		addSequential(new DriveDistance(.9, 150));
		addSequential(new UnlockShooter());
		addSequential(new MediumRangeShot());
	}
}
