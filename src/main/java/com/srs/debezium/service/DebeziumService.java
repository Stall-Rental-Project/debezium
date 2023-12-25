package com.srs.debezium.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.srs.debezium.DebeziumApplication;
import com.srs.debezium.constant.DatabaseConfig;
import com.srs.debezium.constant.DebeziumConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class DebeziumService implements CommandLineRunner {
    private final Logger logger= LoggerFactory.getLogger(DebeziumApplication.class);
    private final RestTemplate restTemplate;
    public DebeziumService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }
    private final ObjectMapper objectMapper;

    @Value("${debezium.host}")
    private String apiUrl;

    public String getApiUrl() {
        return apiUrl;
    }

    @Override
    public void run(String... args) throws Exception {
        var marketConnector= new DebeziumConnector("market",DatabaseConfig.getDatabaseConfig(DatabaseConfig.MARKET));

        registerConnector(marketConnector);
    }

    public Map<String, Object> convertInstanceToMap(Object instance) throws Exception {
        String json = objectMapper.writeValueAsString(instance);
        return objectMapper.readValue(json, Map.class);
    }

    public void registerConnector(DebeziumConnector debeziumConnector) throws Exception {
        var apiUrl = String.format("http://%s/connectors", getApiUrl());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        var request = new HashMap<String, Object>();
        request.put("name", debeziumConnector.getName());
        request.put("config",convertInstanceToMap(debeziumConnector));

        ResponseEntity<LinkedHashMap> registerResponse;
        try {
                registerResponse = restTemplate.postForEntity(apiUrl, request, LinkedHashMap.class);
            if (registerResponse.getStatusCode().is2xxSuccessful()) {
                logger.info("Register successfully for {}",debeziumConnector.getName());
            }
        } catch (HttpClientErrorException e){
                if(e.getRawStatusCode()==409){
                    logger.warn("WARN: Error when registering connector with name: {}. Detail: {}. Trying to update instead", debeziumConnector.getName());
                    updateConnector(debeziumConnector);
                } else {
                    logger.error("Can't update Connector {}",e.getLocalizedMessage());
                }
        }

    }

    public void updateConnector(DebeziumConnector debeziumConnector) throws Exception {
        var apiUrl = String.format("http://%s/connectors/%s/config", getApiUrl(),debeziumConnector.getName());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        var entity = new HttpEntity<>(convertInstanceToMap(debeziumConnector), headers);

        var updateResponse = restTemplate.exchange(
                apiUrl,
                HttpMethod.PUT,
                entity,
                LinkedHashMap.class
        );

        if (updateResponse.getStatusCode().value()>299) {
            throw new RuntimeException("Can't update Connector " + debeziumConnector.getName());
        } else {
            logger.info("Update successfully for {}",debeziumConnector.getName());
        }
    }

}
