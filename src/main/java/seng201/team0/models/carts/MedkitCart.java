package seng201.team0.models.carts;

public class MedkitCart extends Cart {
    private int medkitCapacity;
    private String resourceType;

    public MedkitCart(int size, int speed) {
        super(size, speed);
        this.medkitCapacity = 0;
        this.resourceType = "Medkits";
    }
    public String getResourceType(){
        return resourceType;
    }
}