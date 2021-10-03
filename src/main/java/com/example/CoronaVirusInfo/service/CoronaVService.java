package com.example.CoronaVirusInfo.service;

import com.example.CoronaVirusInfo.models.CoronaData;

import java.util.List;

public interface CoronaVService {
    CoronaData save (CoronaData coronaData);
    List<CoronaData> getAll();
    CoronaData get(long id);
    CoronaData update(CoronaData coronaData, long id, CoronaData country, CoronaData latestTotalCases, CoronaData diffFromPrevDay);
    void delete(long id);
}
