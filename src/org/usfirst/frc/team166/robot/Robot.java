package org.usfirst.frc.team166.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team166.robot.commands.MoveActuatorsDown;
import org.usfirst.frc.team166.robot.commands.MoveActuatorsUp;
import org.usfirst.frc.team166.robot.commands.SlowMediumRangeShot;
import org.usfirst.frc.team166.robot.commands.autonomous.AllDownMidAuto;
import org.usfirst.frc.team166.robot.commands.autonomous.AllDownPositionTwoAuto;
import org.usfirst.frc.team166.robot.commands.autonomous.AllUpMidAuto;
import org.usfirst.frc.team166.robot.commands.autonomous.AllUpPositionTwoAuto;
import org.usfirst.frc.team166.robot.commands.autonomous.FarLeftAuto;
import org.usfirst.frc.team166.robot.commands.autonomous.MidCDFAuto;
import org.usfirst.frc.team166.robot.subsystems.AManipulators;
import org.usfirst.frc.team166.robot.subsystems.AimShooter;
import org.usfirst.frc.team166.robot.subsystems.Drive;
import org.usfirst.frc.team166.robot.subsystems.Intake;
import org.usfirst.frc.team166.robot.subsystems.IntakeRoller;
import org.usfirst.frc.team166.robot.subsystems.Shooter;
import org.usfirst.frc.team166.robot.subsystems.ShooterLock;
import org.usfirst.frc.team166.robot.subsystems.Vision;
import org.usfirst.frc.team166.robot.triggers.LeftXBoxTrigger;
import org.usfirst.frc.team166.robot.triggers.POVDownTrigger;
import org.usfirst.frc.team166.robot.triggers.POVUpTrigger;
import org.usfirst.frc.team166.robot.triggers.RightXBoxTrigger;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to each mode, as
 * described in the IterativeRobot documentation. If you change the name of this class or the package after creating
 * this project, you must also update the manifest file in the resource directory.
 */
public class Robot extends IterativeRobot {

	public static OI oi;

	// subsystems
	public static Drive drive;
	public static Intake intake;
	public static Shooter shooter;
	public static AimShooter aimShooter;
	public static Vision vision;
	public static IntakeRoller intakeRoller;
	public static AManipulators aManipulators;
	public static ShooterLock shooterLock;
	private static SendableChooser autoChooser;

	// triggers
	private POVUpTrigger povUpTrigger = new POVUpTrigger();
	private POVDownTrigger povDownTrigger = new POVDownTrigger();
	private RightXBoxTrigger rightXBoxTrigger = new RightXBoxTrigger();
	private LeftXBoxTrigger leftXBoxTrigger = new LeftXBoxTrigger();

	Command autonomousCommand;

	/**
	 * This function is run when the robot is first started up and should be used for any initialization code.
	 */
	@Override
	public void robotInit() {
		drive = new Drive();
		intake = new Intake();
		shooter = new Shooter();
		aimShooter = new AimShooter();
		vision = new Vision();
		intakeRoller = new IntakeRoller();
		aManipulators = new AManipulators();
		shooterLock = new ShooterLock();

		// autochooser
		autoChooser = new SendableChooser();

		oi = new OI();

		// auto chooser commands
		autoChooser.addObject("NOTHING", null);
		autoChooser.addDefault("ONE: LOW", new FarLeftAuto());
		autoChooser.addObject("MID: UP", new AllUpMidAuto());
		autoChooser.addObject("MID: DOWN", new AllDownMidAuto());
		autoChooser.addObject("MID: CDF", new MidCDFAuto());
		autoChooser.addObject("TWO: DOWN (Untested)", new AllDownPositionTwoAuto());
		autoChooser.addObject("TWO: UP (Untested)", new AllUpPositionTwoAuto());

		// autoChooser.addObject("MidLeftAuto", new MidLeftAuto());
		// autoChooser.addObject("MidRightAuto", new MidRightAuto());
		// autoChooser.addObject("FarRightAuto", new FarRightAuto());
		// autoChooser.addObject("Uber Auto", new UberAuto());

		// Hard code for auto
		// autonomousCommand = new FarLeftAuto();
		// autonomousCommand = new AllDownAuto();
		// autonomousCommand = new AllUpAuto();
		// autonomousCommand = new PaperWeightAuto();

		SmartDashboard.putData("Autonomous", autoChooser);

		povUpTrigger.whenActive(new MoveActuatorsUp());
		povDownTrigger.whenActive(new MoveActuatorsDown());
		rightXBoxTrigger.whenActive(new SlowMediumRangeShot());
		// leftXBoxTrigger.whenActive(new BackwardMovingShot());
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {
		// schedule the autonomous command (example)
		autonomousCommand = (Command) autoChooser.getSelected();
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	/**
	 * This function is called when the disabled button is hit. You can use it to reset subsystems before shutting down.
	 */
	@Override
	public void disabledInit() {
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
