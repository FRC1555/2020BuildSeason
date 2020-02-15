/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.DriverStation;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorMatch;
import edu.wpi.first.wpilibj.SPI;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
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
	
	//Declaring the elements of the drive train
	public static Victor leftMotor;
	public static Victor rightMotor;
	public static int Lmotor = 0;
	public static int Rmotor = 1;
	
	//
	public static Talon intakeMotor;
	public static Talon liftMotor;
	public static Talon controlPanelMotor;
	public static int intake = 2;
	public static int intakeLift = 3;
	public static int cpanelMotor = 6;
	//The hatch panel grabbers are currently run off two ports, although we should be able to change it to one in the future
	public static int hatchSlapper1 = 4;
	public static int hatchSlapper2 = 5;
	public static int spin = 6;
	public static int lift = 7;
	public static int shoot = 8;

	public static int solenoidP1 = 0;
	public static int solenoidP2 = 1;
	
	public static int Lencoder = 0;
	public static int Rencoder = 1;

	public static int sensorA = 2;
	public static int sensorB = 3;
	public static int sensorC = 4;
	public static int sensorD = 5;
	
	public static String limeLightKey = "limelight";
	
	public static Talon hatchMotor;
	public static Talon slapMotor;

	public ColorSensorV3 colourSensor;
	private final I2C.Port i2cPort = I2C.Port.kOnboard;

	public static ColorMatch colourMatch;

	public static AHRS ahrs;
  
	public static Victor spinner;
	public static Victor armLift;
	public static Victor shooter;


	//Initalizes all the hardware
	public void mapAll() {
		leftMotor = new Victor(Lmotor);
		rightMotor = new Victor(Rmotor);
		intakeMotor = new Talon(intake);
		liftMotor = new Talon(intakeLift);
		hatchMotor = new Talon(hatchSlapper1);
		slapMotor = new Talon(hatchSlapper2);
    controlPanelMotor = new Talon(cpanelMotor);
    spinner = new Victor(spin);
		armLift = new Victor(lift);
		shooter = new Victor(shoot);
		colourSensor = new ColorSensorV3(i2cPort);
		colourMatch = new ColorMatch();

		try {
			/* Communicate w/navX-MXP via the MXP SPI Bus.                                     */
			/* Alternatively:  I2C.Port.kMXP, SerialPort.Port.kMXP or SerialPort.Port.kUSB     */
			/* See http://navx-mxp.kauailabs.com/guidance/selecting-an-interface/ for details. */
			ahrs = new AHRS(SPI.Port.kMXP); 
		} catch (RuntimeException ex ) {
			DriverStation.reportError("Error instantiating navX-MXP:  " + ex.getMessage(), true);
		}
	}

	public ColorMatchResult matchClosestColor(Object detectedColor) {
		return null;
	}
	
}