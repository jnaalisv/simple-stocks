package jnaalisv;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class Stock {

    protected String stockSymbol;
    protected BigDecimal lastDividend;
    protected BigDecimal parValue;
    protected List<Trade> trades;
    protected BigDecimal tickerPrice;

    public Stock(String stockSymbol, BigDecimal lastDividend, BigDecimal parValue) {
        this.stockSymbol = stockSymbol;
        this.lastDividend = lastDividend;
        this.parValue = parValue;
        this.trades = new ArrayList<>();
        this.tickerPrice = parValue;
    }

    public void recordATrade(LocalDateTime timestamp, BigDecimal quantityOfShares, BigDecimal price, BuyOrSell buyOrSell) {
        recordATrade(new Trade(timestamp, quantityOfShares, price, buyOrSell));
    }

    public void recordATrade(Trade trade) {
        trades.add(trade);
        calculateTickerPrice();
    }

    private void calculateTickerPrice() {

        BigDecimal sumOfTradeTotals =
                trades
                .stream()
                .map(trade -> trade.getPrice().multiply(trade.getQuantityOfShares()))
                .reduce(BigDecimal.ZERO, (accumulatedTradeTotals, tradeTotal) -> accumulatedTradeTotals.add(tradeTotal));

        BigDecimal sumOfTradeQuantities =
            trades
                    .stream()
                    .map(trade -> trade.getQuantityOfShares())
                    .reduce(BigDecimal.ZERO, (accumulatedQuantities, quantityOfShares) -> accumulatedQuantities.add(quantityOfShares));

        tickerPrice = sumOfTradeTotals.divide(sumOfTradeQuantities, 2, BigDecimal.ROUND_HALF_UP);

    }

    public BigDecimal getTickerPrice() {
        return tickerPrice;
    }

    public BigDecimal getParValue() {
        return parValue;
    }

    public abstract BigDecimal dividendYield();

    public BigDecimal priceToEarningsRatio() {
        return this.tickerPrice.divide(lastDividend, 2, BigDecimal.ROUND_HALF_UP);
    }

}
