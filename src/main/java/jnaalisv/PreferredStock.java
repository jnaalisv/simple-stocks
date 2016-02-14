package jnaalisv;

import java.math.BigDecimal;

public class PreferredStock extends Stock {

    private BigDecimal fixedDividendPercentage;

    public PreferredStock(String stockSymbol, BigDecimal lastDividend, BigDecimal fixedDividendPercentage, BigDecimal parValue) {
        super(stockSymbol, lastDividend, parValue);
        this.fixedDividendPercentage = fixedDividendPercentage;
    }

    @Override
    public BigDecimal dividendYield() {
        return fixedDividendPercentage.multiply(parValue).divide(tickerPrice, 2, BigDecimal.ROUND_HALF_UP);
    }
}
