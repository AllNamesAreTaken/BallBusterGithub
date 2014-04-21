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

public class Main
{

    static ImageDetection imgd;

    public static void main(String[] args)
    {
        PrintWriter loggerLoop = null;
        PrintWriter loggerDegreeCompare = null;
        Bot robot = new Bot(Motor.A, Motor.B);
        imgd = new ImageDetection();

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        try
        {
            loggerLoop =
                    new PrintWriter(new BufferedWriter(new FileWriter(
                            "LogControlLoopTime.csv", false)));
            loggerDegreeCompare =
                    new PrintWriter(new BufferedWriter(new FileWriter(
                            "LogDegreeComparison.csv", false)));
        }
        catch(IOException ex)
        {
            System.out.println("Logging file not found");
        }

        long startTime = 0;
        long endTime = 1;

        imgd.startDet();
        imgd.start();

        robot.start();

        while(!Button.ESCAPE.isDown())
        {
            startTime = System.nanoTime();
            double[] redBallPosition = imgd.getRedBall();
            if(redBallPosition[0] != -1)
            {
                int deg =
                        (int) (200 * Math.atan2(300 - redBallPosition[1],
                                300 + redBallPosition[0]));
                System.out.println("Deg: " + deg + "Ballx: "
                        + (redBallPosition[0]) + " Bally: "
                        + (redBallPosition[1]));
                if(Math.abs(deg) < 40)
                {
                    if(deg > 10)
                        deg = (int) (deg + 30);
                    else if(deg < -10)
                        deg = (int) (deg - 30);
                }
                
                if(loggerDegreeCompare != null)
                {
                    loggerDegreeCompare.append((deg - robot.ramp.getTachoCount()) + ",");
                }
                robot.setDegree(deg);
            }

            endTime = System.nanoTime();

            if(loggerLoop != null)
            {
                loggerLoop.append((endTime - startTime) + ",");
            }
        }

        robot.isRunning = false;
        loggerLoop.close();
        loggerDegreeCompare.close();
        imgd.stopDet();
    }

}
