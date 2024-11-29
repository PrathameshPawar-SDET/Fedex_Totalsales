package fedex_totalsales.Utilities;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;


public class DP {


    @DataProvider(name = "excelReading")

    public Object[][] dpMethod(Method m) throws IOException {
        int rowIndex = 0;
        int cellIndex = 0;
        List<List> outputList = new ArrayList<>();

        // FileInputStream excelFile = new FileInputStream(new File(
        //         "<absolute-path-to-xlsx-file>"));
        FileInputStream excelFile = new FileInputStream(new File(
                "D:\\Projects\\Fedex_Totalsales\\app\\src\\test\\java\\fedex_totalsales\\Data\\DatasetsforFedex.xlsx"));

        XSSFWorkbook workbook = new XSSFWorkbook(excelFile);
        XSSFSheet selectedSheet = workbook.getSheet(m.getName());
        Iterator<Row> iterator = selectedSheet.iterator();
        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            Iterator<Cell> cellIterator = nextRow.cellIterator();
            List<String> innerList = new ArrayList<String>();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                if (rowIndex > 0 && cellIndex > 0) {
                    if (cell.getCellType() == CellType.STRING) {
                        innerList.add(cell.getStringCellValue());
                    } else if (cell.getCellType() == CellType.NUMERIC) {
                        String cellValue = String.valueOf(cell.getNumericCellValue());

                        if (cellValue.endsWith(".0")) {
                            cellValue = cellValue.substring(0, cellValue.length() - 2);
                        }

                        innerList.add(cellValue);
                    }
                }
                cellIndex = cellIndex + 1;
            }
            rowIndex = rowIndex + 1;
            cellIndex = 0;
            if (innerList.size() > 0)
                outputList.add(innerList);
        }

        excelFile.close();

        String[][] stringArray = outputList.stream().map(u -> u.toArray(new String[0])).toArray(String[][]::new);
        return stringArray;

    }
}