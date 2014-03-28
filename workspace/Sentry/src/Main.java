import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.nxt.remote.RemoteMotor;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			BotThread bt = new BotThread();
			Turner turn = new Turner();
			bt.start();
			//turn.start();
			
			while(!Button.ESCAPE.isDown())
			{
				if(bt.isInterrupted())
					bt.run();
//				if(turn.isInterrupted())
//					turn.run();
			}
	}

}
