package org.usfirst.frc.team166.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team166.robot.commands.drive.DriveDistance;
import org.usfirst.frc.team166.robot.commands.drive.TurnAngle;
import org.usfirst.frc.team166.robot.commands.intake.RaiseRake;
import org.usfirst.frc.team166.robot.commands.shooter.SetShooterSpeed;

/**
 *
 */
public class AllUpPositionFiveAuto extends CommandGroup {

	public AllUpPositionFiveAuto() {
		addSequential(new SetShooterSpeed(.9));
		addSequential(new MoveActuatorsUp(), 2);
		addSequential(new DriveDistance(.9, 70));
		addSequential(new RaiseRake());
		addSequential(new DriveDistance(.9, 20));
		addSequential(new TurnAngle(-20));
		addSequential(new MediumRangeShot());
	}
}
