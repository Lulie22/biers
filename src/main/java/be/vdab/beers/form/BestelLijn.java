package be.vdab.beers.form;

import be.vdab.beers.domain.Bier;

import java.math.BigDecimal;

public class BestelLijn {
    private final Bier bier;
    private final int aantal;
    private final BigDecimal teBetalen;

    public BestelLijn(Bier bier, int aantal, BigDecimal teBetalen) {
        this.bier = bier;
        this.aantal = aantal;
        this.teBetalen = teBetalen;
    }

    public Bier getBier() {
        return bier;
    }

    public int getAantal() {
        return aantal;
    }

    public BigDecimal getTeBetalen() {
        return teBetalen;
    }
}
