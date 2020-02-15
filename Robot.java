/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import javax.swing.colorchooser.ColorSelectionModel;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.ColorFinder;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.RotationControl;
import frc.robot.commands.SeekVisionTarget;
import frc.robot.subsystems.ColorSensor;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.encoder;
import frc.robot.subsystems.limelight;
import frc.robot.subsystems.pneumatics;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {

	//Declaring subsystems
  	public static final RobotMap map
	= new RobotMap();		//Maps the robot
	public static final ColorSensor kColorSensor 
	= new ColorSensor();     //Controls the color sensor 
	public static final OI m_oi
	= new OI();		//Object Interface. This creates the controllers
    public static final DriveTrain Drive
    = new DriveTrain();		//The drive train for the robot. Includes all the methods for driving the robot
    public static final limelight kLimelight
    = new limelight();		//The vision tracking classes require this to be used
    public static final pneumatics kPneumatics
    = new pneumatics();		//Pneumatic controls
	public static final encoder encode 
	= new encoder();	//Controls all the encoders
    public static final Timer time
	= new Timer();		//Used for keeping track of time
	// public static final NavX kNavX
	// = new NavX();

	//Declaring commands
	public static SeekVisionTarget kSeekVisionTarget
	= new SeekVisionTarget();
	public static ColorFinder kColorFinder
	= new ColorFinder();
	public static RotationControl kRotationControl
	= new RotationControl();

	double speedDrop;

    //Declaring commands
	Command m_autonomousCommand;
	
	//Used for the camera controls
	public static boolean primaryCamActive;
	
	//I really don't know what this thing is it was here when I made the program and I haven't bothered to figure out what it does yet
	SendableChooser<Command> m_chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */

//Declaring OI

	public void robotInit() {
		//Mapping all the hardware
		map.mapAll();

		//limit = new DigitalInput(0);
		//encoder = new Encoder(9, 8);
	    
	  //Autonomous stuff I haven't figured out yet
	  		//m_chooser.addDefault("Default Auto", new ExampleCommand());
	  		// chooser.addObject("My Auto", new MyAutoCommand());
	  		SmartDashboard.putData("Auto mode", m_chooser);
	  		
	  		//Prepares the pneumatics
	  		kPneumatics.clearStickyFault();
	  		kPneumatics.solenoidOff();
	  		kPneumatics.compressorOn();
	  		
			primaryCamActive = true;
		kColorSensor.robotColorValues();

	}
	
	// TODO: Figure out what this is for
	@Override
	public void robotPeriodic() {
		// TODO Auto-generated method stub
		super.robotPeriodic();
	}
	
	@Override
	public void disabledInit() {
		Drive.stop();
		
		super.disabledInit();
		System.out.println("disabled");
		//encoder.reset();
		kLimelight.setPipe(1);
	}
	
	@Override
	public void disabledPeriodic() {
		// TODO Auto-generated method stub
		super.disabledPeriodic();
	}
	
	@Override
	public void autonomousInit() {
		m_autonomousCommand = m_chooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (m_autonomousCommand != null) {
			m_autonomousCommand.start();
		}
		kLimelight.setStreamPrimary();
		//kLimelight.setStreamSecondary();
		kLimelight.setPipe(1);
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		teleOpControl();
	}
	
	@Override
	public void teleopInit() {
		System.out.println("*************************************************************************************");
		System.out.println("*************************************************************************************");
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
//		if (m_autonomousCommand != null) {
//			m_autonomousCommand.cancel();
//		}
		Drive.stop();

		//Initalizing variables
		kColorFinder.colorTargetValue = "Red";

		//Used to regulate the speed of the drive train
		speedDrop = 1.0;
	}
	
	@Override
	public void teleopPeriodic() {
		teleOpControl();
	}

	public void teleOpControl() {
		//Prints the encoder reading
		//System.out.println("Encoder: " + encoder.getDistance());
		Scheduler.getInstance().run();
		//Drive controls
		
		//Checks to see if left button one is pressed
		if (m_oi.leftButtons[1].get()) {
			//Runs vision seeking controls
			m_oi.leftButtons[1].whileHeld(kSeekVisionTarget);
		}
		else {
			//Runs manual controls
			if (primaryCamActive) {
				Drive.driveTank(m_oi.GetLeftY(), m_oi.GetRightY());
			}
			else {
				Drive.driveTankInverted(m_oi.GetLeftY(), m_oi.GetRightY());
			}
		}
		
		//CPI controls
		m_oi.leftButtons[10].toggleWhenPressed(kRotationControl);
		m_oi.leftButtons[9].toggleWhenPressed(kColorFinder);

		
		//Pneumatics controls
		if (m_oi.shootHatch.get()) {
			kPneumatics.extend();
		}
		else {
			kPneumatics.retract();
		}
		
		//Camera controls
		if (m_oi.leftButtons[2].get()) {
			kLimelight.setStreamPrimary();
			primaryCamActive = true;
			kLimelight.setPipe(1);
		}
		if (m_oi.rightButtons[2].get()) {
			kLimelight.setStreamSecondary();
			primaryCamActive = false;
		}

	}

}