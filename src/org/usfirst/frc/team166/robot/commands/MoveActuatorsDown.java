package org.usfirst.frc.team166.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team166.robot.commands.aManipulators.LowerAManipulators;
import org.usfirst.frc.team166.robot.commands.aimShooter.AimToAngle;
import org.usfirst.frc.team166.robot.commands.intake.LowerRake;
import org.usfirst.frc.team166.robot.commands.shooterLock.UnlockShooter;

/**
 *
 */
public class MoveActuatorsDown extends CommandGroup {

	public MoveActuatorsDown() {
		// Add Commands here:
		// e.g. addSequential(new Command1());
		// addSequential(new Command2());
		// these will run in order.
		addSequential(new UnlockShooter());
		addSequential(new LowerAManipulators());
		addSequential(new LowerRake());
		addSequential(new AimToAngle(43));
	}
}
