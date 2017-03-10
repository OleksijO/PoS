package pos.model.entity.pos;

import pos.model.entity.Beverage;
import pos.model.value.object.operation.OperationResult;
import pos.model.value.object.operation.OperationResultType;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Oleksii_Onysymchuk on 3/9/2017.
 */
public class BeverageBank {

    private Map<Beverage, Integer> beverages = new HashMap<Beverage, Integer>() {{
        for (Beverage beverage : Beverage.values()) {
            put(beverage, 10);
        }
    }};

    public Set<Beverage> getAvailableBeverages() {
        return beverages.keySet();
    }

    public OperationResult prepareBeverage(Beverage beverage) {
        int numberOfBeverages = beverages.get(beverage);
        if (numberOfBeverages == 0) {
            return new OperationResult(OperationResultType.FAULT, "Sorry this beverage is no longer available.");
        }
        return new OperationResult(OperationResultType.OK, "Here your " + beverage.name);
    }

}
