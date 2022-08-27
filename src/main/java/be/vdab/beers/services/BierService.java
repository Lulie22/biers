package be.vdab.beers.services;

import be.vdab.beers.domain.Bier;
import be.vdab.beers.dto.BierNaamLijst;
import be.vdab.beers.repositories.BierRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional(readOnly = true)
public class BierService {
    private final BierRepository bierRepository;

    public BierService(BierRepository bierRepository) {
        this.bierRepository = bierRepository;
    }

    public long aantalBieren() {

        return bierRepository.aantalBieren();
    }

    public List<BierNaamLijst> findBierByBrouwerId(long id) {
        return bierRepository.findByBrouwerId(id);
    }

    public Optional<Bier> findById(long id) {

        return bierRepository.findById(id);
    }
    @Transactional
    public void update(Bier bier) {
       bierRepository.update(bier);
    }
}
