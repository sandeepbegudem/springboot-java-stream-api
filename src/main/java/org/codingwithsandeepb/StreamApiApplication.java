package org.codingwithsandeepb;

import org.codingwithsandeepb.stream.api.application.entity.Vehicle;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

import static org.codingwithsandeepb.stream.api.application.dao.VehicleDAO.listOfVehicles;

@SpringBootApplication
public class StreamApiApplication {


    public static void main(String[] args) {
        SpringApplication.run(StreamApiApplication.class, args);

    }

}
