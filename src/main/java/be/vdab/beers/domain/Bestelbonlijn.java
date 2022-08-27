package be.vdab.beers.domain;

import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class Bestelbonlijn {
    private final long bestelbonId;
    private final long bierId;
    @Positive
    private final int aantal;
    @NumberFormat(pattern = "#00.00")
    private final BigDecimal prijs;

    public Bestelbonlijn(long bestelbonId, long bierId, int aantal, BigDecimal prijs) {
        this.bestelbonId = bestelbonId;
        this.bierId = bierId;
        this.aantal = aantal;
        this.prijs = prijs;
    }

    public long getBestelbonId() {
        return bestelbonId;
    }

    public long getBierId() {
        return bierId;
    }

    public int getAantal() {
        return aantal;
    }

    public BigDecimal getPrijs() {
        return prijs;
    }
//    public BigDecimal getTotalPrijs(int aantal){
//        return prijs.multiply(BigDecimal.valueOf(aantal));
//    }
}
