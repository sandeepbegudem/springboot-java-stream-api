package org.codingwithsandeepb.stream.api.application.controller;

import org.codingwithsandeepb.stream.api.application.entity.Vehicle;
import org.codingwithsandeepb.stream.api.application.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cars")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping("/under-25k")
    public List<Vehicle> getAllVehiclesUnder25k(){
        return vehicleService.carsWithPriceUnder25k();
    }

    @GetMapping("/inventory-value")
    public ResponseEntity<Double> dealershipTotalInventoryValue(){
        return new ResponseEntity<>(vehicleService.totalInventoryValue(), HttpStatus.OK);
    }

    @GetMapping("/make/{make}")
    public ResponseEntity<List<Vehicle>> carsModelsByMake(@PathVariable String make){
        return new ResponseEntity<>(vehicleService.vehiclesByMake(make),HttpStatus.OK);
    }

    @GetMapping("/models/{model}")
    public ResponseEntity<List<Vehicle>> carsModelsByModel(@PathVariable String model){
        return new ResponseEntity<>(vehicleService.getCarsByMakeModel(model),HttpStatus.OK);
    }

    @GetMapping("/filter-by-year/{year}/sort-by-price-low")
    public ResponseEntity<List<Vehicle>> carsByYearPricesLowToHigh(@PathVariable Integer year){
        return new ResponseEntity<>(vehicleService.carsByYearSortByPriceAsc(year),HttpStatus.OK);
    }

    @GetMapping("/filter-by-year/{year}/sort-by-price-high")
    public ResponseEntity<List<Vehicle>> carsByYearPricesHighToLow(@PathVariable Integer year){
        return new ResponseEntity<>(vehicleService.carsByYearSortByPriceDesc(year),HttpStatus.OK);
    }

    @GetMapping("/model/{models}/color/{color}")
    public ResponseEntity<List<Vehicle>> carsByYearPricesDsc(@PathVariable String models, @PathVariable String color){
        return new ResponseEntity<>(vehicleService.carsByModelAndColor(models, color),HttpStatus.OK);
    }

    @GetMapping("/affordable-by-make/{make}")
    public ResponseEntity<Vehicle> cheapestCarByMake(@PathVariable String make){
        return new ResponseEntity<>(vehicleService.leastExpensiveModelByMake(make),HttpStatus.OK);
    }

    @GetMapping("/electric-cars-low-to-high/{make}")
    public ResponseEntity<List<Vehicle>> electricCarsByMakePriceAsc(@PathVariable String make){
        return new ResponseEntity<>(vehicleService.getElectricCarsByMakePriceAsc(make), HttpStatus.OK);

    }

    @GetMapping("/electric-cars-high-to-low/{model}")
    public ResponseEntity<List<Vehicle>> electricCarsByMakePriceDesc(@PathVariable String model){
        return new ResponseEntity<>(vehicleService.getElectricCarsByMakePriceDesc(model), HttpStatus.OK);

    }

    @GetMapping("/monthly-payment-plans/{model}")
    public ResponseEntity<List<Integer>> monthlyPaymentPlans(@PathVariable String model){
        return new ResponseEntity<>(vehicleService.getMonthlyPaymentOptions(model), HttpStatus.OK);
    }

    @GetMapping("/new-cars-pricing-low-to-high")
    public ResponseEntity<List<Vehicle>> newCars(){
        return new ResponseEntity<>(vehicleService.getAllNewCarsByPriceAsc(), HttpStatus.OK);
    }

    @GetMapping("/custom-price-range/{minPrice}/to/{maxPrice}")
    public ResponseEntity<List<Vehicle>> carsByCustomPriceRange(@PathVariable int minPrice, @PathVariable int maxPrice){
        return new ResponseEntity<>(vehicleService.getAllCarsByPriceCustomRange(minPrice, maxPrice), HttpStatus.OK);
    }

    @GetMapping("/custom-price-range-make/{make}/model/{model}/minPrice/{minPrice}/to/maxPrice/{maxPrice}")
    public ResponseEntity<List<Vehicle>> customPriceMakeAndModel(
            @PathVariable String make,
            @PathVariable String model,
            @PathVariable int minPrice,
            @PathVariable int maxPrice){
      return new ResponseEntity<>(vehicleService.getAllCarsByCustomPriceWithMakeAndModel
              (make, model, minPrice, maxPrice), HttpStatus.OK);
    }

}
