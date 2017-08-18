package com.sem;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class App {

    public static void main(String[] args) throws IOException, URISyntaxException {

        LandMapClient landMapClient = new LandMapClient();

        ExcelGenerator excelGenerator = new ExcelGenerator();
        List list = new ArrayList();
        String koatuu = "2610400000";
        String zone =  "08";
        String quartal =  "009";
        String parcel =  "0021";

        Map<String, Object> responseMap =  landMapClient.getLanObject(koatuu, zone, quartal, parcel);
        if(responseMap.containsKey("data") && !((Map) responseMap.get("data")).isEmpty()) {
            Map<String, Object> plotMap = (Map<String, Object>) responseMap.get("data");
            list.add(plotMap);
        }

        excelGenerator.generateLandPlotReport(list);
    }
}
