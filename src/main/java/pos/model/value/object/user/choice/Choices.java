package pos.model.value.object.user.choice;

import java.util.*;

/**
 * Created by Oleksii_Onysymchuk on 3/9/2017.
 */
public class Choices {
    private List<Choice> choices = new ArrayList<>();
    private Iterator<Choice> iterator;
    private String generalMessage;

    public Choices(String generalMessage, List<Choice> choices) {
        this.choices = choices;
        this.generalMessage = generalMessage;
    }

    public void addChoice(int number, Choice choice) {
        choices.add(number, choice);
    }

    public boolean hasMoreChoices() {
        if (iterator == null) {
            iterator = choices.iterator();
        }
        if (!iterator.hasNext()){
            iterator = choices.iterator();
            return false;
        }
        return true;
    }

    public Choice getNextChoice() {
        return iterator.next();
    }

    public String getGeneralMessage() {
        return generalMessage;
    }

    public Choice getChoiceByNumber(int number) {
        for (Choice choice : choices) {
            if (choice.getNumber() == number) {
                return choice;
            }
        }
        return null;
    }
}
