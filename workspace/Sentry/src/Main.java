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

	static ImageDetection imgd;

	public static void main(String[] args) {
		PrintWriter logger = null;
		Bot robot = new Bot(Motor.A, Motor.B);
		imgd = new ImageDetection();

		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

		try {
			logger = new PrintWriter(new BufferedWriter(new FileWriter(
					"LogData.csv", true)));
		} catch (IOException ex) {
			System.out.println("Logging file not found");
		}

		long startTime = 0;
		long endTime = 1;
		
		imgd.startDet();
		imgd.start();

		robot.start();
		while (!Button.ESCAPE.isDown()) {
			startTime = System.nanoTime();
			double[] redBallPosition = imgd.getRedBall();
			robot.setDegree((int) (400* Math.atan2(
					300 - redBallPosition[1], redBallPosition[0])));
			endTime = System.nanoTime();

			if (logger != null) {
				logger.append((endTime - startTime) + ",");
			}
		}

		robot.isRunning = false;
		logger.close();
		imgd.stopDet();
	}

}
