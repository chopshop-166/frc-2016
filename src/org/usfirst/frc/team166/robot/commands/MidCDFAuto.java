package org.usfirst.frc.team166.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team166.robot.commands.aManipulators.LowerAManipulators;
import org.usfirst.frc.team166.robot.commands.drive.DriveDistance;
import org.usfirst.frc.team166.robot.commands.shooter.SetShooterSpeed;
import org.usfirst.frc.team166.robot.commands.shooterLock.UnlockShooter;

/**
 *
 */
public class MidCDFAuto extends CommandGroup {

	public MidCDFAuto() {

		addSequential(new SetShooterSpeed(.9));
		addSequential(new MoveActuatorsUp());
		addSequential(new DriveDistance(.5, 47));
		addSequential(new LowerAManipulators(), 2);
		addSequential(new DriveDistance(.7, 48));
		addSequential(new DriveDistance(.9, 55));
		addSequential(new UnlockShooter());
		addSequential(new MediumRangeShot());
	}
}
