package org.codingwithsandeepb.stream.api.application.entity;

import org.codingwithsandeepb.stream.api.application.dao.VehicleDAO;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

import static org.codingwithsandeepb.stream.api.application.service.VehicleService.*;


public class Vehicle {

    private Long id;
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

    public Vehicle(Long id, String make, Integer year, String model, String color, Double msrp, List<Integer> monthlyPaymentOptions, Transmission transmission, FuelType fuelType) {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    /*
    public static void main(String[] args) {

        //sorting list of vehicles using comparator.comparing by year ascending
        System.out.println("sorting list of vehicles using comparator.comparing by year ascending \n");

        List<Vehicle> vehicles = VehicleDAO.listOfVehicles();
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

        vehiclesInvByYearAsc();
        getElectricVehiclesByYearDesc("printing electric vehicles desc");
        vehiclesInventoryByYearDesc();

        fuelCellCarsByYear("fuel cell cars: \n");

        carsWithPriceUnder25k();
        }
*/






}
