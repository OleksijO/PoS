package pos.model.command;

import pos.model.entity.Beverage;
import pos.model.Command;
import pos.model.PosModel;
import pos.model.value.object.operation.OperationResult;

/**
 * Created by Oleksii_Onysymchuk on 3/9/2017.
 */
public class PrepareBeverageCommand implements Command {
    private PosModel model;
    private Beverage beverage;

    public PrepareBeverageCommand(Beverage beverage, PosModel model) {
        this.model = model;
        this.beverage = beverage;
    }

    @Override
    public OperationResult execute() {
        return model.prepareBeverage(beverage);
    }
}
