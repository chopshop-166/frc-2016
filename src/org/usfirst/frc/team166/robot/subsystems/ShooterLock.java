package org.usfirst.frc.team166.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team166.robot.RobotMap;

/**
 *
 */
public class ShooterLock extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	DoubleSolenoid shooterLockSolenoid = new DoubleSolenoid(RobotMap.Solenoid.ShooterLockForward,
			RobotMap.Solenoid.ShooterLockReverse);

	public void lockShooter() {
		shooterLockSolenoid.set(DoubleSolenoid.Value.kForward);
	}

	public void unlockShooter() {
		shooterLockSolenoid.set(DoubleSolenoid.Value.kReverse);
	}

	@Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
