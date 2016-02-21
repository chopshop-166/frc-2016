package org.usfirst.frc.team166.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team166.robot.commands.aimShooter.AimToAngle;
import org.usfirst.frc.team166.robot.commands.intake.IntakeMotorForward;
import org.usfirst.frc.team166.robot.commands.intake.IntakeMotorStop;
import org.usfirst.frc.team166.robot.commands.intake.LowerRake;
import org.usfirst.frc.team166.robot.commands.intake.RaiseRake;
import org.usfirst.frc.team166.robot.commands.roller.RollerSequence;

/**
 *
 */
public class LoadingProcess extends CommandGroup {

	public LoadingProcess() {
		addParallel(new AimToAngle(45));
		addSequential(new LowerRake());
		addSequential(new IntakeMotorForward());
		addSequential(new RollerSequence());
		addSequential(new IntakeMotorStop());
		addSequential(new RaiseRake());
	}
}
