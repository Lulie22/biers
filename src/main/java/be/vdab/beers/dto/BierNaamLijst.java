package be.vdab.beers.dto;

public class BierNaamLijst {
    private final long id;
    private final String naam;

    public BierNaamLijst(long id, String naam) {
        this.id = id;
        this.naam = naam;
    }

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }
}
