package com.example.CoronaVirusInfo.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class CoronaData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "state", unique = true)
    private String state;

    @Column(name = "country")
    private String country;

    @Column(name = "total_cases_reported")
    private Integer latestTotalCases;

    @Column(name = "changes_since_last_day")
    private Integer diffFromPrevDay;
}
