package org.usfirst.frc.team166.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team166.robot.commands.BatterShot;
import org.usfirst.frc.team166.robot.commands.CancelShot;
import org.usfirst.frc.team166.robot.commands.EjectBall;
import org.usfirst.frc.team166.robot.commands.LoadingProcess;
import org.usfirst.frc.team166.robot.commands.MediumRangeShot;
import org.usfirst.frc.team166.robot.commands.aManipulators.ToggleAManipulators;
import org.usfirst.frc.team166.robot.commands.drive.DriveWithGyro;
import org.usfirst.frc.team166.robot.commands.drive.ToggleDriveDirection;
import org.usfirst.frc.team166.robot.commands.drive.TurnFromLeftWall;
import org.usfirst.frc.team166.robot.commands.drive.TurnFromRightWall;
import org.usfirst.frc.team166.robot.commands.drive.TurnToGoalFast;
import org.usfirst.frc.team166.robot.commands.drive.TurnToGoalWithGyro;
import org.usfirst.frc.team166.robot.commands.intake.ToggleIntakeSolenoid;
import org.usfirst.frc.team166.robot.commands.shooter.SetShooterSpeed;

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
		//
		JoystickButton rightJoyTrigger = new JoystickButton(rightStick, 1);
		JoystickButton leftJoyTrigger = new JoystickButton(leftStick, 1);
		JoystickButton rightJoyButton2 = new JoystickButton(rightStick, 2);
		JoystickButton leftJoyButton3 = new JoystickButton(leftStick, 3);
		JoystickButton rightJoyButton4 = new JoystickButton(rightStick, 4);
		JoystickButton rightJoyButton5 = new JoystickButton(rightStick, 5);
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
		leftJoyTrigger.whileHeld(new DriveWithGyro());
		leftJoyButton3.whenPressed(new TurnToGoalFast());
		rightJoyTrigger.whenPressed(new ToggleDriveDirection());

		rightJoyButton3.whenPressed(new TurnToGoalWithGyro());
		rightJoyButton2.whenPressed(new EjectBall());
		rightJoyButton4.whileHeld(new TurnFromRightWall());
		rightJoyButton5.whileHeld(new TurnFromLeftWall());
		// rightJoyButton7.whenPressed(new Neutral());

		// The Following commands are mapped from buttons on a joystick and may
		// need to be changed if the copilot's controller turns out to be an
		// Xbox controller
		// CPbutton1.whenPressed(new LoadingProcess());
		CPbutton1.whenPressed(new MediumRangeShot());
		CPbutton2.whenPressed(new BatterShot());
		CPbutton3.whenPressed(new CancelShot());
		CPbutton4.whenPressed(new LoadingProcess());
		CPbutton5.whenPressed(new ToggleIntakeSolenoid());
		CPbutton6.whenPressed(new ToggleAManipulators());
		CPbutton7.whenPressed(new SetShooterSpeed(1.0));

	}

	public double getLeftYAxis() {
		return leftStick.getRawAxis(1);
	}

	public double getRightYAxis() {
		return rightStick.getRawAxis(1);
	}

	public double getCopilotRightTrigger() {
		return copilotController.getRawAxis(3);
	}

	public double getCopilotLeftTrigger() {
		return copilotController.getRawAxis(2);
	}

	public double getCopilotLeftJoyUpDownAxis() {
		return -copilotController.getRawAxis(1);
	}

}
