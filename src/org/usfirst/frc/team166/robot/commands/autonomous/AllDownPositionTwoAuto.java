package org.usfirst.frc.team166.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team166.robot.commands.MediumRangeShot;
import org.usfirst.frc.team166.robot.commands.MoveActuatorsDown;
import org.usfirst.frc.team166.robot.commands.drive.DriveDistance;
import org.usfirst.frc.team166.robot.commands.drive.TurnAngle;
import org.usfirst.frc.team166.robot.commands.intake.RaiseRake;
import org.usfirst.frc.team166.robot.commands.shooter.SetShooterSpeed;

/**
 *
 */
public class AllDownPositionTwoAuto extends CommandGroup {

	public AllDownPositionTwoAuto() {
		addSequential(new SetShooterSpeed(.9));
		addSequential(new MoveActuatorsDown(), 2);
		addSequential(new DriveDistance(.9, 70));
		addSequential(new RaiseRake());
		addSequential(new DriveDistance(.9, 50));
		// addSequential(new WaitCommand(1));
		addSequential(new TurnAngle(30));
		// addSequential(new DriveDistance(.7, 112));
		addSequential(new DriveDistance(.7, 10));
		addSequential(new MediumRangeShot());
		// addSequential(new ToggleDriveDirection());
	}
}
