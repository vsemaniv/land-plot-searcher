package com.sem;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.Map;

public class LandMapClient {

    public Map<String, Object> getLanObject(String koatuu, String zone, String quartal, String parcel) throws URISyntaxException, IOException {

        HttpClient client = HttpClientBuilder.create().build();
        URIBuilder uriBuilder = new URIBuilder();

        uriBuilder.setScheme("http").setHost("").setPath("")
                .setParameter("koatuu", koatuu)
                .setParameter("zone", zone)
                .setParameter("quartal", quartal)
                .setParameter("parcel", parcel);
        HttpGet request = new HttpGet(uriBuilder.build());

        HttpResponse response = client.execute(request);

        System.out.println("Response Code : "
                + response.getStatusLine().getStatusCode());

        BufferedReader rd = null;
        try {
            rd = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        ObjectMapper mapper = new ObjectMapper();

        String json = result.toString().replace("[", "").replace("]", "");

        System.out.println(json);

        return mapper.readValue(json, new TypeReference<Map<String, Object>>() {
        });
    }
}
