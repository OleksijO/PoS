package pos.view;

import pos.model.value.object.user.choice.Choices;

/**
 * Created by Oleksii_Onysymchuk on 3/14/2017.
 */
public interface View {
    String WRONG_USER_INPUT_VALUE = "[Error] No such choice. Try again.." ;

    void printPrompt(Choices userChoices);

    void showMessage(String message);
}
