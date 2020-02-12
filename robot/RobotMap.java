/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.CAN;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;
import com.revrobotics.ColorSensorV3;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ColorMatch;



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
	int Lmotor = 0;
	int Rmotor = 1;
	
	//Shooter motors - CAN
	//Intake
	//Intake lift
	//Climb lift
	//Balancer
	//CPI lift
	//CPI spinner

	public CANSparkMax shooter1;
	public CANSparkMax shooter2;
	int shooter1Index = 0;
	int shooter2Index = 1;

	public static Talon intakeMotor;
	public static Talon liftMotor;
	int intake = 2;
	int intakeLift = 3;
	//The hatch panel grabbers are currently run off two ports, although we should be able to change it to one in the future
	int hatchSlapper1 = 4;
	int hatchSlapper2 = 5;
	
	//Transfer the build over to here
	public static int solenoidP1 = 0;
	public static int solenoidP2 = 1;
	
	public static Encoder encDriveL;
	public static Encoder encDriveR;
	public static Encoder encLiftCPI;
	public static Encoder encLiftClimber;
	int encDriveL1 = 0;
	int encDriveL2 = 1;
	int encDriveR1 = 2;
	int encDriveR2 = 3;
	int encLiftCPI1 = 4;
	int encLiftCPI2 = 5;
	int encLiftClimber1 = 6;
	int encLiftClimber2 = 7;

	public static CANEncoder encShooterA;
	public static CANEncoder encShooterB;
	
	public static String limeLightKey = "limelight";
	
	public static Talon hatchMotor;
	public static Talon slapMotor;

	public ColorSensorV3 colourSensor;
	private final I2C.Port i2cPort = I2C.Port.kOnboard;

	public static ColorMatch colourMatch;

	//Initalizes all the hardware
	public void mapAll() {
		//Motor controllers
		leftMotor = new Victor(Lmotor);
		rightMotor = new Victor(Rmotor);
		intakeMotor = new Talon(intake);
		liftMotor = new Talon(intakeLift);
		hatchMotor = new Talon(hatchSlapper1);
		slapMotor = new Talon(hatchSlapper2);
		shooter1 = new CANSparkMax(shooter1Index, MotorType.kBrushless);
		shooter2 = new CANSparkMax(shooter2Index, MotorType.kBrushless);
		//Misc sensors
		colourSensor = new ColorSensorV3(i2cPort);
		colourMatch = new ColorMatch();
		//Encoders
		encDriveL = new Encoder(encDriveL1, encDriveL2);
		encDriveR = new Encoder(encDriveR1, encDriveR2);
		encLiftCPI = new Encoder(encLiftCPI1, encLiftCPI2);
		encLiftClimber = new Encoder(encLiftClimber1, encLiftClimber2);
		encShooterA = shooter1.getEncoder();
		encShooterB = shooter2.getEncoder();
	}

	public ColorMatchResult matchClosestColor(Object detectedColor) {
		return null;
	}
	
}