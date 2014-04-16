import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.nxt.remote.RemoteMotor;
import lejos.robotics.RegulatedMotor;

public class Bot extends Thread{

	RegulatedMotor kicker;
	RegulatedMotor ramp;
	int degree = 0;
	private boolean isRunning;
	private boolean willHit;

	public Bot(RegulatedMotor x, RegulatedMotor y) {
		kicker = x;
		ramp = y;
		setSpeeds(800, 50);
	}
	
	public void run() {
		isRunning = true;
		while(isRunning) {
			aim();
			isRunning = !Button.ESCAPE.isDown();
		}
	}
	
	public boolean isStopping() {
		return isRunning;
	}
	
	public void setSpeeds(int motA, int motB) {
		kicker.setSpeed(A);
		ramp.setSpeed(B);
	}

	public void setDegree(int deg) {
		if (deg != 200 || Math.abs(deg) <= 30) {
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
//		while (ramp.getTachoCount() != degree) {
			if (ramp.getTachoCount() < degree)
				ramp.forward();
			if (ramp.getTachoCount() > degree)
				ramp.backward();
//			System.out.println(ramp.getTachoCount());
//		}
		if(willHit) {
			ramp.stop();
			// ramp.rotateTo(degree);
			kicker.rotate(180);
			willHit = false;
		}
//		long endTime = System.currentTimeMillis();
//		System.out.println("Time to rotate to " + degree + ": "
//				+ (endTime - startTime) + "\n");
		resetRamp();
		// TODO Auto-generated method stub

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
