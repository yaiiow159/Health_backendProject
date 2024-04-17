package com.timmy.health.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Slf4j
public class PoiUtil {

    private static final String xls = "xls";
    private static final String xlsx = "xlsx";
    private static final String DATE_FORMAT = "yyyy/MM/dd";

    public static void checkFile(MultipartFile file) throws IOException {
        if (null == file) {
            throw new FileNotFoundException("文件不存在");
        }
        String fileName = file.getOriginalFilename();
        assert fileName != null;
        if (!fileName.endsWith(xls) && !fileName.endsWith(xlsx)) {
            throw new IOException("格式錯誤 只能傳送excel檔案格式 xls 或 xlsx");
        }
    }

    public static Workbook getWorkBook(@NotNull MultipartFile file) {
        String filename = file.getOriginalFilename();
        Workbook workbook = null;
        try {
            InputStream is = file.getInputStream();
            if (Objects.requireNonNull(filename).endsWith(xls)) {
                workbook = new HSSFWorkbook(is);
            } else if (filename.endsWith(xlsx)) {
                workbook = new XSSFWorkbook(is);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return workbook;
    }

    @NotNull
    public static List<String[]> readExcel(MultipartFile file) throws Exception {
        checkFile(file);
        Workbook workbook;
        List<String[]> list = new ArrayList<>();

        if ((workbook = getWorkBook(file)) != null) {

            for (int sheetNum = 0; sheetNum < workbook.getNumberOfSheets(); sheetNum++) { //get the sheets that the workbook has
                Sheet sheet = workbook.getSheetAt(sheetNum);

                int firstRowNum = sheet.getFirstRowNum(); //the first row that has a data
                int lastRowNum = sheet.getLastRowNum(); // the last row that has a data

                for (int rowNum = firstRowNum + 1; rowNum <= lastRowNum; rowNum++) {

                    Row row = sheet.getRow(rowNum);
                    if (null == row) continue;

                    int firstCellNum = row.getFirstCellNum();
                    int lastCellNum = row.getPhysicalNumberOfCells(); //get the existing cells of number
                    String[] cells = new String[row.getPhysicalNumberOfCells()];

                    for (int cellNum = firstCellNum; cellNum < lastCellNum; cellNum++) {
                        Cell cell = row.getCell(cellNum);
                        cells[cellNum] = getCellValue(cell);
                    }
                    list.add(cells);
                }
            }
            workbook.close();
        }
        return list;
    }


    @Nullable
    private static String getCellValue(@NotNull Cell cell) {
        String cellValue;
        String dataFormatString = cell.getCellStyle().getDataFormatString();

        if (dataFormatString.equals("m/d/yy")) {
            cellValue = new SimpleDateFormat(DATE_FORMAT).format(cell.getDateCellValue());
            return cellValue;
        }

        DataFormatter dataFormatter = new DataFormatter();
        return dataFormatter.formatCellValue(cell);
    }

}
