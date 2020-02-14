package org.com.sda.logic.readers;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.com.sda.dto.*;
import org.com.sda.logic.consts.Consts;
import org.com.sda.logic.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.Iterator;

@Service
public class HotelReader {
    @Autowired
    private HotelService hotelService;

    public void readHotelFromFile() {
        try {
            FileInputStream file = new FileInputStream(new File(Consts.EXCEL_FILE_PATH));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(4);
            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    HotelDTO hotelDTO = new HotelDTO();
                    hotelDTO.setHotelName(cell.getStringCellValue());
                    cell = cellIterator.next();
                    hotelDTO.setHotelDescription(cell.getStringCellValue());
                    cell = cellIterator.next();
                    hotelDTO.setStandard((int) cell.getNumericCellValue());
                    CityDTO cityDTO = new CityDTO();
                    cell = cellIterator.next();
                    cityDTO.setCityName(cell.getStringCellValue());
                    cell = cellIterator.next();
                    CountryDTO countryDTO = new CountryDTO();
                    countryDTO.setCountryName(cell.getStringCellValue());
                    cityDTO.setCountryDTO(countryDTO);
                    hotelDTO.setCityDTO(cityDTO);
                    hotelService.addHotel(hotelDTO);
                }
            }
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
