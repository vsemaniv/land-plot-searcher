package com.sem;

import com.sem.dto.LandPlotResponseDTO;
import com.sem.service.LandPlotService;
import com.sem.service.LandPlotServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;

public class App {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) throws IOException, URISyntaxException {

        LOGGER.info("START GENERATION");
      
        LandPlotService landPlotService = new LandPlotServiceImpl();

        String koatuu = "2610400000";
        String zone =  "08";
        String quartal =  "009";
        String parcel =  "0021";

        LandPlotResponseDTO landPlotDTO = landPlotService.getLandPlot(koatuu, zone, quartal, parcel);
        
/*        ExcelGenerator excelGenerator = new ExcelGenerator();
       
        excelGenerator.generateLandPlotReport(landPlotService.getLandPlotList(koatuu), koatuu);*/

        LOGGER.info("FINISH GENERATION");
    }
}
