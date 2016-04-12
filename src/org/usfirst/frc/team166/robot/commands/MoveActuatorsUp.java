package org.usfirst.frc.team166.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team166.robot.commands.aManipulators.RaiseAManipulators;
import org.usfirst.frc.team166.robot.commands.aimShooter.AimToAngle;
import org.usfirst.frc.team166.robot.commands.intake.RaiseRake;
import org.usfirst.frc.team166.robot.commands.shooterLock.LockShooter;

/**
 *
 */
public class MoveActuatorsUp extends CommandGroup {

	public MoveActuatorsUp() {
		// Add Commands here:
		// e.g. addSequential(new Command1());
		// addSequential(new Command2());
		// these will run in order.
		addSequential(new RaiseAManipulators());
		addSequential(new RaiseRake());
		addSequential(new AimToAngle(55));
		addSequential(new LockShooter());

	}
}
