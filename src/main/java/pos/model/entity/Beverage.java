package pos.model.entity;

/**
 * Created by Oleksii_Onysymchuk on 3/9/2017.
 */
public enum Beverage {
    TEA("Tea",25),
    COFFEE("Coffee",35),
    JUICE("Juice",45);

    public String name;
    public int price;

    Beverage(String name, int price) {
        this.name = name;
        this.price = price;
    }
}
