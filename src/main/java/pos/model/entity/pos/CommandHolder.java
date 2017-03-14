package pos.model.entity.pos;

import pos.model.Command;
import pos.model.PosModel;
import pos.model.command.CancelCommand;
import pos.model.command.InsertCoinCommand;
import pos.model.command.PrepareBeverageCommand;
import pos.model.entity.Beverage;
import pos.model.entity.Coin;
import pos.model.value.object.operation.OperationResult;
import pos.model.value.object.operation.OperationResultType;
import pos.model.value.object.user.choice.Choice;

import java.util.*;

/**
 * Created by Oleksii_Onysymchuk on 3/14/2017.
 */
public class CommandHolder {
    private final Map<Choice, Command> commandMap = new HashMap<>();
    private PosModel pos;
    private List<Choice> choices;

    public Command findCommandByChoice(Choice choice){
        return commandMap
                .getOrDefault(choice, () ->
                        new OperationResult(OperationResultType.FAULT, "Wrong choice. Try again."));
    }

    public List<Choice> getAllChoices(){
        Objects.requireNonNull(choices);
        return choices;
    }

    public void setPos(PosModel pos) {
        this.pos = pos;
    }

    public void init(){
        Objects.requireNonNull(pos);
        int i=0;
        for (Coin coin:Coin.values()){
            commandMap.put(new Choice(++i, "Insert '"+coin.cost+"' coin"), new InsertCoinCommand(coin, pos));
        }
        commandMap.put(
                new Choice(10, "Select " + Beverage.TEA.name + "(" + Beverage.TEA.price + ")"),
                new PrepareBeverageCommand(Beverage.TEA, pos)
        );
        commandMap.put(
                new Choice(11, "Select " + Beverage.COFFEE.name + "(" + Beverage.COFFEE.price + ")"),
                new PrepareBeverageCommand(Beverage.COFFEE, pos)
        );
        commandMap.put(
                new Choice(12, "Select " + Beverage.JUICE.name + "(" + Beverage.JUICE.price + ")"),
                new PrepareBeverageCommand(Beverage.JUICE, pos)
        );
        commandMap.put(
                new Choice(20, "Cancel. Give my money back!"),
                new CancelCommand(pos)
        );
        commandMap.put(
                new Choice(100, "Exit program."),
                () -> new OperationResult(OperationResultType.EXIT, "Good bye!")
        );
        choices = new ArrayList<>(commandMap.keySet());
        Collections.sort(choices);
    }
}
