package be.vdab.beers.services;

import be.vdab.beers.domain.Brouwer;
import be.vdab.beers.dto.BrouwerMetGemeente;
import be.vdab.beers.repositories.BrouwerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BrouwerServiceTest extends AbstractTransactionalJUnit4SpringContextTests {
    private BrouwerService service;
    @Mock
    private BrouwerRepository repository;
    @BeforeEach
    void beforeEach(){
        service = new BrouwerService(repository);
    }
    @Test
    void findBrouwers(){
        List<BrouwerMetGemeente> brouwer = new ArrayList<>();
                brouwer.add(new BrouwerMetGemeente(1,"test","gemeente"));
        when(repository.findBrouwersMetNaamEnGemeente()).thenReturn(brouwer);
        assertThat(service.findBrouwersMetNaamEnGemeente())
                .isEqualTo(brouwer)
                .hasSize(1);
        verify(repository).findBrouwersMetNaamEnGemeente();

    }
    @Test
    void findById(){
        var brouwer = new Brouwer(1,"test","test","1",1000,"test",1);
        when(repository.findById(1)).thenReturn(Optional.of(brouwer));
        assertThat(service.findById(1)).isEqualTo(Optional.of(brouwer));
        verify(repository).findById(1);


    }

}
