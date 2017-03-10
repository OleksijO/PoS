package pos;

import pos.model.value.object.user.choice.Choice;
import pos.model.value.object.user.choice.Choices;

/**
 * Created by Oleksii_Onysymchuk on 3/9/2017.
 */
public class View {
    public static final String WRONG_USER_INPUT_VALUE = "[Error] No such choice. Try again.." ;

    public void printPrompt(Choices userChoices) {
        printLine(userChoices.getGeneralMessage());
        while (userChoices.hasMoreChoices()){
            Choice choice = userChoices.getNextChoice();
            printLine(choice.getNumber()+" - "+choice.getMessage());
        }
        printLine("");
        print("Enter your choice: ");
    }

    public void showMessage(String message) {
        printLine(message);
    }

    private void printLine(String line){
        System.out.println(line);
    }
    private void print(String string){
        System.out.print(string);
    }
}
