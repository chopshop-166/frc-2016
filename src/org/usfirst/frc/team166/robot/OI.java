package org.usfirst.frc.team166.robot;

import edu.wpi.first.wpilibj.Joystick;

public class OI {
	private final Joystick leftStick;
	private final Joystick rightStick;
	private final Joystick xbox;

	public OI() {
		leftStick = new Joystick(RobotMap.leftJoystickPort);
		rightStick = new Joystick(RobotMap.rightJoystickPort);
		xbox = new Joystick(RobotMap.xboxPort);

	}

	public double getLeftYAxis() {
		return leftStick.getRawAxis(1);
	}

	public double getRightYAxis() {
		return rightStick.getRawAxis(1);
	}

	public double getZAxis() {
		return xbox.getRawAxis(2);
	}
}
