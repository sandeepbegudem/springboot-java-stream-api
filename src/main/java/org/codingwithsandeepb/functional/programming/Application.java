package org.codingwithsandeepb.functional.programming;

public class Application {

    public static void main(String[] args) {

        Shape circle = () -> System.out.println("drawing a circle");
        //circle.draw();

        Shape rectangle = () -> System.out.println("drawing a rectangle");
        rectangle.draw();

        Shape shape = () -> System.out.println("printing a square");
        //Shape.print(shape);

        Shape trapezoid = () -> System.out.println("drawing the share of a trapezoid");

     print(circle);
     print(rectangle);
     print(trapezoid);

     // lambda example for products

    }

    private static void addItems(Products products) {
    }


    private static void print(Shape shape){
        shape.draw();
    }




}
