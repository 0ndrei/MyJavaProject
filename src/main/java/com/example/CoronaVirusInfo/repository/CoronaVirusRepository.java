package com.example.CoronaVirusInfo.repository;

import com.example.CoronaVirusInfo.models.CoronaData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoronaVirusRepository extends JpaRepository<CoronaData, Long>  {
}
