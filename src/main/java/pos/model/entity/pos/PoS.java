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
    private final Map<Choice, Command> commandMap = new HashMap<>();
    private int userBalance = 0;
    private List<Choice> choices;

    public PoS() {

        commandMap.put(new Choice(1, "Insert '1' coin"), new InsertCoinCommand(Coin.COIN_1, this));
        commandMap.put(new Choice(2, "Insert '5' coin"), new InsertCoinCommand(Coin.COIN_5, this));
        commandMap.put(new Choice(3, "Insert '10' coin"), new InsertCoinCommand(Coin.COIN_10, this));
        commandMap.put(new Choice(4, "Insert '25' coin"), new InsertCoinCommand(Coin.COIN_25, this));
        commandMap.put(new Choice(5, "Insert '50' coin"), new InsertCoinCommand(Coin.COIN_50, this));
        commandMap.put(
                new Choice(10, "Select " + Beverage.TEA.name + "(" + Beverage.TEA.price + ")"),
                new PrepareBeverageCommand(Beverage.TEA, this)
        );
        commandMap.put(
                new Choice(11, "Select " + Beverage.COFFEE.name + "(" + Beverage.COFFEE.price + ")"),
                new PrepareBeverageCommand(Beverage.COFFEE, this)
        );
        commandMap.put(
                new Choice(12, "Select " + Beverage.JUICE.name + "(" + Beverage.JUICE.price + ")"),
                new PrepareBeverageCommand(Beverage.JUICE, this)
        );
        commandMap.put(
                new Choice(20, "Cancel. Give my money back!"),
                new CancelCommand(this)
        );
        choices = new ArrayList<>(commandMap.keySet());
        Collections.sort(choices);
    }

    @Override
    public Choices getUserChoices() {
        return new Choices("----------------------------------------------------------\n" +
                "You're balance is " + userBalance + ". \n" +
                "Please, make your choice:", choices);
    }

    @Override
    public OperationResult performChoice(Choice choice) {
        return commandMap.getOrDefault(choice, new Command() {
            @Override
            public OperationResult execute() {
                return new OperationResult(OperationResultType.FAULT, "Wrong choice. Try again.");
            }
        }).execute();
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
