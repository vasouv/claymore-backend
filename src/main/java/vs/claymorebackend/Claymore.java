package vs.claymorebackend;

import java.util.List;

public record Claymore(String id, String name, String epitaph, int rank, String type, List<String> techniques,
                       String fate, List<String> generation, String symbol, String profile) {

}
