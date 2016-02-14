package jnaalisv;


import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

public class PreferredStockTest {

    public static final BigDecimal PAR_VALUE = new BigDecimal("100");
    public static final BigDecimal FIXED_DIVIDEND_PERCENTAGE = new BigDecimal("2.00");
    private Stock gin;

    @Before
    public void setUp() {
        gin = new PreferredStock("GIN", new BigDecimal("8"), FIXED_DIVIDEND_PERCENTAGE, PAR_VALUE);
    }

    @Test
    public void shouldCalculateDividendYield() {
        assertThat(gin.dividendYield()).isEqualTo(FIXED_DIVIDEND_PERCENTAGE);
    }
}
