package com.destiny.api.util;

import com.destiny.api.exception.CustomException;
import com.google.common.collect.Lists;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @ClassName POIUtils
 * @Author Administrator
 * @Date 2019/8/1222:48
 * @Version 1.0
 **/
public class POIUtils {

    public static List<String[]> readExcel(MultipartFile file){
        if (!checkFile(file)){
            return null;
        }
        Workbook workbook = getWorkBool(file);
        int sheets = workbook.getNumberOfSheets();
        List<String[]> content = Lists.newArrayList();
        for (int i = 0; i < sheets; i++) {
            Sheet sheet = workbook.getSheetAt(i);
            if (sheet == null){
                continue;
            }
            int firstRowNum = sheet.getFirstRowNum();
            int lastRowNum = sheet.getLastRowNum();
            for (int j = firstRowNum; j < lastRowNum; j++) {
                Row row = sheet.getRow(j);
                if (row == null){
                    continue;
                }
                int firstCellNum = row.getFirstCellNum();
                int lastCellNum = row.getLastCellNum();
                String[] cellValues = new String[lastCellNum];
                for (int k = firstCellNum; k <= lastCellNum; k++) {
                    cellValues[k] = getCellFormatValue(row.getCell(k));
                }
                content.add(cellValues);
            }
        }
       return content;
    }

    public static Boolean checkFile(MultipartFile file){
        if (file == null || file.isEmpty()){
            throw new CustomException("文件不存在或者文件为空", HttpStatus.BAD_REQUEST);
        }
        String filename = file.getOriginalFilename();
        return filename.endsWith("xlsx")|| filename.endsWith("xls");
    }

    public static Workbook getWorkBool(MultipartFile file){
        try (InputStream in = file.getInputStream()){
            String filename = file.getOriginalFilename();
            if (filename.endsWith("xls")){
                return new HSSFWorkbook(in);
            } else if (filename.endsWith("xlsx")){
                return new XSSFWorkbook(in);
            }else{
                throw new CustomException(String.format("不支持%s该类型",filename.split(".")[1]), HttpStatus.BAD_REQUEST);
            }
        }catch (IOException e ){
            e.printStackTrace();
        }
        return null;
    }

    public static String getCellFormatValue(Cell cell){
        String cellValue = "";
        if (cell != null){
            switch (cell.getCellType()){
                case STRING:
                    cellValue = cell.getStringCellValue();
                    break;
                case NUMERIC:
                    cellValue = String.valueOf(cell.getNumericCellValue());
                    break;
                case BOOLEAN:
                     cellValue = String.valueOf(cell.getBooleanCellValue());
                    break;
                case BLANK:
                    cellValue = "";
                    break;
                case FORMULA:
                    cellValue = String.valueOf(cell.getCellFormula());
                    break;
                case ERROR:
                    cellValue= "非法字符";
                    break;
                default:
                    cellValue="未知字符";
            }
        }
        return cellValue;
    }
}
