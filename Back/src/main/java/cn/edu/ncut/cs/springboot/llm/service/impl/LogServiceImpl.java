package cn.edu.ncut.cs.springboot.llm.service.impl;

import cn.edu.ncut.cs.springboot.llm.dao.LogDao;
import cn.edu.ncut.cs.springboot.llm.entity.Log;
import cn.edu.ncut.cs.springboot.llm.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private LogDao logDao;

    @Override
    public List<Log> findAll() {
        return logDao.findAll();
    }

    @Override
    public Optional findById(Integer id) {
        return logDao.findById(id);
    }
    @Override
    public List<Log> findByModel(String model) {
        return logDao.findByModel(model);
    }

    @Override
    public List<Log> findByModelAndUsername(String model, String username) {
        return logDao.findByModelAndUsername(model,username);
    }

    @Override
    public void saveChat(Log log) {
        logDao.save(log);
    }

    @Override
    public void deleteChat(Integer id) {
        logDao.deleteById(id);
    }
}
