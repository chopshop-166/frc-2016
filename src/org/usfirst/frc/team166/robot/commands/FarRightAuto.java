package org.usfirst.frc.team166.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team166.robot.commands.aManipulators.LowerAManipulators;
import org.usfirst.frc.team166.robot.commands.drive.DriveDistance;
import org.usfirst.frc.team166.robot.commands.drive.TurnAngle;
import org.usfirst.frc.team166.robot.commands.intake.LowerRake;
import org.usfirst.frc.team166.robot.commands.intake.RaiseRake;

/**
 *
 */
public class FarRightAuto extends CommandGroup {

	public FarRightAuto() {
		addSequential(new LowerRake());
		addSequential(new LowerAManipulators());
		addSequential(new DriveDistance(.9, 170));
		addSequential(new RaiseRake());
		addSequential(new TurnAngle(20));
		// addSequential(new DriveDistance(.7, 112));
		addSequential(new DriveDistance(.7, 25));
		addSequential(new MediumRangeShot());
	}
}
