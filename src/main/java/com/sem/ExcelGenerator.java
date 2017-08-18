package com.sem;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

public class ExcelGenerator {

    public static String FILE_PATH = "/home/vseman/SS/VMS/LandPlotSearcher/out/";

    /*TODO: temporal list of map should be DTO object*/
    public void generateLandPlotReport(List<Map<String, Object>> responseMapList) {
        System.out.println("Start");

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
        headerRow.createCell(headerCellNum++).setCellValue("area");
        headerRow.createCell(headerCellNum++).setCellValue("unit_area");
        headerRow.createCell(headerCellNum++).setCellValue("ownershipvalue");
        headerRow.createCell(headerCellNum++).setCellValue("id_office");

        String fileName = "LandPlotReport";

        for (Map<String, Object> responseMap : responseMapList) {
            if (responseMap.containsKey("data") && !((Map) responseMap.get("data")).isEmpty()) {
                Map<String, Object> plotMap = (Map<String, Object>) responseMap.get("data");

                fileName += plotMap.get("koatuu");
                int cellNum = 0;

                Row row = sheet.createRow(rowNum++);

                row.createCell(cellNum++).setCellValue((Long) plotMap.get("koatuu"));
                row.createCell(cellNum++).setCellValue((Integer) plotMap.get("zona"));
                row.createCell(cellNum++).setCellValue((Integer) plotMap.get("kvartal"));
                row.createCell(cellNum++).setCellValue((Integer) plotMap.get("parcel"));
                row.createCell(cellNum++).setCellValue((String) plotMap.get("cadnum"));
                row.createCell(cellNum++).setCellValue((Integer) plotMap.get("ownershipcode"));
                row.createCell(cellNum++).setCellValue((String) plotMap.get("purpose"));
                row.createCell(cellNum++).setCellValue((String) plotMap.get("area"));
                row.createCell(cellNum++).setCellValue((String) plotMap.get("unit_area"));
                row.createCell(cellNum++).setCellValue((String) plotMap.get("ownershipvalue"));
                row.createCell(cellNum++).setCellValue((Integer) plotMap.get("id_office"));
            }
        }


        try {
            OutputStream outputStream = new FileOutputStream(FILE_PATH + fileName + ".xlsx");
            workbook.write(outputStream);
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Done");
    }
}
