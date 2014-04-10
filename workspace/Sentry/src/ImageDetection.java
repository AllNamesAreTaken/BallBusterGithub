import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;
import org.opencv.highgui.VideoCapture;
import org.opencv.imgproc.Imgproc;


public class ImageDetection {
	public static VideoCapture capture = new VideoCapture(1);
	public static Mat oriImg;
	public static Mat hsvImg;
	public static Mat redGsImg;
	public static Mat erode;
	public static Mat dilate;
	
	public static void startCamera() {
		capture.set(3, 600);
		capture.set(4, 600);
		erode = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(3,3));
		dilate = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(5,5));
		updateImage();
	}
	
	public static double[] getRedBall() {
		double[] bi = new double[2];
		updateImage();

		Scalar hsv_min = new Scalar(0, 70, 50, 0);
		Scalar hsv_max = new Scalar(10, 255, 255, 0);
		Core.inRange(hsvImg, hsv_min, hsv_max, redGsImg);
		Imgproc.erode(redGsImg, redGsImg, erode);
		Imgproc.dilate(redGsImg, redGsImg, dilate);

        Mat circles = new Mat();
		Imgproc.HoughCircles(redGsImg, circles, Imgproc.CV_HOUGH_GRADIENT, 2, 500, 40, 40, 20, 100);
		if(circles.cols() > 0) 
		{
			bi[0] = circles.get(0, 0)[0];
			bi[1] = circles.get(0, 0)[1];
		}
		else {
			bi[0] = -1.0;
			bi[1] = -1.0;
		}
		return bi;
	}
	
	public static double[] getGreenSquare() {
		double[] bi = new double[2];
		updateImage();

		Scalar hsv_min = new Scalar(90, 70, 50, 0);
		Scalar hsv_max = new Scalar(100, 255, 255, 0);
		Core.inRange(hsvImg, hsv_min, hsv_max, redGsImg);
		Imgproc.erode(redGsImg, redGsImg, erode);
		Imgproc.dilate(redGsImg, redGsImg, dilate);

        Mat circles = new Mat();
		Imgproc.HoughCircles(redGsImg, circles, Imgproc.CV_HOUGH_GRADIENT, 2, 500, 40, 40, 20, 100);
		if(circles.cols() > 0) 
		{
			bi[0] = circles.get(0, 0)[0];
			bi[1] = circles.get(0, 0)[1];
		}
		else {
			bi[0] = -1.0;
			bi[1] = -1.0;
		}
		return bi;
	}
	
	public static void updateImage() {
		capture.read(oriImg);

		Imgproc.GaussianBlur(oriImg, oriImg, new Size(11,11), 30.0);
		Imgproc.cvtColor(oriImg, hsvImg, Imgproc.COLOR_BGR2HSV);
	}
}
