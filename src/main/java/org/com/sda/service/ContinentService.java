package org.com.sda.service;

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

    public Continent getContinentByName(ContinentDTO continentDTO){
        Continent continent = continentDAO.getContinentByName(continentDTO.getContinentName());
        return continent;
    }

}
