package jnaalisv;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

public class CommonStockTest {

    public static final BigDecimal PAR_VALUE = new BigDecimal("1.00");
    public static final BigDecimal FIFTY_PENNIES = new BigDecimal("0.50");
    public static final BigDecimal ONE_POUND = new BigDecimal("1.00");
    public static final BigDecimal TWO_POUNDS = new BigDecimal("2.00");
    public static final BigDecimal LAST_DIVIDEND = new BigDecimal("0.08");

    private Stock pop;

    @Before
    public void setUp() {
        pop = new CommonStock("POP", LAST_DIVIDEND, PAR_VALUE);
    }

    @Test
    public void initiallyTickerPriceShouldBeEqualToParValue() {
        assertThat(pop.getTickerPrice()).isEqualTo(pop.getParValue());
    }

    @Test
    public void tradingAtParValueDoesNotChangeTickerPrice() {

        pop.recordATrade(LocalDateTime.now(), new BigDecimal("10"), ONE_POUND, BuyOrSell.BUY);
        pop.recordATrade(LocalDateTime.now(), new BigDecimal("23432"), ONE_POUND, BuyOrSell.SELL);
        pop.recordATrade(LocalDateTime.now(), new BigDecimal("12"), ONE_POUND, BuyOrSell.BUY);

        assertThat(pop.getTickerPrice()).isEqualTo(ONE_POUND);
    }


    @Test
    public void shouldCalculateTickerPriceForSimpleCase() {

        pop.recordATrade(LocalDateTime.now(), new BigDecimal("5.00"), FIFTY_PENNIES, BuyOrSell.BUY);
        pop.recordATrade(LocalDateTime.now(), new BigDecimal("5.00"), ONE_POUND, BuyOrSell.BUY);
        pop.recordATrade(LocalDateTime.now(), new BigDecimal("5.00"), FIFTY_PENNIES, BuyOrSell.BUY);
        pop.recordATrade(LocalDateTime.now(), new BigDecimal("5.00"), ONE_POUND, BuyOrSell.BUY);

        assertThat(pop.getTickerPrice()).isEqualTo(new BigDecimal("0.75"));
    }

    @Test
    public void shouldCalculateTickerPriceForMoreComplexCase() {

        pop.recordATrade(LocalDateTime.now(), new BigDecimal("3.00"), TWO_POUNDS, BuyOrSell.BUY);
        pop.recordATrade(LocalDateTime.now(), new BigDecimal("9.00"), ONE_POUND, BuyOrSell.BUY);

        assertThat(pop.getTickerPrice()).isEqualTo(new BigDecimal("1.25"));
    }

    @Test
    public void shouldCalculateDividendYield() {
        assertThat(pop.dividendYield()).isEqualTo(LAST_DIVIDEND.divide(pop.getTickerPrice()));
    }

    @Test
    public void shouldCalculatePriceToEarnings() {
        assertThat(pop.priceToEarningsRatio()).isEqualTo(pop.getTickerPrice().divide(LAST_DIVIDEND, 2));
    }
}
