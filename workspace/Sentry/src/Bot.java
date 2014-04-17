import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.nxt.remote.RemoteMotor;
import lejos.robotics.RegulatedMotor;

public class Bot extends Thread{

	RegulatedMotor kicker;
	RegulatedMotor ramp;
	int degree = 0;
	private boolean isRunning = true;
	private boolean willHit;

	public Bot(RegulatedMotor x, RegulatedMotor y) {
		kicker = x;
		ramp = y;
		setSpeeds(800, 50);
	}
	
	public void run() {
//		isRunning = true;
		while (isRunning/*ramp.getTachoCount() != degree*/) {
			if (ramp.getTachoCount() < degree + 1)
				ramp.forward();
			else if (ramp.getTachoCount() > degree - 1)
				ramp.backward();
			else
			{
				ramp.stop();
				// ramp.rotateTo(degree);
				kicker.rotate(180);
			}
//			System.out.println(ramp.getTachoCount());
		}
		ramp.stop();
		resetRamp();
//			isRunning = !Button.ESCAPE.isDown();
	}
	
	public boolean isRunning() {
		return isRunning;
	}
	
	public void stopBot() {
		isRunning = false;
	}
	
	public void setSpeeds(int motA, int motB) {
		kicker.setSpeed(motA);
		ramp.setSpeed(motB);
	}

	public void setDegree(int deg) {
		if (Math.abs(deg) <= 40) {
			degree = deg;
			return;
		}
		degree = 0;
	}
	
	public void hit() {
		willHit = true;
	}

	public void aim() {
//		long startTime = System.currentTimeMillis();
		//tachoCount returns int
			
//		if(willHit) {
//			ramp.stop();
//			// ramp.rotateTo(degree);
//			kicker.rotate(180);
//			willHit = false;
//		}
			
//		long endTime = System.currentTimeMillis();
//		System.out.println("Time to rotate to " + degree + ": "
//				+ (endTime - startTime) + "\n");
//		resetRamp();

	}

	public void resetRamp() {
//		long startTime = System.currentTimeMillis();
		ramp.rotateTo(0);
//		long endTime = System.currentTimeMillis();
//		System.out.println("Time to reset: " + (endTime - startTime) + "\n");
	}

	public void resetTachoCount() {
		Motor.B.resetTachoCount();
	}
}
