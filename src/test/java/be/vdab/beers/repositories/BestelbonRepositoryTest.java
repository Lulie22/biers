package be.vdab.beers.repositories;

import be.vdab.beers.domain.Bestelbon;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import static org.assertj.core.api.Assertions.assertThat;
@JdbcTest
@Import(BestelbonRepository.class)
@Sql("/insertBestelbonnen.sql")
public class BestelbonRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private final BestelbonRepository repository;
    private final String BESTELBONNEN = "bestelbonnen";
    public BestelbonRepositoryTest(BestelbonRepository repository) {
        this.repository = repository;
    }
    @Test
    void create(){
        var id = repository.create(new Bestelbon(1,"test","test","1",1000,"Leuven"));
        assertThat(id).isPositive();
        assertThat(countRowsInTableWhere(BESTELBONNEN,"id="+id)).isOne();
    }
}
