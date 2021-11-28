package com.example.CoronaVirusInfo.controller;

import com.example.CoronaVirusInfo.models.CoronaData;
import com.example.CoronaVirusInfo.service.CoronaVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth/corona")
public class CoronaVController {

    @Autowired
    private CoronaVService coronaVService;

    @PostMapping
    public ResponseEntity<CoronaData> create(@RequestBody CoronaData coronaData){
        return new ResponseEntity<CoronaData>(coronaVService.save(coronaData), HttpStatus.CREATED);
    }

    @GetMapping
    public List<CoronaData> get() {
        return coronaVService.getAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<CoronaData> get(@PathVariable("id") long id){
        return new ResponseEntity<CoronaData>(coronaVService.get(id), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<CoronaData> update(@PathVariable("id") long id, @RequestBody CoronaData coronaData, CoronaData country, CoronaData latestTotalCases, CoronaData diffFromPrevDay){
        return  new ResponseEntity<CoronaData>(coronaVService.update(coronaData, id, country, latestTotalCases, diffFromPrevDay), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable("id") long id) {coronaVService.delete(id);
        return new ResponseEntity<String>("This field has been deleted", HttpStatus.OK);
    }
}