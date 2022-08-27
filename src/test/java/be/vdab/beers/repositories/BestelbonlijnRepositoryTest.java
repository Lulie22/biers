package be.vdab.beers.repositories;

import be.vdab.beers.domain.Bestelbon;
import be.vdab.beers.domain.Bestelbonlijn;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
@JdbcTest
@Import(BestelbonlijnRepository.class)
@Sql({"/insertBrouwers.sql","/insertBieren.sql","/insertBestelbonnen.sql", "/insertBestelbonlijn.sql"})
//@Sql({"/insertBestelbonnen.sql", "/insertBestelbonlijn.sql"})
public class BestelbonlijnRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private final BestelbonlijnRepository repository;
    private final String BESTELBONLIJN = "bestelbonlijnen";

    public BestelbonlijnRepositoryTest(BestelbonlijnRepository repository) {
        this.repository = repository;
    }
    private long idVanBestelbon(){
        return jdbcTemplate.queryForObject(
                "select id from bestelbonnen where naam = 'test2'", Long.class);
    }
    private long idVanBier(){
        return jdbcTemplate.queryForObject(
                "select id from bieren where naam = 'test2'", Long.class);
    }
    private BigDecimal prijs(){
        return jdbcTemplate.queryForObject("select prijs from bieren where naam = 'test2'", BigDecimal.class);
    }
    @Test
    void create(){
        var bierId = idVanBier();
        var bestelbonId = idVanBestelbon();
        var prijs = prijs();
        repository.create(new Bestelbonlijn(bestelbonId,bierId,2, prijs));
        assertThat(countRowsInTableWhere(BESTELBONLIJN,"bierId="+bierId)).isPositive();
        assertThat(countRowsInTableWhere(BESTELBONLIJN,"bestelbonId="+bestelbonId)).isPositive();
    }
}
