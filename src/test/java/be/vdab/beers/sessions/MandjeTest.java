package be.vdab.beers.sessions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

public class MandjeTest {
    private Mandje mandje;

    @BeforeEach
    void beforeEach() {
        this.mandje = new Mandje();
    }

    @Test
    void eenNieuwMandjeIsLeeg() {
        assertThat(mandje.getBieren()).isEmpty();
        assertThat(mandje.isGevuld()).isFalse();
    }
    @Test
    void mandjeIsGevuldnadatJeEenItemInHetMandjeLeg(){
        mandje.toevoeg(1,4);
        assertThat(mandje.isGevuld()).isTrue();
    }

    @Test
    void nadatJeEenItemInHetMandjeLegtBevatDitMandjeEnkelDitItem() {
        mandje.toevoeg(1,4);
        assertThat(mandje.getBieren()).isNotEmpty();
        assertThat(mandje.isGevuld()).isTrue();
        assertThat(mandje.getBieren().containsKey(1));
        assertThat(mandje.getBieren().values().equals(4));
        assertThat(mandje.getBieren().containsKey(2)).isFalse();
    }

    @Test
    void jeKanEenItemMaarÉénKeerToevoegenEnAantalOpsommenAanHetMandje() {
        mandje.toevoeg(1,3);
        mandje.toevoeg(1,4);

        assertThat(mandje.getBieren().size()).isEqualTo(1);
        assertThat(mandje.getBieren().values().equals(7));

    }

    @Test
    void nadatJeTweeItemsInHetMandjeLegtBevatDitMandjeEnkelDieItems() {
        mandje.toevoeg(1,2);
        mandje.toevoeg(2,3);
        assertThat(mandje.getBieren()).containsEntry(1L,2);
        assertThat(mandje.getBieren()).containsOnlyKeys(1L,2L);
        assertThat(mandje.getBieren()).containsExactly(entry(1L,2),entry(2L,3));
    }
    @Test
    void nadatJeItemDeletedMandjeZalLeeg() {
        mandje.toevoeg(2,3);
        mandje.toevoeg(4,3);
        mandje.delete();
        assertThat(mandje.getBieren()).isEmpty();
    }

}
