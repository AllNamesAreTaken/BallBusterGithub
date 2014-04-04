import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import javax.bluetooth.RemoteDevice;

import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.nxt.NXT;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
import lejos.nxt.comm.Bluetooth;
import lejos.nxt.comm.NXTConnection;
import lejos.nxt.comm.RConsole;
import lejos.util.LogColumn;
import lejos.util.NXTDataLogger;

public class BallBusterMain {

	private static NXTRegulatedMotor buster = Motor.A;
	private static NXTRegulatedMotor launcher = Motor.B;
	private static UltrasonicSensor u1 = new UltrasonicSensor(SensorPort.S1);
	private static NXTDataLogger logger;
	private static NXTConnection logConn;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("BALL BUSTER!");
		logger = new NXTDataLogger();
		LogColumn[] columnDefs = new LogColumn[2];
		columnDefs[1] = new LogColumn("No clue", LogColumn.DT_INTEGER);
		logger.setColumns(columnDefs);
		try
		{
		    logger.startRealtimeLog(logConn);
		}
		catch(Exception ex)
		{
		    System.out.println("Logger failed.");
		}
		
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
		logger.writeComment("Button Pressed");
		launcher.setSpeed(25);
		launcher.forward();
		
	}
	private static void hitBall() {
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
		launcher.stop();
		
	}

}
