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
		// Add
		kicker.setSpeed(800);
		ramp.setSpeed(400);
	}
	
	public void run() {
		ramp.resetTachoCount();
		while (isRunning){
			if(detected) { 
				detected = false;
				ramp.rotateTo(degree);
				kicker.rotate(180);
				try {
					this.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				ramp.rotateTo(0);
				try {
					this.sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
//			if (ramp.getTachoCount() < degree)
//				ramp.forward();
//			else if (ramp.getTachoCount() > degree)
//				ramp.backward();
		}
		
		ramp.stop();
		ramp.rotateTo(0);
	}

	
	public void setDegree(int deg) {
		if(Math.abs(deg) < 200) {
			degree = deg;
			detected = true;
		}
		else {
			degree = 0;
			detected = false;
		}
	}
}
