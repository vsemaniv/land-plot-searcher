package com.sem.dto;

import java.io.Serializable;

public class LandPlotResponseDTO implements Serializable {

    private static final long serialVersionUID = 3536783279426710683L;

    private boolean status;
    private LandPlotDTO data;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public LandPlotDTO getData() {
        return data;
    }

    public void setData(LandPlotDTO data) {
        this.data = data;
    }
}
