package com.example.geolocationapi;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Location {

    @Id
    private double latitude;

    private double longitude;
}
