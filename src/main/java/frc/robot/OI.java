/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.GrabRun;
//import frc.robot.commands.LoadBall;
import frc.robot.commands.LoadBallTimed;
import frc.robot.commands.UnloadBall;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);

  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());
  XboxController driverController = new XboxController(RobotMap.DRIVER_CONTROLLER);
	XboxController operatorController = new XboxController(RobotMap.OPERATOR_CONTROLLER);
	
	Button dButtonA = new JoystickButton(this.driverController, RobotMap.BUTTON_A);
	Button dButtonB = new JoystickButton(this.driverController, RobotMap.BUTTON_B);
	Button dButtonX = new JoystickButton(this.driverController, RobotMap.BUTTON_X);
	Button dButtonY = new JoystickButton(this.driverController, RobotMap.BUTTON_Y);
	Button dButtonBack = new JoystickButton(this.driverController, RobotMap.BACK_BUTTON);
	Button dButtonStart = new JoystickButton(this.driverController, RobotMap.START_BUTTON);
	
	Button oButtonA = new JoystickButton(this.operatorController, RobotMap.BUTTON_A);
	Button oButtonB = new JoystickButton(this.operatorController, RobotMap.BUTTON_B);
	Button oButtonY = new JoystickButton(this.operatorController, RobotMap.BUTTON_Y);
	Button oButtonX = new JoystickButton(this.operatorController, RobotMap.BUTTON_X);
	Button oButtonBack = new JoystickButton(this.operatorController, RobotMap.BACK_BUTTON);
	Button oButtonStart = new JoystickButton(this.operatorController, RobotMap.START_BUTTON);
  	Button oButtonRightStick = new JoystickButton(this.operatorController, RobotMap.RIGHT_STICK_BUTTON);
	Button oButtonRightBumper = new JoystickButton(this.operatorController, RobotMap.RIGHT_BUMPER);
	Button oButtonLeftBumper = new JoystickButton(this.operatorController, RobotMap.LEFT_BUMPER);

  	public boolean getOperatorButton(int axis) {
		return this.operatorController.getRawButton(axis);
	}
	
	public boolean getDriverButton(int axis) {
		return this.driverController.getRawButton(axis);
	}
	
	public double getOperatorRawAxis(int axis) {
		return this.operatorController.getRawAxis(axis);
	}
	
	public double getDriverRawAxis(int axis) {
		return this.driverController.getRawAxis(axis);
	}
	
	public int getOperatorPOV(){
		return this.operatorController.getPOV();
	}

	public OI () {
		this.driverController.setRumble(RumbleType.kRightRumble, 0);
		this.driverController.setRumble(RumbleType.kLeftRumble, 0);
		this.operatorController.setRumble(RumbleType.kRightRumble, 0);
		this.operatorController.setRumble(RumbleType.kLeftRumble, 0);

		//this.oButtonA.whenPressed(new SetElevator(RobotMap.ELEVATOR_LOW));
		this.oButtonA.whileHeld(new UnloadBall()); 
		this.oButtonX.whenPressed(new LoadBallTimed()); 
		this.oButtonY.whileHeld(new GrabRun()); 
		//this.oButtonB.whileHeld(new GrabRun(false));
		//this.oButtonStart.whenPressed(new TurnToAngle(-45));
		//this.oButtonStart.whenPressed(new SetLight(true));
		//this.oButtonBack.whenPressed(new SetLight(false));
		//this.oButtonX.whenPressed(new TurnToAngle(Sensors.ballReqester.getAngle()));
		//this.oButtonBack.whileHeld(new IncrementElevator(-RobotMap.ELEVATOR_INCREMENT));
		//this.oButtonStart.whileHeld(new IncrementElevator(RobotMap.ELEVATOR_INCREMENT));
		//this.oButtonBack.whenPressed(new SetElevatorSmartDashboard());

		//this.oButtonLeftBumper.whenPressed(new ToggleClaw());
		//this.oButtonRightBumper.whenPressed(new ToggleClaw());

		//this.dButtonA.whenPressed(new GoToStrip());
		//this.dButtonX.whenPressed(new DriveForward(30));
		//this.dButtonA.whenPressed(new SetFrontClimber(RobotMap.CLIMBER_GROUND, RobotMap.CLIMBER_GROUND_SLOT));
		//this.dButtonX.whenPressed(new SetBackClimber(RobotMap.CLIMBER_GROUND, RobotMap.CLIMBER_GROUND_SLOT));
		//this.dButtonB.whenPressed(new SetFrontClimber(RobotMap.CLIMBER_LIFT, RobotMap.CLIMBER_GROUND_SLOT));
		//this.dButtonY.whenPressed(new SetBackClimber(RobotMap.CLIMBER_LIFT, RobotMap.CLIMBER_GROUND_SLOT));
		//this.dButtonBack.whenPressed(new ZeroClimber(Dart.FRONT));
		//this.dButtonBack.whenPressed(new ZeroBothClimbers());
	}
}
