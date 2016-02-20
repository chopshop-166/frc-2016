package org.usfirst.frc.team166.robot.commands;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team166.robot.commands.aimShooter.Aim;
import org.usfirst.frc.team166.robot.commands.roller.MoveBallIntoShooter;
import org.usfirst.frc.team166.robot.commands.shooter.SetShooterSpeed;

/**
 *
 */
public class TestShoot extends CommandGroup {

	public TestShoot() {
		// Add Commands here:
		// e.g. addSequential(new Command1());
		// addSequential(new Command2());
		// these will run in order.

		// To run multiple commands at the same time,
		// use addParallel()
		// e.g. addParallel(new Command1());
		// addSequential(new Command2());
		// Command1 and Command2 will run in parallel.

		// A command group will require all of the subsystems that each member
		// would require.
		// e.g. if Command1 requires chassis, and Command2 requires arm,
		// a CommandGroup containing them would require both the chassis and the
		// arm.

		addSequential(new SetShooterSpeed(Preferences.getInstance().getDouble("ShooterSpeed", 0.0)));
		SmartDashboard.putNumber("Shooter Speed", Preferences.getInstance().getDouble("ShooterSpeed", 0.0));
		// addSequential(new SetShooterSpeed(.9));
		addSequential(new Aim());
		addSequential(new WaitCommand(2.0));
		addSequential(new MoveBallIntoShooter());
		addSequential(new WaitCommand(1.0));
		addSequential(new SetShooterSpeed(0.0));

	}
}
