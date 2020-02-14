package org.com.sda.logic.readers;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.com.sda.dto.AirportDTO;
import org.com.sda.dto.CityDTO;
import org.com.sda.dto.CountryDTO;
import org.com.sda.dto.FlightDTO;
import org.com.sda.entity.Airport;
import org.com.sda.entity.Country;
import org.com.sda.logic.consts.Consts;
import org.com.sda.logic.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.Iterator;

@Service
public class FlightsReader {
    @Autowired
    private FlightService flightService;

    public void readFlightsFromFile() {
        try {
            FileInputStream file = new FileInputStream(new File(Consts.EXCEL_FILE_PATH));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(3);
            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    FlightDTO flightDTO = new FlightDTO();
                    flightDTO.setFlightNumber(cell.getStringCellValue());
                    cell = cellIterator.next();
                    java.util.Date date = cell.getDateCellValue();
                    Date sqlDate = new Date(date.getTime());
                    flightDTO.setDepartureDate(sqlDate);
                    cell = cellIterator.next();
                    flightDTO.setTotalNumberOfSeats((int) cell.getNumericCellValue());
                    cell = cellIterator.next();
                    flightDTO.setAvailableSeats((int) cell.getNumericCellValue());
                    cell = cellIterator.next();
                    flightDTO.setFlightPrice(cell.getNumericCellValue());
                    cell = cellIterator.next();
                    AirportDTO airportDTO = new AirportDTO();
                    airportDTO.setAirportNameDTO(cell.getStringCellValue());
                    cell = cellIterator.next();
                    CityDTO cityDTO = new CityDTO();
                    cityDTO.setCityName(cell.getStringCellValue());
                    cell = cellIterator.next();
                    CountryDTO countryDTO = new CountryDTO();
                    countryDTO.setCountryName(cell.getStringCellValue());
                    cityDTO.setCountryDTO(countryDTO);
                    airportDTO.setCityDTO(cityDTO);
                    flightDTO.setAirportDTO(airportDTO);
                    flightService.insertFlight(flightDTO);
                }
            }
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}