package vs.claymorebackend;

import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@ToString
@NoArgsConstructor
public class Claymore {

    public String id;
    public String name;
    public String epitaph;
    public int rank;
    public String type;
    public List<String> techniques;
    public String fate;
    public List<String> generation;
    public String symbol;
    public String profile;

}
