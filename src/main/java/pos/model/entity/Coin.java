package pos.model.entity;

/**
 * Created by Oleksii_Onysymchuk on 3/9/2017.
 */
public enum Coin {
    COIN_1(1),
    COIN_5(5),
    COIN_10(10),
    COIN_25(25),
    COIN_50(50);
    public int cost;

    Coin(int cost) {
        this.cost = cost;
    }
}
