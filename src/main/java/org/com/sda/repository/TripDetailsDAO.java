package org.com.sda.repository;

import org.com.sda.config.HibernateUtil;
import org.com.sda.entity.Flight;
import org.com.sda.entity.RoomAvailability;
import org.com.sda.entity.TripDetails;
import org.com.sda.entity.User;
import org.com.sda.service.FlightService;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

@Repository
public class TripDetailsDAO {
    @Autowired
    private RoomAvailabilityDAO roomAvailabilityDAO;
    @Autowired
    private FlightDAO flightDAO;
    @Autowired
    private UserDAO userDAO;

    public String buyTrip(TripDetails tripDetails, int nrOfPersons){
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.persist(tripDetails);

        RoomAvailability roomAvailability = roomAvailabilityDAO.seeAvailability(tripDetails.getTrip());
        int updateSingleRooms = roomAvailability.getNumberSingleRoomsAvailable() - tripDetails.getSingleRooms();
        int updateDoubleRooms = roomAvailability.getNumberDoubleRoomsAvailable() - tripDetails.getDoubleRooms();
        int updateExtraBeds = roomAvailability.getNumberOfExtraBedsAvailable() - tripDetails.getExtraBed();

        Query query = session.createNamedQuery("updateRooms");
        query.setParameter("updateSingleRooms",updateSingleRooms);
        query.setParameter("updateDoubleRooms",updateDoubleRooms);
        query.setParameter("updateExtraBeds",updateExtraBeds);
        query.setParameter("hotel",tripDetails.getTrip().getHotelTrip());
        query.executeUpdate();

        Flight flight = flightDAO.getFlightByDepartureDate(tripDetails.getTrip().getDepartureFlightTrip().getDepartureDate());
        int updateSeats = flight.getAvailableSeats() - nrOfPersons;
        Query updateSeatsQuery = session.createNamedQuery("updateSeats");
        updateSeatsQuery.setParameter("updateAvailableSeats",updateSeats);
        updateSeatsQuery.setParameter("airport",flight.getAirport());
        updateSeatsQuery.executeUpdate();

        User user = userDAO.findExistingUser(tripDetails.getUser().getEmail());
        double updatedAmount = user.getTotalAmount() + tripDetails.getAmount();
        Query updateUserAmount = session.createNamedQuery("updateUserAmount");
        updateUserAmount.setParameter("amount",updatedAmount);
        updateUserAmount.setParameter("email",user.getEmail());
        updateUserAmount.executeUpdate();

        transaction.commit();
        session.close();
        return "Successfully purchased!";
    }
}
