/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
//import frc.robot.RobotMap;
import frc.robot.subsystems.DriveTrain;

public class AutoDrive extends Command {
  //private double length;
  private final double kDriveTick2Feet = 1.0 / 1020 * 6 * Math.PI / 12;
  double leftLength = 0;
  double rightLength = 0;
  final double kP = 0.2;

  public AutoDrive(double x, double y) {
    
    rightLength = x;  
    leftLength = y;
    super.requires(Robot.driveTrain);
     /* // Use requires() here to declare subsystem dependencies // eg.
     * requires(chassis); setTimeout(length);
     */
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    
    DriveTrain.leftMotor1.setSelectedSensorPosition(0);
    DriveTrain.rightMotor1.setSelectedSensorPosition(0);
    //Robot.driveTrain.backwards();

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    //setpoint = 10;

    double sensorPosition = DriveTrain.leftMotor1.getSelectedSensorPosition() * kDriveTick2Feet;
    double errorLeft = leftLength - sensorPosition;
    double errorRight = rightLength - sensorPosition;
    double outputSpeedLeft = kP * errorLeft;
    double outputSpeedRight = kP * errorRight;

    Robot.driveTrain.setLeftMotor(outputSpeedLeft);
    Robot.driveTrain.setRightMotor(outputSpeedRight);
    
      
   
}

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return isTimedOut();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.driveTrain.stop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
