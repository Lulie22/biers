package be.vdab.beers.repositories;

import be.vdab.beers.domain.Bier;
import be.vdab.beers.dto.BierNaamLijst;
import be.vdab.beers.exceptions.BierNietGevondenException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import java.math.BigDecimal;

@JdbcTest
@Import(BierRepository.class)
@Sql({"/insertBrouwers.sql","/insertBieren.sql"})

public class BierRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private final BierRepository repository;
    private final String BIEREN = "bieren";

    public BierRepositoryTest(BierRepository repository) {
        this.repository = repository;
    }
    private long idVanTestBier(){
        return jdbcTemplate.queryForObject(
                "select id from bieren where naam ='test1'", Long.class);
    }
    private long idVanTestBrouwer(){
        return jdbcTemplate.queryForObject(
                "select id from brouwers where naam = 'test1'", Long.class);
    }
    @Test
    void findById(){
        assertThat(repository.findById(idVanTestBier()))
                .hasValueSatisfying(bier -> assertThat(bier.getNaam()).isEqualTo("test1"));
    }
    @Test
    void findByOnbestaandeIdVindtGeenBier(){
        assertThat(repository.findById(-1)).isEmpty();
    }
    @Test
    void aantalBieren(){
        assertThat(countRowsInTable(BIEREN)).isPositive();
    }
    @Test
    void findByBrouwerIdGeeftAlleBierenGesorteerdOpNaam(){

        assertThat(repository.findByBrouwerId(idVanTestBrouwer()))
                .hasSize(countRowsInTableWhere(BIEREN,"brouwerId="+idVanTestBrouwer()))
                .extracting(BierNaamLijst::getNaam)
                .isSortedAccordingTo(String::compareToIgnoreCase);

    }
    @Test
    void findByOnbestaandeBrouwerIdGeeftEenLegeLiijst(){
        assertThat(repository.findByBrouwerId(-1L)).isEmpty();
    }
    @Test
    void Update(){
        repository.findById(idVanTestBier()).ifPresent(repository::update);
        assertThat(countRowsInTableWhere(BIEREN,"besteld = 1 and id ="+idVanTestBier())).isOne();
    }
    @Test
    void updateOnbestaandeBier(){
        assertThatExceptionOfType(BierNietGevondenException.class)
                .isThrownBy(()->repository.update(
                        new Bier(-1,"test",-1,5,BigDecimal.TEN,0)));
    }
}
