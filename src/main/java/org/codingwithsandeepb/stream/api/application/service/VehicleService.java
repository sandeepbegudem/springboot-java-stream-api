package org.codingwithsandeepb.stream.api.application.service;

import jakarta.el.PropertyNotFoundException;
import org.codingwithsandeepb.stream.api.application.entity.Vehicle;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
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
        vehiclesByYearDesc.stream().forEach(System.out::println);
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

    public List<Vehicle> getElectricCarsByMakePriceAsc(String make){
        return electricCarsByMakeWithPriceAsc(make);
    }

    private List<Vehicle> electricCarsByMakeWithPriceAsc(String make){
       return listOfVehicles()
                .stream()
                .sorted(Comparator.comparing(Vehicle::getMsrp))
                .filter(vehicle -> vehicle.getFuelType().equals(ELECTRIC))
                .filter(vehicle -> vehicle.getMake().equalsIgnoreCase(make))
                .collect(Collectors.toList());

    }

    public List<Vehicle> getElectricCarsByMakePriceDesc(String make){
        return electricCarsByMakeWithPriceDesc(make);
    }

    private List<Vehicle> electricCarsByMakeWithPriceDesc(String make){
        return listOfVehicles()
                .stream()
                .sorted(Comparator.comparing(Vehicle::getMsrp).reversed())
                .filter(vehicle -> vehicle.getFuelType().equals(ELECTRIC))
                .filter(vehicle -> vehicle.getMake().equalsIgnoreCase(make))
                .collect(Collectors.toList());
    }

    public static List<Integer> getMonthlyPaymentOptions(String model){

        return monthlyPaymentOptions(model);
    }

    private static List<Integer> monthlyPaymentOptions(String model){

        List<Integer> emiOptions = listOfVehicles()
                .stream().filter(vehicle -> vehicle.getModel().equalsIgnoreCase(model))
                .flatMap(vehicle -> vehicle.getMonthlyPaymentOptions().stream())
                .collect(Collectors.toList());

        return emiOptions;
    }

    public List<Vehicle> getAllNewCarsByPriceAsc(){
        return retrieveNewCars();
    }

    private List<Vehicle> retrieveNewCars(){

        return listOfVehicles()
                .stream()
                .sorted(Comparator.comparing(Vehicle::getMsrp))
                .filter(vehicle -> vehicle.getYear() >= 2023)
                .collect(Collectors.toList());
    }

    public List<Vehicle> getAllCarsByPriceCustomRange(int minPrice, int maxPrice){
        return carsByCustomPriceRange(minPrice, maxPrice);
    }

    private List<Vehicle> carsByCustomPriceRange(int minPrice, int maxPrice){
        return listOfVehicles()
                .stream()
                .sorted(Comparator.comparing(Vehicle::getMsrp))
                .filter(vehicle -> vehicle.getMsrp() >= minPrice && vehicle.getMsrp() <=maxPrice)
                .collect(Collectors.toList());
    }

    public List<Vehicle> getAllCarsByCustomPriceWithMakeAndModel(String make, String model, int minPrice, int maxPrice){
        return retrieveCarsByCustomPriceMakeModel(make, model, minPrice, maxPrice);
    }

    private List<Vehicle> retrieveCarsByCustomPriceMakeModel(String make, String model, int minPrice, int maxPrice){

        return listOfVehicles()
                .stream()
                .sorted(Comparator.comparing(Vehicle::getMsrp))
                .filter(vehicle -> vehicle.getMake().equalsIgnoreCase(make) && vehicle.getModel().equalsIgnoreCase(model)
                 && vehicle.getMsrp() >= minPrice && vehicle.getMsrp() <= maxPrice)
                .collect(Collectors.toList());
    }


}
