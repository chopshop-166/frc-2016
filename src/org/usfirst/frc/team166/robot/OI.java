package org.usfirst.frc.team166.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team166.robot.commands.Aim;
import org.usfirst.frc.team166.robot.commands.intake.IntakeMotorForward;
import org.usfirst.frc.team166.robot.commands.intake.IntakeMotorReverse;
import org.usfirst.frc.team166.robot.commands.intake.IntakeMotorStop;
import org.usfirst.frc.team166.robot.commands.intake.ToggleIntakeMotor;
import org.usfirst.frc.team166.robot.commands.intake.ToggleIntakeSolenoid;

/**
 * This class is the glue that binds the controls on the physical operator interface to the commands and command groups
 * that allow control of the robot.
 */
public class OI {

	Joystick Copilot;
	Joystick Driver;

	public OI() {

		Copilot = new Joystick(RobotMap.Copilot.CoPiolitJoystick);
		Driver = new Joystick(RobotMap.Driver.Joystick);
		JoystickButton CPbutton1 = new JoystickButton(Copilot, 1);
		JoystickButton CPbutton2 = new JoystickButton(Copilot, 2);
		JoystickButton CPbutton3 = new JoystickButton(Copilot, 3);
		JoystickButton CPbutton4 = new JoystickButton(Copilot, 4);
		JoystickButton CPbutton5 = new JoystickButton(Copilot, 5);

		// Buttons
		CPbutton1.whileHeld(new Aim());
		CPbutton2.whileHeld(new IntakeMotorForward());
		CPbutton2.whenReleased(new IntakeMotorStop());
		CPbutton3.whileHeld(new IntakeMotorReverse());
		CPbutton3.whenReleased(new IntakeMotorStop());
		CPbutton4.whenPressed(new ToggleIntakeSolenoid());
		CPbutton5.whenPressed(new ToggleIntakeMotor());

		// The Following commands are mapped from buttons on a joystick and may need to be changed if the
		// copiolits controller turns out to be an Xbox controler
	}

	// // CREATING BUTTONS
	// One type of button is a joystick button which is any button on a joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	// // TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
}
