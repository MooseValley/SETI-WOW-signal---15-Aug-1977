public class Constants
{
   /*
   While perusing data collected on August 15 at 22:16 EDT (02:16 UTC),
   he spotted a series of values of signal intensity and frequency that
   left him and his colleagues astonished.

   On print out, the signal started at 19:16:48.
   Assume  22:16:48 EDT
   */

   public final static int SECONDS_INTERVAL             = 12;  // 12 seconds between values.
   public final static int START_OF_WOW_SIGNAL_INTERVAL = ((19 * 60 + 16) * 60 + 48) / SECONDS_INTERVAL; // 19:16:48 print-out time
   public final static int INTERVALS_IN_DAY             = 24 * 60 * 60 / SECONDS_INTERVAL;
   public final static int NUM_CHANNELS                 = 50;
   public final static String SETI_DATA_FILE            = "SETI__1977-08-15.csv";
   public final static String SETI_DATA_FILE_HEADER     = "# SETI WOW! Signal: approximate reconstruction of data for 15-Aug-1977 by Moose OMalley.";

}