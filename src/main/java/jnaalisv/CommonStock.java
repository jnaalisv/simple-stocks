package jnaalisv;

import java.math.BigDecimal;

public class CommonStock extends Stock {

    public CommonStock(String stockSymbol, BigDecimal lastDividend, BigDecimal parValue) {
        super(stockSymbol, lastDividend, parValue);
    }

    @Override
    public BigDecimal dividendYield() {
        return this.lastDividend.divide(tickerPrice, 2, BigDecimal.ROUND_HALF_UP);
    }

}
