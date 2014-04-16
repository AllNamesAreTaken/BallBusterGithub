import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.nxt.remote.RemoteMotor;
import lejos.util.*;
import lejos.pc.charting.*;

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

//	static Bot robot = new Bot(Motor.A, Motor.B);
	static ImageDetection imgd;

	public static void main(String[] args) {
		PrintWriter logger = null;
		imgd = new ImageDetection();

		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

		try {
			logger = new PrintWriter(new BufferedWriter(new FileWriter("LogData.csv", true)));
		} catch (IOException ex) {
			System.out.println("Logging file not found");
		}


//		Motor.A.setSpeed(800);
//		Motor.B.setSpeed(50);
//		Motor.B.resetTachoCount();
		Boolean quit = false;
		long startTime = 0;
		long endTime = 1;
		int count = 0;
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd|HH:mm:ss");
		//get current date time with Date()
		Date date = new Date();
		logger.append("\n<" + /*dateFormat.format(date)*/"DWG" + ">,");

		imgd.startDet();
		imgd.start();
		while (!Button.ESCAPE.isDown()) {
			startTime = System.currentTimeMillis();
			double[] test = imgd.getRedBall();
			System.out.println(test[0] + " " + test[1]);
			if (test[0] > 0) {
//				robot.hit(20);
			}
			endTime = System.currentTimeMillis();
			
//			if (logger != null) {
//				logger.append((endTime - startTime) + ",");
//			}
//			count++;
//			System.out.println(count + " ");
		}

		logger.close();
		imgd.stopDet();
		System.exit(0);
	}

}
