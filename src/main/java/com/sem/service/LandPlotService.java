package com.sem.service;

import com.sem.dto.LandPlotDTO;
import com.sem.dto.LandPlotResponseDTO;

import java.util.List;

public interface LandPlotService {
    
    LandPlotResponseDTO getLandPlot(String koatuu, String zone, String quartal, String parcel);

    List<LandPlotDTO> getLandPlotList(String koatuu);
}
