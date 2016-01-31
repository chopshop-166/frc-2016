package org.usfirst.frc.team166.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team166.robot.commands.drive.DriveWithGyro;
import org.usfirst.frc.team166.robot.commands.drive.highGearDistancePerPulse;
import org.usfirst.frc.team166.robot.commands.drive.lowGearDistancePerPulse;
import org.usfirst.frc.team166.robot.commands.intake.IntakeMotorForward;
import org.usfirst.frc.team166.robot.commands.intake.IntakeMotorReverse;
import org.usfirst.frc.team166.robot.commands.intake.IntakeMotorStop;
import org.usfirst.frc.team166.robot.commands.intake.ToggleIntakeSolenoid;
import org.usfirst.frc.team166.robot.commands.shooter.Aim;

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
		JoystickButton rightJoyButton2 = new JoystickButton(rightStick, 2);
		JoystickButton rightJoyButton3 = new JoystickButton(rightStick, 3);

		JoystickButton CPbutton1 = new JoystickButton(copilotController, 1);
		JoystickButton CPbutton2 = new JoystickButton(copilotController, 2);
		JoystickButton CPbutton3 = new JoystickButton(copilotController, 3);
		JoystickButton CPbutton4 = new JoystickButton(copilotController, 4);
		JoystickButton CPbutton5 = new JoystickButton(copilotController, 5);

		// Buttons
		rightJoyTrigger.whileHeld(new DriveWithGyro());
		rightJoyButton2.whenPressed(new lowGearDistancePerPulse());
		rightJoyButton3.whenPressed(new highGearDistancePerPulse());

		// The Following commands are mapped from buttons on a joystick and may
		// need to be changed if the copilot's controller turns out to be an
		// Xbox controler
		CPbutton1.whileHeld(new Aim());
		CPbutton2.whileHeld(new IntakeMotorForward());
		CPbutton2.whenReleased(new IntakeMotorStop());
		CPbutton3.whileHeld(new IntakeMotorReverse());
		CPbutton3.whenReleased(new IntakeMotorStop());
		CPbutton4.whenPressed(new ToggleIntakeSolenoid());

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
