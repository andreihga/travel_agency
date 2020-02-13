package org.com.sda.logic.readers;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.com.sda.dto.CityDTO;
import org.com.sda.dto.ContinentDTO;
import org.com.sda.dto.CountryDTO;
import org.com.sda.entity.City;
import org.com.sda.logic.consts.Consts;
import org.com.sda.logic.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

@Service
public class CityReader {
    @Autowired
    private CityService cityService;

    public void readCitiesFromFile() {

        try {
            FileInputStream file = new FileInputStream(new File(Consts.EXCEL_FILE_PATH));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(1);
            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    CityDTO cityDTO = new CityDTO();
                    cityDTO.setCityName(cell.getStringCellValue());
                    cell = cellIterator.next();
                    CountryDTO countryDTO = new CountryDTO();
                    countryDTO.setCountryName(cell.getStringCellValue());
                    cityDTO.setCountryDTO(countryDTO);
                    cityService.addCity(cityDTO);
                }
            }
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

