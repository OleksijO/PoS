package pos.model;

import pos.model.entity.*;
import pos.model.value.object.user.choice.Choice;
import pos.model.value.object.user.choice.Choices;
import pos.model.value.object.operation.OperationResult;

/**
 * Created by Oleksii_Onysymchuk on 3/9/2017.
 */
public interface PosModel {

    Choices getUserChoices();

    OperationResult performChoice(Choice choice);

    OperationResult insertCoin(Coin coin);

    OperationResult prepareBeverage(Beverage beverage);

    OperationResult returnCoins();

}
