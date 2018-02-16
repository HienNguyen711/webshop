package project.webshop.model.entity.helper;

public enum Currency {
    DOLLARS(1, "USD"), POUNDS(0.5, "GBP"), EURO(0.75, "EUR");

    private double rate;

    private String symbol;

    Currency(double rate, String symbol) {
        this.rate = rate;
        this.symbol = symbol;
    }

    public double getRate() {
        return rate;
    }

    public String getSymbol() {
        return symbol;
    }
}