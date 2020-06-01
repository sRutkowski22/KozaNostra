package pl.lodz.p.it.ssbd2020.ssbd05.dto.mos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
public @Data class HallDTO {

    private String name;
    private int capacity;
    private boolean active;
    private double area;
    private String description;
    private double price;
    private Collection<String> eventTypeCollection = new ArrayList<>();
    private boolean newAddress;
    private AddressDTO address;
    private Collection<String> reservationCollection = new ArrayList<>();
}
