package be.vdab.beers.services;

import be.vdab.beers.domain.Bestelbon;
import be.vdab.beers.repositories.BestelbonRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class BestelbonService {
    private final BestelbonRepository repository;

    public BestelbonService(BestelbonRepository repository) {
        this.repository = repository;
    }
    @Transactional
    public long create(Bestelbon bestelbon){
        return repository.create(bestelbon);
    }
}
