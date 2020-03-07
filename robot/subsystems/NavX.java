/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;



//Find out why we can't use this import
//import edu.wpi.first.wpilibj2.PIDController;



public class NavX extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
    AHRS ahrs;
    
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
        // OrientationHistory orientationHistory;
    }

    public void resetNavx(){
        Robot.map.ahrs.reset();
    }
        
    public double Angle() {
        return Robot.map.ahrs.getAngle();
    }
    
    //this is used for steering
    public float getIMUPitch(){
        return Robot.map.ahrs.getPitch();
    }

    // public float getDeltaYaw(){
    //     return Robot.map.ahrs.
    // }

    //Velocities in all three dimensions for the navx
    public double getXVelocity(){
        Float floatXVelocity = Robot.map.ahrs.getVelocityX();
        Double doubleXVelocity = (double) floatXVelocity;
        return doubleXVelocity;
    }

    public double getYVelocity(){
        Float floatYVelocity = Robot.map.ahrs.getVelocityY();
        Double doubleYVelocity = (double) floatYVelocity;
        return doubleYVelocity;
    }
    
    public double getZvelocity(){
        Float floatZVelocity = Robot.map.ahrs.getVelocityZ();
        Double doubleZVelocity = (double) floatZVelocity;
        return doubleZVelocity;
    }
    //resets displacement
    public void resetDisplacement(){
        Robot.map.ahrs.resetDisplacement();
    }

    //Displacements for all thre dimensions
    public double getXDisplacement(){
        Float floatXDisplacement = Robot.map.ahrs.getDisplacementX();
        Double doubleXDisplacement = (double) floatXDisplacement;
        return doubleXDisplacement;
    }

    public double getYDisplacement(){
        Float floatYDisplacement = Robot.map.ahrs.getDisplacementY();
        Double doubleYDisplacement = (double) floatYDisplacement;
        return doubleYDisplacement;
    }

    public double getZDisplacement(){
        Float floatZDisplacement = Robot.map.ahrs.getDisplacementZ();
        Double doubleZDisplacement = (double) floatZDisplacement;
        return doubleZDisplacement;
    }
//acceleration in all 3 dimensions
    public double getXAcceleration(){
        Float floatXAcceleration = Robot.map.ahrs.getRawAccelX();
        Double doubleXAcceleration = (double) floatXAcceleration;
        return doubleXAcceleration;
    }
    public double getYAcceleration(){
        Float floatYAcceleration = Robot.map.ahrs.getRawAccelY();
        Double doubleYAcceleration = (double) floatYAcceleration;
        return doubleYAcceleration;
    }
    public double getZAcceleration(){
        Float floatZAcceleration = Robot.map.ahrs.getRawAccelZ();
        Double doubleZAcceleration = (double) floatZAcceleration;
        return doubleZAcceleration;
    }
}
