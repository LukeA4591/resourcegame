package seng201.team0.models.carts;

public class MedkitCart extends Cart {
    private int medkitCount;

    public MedkitCart(int size, String resourceType, int speed, int medkitCount) {
        super(size, resourceType, speed);
        this.medkitCount = medkitCount;
    }

    public int getMedkitCount() {
        return medkitCount;
    }

    public void setMedkitCount(int medkitCount) {
        this.medkitCount = medkitCount;
    }

    @Override
    public void fillCart() {
        // Implementation specific to MedkitCart
        System.out.println("Filling Medkit Cart with " + medkitCount + " medkits.");
    }
}