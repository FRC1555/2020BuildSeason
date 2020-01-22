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


/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class ColorSensor extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
    public RobotMap map = Robot.map;
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
    }
    //gives the red value detected from the color sensor
    public int red(){
        return map.colourSensor.getRed();
    }
    //gives the red value detected from the color sensor
    public int green(){
        return map.colourSensor.getGreen();
    }
    //gives the red value detected from the color sensor
    public int blue(){
            return map.colourSensor.getBlue();
    }
    //gives the red value detected from the color sensor
    public int ir(){
        return map.colourSensor.getIR();
    }
    //gives the red value detected from the color sensor
    public int proximity(){
        return map.colourSensor.getProximity();
    }
}
