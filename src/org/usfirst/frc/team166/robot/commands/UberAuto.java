package org.usfirst.frc.team166.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team166.robot.Robot;
import org.usfirst.frc.team166.robot.commands.aManipulators.LowerAManipulators;
import org.usfirst.frc.team166.robot.commands.drive.DriveDistance;
import org.usfirst.frc.team166.robot.commands.drive.TurnAngle;
import org.usfirst.frc.team166.robot.commands.intake.LowerRake;
import org.usfirst.frc.team166.robot.commands.intake.RaiseRake;

/**
 *
 */
public class UberAuto extends CommandGroup {

	public UberAuto() {

		// sick 2 ball autonomous that still needs testing

		addSequential(new LowerAManipulators());
		addSequential(new LoadingProcess());
		addSequential(new TurnAngle(-30.0));
		addSequential(new DriveDistance(.9, 37));
		addSequential(new TurnAngle(30.0));
		addSequential(new LowerRake());
		addSequential(new DriveDistance(.9, 162));
		addSequential(new RaiseRake());
		addSequential(new TurnAngle(45.0));
		addSequential(new MediumRangeShot());
		addSequential(new TurnAngle(-45.0 - Robot.drive.turnToGoalAngle));
		addSequential(new LowerRake());
		addSequential(new DriveDistance(-.9, -180.5));
		addSequential(new AutoLoadingProcess());
		addSequential(new DriveDistance(.9, 180.5));
		addSequential(new RaiseRake());
		addSequential(new TurnAngle(45.0));
		addSequential(new MediumRangeShot());

	}
}
