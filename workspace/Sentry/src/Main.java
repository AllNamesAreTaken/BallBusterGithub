import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.nxt.remote.RemoteMotor;
import lejos.util.*;

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
//	static NXTDataLogger logger = new NXTDataLogger();
//    static LogColumn[] columnDefs = { new LogColumn("DinosaursX",
//            LogColumn.DT_DOUBLE), new LogColumn("Dinosaursy",
//                    LogColumn.DT_DOUBLE) };
//	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
//		Don't uncomment if static class is
//		Bot robot = new Bot(Motor.A, Motor.B);
//		robot.resetRamp();
		
		ImageDetection imgd = new ImageDetection();
		imgd.startCamera();
		
		Motor.A.setSpeed(800);
		Motor.B.setSpeed(50);
		Motor.B.resetTachoCount();
		Boolean quit = false;
		long startTime = 0;
		double endTime = 1;
		while(!Button.ESCAPE.isDown())
		{
			startTime = System.currentTimeMillis();
			double[] test = imgd.getRedBall();
			System.out.println(test[0] + " " + test[1]);
			if(test[0] > 0)
			{
				robot.hit(20);
			}
			endTime = System.currentTimeMillis();
			
		}
		
//			Motor.B.forward();
//			
//			while(!Button.ENTER.isDown())
//			{
//				double[] test = imgd.getRedBall();
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
//		
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
			imgd.stopCamera();
			System.exit(0);
	}

}
