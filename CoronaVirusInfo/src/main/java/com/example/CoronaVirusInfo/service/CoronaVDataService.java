package com.example.CoronaVirusInfo.service;

import com.example.CoronaVirusInfo.models.CoronaData;
import com.example.CoronaVirusInfo.repository.CoronaVirusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoronaVDataService {

    @Autowired
    private CoronaVirusRepository repository;

    public List<CoronaData> getAllStats(){
        return repository.findAll();
    }
}
