package be.vdab.beers.repositories;

import be.vdab.beers.dto.BrouwerMetGemeente;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

@JdbcTest
@Import(BrouwerRepository.class)
@Sql("/insertBrouwers.sql")
public class BrouwerRepositoryTest extends
        AbstractTransactionalJUnit4SpringContextTests {
    private static final String BROUWERS = "brouwers";
    private final BrouwerRepository repository;

    public BrouwerRepositoryTest(BrouwerRepository repository) {
        this.repository = repository;
    }
    private long idVanTestBrouwer(){
        return jdbcTemplate.queryForObject("select id from brouwers where naam = 'test1'",Long.class);
    }
    @Test
    void findBrouwersMetNaamEnGemeenteGeeftAlleBrouwers(){
        assertThat(repository.findBrouwersMetNaamEnGemeente())
                .hasSize(countRowsInTable(BROUWERS))
                .extracting(BrouwerMetGemeente::getNaamGemeente)
                .isSortedAccordingTo(String::compareToIgnoreCase);
    }
    @Test
    void findById(){
        assertThat(repository.findById(idVanTestBrouwer()))
                .hasValueSatisfying(brouwer ->
                        assertThat(brouwer.getNaam()).isEqualTo("test1"));
    }
    @Test
    void findByOnbestaandeIdVindtGeenBrouwer() {

        assertThat(repository.findById(-1)).isEmpty();
    }
//    @Test
//    void aantalBrouwers(){
//        assertThat(repository.aantalBrouwers()).isPositive();
//    }
}
