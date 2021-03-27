package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.Talon;

public class Robot extends TimedRobot {
  // private RobotMap robotMap;

  private static final int kJoystickChannel = 0;
  private static final int kFrontLeftChannel = 2;
  private static final int kRearLeftChannel = 3;
  private static final int kFrontRightChannel = 1;
  private static final int kRearRightChannel = 0;
  
  private Joystick m_stick;
  private JoystickButton intakeButton;
  private JoystickButton conveyorButton;
  private JoystickButton conveyorButtonReverse;
  private JoystickButton spinnerButton;

  private PWMVictorSPX spinningWheel;
  private MecanumDrive m_robotDrive;
  private Talon conveyorLeft;
  private Talon conveyorRight;
  private Talon spinnerTop;
  private Talon spinnerBottom;

  @Override
  public void robotInit() {
    // robotMap = new RobotMap();
    PWMVictorSPX frontRight = new PWMVictorSPX(kFrontLeftChannel);
    PWMVictorSPX rearRight = new PWMVictorSPX(kRearLeftChannel);
    PWMVictorSPX frontLeft = new PWMVictorSPX(kFrontRightChannel);
    PWMVictorSPX rearLeft = new PWMVictorSPX(kRearRightChannel);
    m_robotDrive = new MecanumDrive(frontLeft, rearLeft, frontRight, rearRight);

    m_stick = new Joystick(kJoystickChannel);
    conveyorButton = new JoystickButton(m_stick, 6);
    conveyorButtonReverse = new JoystickButton(m_stick, 4);
    intakeButton = new JoystickButton(m_stick, 3);
    spinnerButton = new JoystickButton(m_stick, 5);
    spinnerBottom = new Talon(7);
  
    spinningWheel = new PWMVictorSPX(9);
    spinningWheel.setInverted(true);    
    conveyorLeft = new Talon (5);
    conveyorRight = new Talon(6);
    spinnerTop = new Talon(8);
    spinnerTop.setInverted(true);
  }

  @Override
  public void teleopPeriodic() {
    // Use the joystick X axis for lateral movement, Y axis for forward
    // movement, and Z axis for rotation.
    m_robotDrive.driveCartesian(m_stick.getX(), m_stick.getY(), m_stick.getZ(), 0.0);
  
    if(intakeButton.get()) {
      spinningWheel.set(1.0);
    } else {
      spinningWheel.set(0.0);
    }
      
    if(conveyorButton.get()) {
      conveyorLeft.set(1.0);
      conveyorRight.set(1.0);
    } else if (conveyorButtonReverse.get()) {
      conveyorLeft.set(-1.0);
      conveyorRight.set(-1.0);
    } else {
      conveyorLeft.set(0.0);
      conveyorRight.set(0.0);
    }

    if(spinnerButton.get()) {
      spinnerTop.set(1.0);
      spinnerBottom.set(1.0);
    } else {
      spinnerTop.set(0.0);
      spinnerBottom.set(0.0);
    }
  }

  @Override
  public void autonomousInit() {

  }

  @Override
  public void autonomousPeriodic() {

  }
}
