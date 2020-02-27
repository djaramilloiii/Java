/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;
  //remote values 
	public static final int DRIVER_CONTROLLER = 0;
	public static final int OPERATOR_CONTROLLER = 1;

	public static final int BUTTON_A = 1;
	public static final int BUTTON_B = 2;
	public static final int BUTTON_X = 3;
	public static final int BUTTON_Y = 4;
	public static final int LEFT_BUMPER = 5;
	public static final int RIGHT_BUMPER = 6;
	public static final int BACK_BUTTON = 7;
	public static final int START_BUTTON = 8;
	public static final int LEFT_STICK_BUTTON = 9;
  public static final int RIGHT_STICK_BUTTON = 10;
  
  public static final int DPAD_UP = 0;
  public static final int DPAD_RIGHT = 90;
  public static final int DPAD_DOWN = 180;
  public static final int DPAD_LEFT = 270;
	
	public static final int LEFT_STICK_X = 0;
	public static final int LEFT_STICK_Y = 1;
	public static final int RIGHT_STICK_Y = 5;
	public static final int RIGHT_STICK_X = 4;
	
	public static final int LEFT_TRIGGER = 2;
	public static final int RIGHT_TRIGGER = 3;
  	//end of remote values

  //motors, solenoids and other
  public static enum MOTORS{
    RIGHT_MOTOR_2, //TalonFX CAN ID 0
    RIGHT_MOTOR_1, //TalonFX CAN ID 1
    LEFT_MOTOR_1, //TalonFX CAN ID 2
    LEFT_MOTOR_2, //TalonFX CAN ID 3
    GRAB_MOTOR_1, //VictorSPX CAN ID 4
    ELEV_LIFT_1, //VictorSPX CAN ID 5
    ELEV_LIFT_2, //VivtorSPX CAN ID 6
    SHOOT_MOTOR_1, //VictorSPX CAN ID 7
    CLIMB_MOTOR_1,//VictorSPX CAN ID 8
    CLIMB_MOTOR_2, //VictorSPX CAN ID 9
    TURRET_MOTOR_1, //CANSparkMax CAN ID 10
    

  }

  public static final double MAX_SPEED = 0.95; // 0-1, max speed
  public static final double MAX_SPEED_ADRIVE = 0.2; // 0-1, max speed
  public static final double MAX_SPEED_LIFT1 = .4; //0-1 max speed
  public static final double MAX_SPEED_LIFT2 = .4; //0-1 max speed
  public static final double MAX_SPEED_GRAB = .6; //0-1 max speed
  public static final double MAX_SPEED_TURRET = .5; //0-1 max speed
  public static final double MAX_SPEED_SHOOT = .95; //0-1 max speed
  public static final double MAX_SPEED_CLIMB = .6; //0-1 max speed
  public static final double RAMP_RATE = .3; //time in seconds for the motor to reach max speed
  public static final double TURNING_RATE = 0.3;
  public static final double AUTODRIVE_LENGTH = 1; //time in seconds to drive robot in autodrive
}
