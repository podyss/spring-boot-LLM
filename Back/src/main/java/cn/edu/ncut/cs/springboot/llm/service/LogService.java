package cn.edu.ncut.cs.springboot.llm.service;

import cn.edu.ncut.cs.springboot.llm.entity.Log;

import java.util.List;
import java.util.Optional;

public interface LogService {
    List<Log> findAll();
    Optional findById(Integer id);
    List<Log> findByModel(String model);
    List<Log> findByModelAndUsername(String model, String username);
    void saveChat(Log log);
    void deleteChat(Integer id);
}
