import lejos.nxt.Button;
import lejos.nxt.Motor;


public class Turner extends Thread{

	public void run()
	{
		Motor.B.setSpeed(50);
		{
			turnBot(45);
			turnBot(-45);
		}
		
	}
	private static void turnBot(int angle)
	{
		Motor.B.rotateTo(angle);
	}
}
