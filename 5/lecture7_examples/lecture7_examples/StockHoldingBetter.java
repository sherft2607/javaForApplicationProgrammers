/**
 * Stockholding example code.
 * @author Jeffrey Eppinger & Terry Lee & Hammad Ahmad
 */
public class StockHoldingBetter implements Comparable<StockHoldingBetter> {
    /**
     * Ticker of a stock.
     */
    private final String ticker;
    /**
     * Number of shares of a stock.
     */
    private final int shares;
    /**
     * Full name of a stock.
     */
    private final String name;
    /**
     * Market price of a stock.
     */
    private final float price;

    /**
     * This is the constructor method.
     * @param newTicker ticker
     * @param newShares share price
     */
    public StockHoldingBetter(String newTicker, int newShares) {
        ticker = newTicker;
        shares = newShares;
        StockQuote sq = new StockQuote(ticker);
        price = sq.getPrice();
        name = sq.getName();
    }

    /**
     * Returns ticker of a stock.
     * @return String value of ticker
     */
    public String getTicker() {
        return ticker;
    }

    /**
     * Returns number of shares of a stock.
     * @return int value of number of shares
     */
    public int getShares() {
        return shares;
    }

    /**
     * Returns full name of a stock.
     * @return String value of a full name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns current market price of a stock.
     * @return float value of a market price
     */
    public float getPrice() {
        return price;
    }

    /**
     * Returns the string representation of a stock holding.
     * @return String value of a stock holding
     */
    @Override
    public String toString() {
        return String.format("%s (%s): %d shares at $%.2f each", ticker, name, shares, price);
    }

    /**
     * Performs structural equivalence on another object.
     * @param other Object to compare
     * @return true if other is a StockHoldingBetter object and the two objects are equivalent, false otherwise
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof StockHoldingBetter)) {
            return false;
        }
        StockHoldingBetter otherStockHolding = (StockHoldingBetter) other;
        return (ticker.equals(otherStockHolding.getTicker()) && shares == otherStockHolding.getShares());
    }

    /**
     * Defines the natural ordering of two StockHoldingBetter objects by their value (price*shares).
     * @param other StockHoldingBetter object to compare
     * @return -1, 0, or 1 as this StockHolding is less than, equal to, or greater than the other StockHolding object
     */
    @Override
    public int compareTo(StockHoldingBetter other) {
        double value1 = price * shares;
        double value2 = other.getPrice() * other.getShares();
        return Double.compare(value1, value2);
    }
}
