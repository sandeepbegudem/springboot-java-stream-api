package org.codingwithsandeepb.functional.programming;

import java.util.*;
import java.util.stream.Collectors;

import static org.codingwithsandeepb.functional.programming.Warranty.productWarranty;

class Warranty {
    private Integer warrantyPeriod;


    public Integer getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setWarrantyPeriod(Integer warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }

    public Warranty(Integer warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }

    public static List<Warranty> productWarranty(){
        List<Warranty> warranty = new ArrayList<>();
        warranty.add(new Warranty(3));
        warranty.add(new Warranty(1));
        warranty.add(new Warranty(1));
        warranty.add(new Warranty(1));
        warranty.add(new Warranty(2));
        return warranty;
    }

    @Override
    public String toString() {
        return "Warranty{" +
                "warrantyPeriod=" + warrantyPeriod +
                '}';
    }
}

public class Products {

    private int id;
    private String name;
    private String description;

    public Products(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Products{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public static List<Products> listOfProducts(){

        List<Products> products = new ArrayList<>();
        products.add(new Products(101, "TV", "LG TV Ultra HD"));
        products.add(new Products(102, "Laptop", "MacBook pro M2 pro max"));
        products.add(new Products(103, "Mobile", "Apple Iphone 12 pro max"));
        products.add(new Products(104, "Mobile", "Apple Iphone 13 pro max"));
        products.add(new Products(105, "Mobile", "Apple Iphone 14 pro max"));
        return products;

    }

    public static void getListOfProductsContainingMobilePhone(String phone){
        listOfProducts().stream()
                .filter(products -> products.getName().equalsIgnoreCase(phone))
                .collect(Collectors.toList()).forEach(System.out::println);

        }

        public static void addWarrantyToProduct(Integer id){

            Map<Products, Warranty> hashMap = new HashMap<>();
            hashMap.put(listOfProducts().get(0), productWarranty().get(0));
            hashMap.put(listOfProducts().get(1), productWarranty().get(1));
            hashMap.put(listOfProducts().get(2), productWarranty().get(2));
            hashMap.put(listOfProducts().get(3), productWarranty().get(3));
            hashMap.put(listOfProducts().get(4), productWarranty().get(4));
            //hashMap.entrySet().stream().filter(products -> products.getKey().equals(id))
              //      .collect(Collectors.toList()).forEach(System.out::println);
            System.out.println(hashMap.entrySet().stream().distinct()
                    .filter(p -> p.getValue().getWarrantyPeriod().equals(3))
                    .collect(Collectors.toList()));

        }

    public static void main(String[] args) {
        getListOfProductsContainingMobilePhone(("Mobile"));

        addWarrantyToProduct(105);
    }


}

