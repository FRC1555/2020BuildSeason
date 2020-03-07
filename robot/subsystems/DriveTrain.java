package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;

/**
 *
 */
public class DriveTrain extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    //Declaring motors
    public static Victor L = Robot.map.leftDrive;
    public static Victor R = Robot.map.rightDrive;
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    //Stops the drive train
    public void stop() {
    	Robot.map.leftDrive.stopMotor();
        Robot.map.rightDrive.stopMotor();
    }
    
    //Drives the robot with two separate powers for the wheels
    public void driveTank(double Lspeed, double Rspeed) {
    	Robot.map.leftDrive.set(-Lspeed);
    	Robot.map.rightDrive.set(Rspeed);
    }

    //Drives the robot as if the front were the back
    public void driveTankInverted(double Lspeed, double Rspeed) {
    	Robot.map.leftDrive.set(Rspeed);
    	Robot.map.rightDrive.set(-Lspeed);
    }
    
    //Drives straight
    public void driveStraight(double speed) {
    	Robot.map.leftDrive.set(-speed);
    	Robot.map.rightDrive.set(speed);
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

