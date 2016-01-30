package org.usfirst.frc.team166.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into to a variable name. This provides
 * flexibility changing wiring, makes checking the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

	// Driver Controls
	public static class Driver {
		public static int leftJoystickPort = 0;
		public static int rightJoystickPort = 1;
		public static final int Joystick = 1;
	}

	// Copilot Controls
	public static class Copilot {
		public static int copilotPort = 2;
		public static int Joystick = 2;
	}

	// PWM Channels
	public static class Pwm {
		public static int leftTopDrive = 1;
		public static int leftBotDrive = 2;
		public static int rightTopDrive = 0;
		public static int rightBotDrive = 3;

		public static int rollerPort = 6;

		public static final int LeftShooterMotor = 4;
		public static final int RightShooterMotor = 5;
		public static final int ShooterAngleMotor = 6;
	}

	// Encoder (Digital Input) Channels
	public static class Digital {

		public static int leftEncoderA = 12;
		public static int leftEncoderB = 13;
		public static int rightEncoderA = 10;
		public static int rightEncoderB = 11;

		public static final int ShooterLeftChannelA = 16;
		public static final int ShooterLeftChannelB = 17;
		public static final int ShooterRightChannelA = 14;
		public static final int ShooterRightChannelB = 15;
	}

	// Analog Inputs
	public static class Analog {
		public static int gyroPort = 0;

		public static final int ShooterPotAngle = 16; // NEEDS A COMMENT
	}

	// Solenoid Channels
	public static class Solenoid {

	}
}
