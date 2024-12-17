package cn.edu.ncut.cs.springboot.llm.dao;

import cn.edu.ncut.cs.springboot.llm.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LogDao extends JpaRepository<Log,Integer> {

    List<Log> findByModel(String model);
    List<Log> findByModelAndUsername(String model, String username);
}
