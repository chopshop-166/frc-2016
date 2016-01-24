package org.usfirst.frc.team166.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team166.robot.commands.FireShooter;

/**
 * This class is the glue that binds the controls on the physical operator interface to the commands and command groups
 * that allow control of the robot.
 */

public class OI {
	private final Joystick leftStick;
	private final Joystick rightStick;
	private final Joystick copilotController;

	public OI() {
		leftStick = new Joystick(RobotMap.Driver.leftJoystickPort);
		rightStick = new Joystick(RobotMap.Driver.rightJoystickPort);
		copilotController = new Joystick(RobotMap.Copilot.copilotPort);

		JoystickButton button1 = new JoystickButton(copilotController, 1);

		// Buttons
		button1.whileHeld(new FireShooter());

	}

	public double getLeftYAxis() {
		return leftStick.getRawAxis(1);
	}

	public double getRightYAxis() {
		return rightStick.getRawAxis(1);
	}

	public double getZAxis() {
		return copilotController.getRawAxis(2);
	}
}
