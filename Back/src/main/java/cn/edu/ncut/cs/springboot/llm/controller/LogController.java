package cn.edu.ncut.cs.springboot.llm.controller;

import cn.edu.ncut.cs.springboot.llm.entity.Log;
import cn.edu.ncut.cs.springboot.llm.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/log")
public class LogController {
    @Autowired
    private LogService logService;

    @GetMapping("/list")
    public List<Log> listChat() {
        return logService.findAll();
    }

    @GetMapping("/map/{username}")
    public Map<String, List<Log>> mapChatByUsername(@PathVariable(value = "username")String username) {
        Map<String, List<Log>> map = new HashMap<>();
        List<String> list = Arrays.asList("llama3.2","gemma2:2b","qwen2.5:7b","phi3.5:3.8b");
        for (String item : list){
            map.put(item, logService.findByModelAndUsername(item,username));
        }
        return map;
    }

    @GetMapping("/map")
    public Map<String, List<Log>> mapChat() {
        Map<String, List<Log>> map = new HashMap<>();
        List<String> list = Arrays.asList("llama3.2","gemma2:2b","qwen2.5:7b","phi3.5:3.8b");
        for (String item : list){
            map.put(item, logService.findByModel(item));
        }
        return map;
    }

    @GetMapping("/list/{model}")
    public List<Log> findByModel(@PathVariable("model") String model) {
        return logService.findByModel(model);
    }

    @GetMapping("/{id}")
    public Optional<Log> findById(@PathVariable("id") Integer id){
        return logService.findById(id);
    }

    @PostMapping("/add")
    public String add(@RequestBody() Log log){
        logService.saveChat(log);
        return "success!";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Integer id){
        logService.deleteChat(id);
        return "success!";
    }
}
