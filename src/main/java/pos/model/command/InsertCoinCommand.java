package pos.model.command;

import pos.model.entity.Coin;
import pos.model.Command;
import pos.model.PosModel;
import pos.model.value.object.operation.OperationResult;

/**
 * Created by Oleksii_Onysymchuk on 3/9/2017.
 */
public class InsertCoinCommand implements Command {

    private Coin coin;
    private PosModel pos;

    public InsertCoinCommand(Coin coin, PosModel pos) {
        this.coin = coin;
        this.pos = pos;
    }

    @Override
    public OperationResult execute() {
        return pos.insertCoin(coin);
    }
}
