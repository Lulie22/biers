package be.vdab.beers.dto;

public class BrouwerMetGemeente {
    private final long id;
    private final String naam;
    private final String gemeente;

    public BrouwerMetGemeente(long id, String naam, String gemeente) {
        this.id = id;
        this.naam = naam;
        this.gemeente = gemeente;
    }

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public String getGemeente() {
        return gemeente;
    }
    public String getNaamGemeente(){
        return naam + " ("+ gemeente +")";
    }
}
