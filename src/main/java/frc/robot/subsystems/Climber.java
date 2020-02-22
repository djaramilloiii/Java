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
import frc.robot.commands.ClimberManual;

/**
 * Add your docs here.
 */
public class Climber extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private final VictorSPX climbMotor1 = new VictorSPX(RobotMap.MOTORS.CLIMB_MOTOR_1.ordinal());
  private VictorSPX climbMotor2 = null;
  

  public Climber() {
    this.climbMotor2 = new VictorSPX(RobotMap.MOTORS.CLIMB_MOTOR_2.ordinal());
    this.climbMotor2.follow(this.climbMotor1);
    this.climbMotor2.setInverted(false);
      
  }

 

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    super.setDefaultCommand(new ClimberManual());
  }

  public void setClimbMotor1(double motorSetting) {
    motorSetting = Utilities.scale(motorSetting, RobotMap.MAX_SPEED_CLIMB);
    this.climbMotor1.set(ControlMode.PercentOutput, motorSetting); // 2 is following 1
  }
  
  public void setClimbMotor2(double motorSetting) {
    motorSetting = Utilities.scale(motorSetting, RobotMap.MAX_SPEED_CLIMB);
    this.climbMotor2.set(ControlMode.PercentOutput, motorSetting); // 2 is following 1
  }

  public void stop(){
    this.climbMotor1.set(ControlMode.PercentOutput, 0);
    this.climbMotor2.set(ControlMode.PercentOutput, 0);
  }

  public void run(){
    setClimbMotor1(RobotMap.MAX_SPEED_CLIMB);
    setClimbMotor2(RobotMap.MAX_SPEED_CLIMB);
  }
}
