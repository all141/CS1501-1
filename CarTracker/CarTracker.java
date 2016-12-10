/**
 * Created by Lenny on 3/20/16.
 */
import java.util.*;

public class CarTracker {

    public static void main(String args[]){

        DoubleMinPQ pq = new DoubleMinPQ();
        boolean quit = false;
        Scanner scan = new Scanner(System.in);
        int choice;

        while(!quit){
            printMenu();
            //scan.next();
            System.out.print("\nEnter menu option: ");
            choice=scan.nextInt();

            switch (choice){
                case 1: add(pq);
                    break;
                case 2: update(pq);
                    break;
                case 3: remove(pq);
                    break;
                case 4: lowPrice(pq);
                    break;
                case 5: lowMiles(pq);
                    break;
                case 6: priceMM(pq);
                    break;
                case 7: milesMM(pq);
                    break;
                case 0: quit=true;
                    break;
            }

        }

    }

    private static void printMenu(){

        System.out.println("---------------------------------");
        System.out.println("            MAIN MENU            ");
        System.out.println("---------------------------------");
        System.out.println();
        System.out.println("1. Add a car");
        System.out.println("2. Update a car");
        System.out.println("3. Remove a car");
        System.out.println("4. Retrieve lowest price car");
        System.out.println("5. Retrieve lowest mileage car");
        System.out.println("6. Retrieve the lowest price car by make and model");
        System.out.println("7. Retrieve the lowest mileage car by make and model");
        System.out.println("0. QUIT");
    }

    private static void add(DoubleMinPQ pq){
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter info about the car");

        System.out.print("Enter VIN: ");
        String vin = scan.nextLine();

        System.out.print("\nEnter make: ");
        String make = scan.nextLine();

        System.out.print("\nEnter model: ");
        String model = scan.nextLine();

        System.out.print("\nEnter color: ");
        String color = scan.nextLine();

        System.out.print("\nEnter price: ");
        int price = scan.nextInt();

        System.out.print("\nEnter mileage: ");
        int mileage = scan.nextInt();

        Car car = new Car(vin, make, model, color, price, mileage);

        pq.add(car);
    }

    private static void update(DoubleMinPQ pq){
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter VIN of car to update: ");
        String vin = scan.nextLine();

        System.out.println("Would you like to update: \n\t1. Price\n\t2. Mileage\n\t3. Color");
        int choice = scan.nextInt();

        if(choice == 1){
            System.out.print("Enter new price: ");
            int price = scan.nextInt();
            pq.update(vin, 1, price);
        }
        else if(choice == 2){
            System.out.print("Enter new mileage: ");
            int miles = scan.nextInt();
            pq.update(vin, 2, miles);
        }
        else if(choice == 3){
            System.out.print("Enter new color: ");
            scan.nextLine();
            String color = scan.nextLine();
            pq.update(vin, color);
        }
        else System.out.println("INVALID SELECTION");

    }

    private static void remove(DoubleMinPQ pq){
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter VIN of car to remove: ");
        String vin = scan.nextLine();

        pq.remove(vin);
    }

    private static void lowPrice(DoubleMinPQ pq){
        Car c = pq.getLowPrice();
        if(c != null)System.out.println(c.toString());
    }

    private static void lowMiles(DoubleMinPQ pq){
        Car c = pq.getLowMiles();
        if(c != null)System.out.println(c.toString());
    }

    private static void priceMM(DoubleMinPQ pq){
        Scanner scan = new Scanner(System.in);

        System.out.print("\nEnter make: ");
        String make = scan.nextLine();

        System.out.print("\nEnter model: ");
        String model = scan.nextLine();

        Car c = pq.priceMM(make, model);

        if(c != null) System.out.println(c.toString());

    }

    private static void milesMM(DoubleMinPQ pq){
        Scanner scan = new Scanner(System.in);

        System.out.print("\nEnter make: ");
        String make = scan.nextLine();

        System.out.print("\nEnter model: ");
        String model = scan.nextLine();

        Car c = pq.milesMM(make, model);

        if(c != null) System.out.println(c.toString());

    }

}
