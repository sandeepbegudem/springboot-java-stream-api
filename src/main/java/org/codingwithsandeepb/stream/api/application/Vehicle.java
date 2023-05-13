package org.codingwithsandeepb.stream.api.application;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

public class Vehicle {
    private String id;
    private String make;
    private Integer year;
    private String model;
    private String color;
    private Double msrp;
    public List<Integer> monthlyPaymentOptions;
    private Transmission transmission;
    private FuelType fuelType;

    public void setMonthlyPaymentOptions(List<Integer> monthlyPaymentOptions) {
        this.monthlyPaymentOptions = monthlyPaymentOptions;
    }

    public Vehicle(String id, String make, Integer year, String model, String color, Double msrp, List<Integer> monthlyPaymentOptions, Transmission transmission, FuelType fuelType) {
        this.id = id;
        this.make = make;
        this.year = year;
        this.model = model;
        this.color = color;
        this.msrp = msrp;
        this.monthlyPaymentOptions = monthlyPaymentOptions;
        this.transmission = transmission;
        this.fuelType = fuelType;
    }

    public List<Integer> getMonthlyPaymentOptions() {
        return monthlyPaymentOptions;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public Double getMsrp() {
        return msrp;
    }

    public void setMsrp(Double msrp) {
        this.msrp = msrp;
    }
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getModel() {
        return model;
    }

    public void setDescription(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id='" + id + '\'' +
                ", make='" + make + '\'' +
                ", year=" + year +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", msrp=" + msrp +
                ", monthlyPaymentOptions=" + monthlyPaymentOptions +
                ", transmission=" + transmission +
                ", fuelType=" + fuelType +
                '}';
    }

    public static void main(String[] args) {

        //sorting list of vehicles using comparator.comparing by year ascending
        System.out.println("sorting list of vehicles using comparator.comparing by year ascending \n");

        List<Vehicle> vehicles = Vehicle.listOfVehicles();
        vehicles.stream()
                .sorted(Comparator.comparing(Vehicle::getYear))
                .collect(Collectors.toList())
                .forEach(System.out::println);

// print list of electric vehicles
        vehicles.stream().filter(v -> v.getFuelType().equals(FuelType.ELECTRIC))
                .map(v -> new Vehicle(v.getId(),
                        v.getMake(),
                        v.getYear(),
                        v.getModel(),
                        v.getColor(),
                        v.getMsrp(),
                        v.getMonthlyPaymentOptions(),
                        v.getTransmission(),
                        v.getFuelType()))
                .collect(Collectors.toList()).forEach(System.out::println);

// print list of fuelcell vehicles
        vehicles.stream().filter(v -> v.getFuelType().equals(FuelType.FUEL_CELL))
                .map(v -> new Vehicle(v.getId(),
                        v.getMake(),
                        v.getYear(),
                        v.getModel(),
                        v.getColor(),
                        v.getMsrp(),
                        v.getMonthlyPaymentOptions(),
                        v.getTransmission(),
                        v.getFuelType()))
                .collect(Collectors.toList()).forEach(System.out::println);


        // plugin hybrid vehicles inventory
        vehicles.stream().filter(v -> v.getFuelType().equals(FuelType.PLUGIN_HYBRID))
                .map(v -> new Vehicle(v.getId(),
                        v.getMake(),
                        v.getYear(),
                        v.getModel(),
                        v.getColor(),
                        v.getMsrp(),
                        v.getMonthlyPaymentOptions(),
                        v.getTransmission(),
                        v.getFuelType()))
                .collect(Collectors.toList()).forEach(System.out::println);

        Long vehiclesCount = vehicles.stream().count();
        System.out.println(vehiclesCount);

        //print distinct top 15 vehicles by year latest
        List<Vehicle> top10VehiclesByYearLatest = vehicles
                .stream()
                .sorted(Comparator.comparing(Vehicle::getYear).reversed())
                .collect(Collectors.toList())
                .stream().limit(15)
                .distinct()
                .collect(Collectors.toList());
        top10VehiclesByYearLatest.stream().forEach(System.out::println);

        //vehicles under 25k msrp
        List<Vehicle> vehiclesUnder25kMsrp = vehicles.stream()
                .sorted(Comparator.comparing(Vehicle::getMsrp).reversed())
                .filter(vehicle -> vehicle.getMsrp() <= 30000.00)
                .map(vehicle -> new Vehicle(
                        vehicle.getId(),
                        vehicle.getMake(),
                        vehicle.getYear(),
                        vehicle.getModel(),
                        vehicle.getColor(),
                        vehicle.getMsrp(),
                        vehicle.getMonthlyPaymentOptions(),
                        vehicle.getTransmission(),
                        vehicle.getFuelType()
                ))
                .collect(Collectors.toList());
        vehiclesUnder25kMsrp.stream().forEach(System.out::println);

        // print value of all the vehicles using map and sum
        double totalInventoryValue = vehicles.stream().map(vehicle -> new Vehicle(
                vehicle.getId(),
                vehicle.getMake(),
                vehicle.getYear(),
                vehicle.getModel(),
                vehicle.getColor(),
                vehicle.getMsrp(),
                vehicle.getMonthlyPaymentOptions(),
                vehicle.getTransmission(),
                vehicle.getFuelType()
        )).mapToDouble(price -> price.getMsrp()).sum();
        System.out.println("total inventory value 1 : " + totalInventoryValue);

        // return null if the expected vehicle is not found in the list
        String honda = vehicles.stream()
                .filter(vehicle -> vehicle.getMake().equals("Honda"))
                .map(Vehicle::getModel)
                .findAny()
                .orElse(null);
        System.out.println(honda);

        // filter list of vehicles with color - yellow
        Set<Vehicle> yellowCars = vehicles.stream().filter(vehicle -> vehicle.getColor().equalsIgnoreCase("yellow"))
                .map(vehicle -> new Vehicle(
                        vehicle.getId(),
                        vehicle.getMake(),
                        vehicle.getYear(),
                        vehicle.getModel(),
                        vehicle.getColor(),
                        vehicle.getMsrp(),
                        vehicle.getMonthlyPaymentOptions(),
                        vehicle.getTransmission(),
                        vehicle.getFuelType()
                )).collect(Collectors.toSet());

        yellowCars.stream().forEach(System.out::println);

        //get the maximum price of the vehicle
        Vehicle expensiveVehicle = vehicles.parallelStream()
                .max(Comparator.comparing(Vehicle::getMsrp))
                .orElseThrow(NoSuchElementException::new);
        System.out.println(expensiveVehicle.getModel() + ": " + "id :" +
                expensiveVehicle.getMake() + ": "+ expensiveVehicle.getMsrp());

        // get the least expensive vehicle
        Optional<Vehicle> leastExpensiveVehicle = vehicles.parallelStream()
                .min(Comparator.comparing(Vehicle::getMsrp))
                .map(vehicle -> new Vehicle(
                        vehicle.getId(),
                        vehicle.getMake(),
                        vehicle.getYear(),
                        vehicle.getModel(),
                        vehicle.getColor(),
                        vehicle.getMsrp(),
                        vehicle.getMonthlyPaymentOptions(),
                        vehicle.getTransmission(),
                        vehicle.getFuelType()
                ));
        System.out.println(leastExpensiveVehicle);

        Double vehicleInventoryValue = vehicles.stream().
                distinct()
                .flatMapToDouble(vehicle -> DoubleStream.of(vehicle.getMsrp()))
                .sum();
        System.out.println("\ntotal inventory value : " + vehicleInventoryValue);

        // printing list of Integer of type Integer using flatmap
        List<Integer> monthlyPaymentOptions = vehicles.stream()
                .flatMap(vehicle -> vehicle.getMonthlyPaymentOptions().stream())
                .collect(Collectors.toList());
        System.out.println(monthlyPaymentOptions);

        // print List of List of Integer of streams using map(in-efficient approach due to one to many entity relationship for map can't flatten the stream)
        List<List<Integer>> modelYMap = vehicles.stream()
                .map(vehicle -> vehicle.getMonthlyPaymentOptions())
                .collect(Collectors.toList());
        System.out.println(modelYMap);

        // print monthly payment options for the vehicle Model Y using flatmap
        List<Integer> paymentOptionsForModelY = vehicles.stream()
                .filter(vehicle -> vehicle.getModel()
                        .equalsIgnoreCase("Model Y"))
                .flatMap(vehicle -> vehicle.getMonthlyPaymentOptions().stream())
                .collect(Collectors.toList());
        System.out.println(paymentOptionsForModelY);


    }

    private static List<Vehicle> listOfVehicles(){

        List<Vehicle> vehicles = Arrays.asList(
                new Vehicle(UUID.randomUUID().toString(), "Toyota", 2024, "4Runner TRD off-road","Dark Green",49569.99, Arrays.asList(36, 48, 60, 72, 84, 96), Transmission.AUTOMATIC, FuelType.GASOLINE),
                new Vehicle(UUID.randomUUID().toString(), "Honda", 2014, "Pilot","dark blue",19999.69, Arrays.asList(36, 48, 60, 72, 84, 96), Transmission.AUTOMATIC, FuelType.GASOLINE),
                new Vehicle(UUID.randomUUID().toString(), "RAM", 2003, "HD 2500","shiny black",25000.99, Arrays.asList(36, 48, 60, 72, 84, 96),Transmission.AUTOMATIC, FuelType.DIESEL),
                new Vehicle(UUID.randomUUID().toString(), "GMC", 2013, "Denali", "pearl white",27899.99, Arrays.asList(36, 48, 60, 72, 84, 96),Transmission.AUTOMATIC, FuelType.DIESEL),
                new Vehicle(UUID.randomUUID().toString(), "Tesla", 2017, "Model 3", "maroon",34599.39, Arrays.asList(36, 48, 60, 72, 84, 96),Transmission.AUTOMATIC, FuelType.ELECTRIC),
                new Vehicle(UUID.randomUUID().toString(), "Chevrolet", 2013, "Chevrolet Silverado 1500","grey", 31255.99, Arrays.asList(36, 48, 60, 72, 84, 96),Transmission.AUTOMATIC, FuelType.GASOLINE),
                new Vehicle(UUID.randomUUID().toString(), "Toyota", 2020, "Mirai", "metallic blue",38999.00, Arrays.asList(36, 48, 60, 72, 84, 96),Transmission.AUTOMATIC, FuelType.FUEL_CELL),
                new Vehicle(UUID.randomUUID().toString(), "Lexus", 2017, "RX350", "black",34449.99, Arrays.asList(36, 48, 60, 72, 84, 96),Transmission.AUTOMATIC, FuelType.GASOLINE),
                new Vehicle(UUID.randomUUID().toString(), "Acura", 2015, "MDX", "red",19899.99, Arrays.asList(36, 48, 60, 72, 84, 96),Transmission.AUTOMATIC, FuelType.GASOLINE),
                new Vehicle(UUID.randomUUID().toString(), "BMW", 2003, "M5", "yellow",14959.88, Arrays.asList(36, 48, 60, 72, 84, 96),Transmission.MANUAL, FuelType.GASOLINE),
                new Vehicle(UUID.randomUUID().toString(), "Lexus", 2013, "GX570", "grey",35699.00, Arrays.asList(36, 48, 60, 72, 84, 96),Transmission.AUTOMATIC, FuelType.GASOLINE),
                new Vehicle(UUID.randomUUID().toString(), "Hyundai", 2019, "Nexo", "black",34566.89, Arrays.asList(36, 48, 60, 72, 84, 96),Transmission.AUTOMATIC, FuelType.FUEL_CELL),
                new Vehicle(UUID.randomUUID().toString(), "Lexus", 2013, "GS350", "maroon",29768.99, Arrays.asList(36, 48, 60, 72, 84, 96),Transmission.AUTOMATIC, FuelType.GASOLINE),
                new Vehicle(UUID.randomUUID().toString(), "Audi", 2019, "R8", "white",145999.99, Arrays.asList(36, 48, 60, 72, 84, 96),Transmission.MANUAL, FuelType.GASOLINE),
                new Vehicle(UUID.randomUUID().toString(), "Toyota", 2013, "RAV4 Plugin Hybrid", "grey",65000.99, Arrays.asList(36, 48, 60, 72, 84, 96),Transmission.AUTOMATIC, FuelType.PLUGIN_HYBRID),
                new Vehicle(UUID.randomUUID().toString(), "Mazda", 2006, "CX90", "maroon",64999.99, Arrays.asList(36, 48, 60, 72, 84, 96),Transmission.AUTOMATIC, FuelType.GASOLINE),
                new Vehicle(UUID.randomUUID().toString(), "Honda", 2021, "Clarity","wine red", 34689.99, Arrays.asList(36, 48, 60, 72, 84, 96),Transmission.AUTOMATIC, FuelType.FUEL_CELL),
                new Vehicle(UUID.randomUUID().toString(), "Tesla", 2019, "Model X", "black",43249.99, Arrays.asList(36, 48, 60, 72, 84, 96),Transmission.AUTOMATIC, FuelType.ELECTRIC),
                new Vehicle(UUID.randomUUID().toString(), "Subaru", 2015, "WRX STI", "blue",32359.00, Arrays.asList(36, 48, 60, 72, 84, 96),Transmission.MANUAL, FuelType.GASOLINE),
                new Vehicle(UUID.randomUUID().toString(), "Toyota", 1992, "Supra", "red",100009.99, Arrays.asList(36, 48, 60, 72, 84, 96),Transmission.MANUAL, FuelType.GASOLINE),
                new Vehicle(UUID.randomUUID().toString(), "Tesla", 2020, "Model S", "yellow",35688.69, Arrays.asList(36, 48, 60, 72, 84, 96),Transmission.AUTOMATIC, FuelType.ELECTRIC),
                new Vehicle(UUID.randomUUID().toString(), "Lexus", 2013, "Lexus ES350", "black",19567.68, Arrays.asList(36, 48, 60, 72, 84, 96),Transmission.AUTOMATIC, FuelType.GASOLINE),
                new Vehicle(UUID.randomUUID().toString(), "Toyota", 2024, "GR Corolla", "black",55479.99, Arrays.asList(36, 48, 60, 72, 84, 96),Transmission.MANUAL, FuelType.GASOLINE),
                new Vehicle(UUID.randomUUID().toString(), "Honda", 2024, "Civic TypeR", "blue",45238.00, Arrays.asList(36, 48, 60, 72, 84, 96),Transmission.MANUAL, FuelType.GASOLINE),
                new Vehicle(UUID.randomUUID().toString(), "Tesla", 2023, "Model Y", "grey",45688.48, Arrays.asList(36, 48, 60, 72, 84, 96),Transmission.AUTOMATIC, FuelType.ELECTRIC),
                new Vehicle(UUID.randomUUID().toString(), "Nissan", 2019, "Nissan Leaf", "grey",34999.99, Arrays.asList(36, 48, 60, 72, 84, 96),Transmission.AUTOMATIC, FuelType.ELECTRIC),
                new Vehicle(UUID.randomUUID().toString(), "Ford", 2019, "F-250", "black",89880.99, Arrays.asList(36, 48, 60, 72, 84, 96),Transmission.AUTOMATIC, FuelType.DIESEL),
                new Vehicle(UUID.randomUUID().toString(), "Nissan", 2013, "Titan XD", "white",56745.99, Arrays.asList(36, 48, 60, 72, 84, 96),Transmission.AUTOMATIC, FuelType.DIESEL),
                new Vehicle(UUID.randomUUID().toString(), "Toyota", 2023, "BZ4X", "wine red",46885.00, Arrays.asList(36, 48, 60, 72, 84, 96),Transmission.AUTOMATIC, FuelType.ELECTRIC)
        );
        return vehicles;
    }
}
