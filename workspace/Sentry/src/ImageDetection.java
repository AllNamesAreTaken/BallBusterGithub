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
	private boolean canRun;
	
//	private Panel redGsPanel;
//	private Panel oriPanel;
//	private Frame redGsFrame;
//	private Frame oriFrame;

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
		capture.set(3, 800);
		capture.set(4, 600);

		capture.read(oriImg);
		oriImg.copyTo(redGsImg);
//		redGsPanel = new Panel();
//		redGsFrame = new Frame(redGsPanel, "View", 1000,
//				1000);
//		oriPanel = new Panel();
//		oriFrame = new Frame(oriPanel, "View", 1000,
//				1000);
		updateImage();
		canRun = true;
	}

	@Override
	public void run() {
		double[] bi = new double[2];
		Scalar hsv_min = new Scalar(0, 70, 50, 0);
		Scalar hsv_max = new Scalar(5, 255, 255, 0);
		Scalar hsv_min2 = new Scalar(170, 70, 50, 0);
		Scalar hsv_max2 = new Scalar(180, 255, 255, 0);
		while (canRun) {
			updateImage();
			Mat tempImg = new Mat();
			hsvImg.copyTo(tempImg);
			Core.inRange(hsvImg, hsv_min, hsv_max, redGsImg);
			Core.inRange(hsvImg, hsv_min2, hsv_max2, tempImg);
			Core.bitwise_or(redGsImg, tempImg, redGsImg);
			Imgproc.erode(redGsImg, redGsImg, erode);
			Imgproc.dilate(redGsImg, redGsImg, dilate);

			Mat circles = new Mat();
			Imgproc.HoughCircles(redGsImg, circles, Imgproc.CV_HOUGH_GRADIENT,
					2, 500, 40, 40, 20, 100);
			if (circles.cols() > 0) {
				bi[0] = circles.get(0, 0)[0];
				bi[1] = circles.get(0, 0)[1];
				position = bi;
			} else {
				bi[0] = -1.0;
				bi[1] = -1.0;
				position = bi;
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

	private void updateImage() {
		capture.read(oriImg);
//		redGsPanel.setimagewithMat(redGsImg);
//		oriPanel.setimagewithMat(oriImg);
//		redGsFrame.repaint();
//		oriFrame.repaint();
		Imgproc.GaussianBlur(oriImg, oriImg, new Size(11, 11), 30.0);
		Imgproc.cvtColor(oriImg, hsvImg, Imgproc.COLOR_BGR2HSV);
	}

}
