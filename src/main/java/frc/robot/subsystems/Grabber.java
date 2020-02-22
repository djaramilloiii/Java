/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.Utilities;
import frc.robot.commands.GrabRun;

public class Grabber extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private final VictorSPX grabMotor1 = new VictorSPX(RobotMap.MOTORS.GRAB_MOTOR_1.ordinal());
  

  public Grabber() {


  }

  public VictorSPX getGrabMotor1() {
    return grabMotor1;
  }

  

  @Override
  public void initDefaultCommand() {
    
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    super.setDefaultCommand(new GrabRun());
    //super.setDefaultCommand(new stopGrabber());
  }

  public void setGrabMotor1(double motorSetting) {
    motorSetting = Utilities.scale(motorSetting, RobotMap.MAX_SPEED_GRAB);
    this.grabMotor1.set(ControlMode.PercentOutput, motorSetting); //
  }

  public void stopGrabber(){
    this.grabMotor1.set(ControlMode.PercentOutput, 0);
  }
}
