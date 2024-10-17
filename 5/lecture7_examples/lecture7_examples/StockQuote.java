import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;

/**
 * StockQuote Class Example.
 *
 * date: February 3, 2000 
 * latest updated: August 28, 2023
 * 
 * @author Jeffrey Eppinger (eppinger@cmu.edu)
 * @author Terry Lee (terrylee@cs.cmu.edu)
 */
public class StockQuote implements Runnable {
    /**
     * The ticker for the stock.
     */
    private String ticker;
    /**
     * The stock price.
     */
    private String price;
    /**
     * Today's delta in the stock price.
     */
    private String change;
    /**
     * The time of the stock quote.
     */
    private String time;
    /**
     * The name of the stock.
     */
    private String name;
    /**
     * Support for threading (see below).
     */
    private boolean keepRunning = true;

    /**
     * The URL used to get the stock price.
     */
    private URL priceUrl;

    /**
     * The URL used to get the stock quote.
     */
    private URL quoteUrl;

    /**
     * Count of the number of quotes we've done.
     */
    private static int quoteCount = 0;

    /**
     * An internal routine that reads the URL and stuff the data into instance
     * variables.
     * 
     * @throws IOException IOexception might be thrown
     */
    private void readURLData() throws IOException {
        InputStream is = priceUrl.openStream();
        InputStreamReader isr = new InputStreamReader(is);
        CSVReader c = new CSVReader(isr);
        String[] values = c.readCSVLine();

        price = values[0];
        if (values.length < 1) {
            c.close();
            throw new IOException("Format error reading line, only " + values.length + " items!");
        }

        is = quoteUrl.openStream();
        isr = new InputStreamReader(is);
        c = new CSVReader(isr);
        values = c.readCSVLine();

        ticker = values[0];
        name = values[1];
        change = values[13];
        time = values[5];

        if (values.length < 4) {
            c.close();
            throw new IOException("Format error reading line, only " + values.length + " items!");
        }

        quoteCount = quoteCount + 1;

        c.close();
        isr.close();
        is.close();
    }

    /**
     * constants used to construct priceURL string in constructor. Aug 28, 2023: use
     * twelvedata api to get current price.
     */
    private final String priceUrlHeader = "https://api.twelvedata.com/price?symbol=";

    /**
     * constants used to construct quoteURL string in constructor. Aug 28, 2023: use
     * twelvedata api to get quote.
     */
    private final String quoteUrlHeader = "https://api.twelvedata.com/quote?symbol=";

    /**
     * constant used to construct URL string in constructor.
     * create your own account and
     * toekn at https://twelvedata.com and use your own token (value for apikey param).
     */
    private final String urlTrailer = "&format=CSV&delimiter=,&apikey=";

    /**
     * Constructor takes ticker.
     * 
     * @param ticker ticker of stock
     */
    public StockQuote(String ticker) {
        String priceUrlString = priceUrlHeader + ticker + urlTrailer;
        String quoteUrlString = quoteUrlHeader + ticker + urlTrailer;

        try {
            priceUrl = new java.net.URL(priceUrlString);
            quoteUrl = new java.net.URL(quoteUrlString);
        } catch (IOException e) {
            System.out.println("IOException" + e);
            System.exit(0);
        }
    }

    /**
     * An instance method to provide a current quote for the stock, as a string.
     * 
     * @return string include the ticker, price change and quote date/time
     */
    public String currentQuote() {
        try {
            readURLData();
        } catch (IOException e) {
            System.out.println("IOException" + e);
            System.exit(0);
        }

        String answer = ticker + ' ' + price + ' ' + change + " on " + time;

        return answer;
    }

    /**
     * This method returns a current price for the ticker.
     * 
     * @return current price for the ticker
     */
    public float getPrice() {
        try {
            readURLData();
        } catch (IOException e) {
            System.out.println("IOException");
            System.exit(0);
        }
        return Float.parseFloat(price);
    }

    /**
     * returns a name for the ticker.
     * 
     * @return String value of ticker name
     */
    public String getName() {
        if (name == null) {
            try {
                readURLData();
            } catch (IOException e) {
                System.out.println("IOException");
                System.exit(0);
            }
        }

        return name;
    }

    /**
     * returns a change in the price for the ticker.
     * 
     * @return string value of a change in the price
     */
    public String getChange() {
        if (name == null) {
            try {
                readURLData();
            } catch (IOException e) {
                System.out.println("IOException");
                System.exit(0);
            }
        }

        return change;
    }

    /**
     * Returns quote count value.
     * 
     * @return int value of quote count
     */
    public static int getQuoteCount() {
        return quoteCount;
    }

    /**
     * Supports for printing current quotes from background thread.
     */
    public void run() {
        try {
            while (keepRunning) {
                String quote = currentQuote();
                if (keepRunning) {
                    System.out.println(quote);
                }
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println("Exception while sleep");
            System.exit(0);
        }
    }

    /**
     * make thread running flag false.
     */
    public void stopRunning() {
        keepRunning = false;
    }

    /**
     * A simplified version of CSV Reader.
     *
     * Subclass of a BufferedReader to handle a character stream that consists of
     * comma separated values (CSVs)
     *
     * Provides an additional instance method, readCSVLine(), that parses lines into
     * substrings. The substrings are separated by comma in the original input
     * stream. The readCSVLine() method returns an array of references to Strings.
     * The Strings are the values from the line that were separated by commas. If a
     * value was surrounded by quotes, the quotes are removed.
     *
     * Limitations: Spaces before or after the commas are not removed. In the first
     * and last quote are removed from a value. Embedding commas in a quoted value
     * is not handled properly. (In this case, the commas will separate the values
     * and the quotes will not be removed from the ends of those values.
     *
     * Date: June 10, 2001 Updated: September 4, 2019
     * 
     * @author Jeffrey Eppinger (jle@cs.cmu.com)
     * @author Terry Lee (terrylee@cs.cmu.edu)
     */
    private static class CSVReader extends BufferedReader {

        /**
         * The constructor just initializes the superclass.
         * 
         * @param in reader argument
         */
        CSVReader(Reader in) {
            super(in);
        }

        /**
         * This is the only additional method. It uses readLine from the superclass to
         * get a line but returns the comma separated values as in an array of strings.
         * At the end of the file, readCSVLine returns null (just as readLine does).
         * 
         * @return string array of values
         * @throws IOException IOException might be thrown
         */
        public String[] readCSVLine() throws IOException {
            String last = "", line = "";
            while ((line = super.readLine()) != null) {
                last = line;
            }

            // If we're at the end of the file, readLine returns null
            // so we return null.
            if (last == null) {
                return null;
            }

            boolean inQuotes = false;
            // Count up the number of commas
            int commaCount = 0;
            for (int i = 0; i < last.length(); i++) {
                if (last.charAt(i) == '\"') {
                    inQuotes = !inQuotes;
                }
                if (last.charAt(i) == ',') {
                    if (inQuotes) {
                        last = last.substring(0, i) + last.substring(i + 1);
                    } else {
                        commaCount = commaCount + 1;
                    }
                }
            }

            // Allocate an array of the necessary size to return the strings
            String[] values = new String[commaCount + 1];

            // In a loop, set beginIndex and endIndex to the start and end
            // positions of each argment and then use the substring method
            // to create strings for each of the comma separate values

            // Start beginIndex at the beginning of the String, position 0
            int beginIndex = 0;

            for (int i = 0; i < commaCount; i++) {
                // set endIndex to the position of the (next) comma
                int endIndex = last.indexOf(',', beginIndex);

                // if the argument begins and ends with quotes, remove them

                if (last.charAt(beginIndex) == '"' && last.charAt(endIndex - 1) == '"') {

                    // If we made it here, we have quotes around our string.
                    // Add/substract one from the start/end of the args
                    // to substring to get the value. (See else comment
                    // below for details on how this works.)

                    values[i] = last.substring(beginIndex + 1, endIndex - 1).trim();

                } else if (last.charAt(beginIndex) == '"') {
                    values[i] = last.substring(beginIndex + 1, endIndex - 1).trim();
                } else if (last.charAt(endIndex - 1) == '"') {
                    values[i] = last.substring(beginIndex + 1, endIndex - 1).trim();
                } else {

                    // If we name it here, we don't have quotes around
                    // our string. Take the substring of this line
                    // from the beginIndex to the endIndex. The substring
                    // method called on a String will return the portion
                    // of the String starting with the beginIndex and up
                    // to but not including the endIndex.

                    values[i] = last.substring(beginIndex, endIndex).trim();
                }

                // Set beginIndex to the position character after the
                // comma. (Remember, endIndex was set to the position
                // of the comma.)
                beginIndex = endIndex + 1;
            }

            // handle the value that's after the last comma
            if (last.charAt(beginIndex) == '"' && last.charAt(last.length() - 1) == '"') {
                values[commaCount] = last.substring(beginIndex + 1, last.length() - 1).trim();
            } else {
                values[commaCount] = last.substring(beginIndex, last.length()).trim();
            }

            return values;
        }
    }

}
