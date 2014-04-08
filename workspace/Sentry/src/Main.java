import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.nxt.remote.RemoteMotor;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;
import org.opencv.highgui.VideoCapture;
import org.opencv.imgproc.Imgproc;


public class Main {

	static Bot robot = new Bot(Motor.A, Motor.B);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ImageDetection.startCamera();
		robot.hit(30);
		robot.resetRamp();
//		Motor.A.setSpeed(800);
//		Motor.B.setSpeed(50);
//		Motor.B.resetTachoCount();
//		Boolean quit = false;
//		while(!Button.ESCAPE.isDown())
//		{
//			Motor.B.forward();
//			
//			while(!Button.ENTER.isDown())
//			{
//				if(Motor.B.getTachoCount() > 20)
//				{
//					Motor.B.backward();
//				}
//				if(Motor.B.getTachoCount() < -20)
//				{
//					Motor.B.forward();
//				}
//				if(Button.ESCAPE.isDown())
//				{
//					quit = true;
//					break;
//				}
//			}
//			if(quit)
//			{
//				break;
//			}
//			Motor.B.stop();
//			long startTime = System.currentTimeMillis();
//			Motor.A.rotate(360);
//			long endTime = System.currentTimeMillis();
//			System.out.println(endTime-startTime);
////			break;
//			try {
//				Thread.sleep(8);
//			} catch (Exception e) {
//				// TODO: handle exception
//			}
//		}
//		System.out.println("quitting");
//		Motor.A.stop();
//		Motor.A.flt();
//		Motor.B.rotateTo(0);
//		Motor.B.stop();
//		Motor.B.flt();
		
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
