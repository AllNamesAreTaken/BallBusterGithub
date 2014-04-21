import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.highgui.VideoCapture;
import org.opencv.imgproc.Imgproc;


public class Camera extends Thread{
	private VideoCapture capture;
	private Mat oriImg;
	private Mat hsvImg;
	
	private Panel redGsPanel;
	private Panel oriPanel;
	private Frame redGsFrame;
	private Frame oriFrame;
	private boolean canRun;

	public void startDet() {
		oriImg = new Mat();
		hsvImg = new Mat();
		capture = new VideoCapture(1);
		capture.set(3, 800);
		capture.set(4, 600);

		capture.read(oriImg);
		redGsPanel = new Panel();
		oriPanel = new Panel();
		redGsFrame = new Frame(redGsPanel, "View", 1000,
				1000);
		oriFrame = new Frame(oriPanel, "View", 1000,
				1000);
		capture.read(oriImg);
		Imgproc.GaussianBlur(oriImg, oriImg, new Size(11, 11), 30.0);
		Imgproc.cvtColor(oriImg, hsvImg, Imgproc.COLOR_BGR2HSV);
		canRun = true;
	}

	@Override
	public void run() {
		while (canRun) {
			capture.read(oriImg);
			Imgproc.GaussianBlur(oriImg, oriImg, new Size(11, 11), 30.0);
			Imgproc.cvtColor(oriImg, hsvImg, Imgproc.COLOR_BGR2HSV);
			
			oriPanel.setimagewithMat(oriImg);
			oriFrame.repaint();
		}
	}

	public void stopDet() {
		canRun = false;
		capture.release();
	}
}
