import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.nxt.remote.RemoteMotor;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Motor.A.setSpeed(800);
		Motor.B.setSpeed(50);
		Motor.B.resetTachoCount();
		while(!Button.ESCAPE.isDown())
		{
			Motor.B.forward();
			while(!Button.ENTER.isDown())
			{
				if(Motor.B.getTachoCount() > 20)
				{
					Motor.B.backward();
				}
				if(Motor.B.getTachoCount() < -20)
				{
					Motor.B.forward();
				}
			}
			Motor.B.stop();
			Motor.A.rotate(360);
//			break;
			try {
				Thread.sleep(5);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		System.out.println("quitting");
		Motor.A.stop();
		Motor.A.flt(false);
		
//			BotThread bt = new BotThread();
////			Turner turn = new Turner();
//			bt.start();
////			turn.start();
//			
//			while(!Button.ESCAPE.isDown())
//			{
//				if(bt.isInterrupted())
//					bt.run();
////				if(turn.run();
//			}
	}

}
