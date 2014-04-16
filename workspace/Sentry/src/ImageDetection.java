import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;
import org.opencv.highgui.VideoCapture;
import org.opencv.imgproc.Imgproc;

public class ImageDetection extends Thread {
	private VideoCapture capture;
	private Mat oriImg;
	private Mat hsvImg;
	private Mat redGsImg;
	private Mat erode;
	private Mat dilate;
	private double[] position;
	private double[] prevPos;
	private long prevCaptTime;
	private long recCaptTime;
	private long ballSpeedX;
	private long ballSpeedY;
	private boolean canRun;
	private Panel redGsPanel;
	private Frame redGsFrame;
	private long timeToHit;

	public ImageDetection() {
	}

	public void startDet() {
		erode = Imgproc.getStructuringElement(Imgproc.MORPH_RECT,
				new Size(3, 3));
		dilate = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(5,
				5));
		oriImg = new Mat();
		hsvImg = new Mat();
		redGsImg = new Mat();
		position = new double[] { -1.0, -1.0 };
		capture = new VideoCapture(1);
		capture.set(3, 1000);
		capture.set(4, 1000);

		capture.read(oriImg);
		redGsPanel = new Panel();
		redGsFrame = new Frame(redGsPanel, "Grayscale", 1000,
				1000);
		updateImage();
		canRun = true;
	}

	@Override
	public void run() {
		double[] bi = new double[2];
		while (canRun) {
			updateImage();
			Scalar hsv_min = new Scalar(0, 70, 50, 0);
			Scalar hsv_max = new Scalar(10, 255, 255, 0);
			Core.inRange(hsvImg, hsv_min, hsv_max, redGsImg);
			Imgproc.erode(redGsImg, redGsImg, erode);
			Imgproc.dilate(redGsImg, redGsImg, dilate);

			Mat circles = new Mat();
			Imgproc.HoughCircles(redGsImg, circles, Imgproc.CV_HOUGH_GRADIENT,
					2, 500, 40, 40, 20, 100);
			if (circles.cols() > 0) {
				if(timeToHit == 0) {
					timeToHit = System.nanoTime() + 2000000000;//time to hit is in 2 sec
				}
				bi[0] = circles.get(0, 0)[0];
				bi[1] = circles.get(0, 0)[1];
				prevPos = position;
				position = bi;
				float timePassed = (recCaptTime-prevCaptTime);
				ballSpeedX = (long) ((position[0]-prevPos[0])/timePassed);
				ballSpeedY = (long) ((position[1]-prevPos[1])/timePassed);
				bi[0] = ballSpeedX * (timeToHit-System.nanoTime());
				bi[1] = ballSpeedY * (timeToHit-System.nanoTime());
			} else {
				if(timeToHit == 0) {
					bi[0] = -1.0;
					bi[1] = -1.0;
					position = bi;
					prevPos = bi;
				}
			}
			
		}
	}

	public void stopDet() {
		canRun = false;
		capture.release();
	}

	public double[] getRedBall() {
		return position;
	}
	
	public boolean isTimeToHit() {
		if(timeToHit - System.nanoTime() - 500000000 < 0) {
			timeToHit = 0;
			return true;
		}
		return false;
	}

	// public double[] getGreenSquare() {
	// double[] bi = new double[2];
	// updateImage();
	//
	// Scalar hsv_min = new Scalar(90, 70, 50, 0);
	// Scalar hsv_max = new Scalar(100, 255, 255, 0);
	// Core.inRange(hsvImg, hsv_min, hsv_max, redGsImg);
	// Imgproc.erode(redGsImg, redGsImg, erode);
	// Imgproc.dilate(redGsImg, redGsImg, dilate);
	//
	// Mat circles = new Mat();
	// Imgproc.HoughCircles(redGsImg, circles, Imgproc.CV_HOUGH_GRADIENT, 2,
	// 500, 40, 40, 20, 100);
	// if (circles.cols() > 0) {
	// bi[0] = circles.get(0, 0)[0];
	// bi[1] = circles.get(0, 0)[1];
	// } else {
	// bi[0] = -1.0;
	// bi[1] = -1.0;
	// }
	// return bi;
	// }

	private void updateImage() {
		prevCaptTime = recCaptTime;
		capture.read(oriImg);
		recCaptTime = System.nanoTime();
		redGsPanel.setimagewithMat(oriImg);// redGsImg);
		redGsFrame.repaint();
		Imgproc.GaussianBlur(oriImg, oriImg, new Size(11, 11), 30.0);
		Imgproc.cvtColor(oriImg, hsvImg, Imgproc.COLOR_BGR2HSV);
	}

}
