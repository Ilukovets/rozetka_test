package helpers;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;

public class ExcelWriter extends ParentHelper
{
    private FileOutputStream fileXLSX;

    private Workbook workbook;
    private Sheet sheet;
    private Row row;

    private List<String> stringList;

    public void createTitleLists(List<List<String>> stringLists, String filePath) throws IOException
    {
        workbook
            = new XSSFWorkbook();
        fileXLSX
            = new FileOutputStream(filePath);

        int countSheet = stringLists.size();
        for (int numberSheet = 0; numberSheet < countSheet; numberSheet++)
        {
            sheet = workbook
                .createSheet(""+numberSheet);

            stringList = stringLists
                .get(numberSheet);

            int countStr = stringList.size();
            for (int num = 0; num < countStr; num++)
            {
                row = sheet
                    .createRow(num);

                row.createCell(0)
                    .setCellValue(stringList.get(num));
            }
        }

        workbook.write(fileXLSX);

        fileXLSX.close();
        workbook.close();
    }

}
