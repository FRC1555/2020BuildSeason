/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * An example command.  You can replace me with your own command.
 */
public class PIDControl extends Command {
    
    //Initializing class variable
    // SP = Set Point, target trying to be achieved by the PID system
    public int SP;
    // PV = Process Variable, current position of object
    public int PV;
    // Position error
    int Error;
    // Gain constants for the proportional, integral, and derivative functions
    // Gain constant- multiplied by the calculation in each case to get the final values
    public double Kp;
    public double Ki;
    public double Kd;
    // Store the actual calculations of each of the three functions 
    double P;
    double I;
    double D;
    // List to house the Error values gathered
    List ErrorY = new ArrayList();
    // List to house the x values for the integral graph
    List TimeX = new ArrayList();
    // Establishes a new Timer to allow for the collection of timestamps acting as x values
    Timer Time; 
    // Number of individual subintervals involved with the larger interval between o and t
    int N;
    // 
    double X;
    // Denotes the summation of all right Riemann sums taken
    double R;
    
    

    public PIDControl() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.kExampleSubsystem);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
        ErrorY.clear();
        TimeX.clear();
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
        Error = SP - PV;
        P = Proportional();
        I = Integral();
    }
    
    protected double Proportional() {
        return Kp * Error;
    }

    protected double Integral() {
        N = TimeX.size();
        R = 0;
        ErrorY.add(0, 0);
        TimeX.add(0, 0);
        ErrorY.add(-1, Error);
        TimeX.add(-1, Time.get());     
        
        // Possibly not necessary for the equation
        //X = ((Integer) TimeX.get(-1) - (Integer) TimeX.get(0)) / N;

        for (int i = 1; i <= N; i++) {
            R += (Integer) ErrorY.get(i) * ((Integer) TimeX.get(i) - (Integer) TimeX.get(i-1));
        }

        return R;
        
    }

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}
