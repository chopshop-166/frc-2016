package org.usfirst.frc.team166.robot.commands;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

import org.usfirst.frc.team166.robot.commands.aimShooter.AimToAngle;
import org.usfirst.frc.team166.robot.commands.drive.DriveToBatter;
import org.usfirst.frc.team166.robot.commands.roller.MoveBallIntoShooter;
import org.usfirst.frc.team166.robot.commands.shooter.SetShooterSpeed;

/**
 *
 */
public class BatterShot extends CommandGroup {

	public BatterShot() {
		addSequential(new DriveToBatter());
		addSequential(new SetShooterSpeed(Preferences.getInstance().getDouble("ShooterSpeed", 0.0)));
		addSequential(new AimToAngle(60));
		addSequential(new WaitCommand(1.0));
		addSequential(new MoveBallIntoShooter());
		addSequential(new WaitCommand(1.0));
		addSequential(new SetShooterSpeed(0.0));

	}
}
