import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;




public class BallBuster2Main {

	private static NXTRegulatedMotor buster = Motor.A;
	private static UltrasonicSensor u1 = new UltrasonicSensor(SensorPort.S1);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		while(!Button.ESCAPE.isDown())
		{
			if(u1.getDistance() < 20)
			{
				hitBall();
			}
		}
		
	}
	private static void hitBall() {
		while(buster.getPosition() < 90)
		{
			buster.forward();
		}
		while(buster.getPosition() < 0)
		{
			buster.backward();
		}
		
	}

}
