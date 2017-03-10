package pos.model.command;

import pos.model.Command;
import pos.model.PosModel;
import pos.model.value.object.operation.OperationResult;

/**
 * Created by Oleksii_Onysymchuk on 3/10/2017.
 */
public class CancelCommand implements Command {
    private PosModel model;

    public CancelCommand(PosModel model) {

        this.model = model;
    }

    @Override
    public OperationResult execute() {
        return model.returnCoins();
    }
}
