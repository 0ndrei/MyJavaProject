package com.example.CoronaVirusInfo.service;

import com.example.CoronaVirusInfo.exception.ResourceNotFoundException;
import com.example.CoronaVirusInfo.models.CoronaData;
import com.example.CoronaVirusInfo.repository.CoronaVirusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoronaVDataService implements CoronaVService{

    @Autowired
    private CoronaVirusRepository repository;

    public List<CoronaData> getAllStats(){
        return repository.findAll();
    }

    @Override
    public CoronaData save(CoronaData coronaData) {
        return repository.save(coronaData);
    }

    @Override
    public List<CoronaData> getAll() {
        return repository.findAll();
    }

    @Override
    public CoronaData get(long id) {
        return repository.findById(id).orElseThrow( () -> new ResourceNotFoundException("CoronaData", "id", id));
    }

    @Override
    public CoronaData update(CoronaData coronaData, long id, CoronaData country, CoronaData latestTotalCases, CoronaData diffFromPrevDay) {
        CoronaData exitingCorona = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("CoronaData", "id", id));
        exitingCorona.setState(coronaData.getState());
        exitingCorona.setId(coronaData.getId());
        exitingCorona.setCountry(coronaData.getCountry());
        exitingCorona.setLatestTotalCases(coronaData.getLatestTotalCases());
        exitingCorona.setDiffFromPrevDay(coronaData.getDiffFromPrevDay());
        repository.save(exitingCorona);
        return null;
    }

    @Override
    public void delete(long id) {
        repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("CoronaData", "id", id));
        repository.deleteById(id);
    }
}
