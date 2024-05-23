package seng201.team0.models.carts;

public class MedkitCart extends Cart {
    private int medkitCount;
    private String resourceType;

    public MedkitCart(int size, int speed) {
        super(size, speed);
        this.medkitCount = 0;
        this.resourceType = "Medkits";
    }
    public String getResourceType(){
        return resourceType;
    }

    public int getMedkitCapacity() {
        return medkitCount;
    }

    public void setMedkitCapacity(int medkitCount) {
        this.medkitCount = medkitCount;
    }


    public void fillCart() {
        // Implementation specific to MedkitCart
        System.out.println("Filling Medkit Cart with " + medkitCount + " medkits.");
    }
}