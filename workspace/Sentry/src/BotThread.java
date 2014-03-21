import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.MoveController;
import lejos.robotics.navigation.Navigator;


public class BotThread extends Thread {

	public void run()
	{
		int count = 0;
		//MoveController MC = new MoveController(Motor.A, Motor.B);
		DifferentialPilot pilot = new DifferentialPilot(5.6,14, Motor.A, Motor.C);
		Navigator n = new Navigator(pilot);
		while(!Button.ESCAPE.isDown())
		{
			//if(Button.LEFT.isDown())
			{
				if(!this.isInterrupted())
				{
					System.out.println(count + "\n");
					count++;
				}
				else
				{
					break;
				}
			}
		}
		System.out.println("quitting");
		Motor.A.stop();
	}
}
