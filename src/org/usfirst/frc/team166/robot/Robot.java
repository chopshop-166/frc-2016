package org.usfirst.frc.team166.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team166.robot.commands.Autonomous;
import org.usfirst.frc.team166.robot.commands.roller.RunFeederSystem;
import org.usfirst.frc.team166.robot.commands.shooter.Aim;
import org.usfirst.frc.team166.robot.subsystems.Drive;
import org.usfirst.frc.team166.robot.subsystems.Intake;
import org.usfirst.frc.team166.robot.subsystems.Shooter;
import org.usfirst.frc.team166.robot.subsystems.ShooterFeeder;
import org.usfirst.frc.team166.robot.subsystems.Vision;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to each mode, as
 * described in the IterativeRobot documentation. If you change the name of this class or the package after creating
 * this project, you must also update the manifest file in the resource directory.
 */
public class Robot extends IterativeRobot {

	public static Drive drive;
	public static Intake intake;
	public static Shooter shooter;
	public static Vision vision;
	public static ShooterFeeder shooterFeeder;
	public static OI oi;

	Command autonomousCommand;

	/**
	 * This function is run when the robot is first started up and should be used for any initialization code.
	 */
	@Override
	public void robotInit() {
		drive = new Drive();
		intake = new Intake();
		shooter = new Shooter();
		vision = new Vision();
		shooterFeeder = new ShooterFeeder();
		oi = new OI();
		// instantiate the command used for the autonomous period
		autonomousCommand = new Autonomous();
		SmartDashboard.putData("Aim", new Aim());
		SmartDashboard.putData("RunRollerSystem", new RunFeederSystem());
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {
		// schedule the autonomous command (example)
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
