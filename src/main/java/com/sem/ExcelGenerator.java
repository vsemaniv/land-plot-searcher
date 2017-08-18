package com.sem;

import com.sem.dto.LandPlotDTO;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class ExcelGenerator {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelGenerator.class);

    public static String FILE_PATH = "/home/vseman/SS/VMS/LandPlotSearcher/out/";

    public void generateLandPlotReport(List<LandPlotDTO> landPlotList, String koatuu) {
        
        LOGGER.debug("In generateLandPlotReport");

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Земельні ділянки");

        //create header
        int rowNum = 0;
        Row headerRow = sheet.createRow(rowNum++);
        int headerCellNum = 0;
        headerRow.createCell(headerCellNum++).setCellValue("koatuu");
        headerRow.createCell(headerCellNum++).setCellValue("zona");
        headerRow.createCell(headerCellNum++).setCellValue("kvartal");
        headerRow.createCell(headerCellNum++).setCellValue("parcel");
        headerRow.createCell(headerCellNum++).setCellValue("cadnum");
        headerRow.createCell(headerCellNum++).setCellValue("ownershipcode");
        headerRow.createCell(headerCellNum++).setCellValue("purpose");
        headerRow.createCell(headerCellNum++).setCellValue("use");
        headerRow.createCell(headerCellNum++).setCellValue("area");
        headerRow.createCell(headerCellNum++).setCellValue("unit_area");
        headerRow.createCell(headerCellNum++).setCellValue("ownershipvalue");
        headerRow.createCell(headerCellNum++).setCellValue("id_office");

        for (LandPlotDTO landPlotDTO : landPlotList) {
            int cellNum = 0;

            Row row = sheet.createRow(rowNum++);

            row.createCell(cellNum++).setCellValue(landPlotDTO.getKoatuu());
            row.createCell(cellNum++).setCellValue(landPlotDTO.getZona());
            row.createCell(cellNum++).setCellValue(landPlotDTO.getKvartal());
            row.createCell(cellNum++).setCellValue(landPlotDTO.getParcel());
            row.createCell(cellNum++).setCellValue(landPlotDTO.getCadNum());
            row.createCell(cellNum++).setCellValue(landPlotDTO.getOwnershipCode());
            row.createCell(cellNum++).setCellValue(landPlotDTO.getPurpose());
            row.createCell(cellNum++).setCellValue(landPlotDTO.getUse());
            row.createCell(cellNum++).setCellValue(landPlotDTO.getArea());
            row.createCell(cellNum++).setCellValue(landPlotDTO.getUnitArea());
            row.createCell(cellNum++).setCellValue(landPlotDTO.getOwnershipValue());
            row.createCell(cellNum++).setCellValue(landPlotDTO.getOfficeId());
        }

        try (OutputStream outputStream = new FileOutputStream(FILE_PATH + "LandPlotReport" + koatuu + ".xlsx")) {
            workbook.write(outputStream);
            LOGGER.info("Generation of excel file finished successfully");
        } catch (IOException e) {
            LOGGER.error("Error during generation of excel report", e);
        }       
    }
}
