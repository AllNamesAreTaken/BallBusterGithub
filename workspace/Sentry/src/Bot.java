import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.nxt.remote.RemoteMotor;
import lejos.robotics.RegulatedMotor;

public class Bot extends Thread{

	RegulatedMotor kicker;
	RegulatedMotor ramp;
	int degree = 0;
	boolean isRunning = true;
	boolean detected = false;

	public Bot(RegulatedMotor x, RegulatedMotor y) {
		kicker = x;
		ramp = y;
		kicker.setSpeed(600);
		ramp.setSpeed(400);
	}
	
	public void run() {
		while (isRunning){
			if(detected) { 
				detected = false;
				ramp.rotateTo(degree);
				kicker.rotate(180);
				ramp.rotateTo(0);
//				continue;
			}
//			if (ramp.getTachoCount() < degree)
//				ramp.forward();
//			else if (ramp.getTachoCount() > degree)
//				ramp.backward();
		}
		ramp.stop();
		ramp.rotateTo(0);
	}
	
	public boolean isRunning() {
		return isRunning;
	}
	
	public void stopBot() {
		isRunning = false;
	}

	public void setDegree(int deg) {
		if(Math.abs(deg) < 400) {
			detected = true;
			degree = deg;
		}
		else {
			detected = false;
			degree = 0;
		}
	}

	public void resetRamp() {
		ramp.rotateTo(0);
	}

	public void resetTachoCount() {
		Motor.B.resetTachoCount();
	}
}
