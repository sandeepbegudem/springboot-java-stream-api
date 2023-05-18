package org.codingwithsandeepb.stream.api.application.service;

import org.codingwithsandeepb.stream.api.application.entity.Vehicle;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.codingwithsandeepb.stream.api.application.dao.VehicleDAO.listOfVehicles;
import static org.codingwithsandeepb.stream.api.application.entity.FuelType.ELECTRIC;
import static org.codingwithsandeepb.stream.api.application.entity.FuelType.FUEL_CELL;

@Service
public class VehicleService {

    public static List<Vehicle> vehiclesInvByYearAsc(){
        return getVehiclesByYearAsc();
    }

    private static List<Vehicle> getVehiclesByYearAsc(){
        List<Vehicle> vehiclesByYearAsc = listOfVehicles().stream()
                .sorted(Comparator.comparing(Vehicle::getYear))
                .collect(Collectors.toList());
        return vehiclesByYearAsc;
    }

    public static List<Vehicle> vehiclesInventoryByYearDesc(){
        return getVehiclesByYearDesc();
    }

    private static List<Vehicle> getVehiclesByYearDesc() {
        List<Vehicle> vehiclesByYearDesc = listOfVehicles().stream()
                .sorted(Comparator.comparing(Vehicle::getYear).reversed())
                .collect(Collectors.toList());
        System.out.println("printing list of vehicles by year starts:  \n ");
        vehiclesByYearDesc.stream().forEach(System.out::println);
        System.out.println("printing list of vehicles by year ends! \n ");
        return vehiclesByYearDesc;
    }

    public static List<Vehicle> getElectricVehiclesByYearDesc(String message){

        System.out.println(message);
        return electricVehiclesByYearDesc();
    }

    private static List<Vehicle> electricVehiclesByYearDesc(){

        List<Vehicle> electricCars = listOfVehicles()
                .stream()
                .sorted(Comparator.comparing(Vehicle::getYear).reversed())
                .filter(vehicle -> vehicle.getFuelType().equals(ELECTRIC))
                .map(vehicle -> new Vehicle(
                        vehicle.getId(),
                        vehicle.getMake(),
                        vehicle.getYear(),
                        vehicle.getModel(),
                        vehicle.getColor(),
                        vehicle.getMsrp(),
                        vehicle.getMonthlyPaymentOptions(),
                        vehicle.getTransmission(),
                        vehicle.getFuelType())
                ).collect(Collectors.toList());
        System.out.println("printing electric cars by year desc starts here : \n ");

        electricCars.stream().forEach(System.out::println);

        System.out.println("printing electric cars by year desc ends here!!! \n ");

        return electricCars;
    }

    public static List<Vehicle> fuelCellCarsByYear(String message){
        System.out.println(message.toUpperCase());
        return getAllFuelCarsByYear();
    }

    private static List<Vehicle> getAllFuelCarsByYear(){

        List<Vehicle> fuelCellCars = listOfVehicles().stream()
                .sorted(Comparator.comparing(Vehicle::getYear).reversed())
                .filter(vehicle -> vehicle.getFuelType().equals(FUEL_CELL))
                .collect(Collectors.toList());
        fuelCellCars.stream().forEach(System.out::println);
        return fuelCellCars;
    }

    public static List<Vehicle> carsWithPriceUnder25k(){
        return getCarsPricedUnder25K();
    }

    private static List<Vehicle> getCarsPricedUnder25K() {
        List<Vehicle> carsUnder25K = listOfVehicles().stream()
                .sorted(Comparator.comparing(Vehicle::getMsrp))
                .filter(vehicle -> vehicle.getMsrp() < 25000.00)
                .collect(Collectors.toList());
        return carsUnder25K;
    }

    public static Double totalInventoryValue(){
        return inventoryValue();
    }

    private static Double inventoryValue(){
        double inventoryValueSum = listOfVehicles().stream()
                .distinct()
                .mapToDouble(Vehicle::getMsrp)
                .sum();
        return inventoryValueSum;
    }

    public static List<Vehicle> vehiclesByMake(String make){
        if (null == make) throw new RuntimeException("cannot be null please try again later!");
        else
            return getVehiclesByMake(make);
    }

    private static List<Vehicle> getVehiclesByMake(String make){

        List<Vehicle> modelByMake = listOfVehicles().stream()
                .sorted(Comparator.comparing(Vehicle::getModel))
                .filter(vehicle -> vehicle.getMake().equalsIgnoreCase(make))
                .map(vehicle -> new Vehicle(
                        vehicle.getId(),
                        vehicle.getMake(),
                        vehicle.getYear(),
                        vehicle.getModel(),
                        vehicle.getColor(),
                        vehicle.getMsrp(),
                        vehicle.getMonthlyPaymentOptions(),
                        vehicle.getTransmission(),
                        vehicle.getFuelType()))
                .collect(Collectors.toList());
        return modelByMake;
    }

    public static List<Vehicle> getCarsByMakeModel(String model){

        return carsByModel(model);

    }

    private static List<Vehicle> carsByModel(String model){

        return listOfVehicles().stream()
                .sorted(Comparator.comparing(Vehicle::getMsrp))
                .filter(vehicle -> vehicle.getModel().equalsIgnoreCase(model))
                .map(vehicle -> new Vehicle(
                        vehicle.getId(),
                        vehicle.getMake(),
                        vehicle.getYear(),
                        vehicle.getModel(),
                        vehicle.getColor(),
                        vehicle.getMsrp(),
                        vehicle.getMonthlyPaymentOptions(),
                        vehicle.getTransmission(),
                        vehicle.getFuelType()))
                .collect(Collectors.toList());
    }

    public static List<Vehicle> carsByYearSortByPriceAsc(Integer year){

        return carsByPriceAsc(year);
    }

    private static List<Vehicle> carsByPriceAsc(Integer year){
        return listOfVehicles().stream()
                .sorted(Comparator.comparing(Vehicle::getMsrp))
                .filter(vehicle -> vehicle.getYear().equals(year))
                .collect(Collectors.toList());
    }

    public static List<Vehicle> carsByYearSortByPriceDesc(Integer year){

        return carsByPriceDesc(year);
    }

    private static List<Vehicle> carsByPriceDesc(Integer year){
        return listOfVehicles().stream()
                .sorted(Comparator.comparing(Vehicle::getMsrp).reversed())
                .filter(vehicle -> vehicle.getYear().equals(year))
                .collect(Collectors.toList());
    }

    public static List<Vehicle> carsByModelAndColor(String model, String color){

        return getCarsByModelAndPriceDesc(model, color);
    }

    private static List<Vehicle> getCarsByModelAndPriceDesc(String model, String color){
        List<Vehicle> vehicleStream = listOfVehicles().stream()
                .sorted(Comparator.comparing(Vehicle::getMsrp).reversed())
                .filter(vehicle -> vehicle.getModel().equalsIgnoreCase(model))
                .map(vehicle -> new Vehicle(
                        vehicle.getId(),
                        vehicle.getMake(),
                        vehicle.getYear(),
                        vehicle.getModel(),
                        vehicle.getColor(),
                        vehicle.getMsrp(),
                        vehicle.getMonthlyPaymentOptions(),
                        vehicle.getTransmission(),
                        vehicle.getFuelType()))
                .collect(Collectors.toList()).stream().filter(vehicle -> vehicle.getColor().equalsIgnoreCase(color))
                .collect(Collectors.toList());

        return vehicleStream;

    }

    public static Vehicle leastExpensiveModelByMake(String make){

        return cheapestCarByMake(make);

    }

    private static Vehicle cheapestCarByMake(String make){
        //return
        Stream<Vehicle> vehicleStream = listOfVehicles().parallelStream()
                .sorted(Comparator.comparing(Vehicle::getMsrp))
                .filter(vehicle -> vehicle.getMake().equalsIgnoreCase(make))
                .map(vehicle -> new Vehicle(
                        vehicle.getId(),
                        vehicle.getMake(),
                        vehicle.getYear(),
                        vehicle.getModel(),
                        vehicle.getColor(),
                        vehicle.getMsrp(),
                        vehicle.getMonthlyPaymentOptions(),
                        vehicle.getTransmission(),
                        vehicle.getFuelType()));

        return vehicleStream.findFirst().get();

    }



}
