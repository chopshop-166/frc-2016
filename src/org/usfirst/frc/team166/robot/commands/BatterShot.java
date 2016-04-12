package org.usfirst.frc.team166.robot.commands;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

import org.usfirst.frc.team166.robot.commands.aimShooter.AimToAngle;
import org.usfirst.frc.team166.robot.commands.intake.LowerRake;
import org.usfirst.frc.team166.robot.commands.intake.RaiseRake;
import org.usfirst.frc.team166.robot.commands.roller.MoveBallIntoShooter;
import org.usfirst.frc.team166.robot.commands.shooter.SetShooterSpeed;
import org.usfirst.frc.team166.robot.commands.shooterLock.UnlockShooter;

/**
 *
 */
public class BatterShot extends CommandGroup {

	public BatterShot() {
		addSequential(new UnlockShooter());
		addSequential(new LowerRake());
		addSequential(new SetShooterSpeed(Preferences.getInstance().getDouble("ShooterSpeed", 0.0)));
		addSequential(new AimToAngle(61));
		addSequential(new WaitCommand(1.0));
		addSequential(new MoveBallIntoShooter());
		addSequential(new WaitCommand(1.0));
		addSequential(new SetShooterSpeed(0.0));
		addSequential(new RaiseRake());

	}
}
