package jnaalisv;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

public class StockTest {

    private BigDecimal fifty = new BigDecimal("50.00");
    private BigDecimal oneHundred = new BigDecimal("100.00");

    private Stock pop;

    @Before
    public void setUp() {
        pop = new Stock("POP", StockType.Common, new BigDecimal("8.00"), BigDecimal.ZERO, new BigDecimal("100.00"));
    }

    @Test
    public void initiallyTickerPriceShouldBeEqualToParValue() {
        assertThat(pop.getTickerPrice()).isEqualTo(pop.getParValue());
    }

    @Test
    public void tradingAtParValueDoesNotChangeTickerPrice() {

        pop.recordATrade(LocalDateTime.now(), new BigDecimal("10"), oneHundred, BuyOrSell.BUY);
        pop.recordATrade(LocalDateTime.now(), new BigDecimal("23432"), oneHundred, BuyOrSell.SELL);
        pop.recordATrade(LocalDateTime.now(), new BigDecimal("12"), oneHundred, BuyOrSell.BUY);

        assertThat(pop.getTickerPrice()).isEqualTo(oneHundred);
    }


    @Test
    public void shouldCalculateTickerPriceForSimpleCase() {

        pop.recordATrade(LocalDateTime.now(), new BigDecimal("5.00"), fifty, BuyOrSell.BUY);
        pop.recordATrade(LocalDateTime.now(), new BigDecimal("5.00"), oneHundred, BuyOrSell.BUY);
        pop.recordATrade(LocalDateTime.now(), new BigDecimal("5.00"), fifty, BuyOrSell.BUY);
        pop.recordATrade(LocalDateTime.now(), new BigDecimal("5.00"), oneHundred, BuyOrSell.BUY);

        assertThat(pop.getTickerPrice()).isEqualTo(new BigDecimal("75.00"));
    }

    @Test
    public void shouldCalculateTickerPriceForMoreComplexCase() {

        pop.recordATrade(LocalDateTime.now(), new BigDecimal("3.00"), fifty, BuyOrSell.BUY);
        pop.recordATrade(LocalDateTime.now(), new BigDecimal("9.00"), oneHundred, BuyOrSell.BUY);

        assertThat(pop.getTickerPrice()).isEqualTo(new BigDecimal("87.50"));
    }

}
