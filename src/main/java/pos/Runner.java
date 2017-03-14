package pos;

import pos.controller.Controller;
import pos.model.PosModel;
import pos.model.entity.pos.CommandHolder;
import pos.model.entity.pos.PoS;
import pos.view.ConsoleView;
import pos.view.View;

/**
 * Created by Oleksii_Onysymchuk on 3/9/2017.
 */
public class Runner {
    public static void main(String[] args) {
        CommandHolder commandHolder = new CommandHolder();
        PosModel pos = new PoS(commandHolder);
        commandHolder.setPos(pos);
        commandHolder.init();
        View view = new ConsoleView();
        new Controller(
                pos,
                new ConsoleView()
        ).operate();
    }
}
