package com.sem.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sem.ExcelGenerator;
import com.sem.dto.LandPlotDTO;
import com.sem.dto.LandPlotResponseDTO;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class LandPlotServiceImpl implements LandPlotService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelGenerator.class);

    private static HttpClient client = HttpClientBuilder.create().build();
    
    @Override
    public LandPlotResponseDTO getLandPlot(String koatuu, String zone, String quartal, String parcel) {
        
        LOGGER.info("In getLandPlot, koatuu: {}, zone: {}, quartal: {}, parcel: {}", koatuu, zone, quartal, parcel);

        URIBuilder uriBuilder = new URIBuilder();

        uriBuilder.setScheme("http")
                .setHost("")
                .setPath("")
                .setParameter("koatuu", koatuu)
                .setParameter("zone", zone)
                .setParameter("quartal", quartal)
                .setParameter("parcel", parcel);

        LandPlotResponseDTO landPlotResponseDTO = null;

        try {
            HttpGet request = new HttpGet(uriBuilder.build());
            HttpResponse response = client.execute(request);

            LOGGER.info("Response Code : "
                    + response.getStatusLine().getStatusCode());

            BufferedReader rd = null;

            rd = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));

            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }

            // To avoid list creating, API returns only one land plot per one request
            String json = result.toString().replace("[", "").replace("]", "");

            LOGGER.info(json);

            landPlotResponseDTO = new ObjectMapper().readValue(json, LandPlotResponseDTO.class);
        } catch (URISyntaxException e) {
            LOGGER.error(e.getMessage());
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
        return landPlotResponseDTO;
    }

    @Override
    public List<LandPlotDTO> getLandPlotList(String koatuu) {

        List<LandPlotDTO> list = new ArrayList<>();

        for (int zoneInt = 1; zoneInt < 99; zoneInt++) {

            String zone = String.format("%02d", zoneInt);

            for (int quartalInt = 1; quartalInt < 999; quartalInt++) {

                String quartal = String.format("%03d", quartalInt);

                //To avoid redundant calls to API if there are no more parcels in the quartal
                int absentCount = 0;
                for (int parcelInt = 1; parcelInt < 9999 && absentCount < 50; parcelInt++) {

                    String parcel = String.format("%04d", parcelInt);

                    LandPlotResponseDTO landPlotResponseDTO = getLandPlot(koatuu, zone, quartal, parcel);

                    if (landPlotResponseDTO.getData() != null) {
                        list.add(landPlotResponseDTO.getData());
                    } else {
                        absentCount++;
                    }
                }
            }
        }
        return list;
    }
}
