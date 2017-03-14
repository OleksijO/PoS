package pos.controller;

import pos.view.View;
import pos.model.*;
import pos.model.value.object.user.choice.Choice;
import pos.model.value.object.user.choice.Choices;
import pos.model.value.object.operation.OperationResult;
import pos.model.value.object.operation.OperationResultType;

/**
 * Created by Oleksii_Onysymchuk on 3/9/2017.
 */
public class Controller {

    private PosModel model;

    private View view;

    private MyScanner scanner = new MyScanner();

    public Controller(PosModel model, View view) {
        this.model = model;
        this.view = view;
    }

    /**
     * runs main cycle
     */
    public void operate() {
        OperationResult result;
        do {
            Choices choices = model.getUserChoices();
            view.showMessage("");
            view.printPrompt(choices);
            int userInput = scanner.inputChoice();
            Choice choice = choices.getChoiceByNumber(userInput);
            result = model.performChoice(choice);
            view.showMessage(result.resultType.toString());
            view.showMessage(result.resultMessage);

        } while (result.resultType != OperationResultType.EXIT);

    }

    public void setModel(PosModel model) {
        this.model = model;
    }

    public void setView(View view) {
        this.view = view;
    }

    public void setScanner(MyScanner scanner) {
        this.scanner = scanner;
    }
}
