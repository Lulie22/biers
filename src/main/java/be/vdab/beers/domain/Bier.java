package be.vdab.beers.domain;

import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;

public class Bier {
    private final long id;
    private final String naam;
    private final long brouwerId;
    @NumberFormat(pattern = "#0.00")
    private final long alcohol;
    @NumberFormat(pattern = "00.00")
    private final BigDecimal prijs;
    private final long besteld;



    public Bier(long id, String naam, long brouwerId, long alcohol, BigDecimal prijs, long besteld) {
        this.id = id;
        this.naam = naam;
        this.brouwerId = brouwerId;
        this.alcohol = alcohol;
        this.prijs = prijs;
        this.besteld = besteld;
    }

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public long getBrouwerId() {
        return brouwerId;
    }

    public long getAlcohol() {
        return alcohol;
    }

    public BigDecimal getPrijs() {
        return prijs;
    }

    public long getBesteld() {
        return besteld;
    }
    public BigDecimal teBetalen(int aantal) {

        return prijs.multiply(BigDecimal.valueOf(aantal));
    }

}
