import java.util.Formatter;
import java.security.SecureRandom;

public class SETIWOWGenerateDataFile
{
   private static SecureRandom random = new SecureRandom();

   private int[][] singalsArray = new int[Constants.INTERVALS_IN_DAY][Constants.NUM_CHANNELS];
   private int hour = 0;
   private int min  = 0;
   private int sec  = 0;


   private void incrementTime()
   {
      sec    += Constants.SECONDS_INTERVAL;

      if (sec > 60)
      {
         min++;
         sec -= 60;
      }

      if (min > 60)
      {
         hour++;
         min -= 60;
      }
   }


   private void addRandomData ()
   {
      for (int interval = 0; interval < Constants.INTERVALS_IN_DAY; interval++)
      {
         for (int channelNum = 0; channelNum < Constants.NUM_CHANNELS; channelNum++)
         {
            // Fill the array with random values - mostly 0, some 1's, less 2's, a few 3's ...
            singalsArray [interval][channelNum] = 0;

            double chance = random.nextDouble();

            if (chance < 0.2073)
               singalsArray [interval][channelNum] = 1;
            else if (chance < 0.0491)
               singalsArray [interval][channelNum] = 2;
            else if (chance < 0.0073)
               singalsArray [interval][channelNum] = 3;
            else if (chance < 0.0001)
               singalsArray [interval][channelNum] = random.nextInt(10); // 0..9
         }
      }
   }

   private void addWOWEvent ()
   {
      int curr = Constants.START_OF_WOW_SIGNAL_INTERVAL;

      int chIndex = 2 - 1;

      singalsArray [curr++][chIndex] = 6;
      singalsArray [curr++][chIndex] = 'E' - 'A' + 10;
      singalsArray [curr  ][chIndex] = 'Q' - 'A' + 10;     singalsArray [curr++][ 7-1] = 6;
      singalsArray [curr  ][chIndex] = 'U' - 'A' + 10;     singalsArray [curr++][16-1] = 7;
      singalsArray [curr++][chIndex] = 'J' - 'A' + 10;
      singalsArray [curr++][chIndex] = 5;
   }

   @Override
   public String toString ()
   {
      StringBuilder outSB  = new StringBuilder();

      hour = 0;
      min  = 0;
      sec  = 0;

      outSB.append (Constants.SETI_DATA_FILE_HEADER + "\n");

      for (int interval = 0; interval < Constants.INTERVALS_IN_DAY; interval++)
      {
         outSB.append (String.format ("%02d:%02d:%02d", hour, min, sec) + "," );

         incrementTime();

         for (int channelNum = 0; channelNum < Constants.NUM_CHANNELS; channelNum++)
         {
            outSB.append (singalsArray [interval][channelNum] + "," );
         }
         outSB.append ("\n");
      }

      return outSB.toString();
   }

   private void saveToFile()
   {
      try
      {
         Formatter outputFile = new Formatter (Constants.SETI_DATA_FILE);
         outputFile.format ("%s", toString () );

         outputFile.close();
         System.out.println ("Success: data written to file '" + Constants.SETI_DATA_FILE + "'.");
      }
      catch(Exception err)
      {
         System.out.println ("Error writing to file '" + Constants.SETI_DATA_FILE + "'.");
         System.exit(-1);
      }
   }

   public static void main (String[] args)
   {
      SETIWOWGenerateDataFile seti = new SETIWOWGenerateDataFile ();

      seti.addRandomData ();
      seti.addWOWEvent ();

      System.out.println (seti.toString () );

      seti.saveToFile();
   }
}