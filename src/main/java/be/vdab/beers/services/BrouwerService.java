package be.vdab.beers.services;

import be.vdab.beers.domain.Brouwer;
import be.vdab.beers.dto.BrouwerMetGemeente;
import be.vdab.beers.repositories.BrouwerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BrouwerService {
    private final BrouwerRepository brouwerRepository;

    public BrouwerService(BrouwerRepository brouwerRepository) {
        this.brouwerRepository = brouwerRepository;
    }
    public List<BrouwerMetGemeente> findBrouwersMetNaamEnGemeente(){
        return brouwerRepository.findBrouwersMetNaamEnGemeente();
    }
    public Optional<Brouwer> findById(long id){

        return brouwerRepository.findById(id);
    }
}
