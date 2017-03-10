package pos.model.value.object.user.choice;

/**
 * Created by Oleksii_Onysymchuk on 3/9/2017.
 */
public class Choice implements Comparable<Choice> {
    private final int number;
    private final String message;

    public Choice(int number, String message) {
        this.number = number;
        this.message = message;
    }

    public int getNumber() {
        return number;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public int compareTo(Choice o) {
        if (number>o.number) {return 1;}
        if (number==o.number) {return 0;}
        return -1;
    }
}
