package be.vdab.beers.repositories;

import be.vdab.beers.domain.Bestelbonlijn;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class BestelbonlijnRepository {
    private final JdbcTemplate template;
    private final SimpleJdbcInsert insert;

    public BestelbonlijnRepository(JdbcTemplate template) {
        this.template = template;
        insert = new SimpleJdbcInsert(template)
                .withTableName("bestelbonlijnen");
    }
    public int create(Bestelbonlijn  bestelbonlijn){
        return insert.execute(
                Map.of("bestelbonId",bestelbonlijn.getBestelbonId(),
                                "bierid",bestelbonlijn.getBierId(),
                                "aantal",bestelbonlijn.getAantal(),
                                "prijs",bestelbonlijn.getPrijs()));
        //System.out.println("created bestelbonlin :"+ins);
        //return ins;

    }

}
