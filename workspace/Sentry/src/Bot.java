import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.nxt.remote.RemoteMotor;
import lejos.robotics.RegulatedMotor;
public class Bot {
	
	RegulatedMotor kicker;
	RegulatedMotor ramp;
	
	public Bot(RegulatedMotor x, RegulatedMotor y)
	{
		kicker = x;
		ramp = y;
		kicker.setSpeed(800);
		ramp.setSpeed(50);
	}
	
	public void hit(int degree) 
	{
		long startTime = System.currentTimeMillis();
		while(ramp.getTachoCount() != degree)
		{
			if(ramp.getTachoCount() < degree)
				ramp.forward();
			if(ramp.getTachoCount() > degree)
				ramp.backward();
			System.out.println(ramp.getTachoCount());
		}
		ramp.stop();
//		ramp.rotateTo(degree);
		kicker.rotate(180);
		long endTime = System.currentTimeMillis();
		System.out.println("Time to rotate to " + degree + ": " + (endTime-startTime) + "\n");
		resetRamp();
		// TODO Auto-generated method stub
		
	}
	
	public void resetRamp()
	{
		long startTime = System.currentTimeMillis();
		ramp.rotateTo(0);
		long endTime = System.currentTimeMillis();
		System.out.println("Time to reset: " + (endTime-startTime) + "\n");
	}

}
