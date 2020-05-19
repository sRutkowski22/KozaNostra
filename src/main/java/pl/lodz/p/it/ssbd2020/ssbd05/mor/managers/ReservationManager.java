package pl.lodz.p.it.ssbd2020.ssbd05.mor.managers;


import pl.lodz.p.it.ssbd2020.ssbd05.abstraction.AbstractManager;
import pl.lodz.p.it.ssbd2020.ssbd05.entities.mor.Reservation;
import pl.lodz.p.it.ssbd2020.ssbd05.entities.mor.Status;
import pl.lodz.p.it.ssbd2020.ssbd05.entities.mos.EventType;
import pl.lodz.p.it.ssbd2020.ssbd05.exceptions.AppBaseException;
import pl.lodz.p.it.ssbd2020.ssbd05.interceptors.TrackerInterceptor;
import pl.lodz.p.it.ssbd2020.ssbd05.mor.facades.EventTypesFacade;
import pl.lodz.p.it.ssbd2020.ssbd05.mor.facades.ExtraServiceFacade;
import pl.lodz.p.it.ssbd2020.ssbd05.mor.facades.ReservationFacade;
import pl.lodz.p.it.ssbd2020.ssbd05.mor.facades.StatusFacade;

import javax.annotation.security.RolesAllowed;
import javax.ejb.*;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import java.util.ArrayList;
import java.util.List;

@Stateful
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@LocalBean
@Interceptors(TrackerInterceptor.class)
public class ReservationManager extends AbstractManager implements SessionSynchronization {
    @Inject
    private ReservationFacade reservationFacade;

    @Inject
    private EventTypesFacade eventTypesFacade;

    @Inject
    private StatusFacade statusFacade;

    public List<Reservation> getAllReservations(){
        //TODO Implementacja
        return new ArrayList<>();
    }

    public List<EventType> getAllEventTypes(){
        //TODO Implementacja
        return new ArrayList<>();
    }

    public void createReservation(Reservation reservation){
        //TODO Implementacja
    }

    @RolesAllowed("getAllReservations")
    public List<Reservation> getAllUsersReservations(String login){
        //TODO Implementacja
        throw new UnsupportedOperationException();
    }
    @RolesAllowed("getStatusByName")
    public Status getStatusByName(String statusName) {
        throw new UnsupportedOperationException();
    }
    @RolesAllowed("getReservationByNumber")
    public Reservation getReservationByNumber(String reservationNumber) throws AppBaseException {
        throw new UnsupportedOperationException();
    }
    @RolesAllowed("changeReservationStatus")
    public void changeReservationStatus(Reservation reservation) throws AppBaseException {
        throw new UnsupportedOperationException();
    }
    @RolesAllowed("getStatusCanceled")
    public Status getStatusCanceled() {
        throw new UnsupportedOperationException();
    }
    @RolesAllowed("cancelReservation")
    public void cancelReservation(Reservation reservation) throws AppBaseException {
        throw new UnsupportedOperationException();
    }
}
