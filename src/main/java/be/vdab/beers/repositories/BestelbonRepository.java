package be.vdab.beers.repositories;

import be.vdab.beers.domain.Bestelbon;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Controller;

import java.util.Map;
@Controller
public class BestelbonRepository {
    private final JdbcTemplate template;
    private final SimpleJdbcInsert insert;

    public BestelbonRepository(JdbcTemplate template) {
        this.template = template;
        this.insert = new SimpleJdbcInsert(template)
                .withTableName("bestelbonnen")
                .usingGeneratedKeyColumns("id");
    }

    public long create(Bestelbon bestelbon) {
        System.out.println("Naam: "+bestelbon.getNaam());
        return insert.executeAndReturnKey(Map.of(
                "naam", bestelbon.getNaam(), "straat", bestelbon.getStraat(),
                "huisNr", bestelbon.getHuisNr(), "postcode", bestelbon.getPostcode(),
                "gemeente", bestelbon.getGemeente()))
                .longValue();
    }
}
