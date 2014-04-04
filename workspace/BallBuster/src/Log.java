import java.io.IOException;
import lejos.nxt.*;
import lejos.nxt.comm.Bluetooth;
import lejos.util.*;

public class Log
{
    static NXTDataLogger logger = new NXTDataLogger();
    static LogColumn[] columnDefs = { new LogColumn("DinosaursX",
            LogColumn.DT_DOUBLE), new LogColumn("Dinosaursy",
                    LogColumn.DT_DOUBLE) };

    public static void main(String[] args)
    {
        // Set up the logging connection
        boolean connectionSuccess = false;
        double dinosaurX = 0;
        double dinosaurY = 0;
        
        while(!connectionSuccess && !Button.ESCAPE.isDown())
        {
            try
            {
                logger.startRealtimeLog(Bluetooth.waitForConnection());
                connectionSuccess = true;
            }
            catch(IOException e)
            {
                System.out.println("You done goofed");
            }
        }
        
        logger.setColumns(columnDefs);
        System.out.println("Press escape to quit");
        
        while(!Button.ESCAPE.isDown())
        {   
            logger.writeLog(Math.cos(dinosaurX/100));
            logger.writeLog(Math.sin(dinosaurY/100));
            logger.finishLine();
            dinosaurX =dinosaurX + 1;
            dinosaurY =dinosaurY + 2;
        }
        
        logger.stopLogging();
    }
}