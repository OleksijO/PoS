package pos.model.entity.pos;

import pos.model.Command;
import pos.model.PosModel;
import pos.model.command.CancelCommand;
import pos.model.command.InsertCoinCommand;
import pos.model.command.PrepareBeverageCommand;
import pos.model.entity.Beverage;
import pos.model.entity.Coin;
import pos.model.value.object.user.choice.Choice;
import pos.model.value.object.user.choice.Choices;
import pos.model.value.object.operation.OperationResult;
import pos.model.value.object.operation.OperationResultType;

import java.util.*;

/**
 * Created by Oleksii_Onysymchuk on 3/9/2017.
 */
public class PoS implements PosModel {
    private final CoinBank coinBank = new CoinBank();
    private final BeverageBank beverageBank = new BeverageBank();

    private int userBalance = 0;

    private CommandHolder commandHolder;

    public PoS(CommandHolder commandHolder) {
        this.commandHolder = commandHolder;
    }

    @Override
    public Choices getUserChoices() {
        return new Choices("----------------------------------------------------------\n" +
                "You're balance is " + userBalance + ". \n" +
                "Please, make your choice:", commandHolder.getAllChoices());
    }

    @Override
    public OperationResult performChoice(Choice choice) {
        return commandHolder.findCommandByChoice(choice).execute();
    }

    @Override
    public OperationResult insertCoin(Coin coin) {
        OperationResult result = coinBank.addCoin(coin);
        if (result.resultType == OperationResultType.OK) {
            userBalance += coin.cost;
        }
        return result;
    }

    @Override
    public OperationResult prepareBeverage(Beverage beverage) {
        if (beverage.price <= userBalance) {
            OperationResult beverageResult = beverageBank.prepareBeverage(beverage);

            if (beverageResult.resultType == OperationResultType.OK) {
                userBalance -= beverage.price;
                OperationResult restResult = coinBank.getCoins(userBalance);
                userBalance = 0;
                return
                        new OperationResult(
                                restResult.resultType,
                                beverageResult.resultMessage + "\n" + restResult.resultMessage
                        );
            }
            OperationResult restResult = coinBank.getCoins(userBalance);
            return
                    new OperationResult(
                            restResult.resultType,
                            beverageResult.resultMessage + "\n" + restResult.resultMessage
                    );
        }
        return new OperationResult(
                OperationResultType.FAULT,
                "You have unsufficient founds. Please, select another beverage or add more coins"
        );
    }

    @Override
    public OperationResult returnCoins() {
        OperationResult result = coinBank.getCoins(userBalance);
        if (result.resultType == OperationResultType.OK) {
            userBalance = 0;
        }
        return result;
    }

}
