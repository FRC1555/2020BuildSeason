/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;

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
	
	//Declaring the motor controllers
	public CANSparkMax shooter1;
	public CANSparkMax shooter2;
	public Victor leftDrive;
	public Victor rightDrive;
	public Victor shooterLift;
	public Victor climber;
	public Victor balancer;
	public Victor CPILift;
	public Talon CPISpinner;

	//Declaring the indexes of the motor controllers
	//The shooters are part of the CAN bus, which puts them on a separate index list
	int shooter1Index = 0;
	int shooter2Index = 1;
	int leftDriveIndex = 0;
	int rightDriveIndex = 1;
	int shooterLiftIndex = 2;
	int climberIndex = 3;
	int balancerIndex = 4;
	int CPILiftIndex = 5;
	int CPISpinnerIndex = 6;

	//Declaring the solenoids and their indexes
	public DoubleSolenoid exampleSolenoid;
	int solenoidP1 = 0;
	int solenoidP2 = 1;
	
	//Declaring encoders
	public CANEncoder encShooterA;
	public CANEncoder encShooterB;
	public Encoder encDriveL;
	public Encoder encDriveR;
	public Encoder encLiftCPI;
	public Encoder encLiftClimber;

	//Declaring encoder indexes
	//Each encoder needs two ports on the digital IO
	//CAN encoders don't need any
	int encDriveL1 = 0;
	int encDriveL2 = 1;
	int encDriveR1 = 2;
	int encDriveR2 = 3;
	int encLiftCPI1 = 4;
	int encLiftCPI2 = 5;
	int encLiftClimber1 = 6;
	int encLiftClimber2 = 7;	
	
	//Declaring limelight key
	// TODO: discover whether this is needed or not
	public static String limeLightKey = "limelight";

	//Declaring the color sensor and its dependants
	public ColorSensorV3 colourSensor;
	private final I2C.Port i2cPort = I2C.Port.kOnboard;
	public ColorMatch colourMatch;

	//Declaring the navX
	public AHRS ahrs;
  
	//Initalizes all the hardware
	public void mapAll() {
		//Motor controllers
		//Initalizing the CAN devices causes errors while they aren't attached
		// shooter1 = new CANSparkMax(shooter1Index, MotorType.kBrushless);
		// shooter2 = new CANSparkMax(shooter2Index, MotorType.kBrushless);
		leftDrive = new Victor(leftDriveIndex);
		rightDrive = new Victor(rightDriveIndex);
		shooterLift = new Victor(shooterLiftIndex);
		climber = new Victor(climberIndex);
		balancer = new Victor(balancerIndex);
		CPILift = new Victor(CPILiftIndex);
    	CPISpinner = new Talon(CPISpinnerIndex);
		
    	//Misc sensors
		colourSensor = new ColorSensorV3(i2cPort);
		colourMatch = new ColorMatch();
    	try {

			/* Communicate w/navX-MXP via the MXP SPI Bus.                                     */
			/* Alternatively:  I2C.Port.kMXP, SerialPort.Port.kMXP or SerialPort.Port.kUSB     */
			/* See http://navx-mxp.kauailabs.com/guidance/selecting-an-interface/ for details. */
			ahrs = new AHRS(SPI.Port.kMXP); 
		} 
		catch (RuntimeException ex) {
			DriverStation.reportError("Error instantiating navX-MXP:  " + ex.getMessage(), true);
		}

		//Encoders
		encDriveL = new Encoder(encDriveL1, encDriveL2);
		encDriveR = new Encoder(encDriveR1, encDriveR2);
		encLiftCPI = new Encoder(encLiftCPI1, encLiftCPI2);
		encLiftClimber = new Encoder(encLiftClimber1, encLiftClimber2);
		// encShooterA = shooter1.getEncoder();
		// encShooterB = shooter2.getEncoder();

		//Solenoids
		exampleSolenoid = new DoubleSolenoid(solenoidP1, solenoidP2);
		
	}

}
