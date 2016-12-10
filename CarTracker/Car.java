/**
 * Created by Lenny on 3/19/16.
 */
public class Car {

    String vin;
    String make;
    String model;
    String color;
    int price;
    int mileage;

    public Car(String v, String make, String model, String color, int price, int mileage){
        vin=v;
        this.make=make;
        this.model=model;
        this.color=color;
        this.price=price;
        this.mileage=mileage;

    }

    public void setVin(String s){
        vin = s;
    }

    public void setMake(String s){
        make = s;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public String getVin() {
        return vin;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public int getPrice() {
        return price;
    }

    public int getMileage() {
        return mileage;
    }

    public String toString(){
        String s;
        s="VIN: "+vin+"\nMAKE: "+make+"\nMODEL: "+model+"\nCOLOR: "+color+"\nPRICE: $"+price+"\nMILEAGE: "+mileage;
        return s;
    }
}
