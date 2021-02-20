package vs.claymorebackend;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClaymoreService {

    @Autowired
    ResourceLoader resourceLoader;

    private List<Claymore> claymores;

    @PostConstruct
    public void init() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:claymore.json");
        InputStream inputStream = resource.getInputStream();
        String contents = new BufferedReader(new InputStreamReader(inputStream)).lines().collect(Collectors.joining("\n"));
        claymores = new ObjectMapper().readValue(contents, new TypeReference<List<Claymore>>() {
        });
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
}
