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

		// public static int rollerPort = 6; unused?

		public static final int LeftShooterMotor = 4;
		public static final int RightShooterMotor = 3;
		public static final int ShooterAngleMotor = 2;

		public static final int leftTransmissionServoPort = 7;
		public static final int rightTransmissionServoPort = 8;

		public static final int MainIntakeVictor = 1;
		public static final int CrossIntakeVictor = 5;
		public static final int RollerVictor = 0;
	}

	public static class CAN {
		public static int leftTopDrive = 3;
		public static int leftBotDrive = 4;
		public static int rightTopDrive = 1;
		public static int rightBotDrive = 2;
	}

	// Encoder (Digital Input) Channels
	public static class Digital {

		public static int leftEncoderA = 2; // front left
		public static int leftEncoderB = 3;
		public static int rightEncoderA = 0; // front right
		public static int rightEncoderB = 1;

		public static final int ShooterLeftChannelA = 6;
		public static final int ShooterLeftChannelB = 7;

		public static final int ShooterRightChannelA = 4;
		public static final int ShooterRightChannelB = 5;

	}

	// Analog Inputs
	public static class Analog {
		public static int gyroPort = 0;
		public static final int IntakeSensor = 1;
		public static final int ShooterPotAngle = 2;
		public static final int frontUltrasonic = 3;
	}

	// Solenoids
	public static class Solenoid {
		public static final int IntakeSolenoidForwards = 0;
		public static final int IntakeSolenoidBackwards = 1;
		public static final int AManipulatorForward = 2;
		public static final int AManipulatorReverse = 3;
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

		public static final String IntakeRollerRotations = "IntakeRollerRotations";
		public static final String IntakeRollerMotorSpeed = "IntakeRollerMotorSpeed";
		public static final String IntakeSensorThreshold = "IntakeSensorThreshold";
	}

}
