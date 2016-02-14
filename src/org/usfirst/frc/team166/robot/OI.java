package org.usfirst.frc.team166.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team166.robot.commands.aimShooter.Aim;
import org.usfirst.frc.team166.robot.commands.drive.DriveWithGyro;
import org.usfirst.frc.team166.robot.commands.drive.DriveWithJoysticksBackward;
import org.usfirst.frc.team166.robot.commands.drive.HighGear;
import org.usfirst.frc.team166.robot.commands.drive.LowGear;
import org.usfirst.frc.team166.robot.commands.drive.Neutral;
import org.usfirst.frc.team166.robot.commands.intake.IntakeMotorForward;
import org.usfirst.frc.team166.robot.commands.intake.IntakeMotorReverse;
import org.usfirst.frc.team166.robot.commands.intake.IntakeMotorStop;
import org.usfirst.frc.team166.robot.commands.intake.LoadingProcess;
import org.usfirst.frc.team166.robot.commands.intake.ToggleIntakeSolenoid;

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

		JoystickButton rightJoyTrigger = new JoystickButton(rightStick, 1);
		JoystickButton leftJoyTrigger = new JoystickButton(leftStick, 1);
		JoystickButton rightJoyButton2 = new JoystickButton(rightStick, 2);
		JoystickButton rightJoyButton3 = new JoystickButton(rightStick, 3);
		JoystickButton rightJoyButton7 = new JoystickButton(rightStick, 7);

		JoystickButton CPbutton1 = new JoystickButton(copilotController, 1);
		JoystickButton CPbutton2 = new JoystickButton(copilotController, 2);
		JoystickButton CPbutton3 = new JoystickButton(copilotController, 3);
		JoystickButton CPbutton4 = new JoystickButton(copilotController, 4);
		JoystickButton CPbutton5 = new JoystickButton(copilotController, 5);
		JoystickButton CPbutton6 = new JoystickButton(copilotController, 6);
		JoystickButton CPbutton7 = new JoystickButton(copilotController, 7);
		JoystickButton CPbutton8 = new JoystickButton(copilotController, 8);

		// Buttons
		rightJoyTrigger.whileHeld(new DriveWithGyro());
		leftJoyTrigger.whileHeld(new DriveWithJoysticksBackward());

		rightJoyButton3.whenPressed(new HighGear());
		rightJoyButton2.whenPressed(new LowGear());
		rightJoyButton7.whenPressed(new Neutral());

		// The Following commands are mapped from buttons on a joystick and may
		// need to be changed if the copilot's controller turns out to be an
		// Xbox controler
		CPbutton1.whileHeld(new Aim());
		CPbutton2.whileHeld(new IntakeMotorForward());
		CPbutton2.whenReleased(new IntakeMotorStop());
		CPbutton3.whileHeld(new IntakeMotorReverse());
		CPbutton3.whenReleased(new IntakeMotorStop());
		CPbutton4.whenPressed(new ToggleIntakeSolenoid());
		CPbutton5.whenPressed(new LoadingProcess());// this the entire loading and prepping process.

		// The Following commands are mapped from buttons on a joystick and may need to be changed if the
		// copiolits controller turns out to be an Xbox controler
	}

	public double getLeftYAxis() {
		return -leftStick.getRawAxis(1);
	}

	public double getRightYAxis() {
		return -rightStick.getRawAxis(1);
	}

	public double getZAxis() {
		return copilotController.getRawAxis(2);
	}
}
