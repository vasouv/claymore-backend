package vs.claymorebackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("claymore")
public class ClaymoreController {

    @Autowired
    private ClaymoreService service;

    @GetMapping
    public List<Claymore> findAll() {
        return service.findAll();
    }

    @GetMapping("{name}")
    public Claymore findByName(@PathVariable String name) {
        return service.findByID(name);
    }

}
