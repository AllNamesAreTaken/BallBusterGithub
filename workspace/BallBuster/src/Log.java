import java.io.IOException;
import lejos.nxt.*;
import lejos.nxt.comm.Bluetooth;
import lejos.util.*;

public class Log
{
    static NXTDataLogger logger = new NXTDataLogger();
    static LogColumn[] columnDefs = { new LogColumn("Dinosaurs",
            LogColumn.DT_BOOLEAN) };

    public static void main(String[] args)
    {
        // Set up the logging connection
        boolean connectionSuccess = false;
        boolean dinosaur = true;
        
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
            if(dinosaur)
            {
                dinosaur = false;
            }
            else
            {
                dinosaur = true;
            }
            
            logger.writeLog(dinosaur);
        }
    }
}