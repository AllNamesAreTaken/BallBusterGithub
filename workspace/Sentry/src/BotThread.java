import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.nxt.remote.RemoteMotor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.MoveController;
import lejos.robotics.navigation.Navigator;


public class BotThread extends Thread {
	//Test Commit
	public void run()
	{
		Motor.A.setSpeed(800);
		Motor.B.setSpeed(50);
		Motor.B.forward();
		Motor.B.resetTachoCount();
		//Turner turn = new Turner();
		//turn.start();
		while(!Button.ESCAPE.isDown())
		{
			//turn.run();
			if(Motor.B.getTachoCount() > 50)
			{
				Motor.B.backward();
			}
			if(Motor.B.getTachoCount() < -50)
			{
				Motor.B.forward();
			}
			while(!Button.ENTER.isDown())
			{
				
				if(this.isInterrupted())
				{
					break;
				}
//				Motor.B.rotateTo(25);
//				Motor.B.rotateTo(-25);
			}
			//if(Button.LEFT.isDown())
			{
//				turn.interrupt();
				if(!this.isInterrupted())
				{
					Motor.A.rotate(360);
					//count++;
				}
				else
				{
					break;
				}
//				turn.run();
			}
		}
		System.out.println("quitting");
		Motor.A.stop();
		Motor.A.flt(false);
	}
	
	
}
