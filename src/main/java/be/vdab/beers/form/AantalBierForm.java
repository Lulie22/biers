package be.vdab.beers.form;

import org.springframework.data.relational.core.sql.In;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class AantalBierForm {
    @NotNull
    @Positive
    @Min(1)
    private final Integer aantal;

    public AantalBierForm(Integer aantal) {
        this.aantal = aantal;
    }

    public Integer getAantal() {
        return aantal;
    }
}
