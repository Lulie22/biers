package be.vdab.beers.sessions;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Component
@SessionScope
public class Mandje {
    private final Map<Long, Integer> bieren = new HashMap<>();
    private BigDecimal totaal = BigDecimal.ZERO;

    public void  toevoeg(long id, int aantal) {
        if(bieren.containsKey(id)) {
            bieren.put(id, bieren.get(id) + aantal);
        } else {
            bieren.put(id, aantal);
        }
    }
    public void addTotaal(BigDecimal prijs) {

        totaal = totaal.add(prijs);
    }
    private void setTotaal(BigDecimal nieuweTotaal){
        totaal = nieuweTotaal;
    }

    public Map<Long, Integer> getBieren() {

        return bieren;
    }
    public BigDecimal getTotaal() {

        return totaal;
    }
    public boolean isGevuld() {

        return !bieren.isEmpty();
    }
    public void delete() {
        bieren.clear();
        totaal = BigDecimal.ZERO;
    }
    public void removeItem(long id, BigDecimal teBetalen){
        setTotaal(teBetalen);
        bieren.remove(id);

    }
}
