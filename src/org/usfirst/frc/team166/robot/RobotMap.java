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
		public static final int Joystick = 1;
	}

	// Copilot Controls
	public static class Copilot {
		public static final int CoPiolitJoystick = 0;
	}

	// PWM Channels
	public static class Pwm {
		public static final int LeftShooterMotor = 8;
		public static final int RightShooterMotor = 7;
		public static final int ShooterAngleMotor = 6;
		public static final int IntakeVictor = 4;
		public static final int IntakeVictor2 = 5;
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

	// Solenoids
	public static class Solenoid {
		public static final int IntakeSolenoidR = 0;
		public static final int IntakeSolenoidF = 1;
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
