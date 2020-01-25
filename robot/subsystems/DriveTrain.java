package frc.robot.subsystems;

import frc.robot.RobotMap;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class DriveTrain extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    //Declaring motors
    public static Victor L = robot.map.leftMotor;
    public static Victor R = robot.map.Rightmotor;
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    //Stops the drive train
    public static void stop() {
    	L.stopMotor();
        R.stopMotor();
    }
    
    //Drives the robot with two separate powers for the wheels
    public static void driveTank(double Lspeed, double Rspeed) {
    	L.set(-Lspeed);
    	R.set(Rspeed);
    }
    
    //Drives straight
    public static void driveStraight(double speed) {
    	L.set(-speed);
    	R.set(speed);
    }
    
    //Function pending
    //Will allow you to drive with separate powers for the wheels for a set amount of time
    public static void driveTank(double Lspeed, double Rspeed, long time) {
    	driveTank(Lspeed, Rspeed);
    	//insert wait command here
    	stop();
    }
    
    //Function pending
    //Will allow you to drive straight for a set amount of time
    public static void driveStraight(double speed, double time) {
    	driveStraight(speed);
    	//insert wait command here
    	stop();
    }
}

