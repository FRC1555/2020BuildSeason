package frc.robot.subsystems;

import frc.robot.RobotMap;
import frc.robot.Robot;

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
    public static Victor L = Robot.map.leftMotor;
    public static Victor R = Robot.map.rightMotor;
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    //Stops the drive train
    public void stop() {
    	Robot.map.leftMotor.stopMotor();
        Robot.map.rightMotor.stopMotor();
    }
    
    //Drives the robot with two separate powers for the wheels
    public void driveTank(double Lspeed, double Rspeed) {
    	Robot.map.leftMotor.set(-Lspeed);
    	Robot.map.rightMotor.set(Rspeed);
    }

    //Drives the robot as if the front were the back
    public void driveTankInverted(double Lspeed, double Rspeed) {
    	Robot.map.leftMotor.set(Rspeed);
    	Robot.map.rightMotor.set(-Lspeed);
    }
    
    //Drives straight
    public void driveStraight(double speed) {
    	Robot.map.leftMotor.set(-speed);
    	Robot.map.rightMotor.set(speed);
    }
    
    //Function pending
    //Will allow you to drive with separate powers for the wheels for a set amount of time
    public void driveTank(double Lspeed, double Rspeed, long time) {
    	driveTank(Lspeed, Rspeed);
    	//insert wait command here
    	stop();
    }
    
    //Function pending
    //Will allow you to drive straight for a set amount of time
    public void driveStraight(double speed, double time) {
    	driveStraight(speed);
    	//insert wait command here
    	stop();
    }
}

