package com.example.CoronaVirusInfo.parser;

import com.example.CoronaVirusInfo.models.CoronaData;
import com.example.CoronaVirusInfo.repository.CoronaVirusRepository;
import lombok.SneakyThrows;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import java.util.Date;
import java.util.Timer;

@Component
public class InfoParser implements CommandLineRunner {
    private final String VIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";

    @Autowired
    private CoronaVirusRepository repository;

    @Override
    public void run(String... args) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @SneakyThrows
            @Override
            public void run() {
                fetchVirusData();
            }
        }, 0, 300000);
    }

    public void fetchVirusData() throws IOException, InterruptedException {
        this.repository.deleteAll();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(VIRUS_DATA_URL))
                .build();
        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
        StringReader csvBodyReader = new StringReader(httpResponse.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
        for (CSVRecord record : records) {
            CoronaData coronaData = new CoronaData();
            coronaData.setState(record.get("Province/State"));
            coronaData.setCountry(record.get("Country/Region"));
            int latestCases = Integer.parseInt(record.get(record.size() - 1));
            int prevDayCases = Integer.parseInt(record.get(record.size() - 2));
            coronaData.setLatestTotalCases(latestCases);
            coronaData.setDiffFromPrevDay(latestCases - prevDayCases);
            this.repository.save(coronaData);
        }
    }
}