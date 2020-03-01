/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Command;

public class ServoPin extends Command {
  public Servo servo = new Servo(9);
  double angle;
	
	/**
	 * This is the constructor which sets inputAngle to the double angle
	 * @param takes in inputAngle
	 */
	public ServoPin(double inputAngle) {
        // Use requires() here to declare subsystem dependencies
        
        
        angle = inputAngle;
    }

    
    // Called just before this Command runs the first time
    protected void initialize() {
      servo.setAngle(0);
    }
    
    /**
     * takes in a double angle for the motor's position and moves motor to said position
     */
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	servo.setAngle(angle);
    }

    /**
     * when the angle of the motor equals the set angle, the command terminates
     */
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        //if (servo.getAngle() == angle)
        //	return true;
        //else
        	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
      servo.setAngle(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
