/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class LimeLightAim extends Command {
  private double kpAim = 0.05;
    private double kpDistance = 0.05;
    private double m_moveValue;
    private double m_rotateValue;

  public LimeLightAim() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.driveTrainSlow);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double tx = Robot.driveTrain.gLimeLight().getdegRotationToTarget();
      double ty = Robot.driveTrain.gLimeLight().getdegVerticalToTarget();
      boolean targetFound = Robot.driveTrain.gLimeLight().getIsTargetFound();
  
      if(targetFound){
        m_moveValue = ty * kpDistance;
        m_rotateValue = tx * kpAim;
      }else{
        m_moveValue = 0;
        m_rotateValue = 0;
      }
      Robot.driveTrainSlow.setLeftMotor(m_moveValue + m_rotateValue);
      Robot.driveTrainSlow.setRightMotor(-m_moveValue + m_rotateValue);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.driveTrainSlow.stop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }

 /* private double Estimated_Distance(double a2){
    double h1 = 6.0;
    double h2 = 36.0;
    double a1 = 0.0;
    return (h2-h1)/Math.tan(a1+a2);
  }*/
}
