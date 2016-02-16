package org.usfirst.frc.team166.robot.commands.intake;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team166.robot.commands.roller.FeedBallIntoRoller;
import org.usfirst.frc.team166.robot.commands.roller.WaitForBall;

/**
 *
 */
public class LoadingProcess extends CommandGroup {

	public LoadingProcess() {
		addSequential(new LowerRake());
		addSequential(new IntakeMotorForward());
		addSequential(new WaitForBall());
		addSequential(new FeedBallIntoRoller());
		addSequential(new IntakeMotorStop());
		addSequential(new RaiseRake());
	}
}
