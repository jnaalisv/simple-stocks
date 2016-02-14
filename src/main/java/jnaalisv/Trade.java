package jnaalisv;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Trade {

    private LocalDateTime timestamp;
    private BigDecimal quantityOfShares;
    private BigDecimal price;
    private BuyOrSell buyOrSell;

    public Trade(LocalDateTime timestamp, BigDecimal quantityOfShares, BigDecimal price, BuyOrSell buyOrSell) {
        this.timestamp = timestamp;
        this.quantityOfShares = quantityOfShares;
        this.price = price;
        this.buyOrSell = buyOrSell;
    }

    public BigDecimal getQuantityOfShares() {
        return quantityOfShares;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
