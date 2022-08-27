package be.vdab.beers.domain;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class Bestelbon {
    private final long id;
    @NotBlank
    private final String naam;
    @NotBlank
    private final String straat;
    @NotBlank
    private final String huisNr;
    @NotNull
    @Positive
    @Range(min = 1000, max = 9999)
    private final Integer postcode;
    private final String gemeente;

    public Bestelbon(long id, String naam, String straat, String huisNr, Integer postcode, String gemeente) {
        this.id = id;
        this.naam = naam;
        this.straat = straat;
        this.huisNr = huisNr;
        this.postcode = postcode;
        this.gemeente = gemeente;
    }

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public String getStraat() {
        return straat;
    }

    public String getHuisNr() {
        return huisNr;
    }

    public Integer getPostcode() {
        return postcode;
    }

    public String getGemeente() {
        return gemeente;
    }
}
