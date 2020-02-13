package org.com.sda.logic.readers;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.com.sda.dto.ContinentDTO;
import org.com.sda.dto.CountryDTO;
import org.com.sda.logic.consts.Consts;
import org.com.sda.logic.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@Service
public class CountryReader {
    @Autowired
    private CountryService countryService;

    public void readCountryFromFile() {
        try {
            FileInputStream file = new FileInputStream(new File(Consts.EXCEL_FILE_PATH));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()){
                    Cell cell = cellIterator.next();
                    CountryDTO countryDTO = new CountryDTO();
                    countryDTO.setCountryName(cell.getStringCellValue());
                    cell = cellIterator.next();
                    ContinentDTO continentDTO = new ContinentDTO();
                    continentDTO.setContinentName(cell.getStringCellValue());
                    countryDTO.setContinentDTO(continentDTO);
                    countryService.addCountries(countryDTO);
                }
            }
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
