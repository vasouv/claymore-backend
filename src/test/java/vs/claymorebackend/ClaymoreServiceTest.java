package vs.claymorebackend;

import org.eclipse.collections.api.list.ImmutableList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
            System.out.println(claymore);
            assertThat(claymore.symbol).isNull();
        }

    }

    @Test
    @DisplayName("Find all Claymores killed in the Northern Campaign")
    public void killedInNorthernCampaign() {
        fail();
    }

    @Test
    @DisplayName("Find all Dead/Awakened Claymores")
    public void deadAwakened() {
        fail();
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
        fail();
    }
}