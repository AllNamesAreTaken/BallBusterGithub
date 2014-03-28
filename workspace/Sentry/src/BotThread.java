import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.nxt.remote.RemoteMotor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.MoveController;
import lejos.robotics.navigation.Navigator;


public class BotThread extends Thread {

	public void run()
	{
		int count = 0;
		Motor.A.setSpeed(800);
		//MoveController MC = new MoveController(Motor.A, Motor.B);
		//DifferentialPilot pilot = new DifferentialPilot(5.6,14, Motor.A, Motor.C);
		//Navigator n = new Navigator(pilot);
		while(!Button.ESCAPE.isDown())
		{
			while(!Button.ENTER.isDown())
			{
				if(this.isInterrupted())
				{
					break;
				}
			}
			//if(Button.LEFT.isDown())
			{
				if(!this.isInterrupted())
				{
					Motor.A.rotate(360);
					//count++;
				}
				else
				{
					break;
				}
			}
		}
		System.out.println("quitting");
		Motor.A.stop();
		Motor.A.flt(false);
	}
	
	
}
