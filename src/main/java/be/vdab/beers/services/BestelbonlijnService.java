package be.vdab.beers.services;

import be.vdab.beers.domain.Bestelbon;
import be.vdab.beers.domain.Bestelbonlijn;
import be.vdab.beers.exceptions.BierNietGevondenException;
import be.vdab.beers.repositories.BestelbonlijnRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Transactional(readOnly = true)
public class BestelbonlijnService {
    private final BestelbonlijnRepository bestelbonlijnRepository;
    private final BierService bierService;
    private final BestelbonService bestelbonService;

    public BestelbonlijnService(BestelbonlijnRepository bestelbonlijnRepository,
                                BierService bierService, BestelbonService bestelbonService) {
        this.bestelbonlijnRepository = bestelbonlijnRepository;
        this.bierService = bierService;
        this.bestelbonService = bestelbonService;
    }

    @Transactional
    public long bestelbonBevestigen(Map<Long,Integer> bieren, Bestelbon bestelbon){
        try {
            var bestelbonId = bestelbonService.create(bestelbon);
            bieren.entrySet().stream().forEach(entry -> bierService.findById(entry.getKey()).ifPresent(bier -> {
                bierService.update(bier);
                bestelbonlijnRepository
                        .create(new Bestelbonlijn(bestelbonId, bier.getId(), entry.getValue(), bier.getPrijs()));
            }));
            return bestelbonId;
        }catch (BierNietGevondenException ex){
            throw new BierNietGevondenException();
        }
    }
}













