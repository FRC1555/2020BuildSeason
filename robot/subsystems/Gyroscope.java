/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import frc.robot.Robot;
import frc.robot.RobotMap;

public class Gyroscope extends Subsystem {
	// Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    public RobotMap map = Robot.map;

    public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
    }

    // Obtains the angle of the gyroscope
    public double angle() {
        return map.gyro.getAngle();
    }

    // Obtains the angular velocity of the gyroscope
    public double rate() {
        return map.gyro.getRate();
    }

    // Sets the current orientation of the gyroscope to 0 degrees
    public void reset() {
        map.gyro.reset();
    }
    
    // Calibrates the gyro by running for a number of samples and computing the center value
    public void calibrate() {
        map.gyro.calibrate();
    }

}
