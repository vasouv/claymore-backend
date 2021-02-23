package vs.claymorebackend;

import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.set.ImmutableSet;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ClaymoreServiceTest {

    @Autowired
    ClaymoreService service;

    @Test
    @DisplayName("Find all Claymores with no epitaph")
    public void noEpitaph() {
        ImmutableList<Claymore> claymores = service.noEpitaph();

        for (Claymore claymore : claymores) {
            assertThat(claymore.epitaph).isEqualTo("None");
        }
    }

    @Test
    @DisplayName("Find all Claymores with no symbol")
    public void noSymbol() {
        ImmutableList<Claymore> claymores = service.noSymbol();

        for (Claymore claymore : claymores) {
            assertThat(claymore.symbol).isNull();
        }

    }

    @Test
    @DisplayName("Find all Claymores killed in the Northern Campaign")
    public void killedInNorthernCampaign() {
        ImmutableList<Claymore> claymores = service.killedNorthernCampaign();

        for (Claymore claymore : claymores) {
            assertThat(claymore.generation).containsExactly("Clare");
            assertThat(claymore.fate).isEqualTo("Dead (Killed in the Northern Campaign)");
        }
    }

    @Test
    @DisplayName("Find all Dead/Awakened Claymores")
    public void deadAwakened() {
        ImmutableList<Claymore> claymores = service.deadAwakened();

        for (Claymore claymore : claymores) {
            assertThat(claymore.fate).contains(Arrays.asList("Dead", "Awakened"));
        }
    }

    @Test
    @DisplayName("Order by rank")
    public void orderByRank() {
        fail();
    }

    @Test
    @DisplayName("Count all Claymores by generation")
    public void countByGeneration() {
        fail();
    }

    @Test
    @DisplayName("Find the average rank by generation")
    public void averageRankByGeneration() {
        fail();
    }

    @Test
    @DisplayName("Find the sum of ranks in all generations")
    public void sumOfAllRanksAllGenerations() {
        fail();
    }

    @Test
    @DisplayName("Find the distinct generations for all Claymores")
    public void distinctGenerations() {
        ImmutableList<String> generations = service.distinctGenerations();
        assertThat(generations).containsExactlyInAnyOrder("Clare", "Teresa", "Clarice");
    }

}