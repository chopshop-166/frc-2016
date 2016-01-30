package org.usfirst.frc.team166.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into to a variable name. This provides
 * flexibility changing wiring, makes checking the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;

	// Driver Conrols
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

		public static final int LeftShooterMotor = 5;
		public static final int RightShooterMotor = 4;
		public static final int ShooterAngleMotor = 6;
		public static final int IntakeVictor = 7;
	}

	// Encoder (Digital Input) Channels
	public static class Digital {

		public static int leftEncoderA = 12; // front left
		public static int leftEncoderB = 13;
		public static int rightEncoderA = 10; // front right
		public static int rightEncoderB = 11;

		public static int leftEncoder1A = 16; // rear left
		public static int leftEncoder1B = 17;
		public static int rightEncoder1A = 14; // rear right
		public static int rightEncoder1B = 15;

		public static final int ShooterLeftChannelA = 18;
		public static final int ShooterLeftChannelB = 19;
		public static final int ShooterRightChannelA = 22;
		public static final int ShooterRightChannelB = 23;
	}

	// Analog Inputs
	public static class Analog {
		public static int gyroPort = 0;

		public static final int ShooterPotAngle = 1; // NEEDS A COMMENT
	}

	// Solenoids
	public static class Solenoid {
		public static final int IntakeSolenoidForwards = 1;
		public static final int IntakeSolenoidBackwards = 2;

	}

	// Prefs
	public static class Prefs {
		public static final String ShooterP = "ShooterP";
		public static final String ShooterI = "ShooterI";
		public static final String ShooterD = "ShooterD";
		public static final String ShooterF = "ShooterF";

		public static final String ShooterAngleP = "ShooterAngleP";
		public static final String ShooterAngleI = "ShooterAngleI";
		public static final String ShooterAngleD = "ShooterAngleD";
		public static final String ShooterAngleF = "ShooterAngleF";

		public static final String ShooterSpeed = "ShooterSpeed";

		public static final String angleToDisplacementConstant = "angleToDisplacementConstant";
	}

}
