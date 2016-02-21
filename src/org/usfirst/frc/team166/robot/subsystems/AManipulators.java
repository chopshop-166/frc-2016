package org.usfirst.frc.team166.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team166.robot.RobotMap;

/**
 *
 */
public class AManipulators extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	DoubleSolenoid aManipulatorSolenoid = new DoubleSolenoid(RobotMap.Solenoid.AManipulatorForward,
			RobotMap.Solenoid.AManipulatorReverse);

	public void toggleAManipulators() {
		Value solenoidVal = aManipulatorSolenoid.get();
		if (solenoidVal == Value.kForward) {
			aManipulatorSolenoid.set(DoubleSolenoid.Value.kReverse);
		} else {
			aManipulatorSolenoid.set(DoubleSolenoid.Value.kForward);
		}
	}

	public void lowerAManipulators() {
		aManipulatorSolenoid.set(DoubleSolenoid.Value.kForward);
	}

	public void raiseAManipulators() {
		aManipulatorSolenoid.set(DoubleSolenoid.Value.kReverse);
	}

	@Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
