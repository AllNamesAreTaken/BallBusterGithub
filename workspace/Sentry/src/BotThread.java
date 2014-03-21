import lejos.nxt.Button;


public class BotThread extends Thread {

	public void run()
	{
		while(!Button.ESCAPE.isDown())
		{
			if(!Button.LEFT.isDown())
			{
			System.out.println("in BotThread");
			}
		}
	}
}
