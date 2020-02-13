package org.com.sda.logic.service;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellReference;
import org.com.sda.dto.ContinentDTO;
import org.com.sda.entity.Continent;
import org.com.sda.repository.ContinentDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContinentService {

    @Autowired
    private ContinentDAO continentDAO;

    public ContinentDTO getContinentDTO(String continentName){
        Continent continent = continentDAO.getContinentByName(continentName);
        ContinentDTO continentDTO = new ContinentDTO();
        continentDTO.setContinentName(continent.getContinent_name());
        return continentDTO;
    }

    public Continent addContinent(ContinentDTO continentDTO){
        Continent continent = new Continent();
        continent.setContinent_name(continentDTO.getContinentName());
        //continentDAO.addContinent(continent);

        return continent;
    }

    public void readContentFromExcel(){
        Workbook wb = new HSSFWorkbook();
        DataFormatter formatter = new DataFormatter();
        Sheet sheet = wb.getSheetAt(0);
        for (Row row:sheet){
            for (Cell cell : row){
                CellReference cellReference = new CellReference(row.getRowNum(),cell.getColumnIndex());
                System.out.println(cellReference.formatAsString());
                System.out.println(" - ");
                String text = formatter.formatCellValue(cell);
                System.out.println(text);
            }
        }
    }

    public Continent getContinentByName(ContinentDTO continentDTO){
        Continent continent = continentDAO.getContinentByName(continentDTO.getContinentName());
        return continent;
    }

    public Continent getContinentFromContinentDTO(ContinentDTO continentDTO){
        Continent continent = new Continent();
        continent.setContinent_name(continentDTO.getContinentName());
        return continent;
    }

}
