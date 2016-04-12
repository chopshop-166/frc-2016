package org.usfirst.frc.team166.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

import org.usfirst.frc.team166.robot.commands.intake.IntakeMotorReverse;
import org.usfirst.frc.team166.robot.commands.intake.IntakeMotorStop;
import org.usfirst.frc.team166.robot.commands.intake.RaiseRake;
import org.usfirst.frc.team166.robot.commands.roller.ReverseRoller;
import org.usfirst.frc.team166.robot.commands.roller.StopRoller;

/**
 *
 */
public class EjectBall extends CommandGroup {

	public EjectBall() {
		addSequential(new RaiseRake());
		addParallel(new ReverseRoller());
		addSequential(new IntakeMotorReverse());
		addSequential(new WaitCommand(1.0)); // maybe change this
		addSequential(new StopRoller());
		addSequential(new IntakeMotorStop());
	}
}
