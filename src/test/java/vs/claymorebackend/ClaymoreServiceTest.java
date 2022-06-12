package vs.claymorebackend;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Arrays;

import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.map.ImmutableMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ClaymoreServiceTest {

  @Autowired ClaymoreService service;

  @Test
  @DisplayName("Find all Claymores with no epitaph")
  public void noEpitaph() {
    ImmutableList<Claymore> claymores = service.noEpitaph();

    for (Claymore claymore : claymores) {
      assertThat(claymore.epitaph()).isEqualTo("None");
    }
  }

  @Test
  @DisplayName("Find all Claymores with no symbol")
  public void noSymbol() {
    ImmutableList<Claymore> claymores = service.noSymbol();

    for (Claymore claymore : claymores) {
      assertThat(claymore.symbol()).isNull();
    }
  }

  @Test
  @DisplayName("Find all Claymores killed in the Northern Campaign")
  public void killedInNorthernCampaign() {
    ImmutableList<Claymore> claymores = service.killedNorthernCampaign();

    for (Claymore claymore : claymores) {
      assertThat(claymore.generation()).containsExactly("Clare");
      assertThat(claymore.fate()).isEqualTo("Dead (Killed in the Northern Campaign)");
    }
  }

  @Test
  @DisplayName("Find all Dead/Awakened Claymores")
  public void deadAwakened() {
    ImmutableList<Claymore> claymores = service.deadAwakened();

    for (Claymore claymore : claymores) {
      assertThat(claymore.fate()).contains(Arrays.asList("Dead", "Awakened"));
    }
  }

  @Test
  @DisplayName("Count all Claymores by generation")
  public void countByGeneration() {
    ImmutableMap<String, Integer> countMap = service.countClaymoresByGeneration();
    assertThat(countMap.keysView()).containsExactlyInAnyOrder("Clare", "Teresa", "Clarice");
    assertThat(countMap.get("Clare")).isEqualTo(31);
    assertThat(countMap.get("Teresa")).isEqualTo(6);
    assertThat(countMap.get("Clarice")).isEqualTo(21);
    fail("Needs more work");
  }

  @Test
  @DisplayName("Find the average rank by generation")
  public void averageRankByGeneration() {
    fail();
  }

  @Test
  @DisplayName("Find the sum of ranks in all generations")
  public void sumOfAllRanksAllGenerations() {
    int sum = service.sumOfAllRanksAllGenerations();
    assertThat(sum).isEqualTo(961);
  }

  @Test
  @DisplayName("Find the distinct generations for all Claymores")
  public void distinctGenerations() {
    ImmutableList<String> generations = service.distinctGenerations();
    assertThat(generations).containsExactlyInAnyOrder("Clare", "Teresa", "Clarice");
  }
}
