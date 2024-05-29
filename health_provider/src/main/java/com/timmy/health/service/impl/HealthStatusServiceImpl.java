package com.timmy.health.service.impl;

import com.timmy.health.service.ExcelGenerator;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

@DubboService(interfaceClass = ExcelGenerator.class)
public class HealthStatusServiceImpl implements ExcelGenerator {
    @Override
    public ByteArrayInputStream generateExcel(Map<String, Object> data) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("個人健檢數據報告");

            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
            headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerCellStyle.setAlignment(HorizontalAlignment.CENTER);

            CellStyle tableCellStyle = workbook.createCellStyle();
            tableCellStyle.setBorderTop(BorderStyle.THIN);
            tableCellStyle.setBorderBottom(BorderStyle.THIN);
            tableCellStyle.setBorderLeft(BorderStyle.THIN);
            tableCellStyle.setBorderRight(BorderStyle.THIN);

            sheet.addMergedRegion(new CellRangeAddress(3, 4, 3, 9));

            Row titleRow = sheet.createRow(3);
            Cell titleCell = titleRow.createCell(3);
            titleCell.setCellValue("個人健檢數據報告");
            titleCell.setCellStyle(headerCellStyle);

            Row headerRow = sheet.createRow(1);
            Cell nameCell = headerRow.createCell(3);
            nameCell.setCellValue("受檢者名稱:");
            nameCell.setCellStyle(tableCellStyle);

            Cell nameValueCell = headerRow.createCell(4);
            nameValueCell.setCellValue((String) data.getOrDefault("name", ""));
            nameValueCell.setCellStyle(tableCellStyle);

            Cell dateCell = headerRow.createCell(8);
            dateCell.setCellValue("檢查日期:");
            dateCell.setCellStyle(tableCellStyle);

            Cell dateValueCell = headerRow.createCell(9);
            dateValueCell.setCellValue((String)data.getOrDefault("reportDate", ""));
            dateValueCell.setCellStyle(tableCellStyle);

            String[] headers = {"身高", "體重", "BMI", "BMR", "卡路里攝取量", "健康狀況"};
            String[] fields = {"height", "weight", "bmi", "bmr", "calories", "healthStatus"};
            for (int i = 0; i < headers.length; i++) {
                Row row = sheet.createRow(6 + i);
                Cell cell = row.createCell(3);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(tableCellStyle);

                Cell valueCell = row.createCell(9);
                if(data.get(fields[i]) instanceof BigDecimal) {
                    valueCell.setCellValue(((BigDecimal) data.get(fields[i])).doubleValue());
                } else if (data.get(fields[i]) instanceof Double) {
                    valueCell.setCellValue((Double) data.get(fields[i]));
                } else if (data.get(fields[i]) instanceof String) {
                    valueCell.setCellValue((String) data.get(fields[i]));
                }
                valueCell.setCellStyle(tableCellStyle);
            }

            for (int i = 3; i <= 9; i++) {
                sheet.autoSizeColumn(i);
            }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            return new ByteArrayInputStream(outputStream.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
