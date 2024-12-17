package cn.edu.ncut.cs.springboot.llm.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.Date;
import jakarta.persistence.*;

@Entity
@Table(name = "log", indexes = {
        @Index(columnList = "model"),
        @Index(columnList = "username")
})
@Data
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(value = "id")
    @Column(name="id")
    private int id;

    @JsonProperty(value = "model")
    @Column(name="model",nullable = false)
    private String model;

    @JsonProperty(value = "username")
    @Column(name="username",nullable = false)
    private String username;

    @JsonProperty(value = "question")
    @Column(name="question",nullable = false,length = 65536)
    private String question;

    @JsonProperty(value = "content")
    @Column(name="content",nullable = false,length = 65536)
    private String content;

    @JsonProperty(value = "date")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name="date",nullable = false)
    private Date date;

    public void print(){
        System.out.println("id:"+id+",model:"+model+",content:"+content);
    }

    public Log(String model, String username, String question, String content) {
        this.model = model;
        this.username = username;
        this.question=question;
        this.content = content;
        this.date = new Date();
    }
    public Log() {

    }
}