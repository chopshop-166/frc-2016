package org.usfirst.frc.team166.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team166.robot.commands.drive.DriveDistance;
import org.usfirst.frc.team166.robot.commands.drive.TurnAngle;
import org.usfirst.frc.team166.robot.commands.intake.RaiseRake;
import org.usfirst.frc.team166.robot.commands.shooter.SetShooterSpeed;

/**
 *
 */
public class FarLeftAuto extends CommandGroup {

	public FarLeftAuto() {
		addSequential(new SetShooterSpeed(.9));
		addSequential(new MoveActuatorsDown(), 2);
		addSequential(new DriveDistance(.9, 70));
		addSequential(new RaiseRake());
		addSequential(new DriveDistance(.9, 100));
		// addSequential(new WaitCommand(1));
		addSequential(new TurnAngle(45));
		// addSequential(new DriveDistance(.7, 112));
		addSequential(new DriveDistance(.7, 36));
		addSequential(new MediumRangeShot());
	}
}
