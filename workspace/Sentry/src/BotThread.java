import lejos.nxt.Button;


public class BotThread extends Thread {

	public void run()
	{
		int count = 0;
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
	}
}
