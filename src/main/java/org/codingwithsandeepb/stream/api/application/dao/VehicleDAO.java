package org.codingwithsandeepb.stream.api.application.dao;

import org.codingwithsandeepb.stream.api.application.entity.FuelType;
import org.codingwithsandeepb.stream.api.application.entity.Transmission;
import org.codingwithsandeepb.stream.api.application.entity.Vehicle;

import java.util.Arrays;
import java.util.List;

public class VehicleDAO {

    public static List<Vehicle> listOfVehicles(){

        List<Vehicle> vehicles = Arrays.asList(
                new Vehicle(1015242321123425432L, "Toyota", 2024, "4Runner-TRD off-road","dark-green",49569.99, Arrays.asList(36, 48, 60, 72, 84, 96), Transmission.AUTOMATIC, FuelType.GASOLINE),
                new Vehicle(1023342212342341231L, "Honda", 2014, "Pilot","dark-blue",19999.69, Arrays.asList(36, 48, 60, 72, 84, 96), Transmission.AUTOMATIC, FuelType.GASOLINE),
                new Vehicle(2345321322435456543L, "RAM", 2003, "HD2500","shiny-black",25000.99, Arrays.asList(36, 48, 60, 72, 84, 96),Transmission.AUTOMATIC, FuelType.DIESEL),
                new Vehicle(765789023423416754L, "GMC", 2013, "Denali", "pearl-white",27899.99, Arrays.asList(36, 48, 60, 72, 84, 96),Transmission.AUTOMATIC, FuelType.DIESEL),
                new Vehicle(8976879784325432L, "Tesla", 2021, "Model-Y", "pearl-white",39768.98, Arrays.asList(36, 48, 60, 72, 84, 96),Transmission.AUTOMATIC, FuelType.ELECTRIC),
                new Vehicle(786546781232346543L, "Tesla", 2017, "Model-3", "maroon",34599.39, Arrays.asList(36, 48, 60, 72, 84, 96),Transmission.AUTOMATIC, FuelType.ELECTRIC),
                new Vehicle(4567542327342453L, "Chevrolet", 2013, "Silverado-1500","grey", 31255.99, Arrays.asList(36, 48, 60, 72, 84, 96),Transmission.AUTOMATIC, FuelType.GASOLINE),
                new Vehicle(8976578324532345132L, "Toyota", 2020, "Mirai", "metallic blue",38999.00, Arrays.asList(36, 48, 60, 72, 84, 96),Transmission.AUTOMATIC, FuelType.FUEL_CELL),
                new Vehicle(987657893242654345L, "Lexus", 2017, "RX350", "black",34449.99, Arrays.asList(36, 48, 60, 72, 84, 96),Transmission.AUTOMATIC, FuelType.GASOLINE),
                new Vehicle(8976879784325432231L, "Acura", 2015, "MDX", "red",19899.99, Arrays.asList(36, 48, 60, 72, 84, 96),Transmission.AUTOMATIC, FuelType.GASOLINE),
                new Vehicle(657253127812123L, "Toyota", 2024, "Corolla", "grey",31549.99, Arrays.asList(36, 48, 60, 72, 84, 96),Transmission.AUTOMATIC, FuelType.HYBRID),
                new Vehicle(8976879784343213241L, "BMW", 2003, "M5", "yellow",14959.88, Arrays.asList(36, 48, 60, 72, 84, 96),Transmission.MANUAL, FuelType.GASOLINE),
                new Vehicle(823976879784325432L, "Tesla", 2022, "Model-Y", "wine red",40588.59, Arrays.asList(36, 48, 60, 72, 84, 96),Transmission.AUTOMATIC, FuelType.ELECTRIC),
                new Vehicle(23476879784325432L, "Lexus", 2013, "GX570", "grey",35699.00, Arrays.asList(36, 48, 60, 72, 84, 96),Transmission.AUTOMATIC, FuelType.GASOLINE),
                new Vehicle(56436879784325432L, "Hyundai", 2019, "Nexo", "black",34566.89, Arrays.asList(36, 48, 60, 72, 84, 96),Transmission.AUTOMATIC, FuelType.FUEL_CELL),
                new Vehicle(8976823479784325432L, "Lexus", 2013, "GS350", "maroon",29768.99, Arrays.asList(36, 48, 60, 72, 84, 96),Transmission.AUTOMATIC, FuelType.GASOLINE),
                new Vehicle(657253127812123L, "Toyota", 2024, "Camry", "white",33654.99, Arrays.asList(36, 48, 60, 72, 84, 96),Transmission.AUTOMATIC, FuelType.HYBRID),
                new Vehicle(897687978432543654L, "Audi", 2019, "R8", "white",145999.99, Arrays.asList(36, 48, 60, 72, 84, 96),Transmission.MANUAL, FuelType.GASOLINE),
                new Vehicle(4368797843257623432L, "Toyota", 2013, "RAV4", "grey",65000.99, Arrays.asList(36, 48, 60, 72, 84, 96),Transmission.AUTOMATIC, FuelType.PLUGIN_HYBRID),
                new Vehicle(7865897686653477L, "Mazda", 2006, "CX90", "maroon",64999.99, Arrays.asList(36, 48, 60, 72, 84, 96),Transmission.AUTOMATIC, FuelType.GASOLINE),
                new Vehicle(87654678932423445L, "Tesla", 2019, "Model-Y", "midnight-black",35299.10, Arrays.asList(36, 48, 60, 72, 84, 96),Transmission.AUTOMATIC, FuelType.ELECTRIC),
                new Vehicle(435678976578976586L, "Honda", 2021, "Clarity","wine-red", 34689.99, Arrays.asList(36, 48, 60, 72, 84, 96),Transmission.AUTOMATIC, FuelType.FUEL_CELL),
                new Vehicle(76545675643542345L, "Tesla", 2019, "Model-X", "black",43249.99, Arrays.asList(36, 48, 60, 72, 84, 96),Transmission.AUTOMATIC, FuelType.ELECTRIC),
                new Vehicle(78675645673223444L, "Subaru", 2015, "WRX-STI", "blue",32359.00, Arrays.asList(36, 48, 60, 72, 84, 96),Transmission.MANUAL, FuelType.GASOLINE),
                new Vehicle(56432456432435245L, "Toyota", 1992, "Supra", "red",100009.99, Arrays.asList(36, 48, 60, 72, 84, 96),Transmission.MANUAL, FuelType.GASOLINE),
                new Vehicle(6572531234327812123L, "Toyota", 2024, "Prius", "black",34559.99, Arrays.asList(36, 48, 60, 72, 84, 96),Transmission.AUTOMATIC, FuelType.HYBRID),
                new Vehicle(234867834267343345L, "Tesla", 2020, "Model-S", "yellow",35688.69, Arrays.asList(36, 48, 60, 72, 84, 96),Transmission.AUTOMATIC, FuelType.ELECTRIC),
                new Vehicle(89765712341234435L, "Tesla", 2024, "Model-Y", "yellow",45899.48, Arrays.asList(36, 48, 60, 72, 84, 96),Transmission.AUTOMATIC, FuelType.ELECTRIC),
                new Vehicle(4352356344234432453L, "Lexus", 2013, "ES350", "black",19567.68, Arrays.asList(36, 48, 60, 72, 84, 96),Transmission.AUTOMATIC, FuelType.GASOLINE),
                new Vehicle(54321234532565643L, "Toyota", 2024, "GR-Corolla", "black",55479.99, Arrays.asList(36, 48, 60, 72, 84, 96),Transmission.MANUAL, FuelType.GASOLINE),
                new Vehicle(6782345785436573245L, "Honda", 2024, "Civic-TypeR", "blue",45238.00, Arrays.asList(36, 48, 60, 72, 84, 96),Transmission.MANUAL, FuelType.GASOLINE),
                new Vehicle(567276823487564309L, "Tesla", 2023, "Model-Y", "grey",45688.48, Arrays.asList(36, 48, 60, 72, 84, 96),Transmission.AUTOMATIC, FuelType.ELECTRIC),
                new Vehicle(2342765339872342343L, "Nissan", 2019, "Leaf", "grey",34999.99, Arrays.asList(36, 48, 60, 72, 84, 96),Transmission.AUTOMATIC, FuelType.ELECTRIC),
                new Vehicle(435432478973445365L, "Ford", 2019, "F-250", "black",89880.99, Arrays.asList(36, 48, 60, 72, 84, 96),Transmission.AUTOMATIC, FuelType.DIESEL),
                new Vehicle(543423556467543323L, "Nissan", 2013, "Titan-XD", "white",56745.99, Arrays.asList(36, 48, 60, 72, 84, 96),Transmission.AUTOMATIC, FuelType.DIESEL),
                new Vehicle(657253127812123L, "Toyota", 2024, "Prius", "grey",34549.99, Arrays.asList(36, 48, 60, 72, 84, 96),Transmission.AUTOMATIC, FuelType.HYBRID),
                new Vehicle(3424543535643345653L, "Toyota", 2023, "BZ4X", "wine-red",46885.00, Arrays.asList(36, 48, 60, 72, 84, 96),Transmission.AUTOMATIC, FuelType.ELECTRIC)
        );
        return vehicles;

    }
}
