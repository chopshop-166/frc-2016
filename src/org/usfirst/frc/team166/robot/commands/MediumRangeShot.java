package org.usfirst.frc.team166.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

import org.usfirst.frc.team166.robot.commands.aimShooter.Aim;
import org.usfirst.frc.team166.robot.commands.aimShooter.AimParallel;
import org.usfirst.frc.team166.robot.commands.drive.DriveWithJoysticks;
import org.usfirst.frc.team166.robot.commands.drive.TurnToGoalWithGyro;
import org.usfirst.frc.team166.robot.commands.roller.MoveBallIntoShooter;
import org.usfirst.frc.team166.robot.commands.shooter.SetShooterSpeed;
import org.usfirst.frc.team166.robot.commands.shooterLock.UnlockShooter;

/**
 *
 */
public class MediumRangeShot extends CommandGroup {

	public MediumRangeShot() {
		addSequential(new UnlockShooter()); // Release shooter piston
		addSequential(new SetShooterSpeed(.9)); // Set shooter wheel PIDs
		addParallel(new AimParallel());
		addSequential(new TurnToGoalWithGyro()); // see TurnToGoalWithGyro()
		addSequential(new WaitCommand(.5));
		addSequential(new Aim()); // Ajusts shooter angle based on distance algorithm
		// addSequential(new WaitCommand(.5)); // Waits for shooter to settle
		addSequential(new MoveBallIntoShooter()); // Drive feeder roller untill ball leaves
		addSequential(new WaitCommand(.25)); // Guarantee ball has left
		addSequential(new SetShooterSpeed(0.0)); // Spin down shooter
		addSequential(new CancelShot()); // Free up shooting subsystems
		addSequential(new DriveWithJoysticks()); // Return control to the driver
	}
}
