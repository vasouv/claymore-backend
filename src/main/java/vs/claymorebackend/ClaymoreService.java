package vs.claymorebackend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ClaymoreService {

  @Autowired ResourceLoader resourceLoader;

  private List<Claymore> claymores;

  @PostConstruct
  public void init() throws IOException {
    Resource resource = resourceLoader.getResource("classpath:claymore.json");
    InputStream inputStream = resource.getInputStream();
    String contents =
        new BufferedReader(new InputStreamReader(inputStream))
            .lines()
            .collect(Collectors.joining("\n"));
    claymores = new ObjectMapper().readValue(contents, new TypeReference<List<Claymore>>() {});
  }

  public List<Claymore> findAll() {
    return claymores;
  }

  public Claymore findByID(String id) {
    return claymores.stream().filter(claymore -> claymore.id.equals(id)).findFirst().get();
  }

  public ImmutableList<Claymore> noEpitaph() {
    MutableList<Claymore> list = FastList.newList(this.claymores);
    return list.select(claymore -> claymore.epitaph.equals("None")).toImmutable();
  }

  public ImmutableList<Claymore> noSymbol() {
    MutableList<Claymore> list = FastList.newList(this.claymores);
    return list.select(claymore -> claymore.symbol == null).toImmutable();
  }

  public ImmutableList<Claymore> killedNorthernCampaign() {
    MutableList<Claymore> list = FastList.newList(this.claymores);
    return list.select(claymore -> claymore.generation.contains("Clare"))
        .select(claymore -> claymore.fate.contains("Dead (Killed in the Northern Campaign)"))
        .toImmutable();
  }

  public ImmutableList<Claymore> deadAwakened() {
    MutableList<Claymore> list = FastList.newList(this.claymores);

    Predicate<Claymore> deadAwakened =
        claymore -> claymore.fate.contains("Dead") && claymore.fate.contains("Awakened");

    return list.reject(claymore -> claymore.fate.equals("Alive"))
        .select(deadAwakened)
        .toImmutable();
  }

  public ImmutableList<String> distinctGenerations() {
    MutableList<Claymore> list = FastList.newList(this.claymores);
    return list.flatCollect(claymore -> claymore.generation).distinct().toImmutable();
  }
}
