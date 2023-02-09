package cz.muni.fi.cpstars.dal.temporaryData;

import cz.muni.fi.cpstars.dal.classes.StarBasicInfo;
import lombok.Getter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TemporaryStarBasicInfo {
    private static final String FILEPATH = "./src/main/java/cz/muni/fi/cpstars/dal/temporaryData/Renson_Complete.xlsx";

    public static List<StarBasicInfo> loadedData;

//    public static List<StarBasicInfo> loadData() {
//        FileInputStream file = null;
//        Workbook workbook;
//        try {
//            file = new FileInputStream(FILEPATH);
//            workbook = new XSSFWorkbook(file);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        workbook.setMissingCellPolicy(Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
//
//        Sheet sheet = workbook.getSheetAt(0);
//
//        List<Integer> columnIndices = new ArrayList<>();
//
//        List<StarBasicInfo> data = new ArrayList<>();
//        int i = 0;
//        for (Row row : sheet) {
//            int j = 0;
//            if (i > 0) {
//                data.add(new StarBasicInfo());
//            } else {
//                for (Cell cell : row) {
//                    if (i == 0) {
//                        if (cell.getRichStringCellValue().getString().equals("index")) {
//                            columnIndices.add(j);
//                        } else if (cell.getRichStringCellValue().getString().equals("Name")) {
//                            columnIndices.add(j);
//                        } else if (cell.getRichStringCellValue().getString().equals("RA_ICRS (deg)")) {
//                            columnIndices.add(j);
//                        } else if (cell.getRichStringCellValue().getString().equals("DE_ICRS (deg)")) {
//                            columnIndices.add(j);
//                        } else if (cell.getRichStringCellValue().getString().equals("GLON (deg)")) {
//                            columnIndices.add(j);
//                        } else if (cell.getRichStringCellValue().getString().equals("GLAT (deg)")) {
//                            columnIndices.add(j);
//                        }
//                    }
//                    ++j;
//                }
//            }
//
//
//            Cell cell;
//            if (i > 0) {
//                data.get(i - 1).setId((int) row.getCell(columnIndices.get(0)).getNumericCellValue());
//
//                cell = row.getCell(columnIndices.get(1));
//                if (cell == null) {
//                    data.get(i - 1).setName("");
//                } else {
//                    data.get(i - 1).setName(cell.getRichStringCellValue().toString());
//                }
//
//                cell = row.getCell(columnIndices.get(2));
//                if (cell == null || cell.getCellType() == CellType.STRING) {
//                    data.get(i - 1).setIcrsRightAscension("");
//                } else {
//                    data.get(i - 1).setIcrsRightAscension(String.valueOf(cell.getNumericCellValue()));
//                }
//
//                cell = row.getCell(columnIndices.get(3));
//                if (cell == null || cell.getCellType() == CellType.STRING) {
//                    data.get(i - 1).setIcrsDeclination("");
//                } else {
//                    data.get(i - 1).setIcrsDeclination(String.valueOf(cell.getNumericCellValue()));
//                }
//
//                cell = row.getCell(columnIndices.get(4));
//                if (cell == null || cell.getCellType() == CellType.STRING) {
//                    data.get(i - 1).setGalacticLongitude("");
//                } else {
//                    data.get(i - 1).setGalacticLongitude(String.valueOf(cell.getNumericCellValue()));
//                }
//
//                cell = row.getCell(columnIndices.get(5));
//                if (cell == null || cell.getCellType() == CellType.STRING) {
//                    data.get(i - 1).setGalacticLatitude("");
//                } else {
//                    data.get(i - 1).setGalacticLatitude(String.valueOf(cell.getNumericCellValue()));
//                }
//            }
//
//
//            ++i;
//            if (i > 8205) { break; }
//        }
//        System.out.println("Return...");
//        loadedData = data;
//        return data;
//    }

}

