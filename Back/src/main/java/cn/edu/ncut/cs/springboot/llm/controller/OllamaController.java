package cn.edu.ncut.cs.springboot.llm.controller;

import cn.edu.ncut.cs.springboot.llm.entity.Log;
import cn.edu.ncut.cs.springboot.llm.service.LogService;
import jakarta.annotation.Resource;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class OllamaController {
    @Autowired
    private LogService logService;

    @Resource
    private OllamaChatModel ollamaChatModel;
    ExecutorService executor = Executors.newCachedThreadPool();

    @RequestMapping(value = "/ai/chat/{model}")
    public Object chat(@PathVariable("model")String model, @RequestParam(value = "username")String username,
                       @RequestParam(value = "msg")String msg){
        System.out.println(msg);
        ChatResponse chatResponse=ollamaChatModel.call(new Prompt(msg, OllamaOptions.create()
                .withModel(model)
                .withTemperature(0.4)));
        String content = chatResponse.getResult().getOutput().getContent();
        System.out.println(content);
        Log log = new Log(model,username,msg,content);
        logService.saveChat(log);
        return content;
    }
    @RequestMapping(value = "/ai/stream_chat/{model}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ChatResponse> stream_chat(@PathVariable("model")String model, @RequestParam(value = "username")String username,
                                          @RequestParam(value = "msg")String msg){
        System.out.println(msg);
        Flux<ChatResponse> chatResponse=ollamaChatModel.stream(new Prompt(msg, OllamaOptions.create()
                .withModel(model)
                .withTemperature(0.4)));
        executor.submit(new Runnable() {
            @Override
            public void run() {
                recordLog(model,username,msg,chatResponse);
            }
        });
        return chatResponse;
    }

    public void recordLog(String model, String username, String msg, Flux<ChatResponse> chatResponse) {
        final String[] content = {""};
        executor.submit(()->{
            chatResponse.doOnComplete(()->{
                System.out.println(content[0]);
                Log log = new Log(model,username,msg,content[0]);
                logService.saveChat(log);
            }).subscribe(
                    value -> content[0] += value.getResult().getOutput().getContent(),
                    error -> System.out.println("Error: " + error.getMessage()),
                    () -> {}
            );
        });
    }
}