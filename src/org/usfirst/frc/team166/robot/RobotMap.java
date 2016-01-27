package org.usfirst.frc.team166.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into to a variable name. This provides
 * flexibility changing wiring, makes checking the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

	// Driver Controls
	public static class Driver {
		public static final int Joystick = 1;
	}

	// Copilot Controls
	public static class Copilot {
		public static final int Joystick = 2;
	}

	// PWM Channels
	public static class Pwm {
		public static final int LeftShooterMotor = 4;
		public static final int RightShooterMotor = 5;
		public static final int ShooterAngleMotor = 6;
	}

	// Encoder (Digital Input) Channels
	public static class Digital {
		public static final int ShooterLeftChannelA = 12;
		public static final int ShooterLeftChannelB = 13;
		public static final int ShooterRightChannelA = 14;
		public static final int ShooterRightChannelB = 15;
	}

	// Analog Inputs
	public static class Analog {
		public static final int ShooterPotAngle = 16;
	}

	// Solenoid Channels
	public static class Solenoid {

	}
}
