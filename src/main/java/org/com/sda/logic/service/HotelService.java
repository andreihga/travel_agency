package org.com.sda.logic.service;

import org.com.sda.dto.HotelDTO;
import org.com.sda.entity.Hotel;
import org.com.sda.logic.service.CityService;
import org.com.sda.repository.HotelDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelService {
    @Autowired
    private CityService cityService;
    @Autowired
    private HotelDAO hotelDAO;

    public void addHotel(HotelDTO hotelDTO) {
        Hotel hotel = new Hotel();
        hotel.setHotelName(hotelDTO.getHotelName());
        hotel.setHotelDescription(hotelDTO.getHotelDescription());
        hotel.setStandard(hotelDTO.getStandard());
        hotel.setCity(cityService.getCityFromCityDTO(hotelDTO.getCityDTO()));
        hotelDAO.addHotel(hotel);
    }

    public Hotel getHotelByHotelDTO(HotelDTO hotelDTO) {
        Hotel hotel = hotelDAO.getHotelByNameAndCity(hotelDTO.getHotelName(), cityService.getCityFromCityDTO(hotelDTO.getCityDTO()));
        return hotel;
    }


    public HotelDTO getHotelDTOfromHotel(Hotel hotel) {
        HotelDTO hotelDTO = new HotelDTO();
        hotelDTO.setHotelName(hotel.getHotelName());
        hotelDTO.setHotelDescription(hotel.getHotelDescription());
        hotelDTO.setStandard(hotel.getStandard());
        hotelDTO.setCityDTO(cityService.getCityDTOFromCity(hotel.getCity()));
        return hotelDTO;
    }


}
