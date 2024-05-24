package seng201.team0.models.carts;

public class MedkitCart extends Cart {
    private final String resourceType;

    public MedkitCart(int size, int speed) {
        super(size, speed);
        this.resourceType = "Medkits";
    }
    public String getResourceType(){
        return resourceType;
    }
}