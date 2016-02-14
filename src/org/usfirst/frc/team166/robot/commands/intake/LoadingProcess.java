package org.usfirst.frc.team166.robot.commands.intake;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team166.robot.commands.roller.RunRollerSystem;

/**
 *
 */
public class LoadingProcess extends CommandGroup {

	public LoadingProcess() {
		addSequential(new LowerRake());
		addSequential(new IntakeMotorForward());
		addSequential(new RunRollerSystem());
		addSequential(new IntakeMotorStop());
		addSequential(new RaiseRake());
	}
}
