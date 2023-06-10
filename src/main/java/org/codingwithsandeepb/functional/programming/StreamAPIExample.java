package org.codingwithsandeepb.functional.programming;


import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Countries{
    private int countryId;
    private String country;

    public Countries(int countryId, String country) {
        this.countryId = countryId;
        this.country = country;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Countries{" +
                "countryId=" + countryId +
                ", country='" + country + '\'' +
                '}';
    }
}
class Items{
    private int itemId;
    private String name;
    private Double price;

    public Items(int itemId, String name, double price) {
        this.itemId = itemId;
        this.name = name;
        this.price = price;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setItemId(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Items{" +
                "itemId=" + itemId +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}

public class StreamAPIExample {

    public static void main(String[] args) {

        // Create stream using Stream type
        Stream<String> hobbies = Stream.of("music", "gym", "road-trips", "study", "relax");
         // printing the stream using the ForEach method
        hobbies.forEach(System.out::println);


        // create stream using Collection

        Collection<String> message = Arrays.asList("Hello,", "my name is: ", "sandeep", "begudem");
        message.stream().forEach(System.out::println);


        // create List of objects using stream

        List<String> items = Arrays.asList("watch", "backpack", "shoes", "snacks", "phone", "wallet");
        items.stream().forEach(System.out::println);

        // create a stream using Set interface
        Set<String> cars = Set.of("Bugatti", "Ferrari", "Lotus", "Aston Martin", "Lamborghini", "Toyota");
        Set<String> setOfCars = cars.stream().sorted().collect(Collectors.toSet());
        System.out.println(setOfCars);

// traditional approach to apply a filter
        Set<Items> itemsGreaterthanHundered = new LinkedHashSet<>();

        List<Items> item = StreamAPIExample.getAllItems();

        for(Items listofItems : item){
            if (listofItems.getPrice() > 100.00){
                itemsGreaterthanHundered.add(listofItems);
            }
        }
        for (Items updatedItems: itemsGreaterthanHundered){
            //System.out.println(updatedItems);
        }

     // modern way to apply filter using stream
    // filter items price greater than 100
        List<Items> allItems = StreamAPIExample.getAllItems();
        allItems.stream().filter(p -> p.getPrice() >= 100)
                .collect(Collectors.toList())
                .forEach(System.out::println);


        // sort the items by id in ascending order by ID
        allItems.stream()
                .sorted(Comparator.comparing(Items::getItemId))
                .collect(Collectors.toList())
                .forEach(System.out::println);
        //System.out.println(sortById);

        // sorting the list of Items in descending order by Id

        allItems.stream().sorted(Comparator.comparing(Items::getItemId).reversed()).collect(Collectors.toList())
                .forEach(System.out::println);

        // sorting the list of items by price descending

        allItems.stream().sorted(Comparator.comparing(Items::getPrice).reversed()).collect(Collectors.toList())
                .forEach(System.out::println);

        //sorting the list of Items by price in ascending order
        allItems.stream().sorted(Comparator.comparing(Items::getPrice))
                .collect(Collectors.toList())
                .forEach(System.out::println);

        //sorting the list of the items by alphabetical order
        System.out.println("sorting the list of the items by alphabetical order below:- \n");

        allItems.stream().sorted(Comparator.comparing(Items::getName))
                .collect(Collectors.toList())
                .forEach(System.out::println);

        Map<String, Optional<Items>> collect = allItems.stream()
                .collect(Collectors.groupingBy(Items::getName,
                        Collectors.maxBy(Comparator.comparing(Items::getPrice))));
        System.out.println("max price \n: ");
        System.out.println(collect);

        System.out.println("******************* Items above 100 USD : starts ************************\n");

        Items priceAbove100 = allItems.stream().
                filter(items1 -> items1.getPrice() > 250.00)
                .map(items1 -> new Items(
                        items1.getItemId(),
                        items1.getName(),
                        items1.getPrice()
                )).sorted(Comparator.comparing(Items::getPrice).reversed())
                .findFirst()
                .orElse(null);

        System.out.println(priceAbove100);
        System.out.println("******************* End of items above 100 USD ************************\n");

        Items maxPriceItem = allItems.stream().min(Comparator.comparing(Items::getPrice).reversed())
                .stream().findFirst()
                .orElse(null);
        System.out.println(maxPriceItem);


        // List of fruits to sort them using comparator compareTo
        List<String> fruits = Arrays.asList("mango", "custard apple", "orange", "banana", "strawberry", "blueberry");

        fruits.stream().sorted(((o1, o2) -> o2.compareTo(o1)))
                .collect(Collectors.toList())
                .forEach(System.out::println);

        // sorting list of countries by country name alphabetical order
        System.out.println(" sorting list of countries by country name alphabetical order");
        List<Countries> countries = StreamAPIExample.listOfCountries();
        countries.stream()
                .sorted(Comparator.comparing(Countries::getCountry))
                .collect(Collectors.toList())
                .forEach(System.out::println);





    }

    private static List<Items> getAllItems(){
        // create list of Items

        List<Items> inStockItems = Arrays.asList(
                new Items(6, "keyboard", 29.99),
                new Items(1, "standing-desk", 399.49),
                new Items(4, "chair", 568.99),
                new Items(3, "mouse", 105.39),
                new Items(2, "light", 19.99),
                new Items(5, "mouse-pad", 5.89),
                new Items(7, "bluetooth-speakers", 259.89)
        );
        return inStockItems;
    }

    private static List listOfCountries() {
        List<Countries> countriesList = new ArrayList<>();
        countriesList.add(new Countries(7, "Japan"));
        countriesList.add(new Countries(2, "Australia"));
        countriesList.add(new Countries(5, "UK"));
        countriesList.add(new Countries(4, "UAE"));
        countriesList.add(new Countries(1, "India"));
        countriesList.add(new Countries(3, "USA"));
        countriesList.add(new Countries(6, "Canada"));
        return countriesList;
    }


}
