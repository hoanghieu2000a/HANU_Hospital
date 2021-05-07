package se2.hanu_hospital.consumable;


public class ConsumablePayload {
    private String name;
    private int quantity;
    private int priceBought;
    private int priceSell;

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPriceBought() {
        return priceBought;
    }

    public int getPriceSell() {
        return priceSell;
    }
}
