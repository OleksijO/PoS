package pos.model.entity.pos;

import pos.model.entity.Coin;
import pos.model.value.object.operation.OperationResult;
import pos.model.value.object.operation.OperationResultType;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Oleksii_Onysymchuk on 3/9/2017.
 */
public class CoinBank {
    private static final int MAX_COINS = 10;
    private Map<Coin, Integer> coins = new HashMap<>();

    public OperationResult addCoin(Coin coin) {
        coins.putIfAbsent(coin, 0);
        int numberOfCoins = coins.get(coin);
        if (numberOfCoins < MAX_COINS) {
            coins.put(coin, ++numberOfCoins);
            return new OperationResult(
                    OperationResultType.OK,
                    "Your '" + coin.cost + "'coin was added to bank."
            );
        } else
            return new OperationResult(
                    OperationResultType.FAULT,
                    "Your '" + coin.cost + "'coin was NOT added to bank because it is full. " +
                            "Use another coin nominal, please."
            );
    }

    public OperationResult getCoins(int sum) {
        return new OperationResult(OperationResultType.OK, "Here is your rest: " + sum + " in coins");
    }
}
