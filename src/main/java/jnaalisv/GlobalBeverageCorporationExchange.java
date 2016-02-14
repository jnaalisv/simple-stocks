package jnaalisv;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class GlobalBeverageCorporationExchange {

    private Map<String, Stock> stocks;

    public GlobalBeverageCorporationExchange() {
        this.stocks = new HashMap<>();
        this.stocks.put("TEA", new CommonStock("TEA", new BigDecimal("0.00"), new BigDecimal("1.00")));
        this.stocks.put("POP", new CommonStock("POP", new BigDecimal("0.08"), new BigDecimal("1.00")));
        this.stocks.put("ALE", new CommonStock("ALE", new BigDecimal("0.23"), new BigDecimal("0.60")));
        this.stocks.put("GIN", new PreferredStock("GIN", new BigDecimal("0.08"), new BigDecimal("2.00"), new BigDecimal("1.00")));
        this.stocks.put("JOE", new CommonStock("JOE", new BigDecimal("0.13"), new BigDecimal("2.50")));
    }

    public void recordATrade (LocalDateTime timestamp, String stockSymbol, BigDecimal quantityOfShares, BigDecimal price, BuyOrSell buyOrSell) {
        this.stocks.get(stockSymbol).recordATrade(timestamp, quantityOfShares, price, buyOrSell);

    }

    public double allShareIndex() {

        BigDecimal productOfTickerPrices =
            this.stocks
                .values()
                .stream()
                .map(Stock::getTickerPrice)
                .reduce(BigDecimal.ONE, (accumulator, nextTickerPrice) -> accumulator.multiply(nextTickerPrice));

        return Math.pow(productOfTickerPrices.doubleValue(), 1 / stocks.values().size());
    }
}
