import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.nxt.NXT;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;




public class BallBusterMain {

	private static NXTRegulatedMotor buster = Motor.A;
	private static NXTRegulatedMotor launcher = Motor.B;
	private static UltrasonicSensor u1 = new UltrasonicSensor(SensorPort.S1);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		while(!Button.ESCAPE.isDown())
		{
			if(u1.getDistance() < 20)
			{
				hitBall();
			}
			if(Button.LEFT.isDown())
			{
				launchBall();
			}
		}
		
	}
	private static void launchBall() {
		launcher.setSpeed(25);
		launcher.forward();
		
	}
	private static void hitBall() {
		launcher.stop();
		int mode = u1.getMode();
		u1.setMode(u1.off());
		while(buster.getPosition() < 90)
		{
			buster.setSpeed(800);
			buster.forward();
		}
		while(buster.getPosition() != 0)
		{
			buster.setSpeed(100);
			if(buster.getPosition() > 0)
				buster.backward();
			if(buster.getPosition() < 0)
				buster.forward();
		}
		buster.stop();
		u1.setMode(mode);
		
	}

}
