/**
 * Created by Lenny on 3/19/16.
 */
import java.util.*;

public class DoubleMinPQ {

    MinPQ<PQNode> price;
    MinPQ<PQNode> miles;
    ArrayList<Car> cars;
    int size;

    Car dud = new Car("_______", "_______","_______","_______", Integer.MAX_VALUE, Integer.MAX_VALUE);

    public DoubleMinPQ(){
        price = new MinPQ();
        miles = new MinPQ();
        cars = new ArrayList();
        size = 0;
    }


    public void add(Car c){
        cars.add(size, c);
        price.insert(new PQNode(size, c.getPrice()));
        miles.insert(new PQNode(size, c.getMileage()));
        size++;
    }

    public void remove(String vin){
        if(size==0){
            System.out.println("No cars to remove");
            return;
        }

        int pos = -1;
        for(int x = 0; x<size; x++){
            if(cars.get(x).getVin().equalsIgnoreCase(vin)){
                pos=x;
                break;
            }
        }

        if(pos==-1){
            System.out.println("Car with given VIN not found");
            return;
        }

        cars.set(pos, dud);
        ArrayList<PQNode> temp = new ArrayList<PQNode>();
        int tempSize = 0;

        while(!price.isEmpty()){
            PQNode node = price.delMin();
            if(node.pos == pos){
                break;
            }
            else{
                temp.add(node);
                tempSize++;
            }
        }

        for(int x = 0; x<tempSize; x++){
            price.insert(temp.get(x));
        }

        tempSize=0;
        temp.clear();

        while(!miles.isEmpty()){
            PQNode node = miles.delMin();
            if(node.pos == pos){
                break;
            }
            else{
                temp.add(node);
                tempSize++;
            }
        }

        for(int x = 0; x<tempSize; x++){
            miles.insert(temp.get(x));
        }
        size--;
    }

    public Car getLowPrice(){
        if(size==0){
            System.out.println("No cars in system");
            return null;
        }
        return cars.get(price.min().pos);
    }

    public Car getLowMiles(){
        if(size==0){
            System.out.println("No cars in system");
            return null;
        }
        return cars.get(miles.min().pos);
    }

    public void update(String vin, String newColor){
        if(size==0){
            System.out.println("No cars in system");
            return;
        }

        int pos = -1;
        for(int x = 0; x<size; x++){
            if(cars.get(x).getVin().equalsIgnoreCase(vin)){
                pos=x;
                break;
            }
        }

        if(pos==-1){
            System.out.println("Car with given VIN not found");
            return;
        }

        cars.get(pos).setColor(newColor);
    }

    public void update(String vin, int mode, int data){
        if(size==0){
            System.out.println("No cars in system");
            return;
        }

        int pos = -1;
        for(int x = 0; x<size; x++){
            if(cars.get(x).getVin().equalsIgnoreCase(vin)){
                pos=x;
                break;
            }
        }

        if(pos==-1){
            System.out.println("Car with given VIN not found");
            return;
        }

        if(mode==1){
            cars.get(pos).setPrice(data);
            ArrayList<PQNode> temp = new ArrayList<PQNode>();
            int tempSize = 0;

            while(!price.isEmpty()){
                PQNode node = price.delMin();
                if(node.pos == pos){
                    node.val=data;
                    temp.add(node);
                    tempSize++;
                    break;
                }
                else{
                    temp.add(node);
                    tempSize++;
                }
            }

            for(int x = 0; x<tempSize; x++){
                price.insert(temp.get(x));
            }

        }
        else{
            cars.get(pos).setMileage(data);

            ArrayList<PQNode> temp = new ArrayList<PQNode>();
            int tempSize = 0;

            while(!miles.isEmpty()){
                PQNode node = miles.delMin();
                if(node.pos == pos){
                    node.val=data;
                    temp.add(node);
                    tempSize++;
                    break;
                }
                else{
                    temp.add(node);
                    tempSize++;
                }
            }

            for(int x = 0; x<tempSize; x++){
                miles.insert(temp.get(x));
            }

        }

    }

    public Car priceMM(String make, String model){
        if(size==0){
            System.out.println("No cars in system");
            return null;
        }

        ArrayList<PQNode> temp = new ArrayList<PQNode>();
        int tempSize = 0;
        PQNode node=null;

        boolean found = false;
        while(!price.isEmpty()){
            node = price.delMin();
            if(cars.get(node.pos).getMake().equalsIgnoreCase(make) && cars.get(node.pos).getModel().equalsIgnoreCase(model)){
                temp.add(node);
                tempSize++;
                found=true;
                break;
            }
            else{
                temp.add(node);
                tempSize++;
            }
        }

        for(int x = 0; x<tempSize; x++){
            price.insert(temp.get(x));
        }
        if(!found){
            System.out.println("No car with given make and model found");
            return null;
        }

        return cars.get(node.pos);

    }


    public Car milesMM(String make, String model){
        if(size==0){
            System.out.println("No cars in system");
            return null;
        }

        ArrayList<PQNode> temp = new ArrayList<PQNode>();
        int tempSize = 0;
        PQNode node=null;

        boolean found = false;
        while(!miles.isEmpty()){
            node = miles.delMin();
            if(cars.get(node.pos).getMake().equalsIgnoreCase(make) && cars.get(node.pos).getModel().equalsIgnoreCase(model)){
                temp.add(node);
                tempSize++;
                found=true;
                break;
            }
            else{
                temp.add(node);
                tempSize++;
            }
        }

        for(int x = 0; x<tempSize; x++){
            miles.insert(temp.get(x));
        }
        if(!found){
            System.out.println("No car with given make and model found");
            return null;
        }

        return cars.get(node.pos);

    }

}
