package org.usfirst.frc.team166.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

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
		addSequential(new DriveDistance(.5, 42));
		addSequential(new LowerAManipulators(), 2);
		addSequential(new WaitCommand(.5));
		addSequential(new DriveDistance(-.4, -6));
		addSequential(new DriveDistance(.7, 90));
		addSequential(new MoveActuatorsUp());
		addSequential(new UnlockShooter());
		addSequential(new MediumRangeShot());
	}
}
