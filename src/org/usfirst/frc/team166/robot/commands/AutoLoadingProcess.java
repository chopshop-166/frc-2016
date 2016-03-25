package org.usfirst.frc.team166.robot.commands;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team166.robot.commands.aimShooter.AimToAngle;
import org.usfirst.frc.team166.robot.commands.intake.IntakeMotorForward;
import org.usfirst.frc.team166.robot.commands.intake.IntakeMotorStop;
import org.usfirst.frc.team166.robot.commands.intake.LowerRake;
import org.usfirst.frc.team166.robot.commands.roller.RollerSequence;
import org.usfirst.frc.team166.robot.commands.shooter.SetShooterSpeed;
import org.usfirst.frc.team166.robot.commands.shooterLock.UnlockShooter;

/**
 *
 */
public class AutoLoadingProcess extends CommandGroup {

	public AutoLoadingProcess() {
		addSequential(new UnlockShooter());
		addParallel(new AimToAngle(45));
		addSequential(new LowerRake());
		addSequential(new IntakeMotorForward());
		addSequential(new RollerSequence());
		addSequential(new IntakeMotorStop());
		addSequential(new SetShooterSpeed(Preferences.getInstance().getDouble("ShooterSpeed", 0.0)));
	}
}
