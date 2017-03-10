package pos;

import pos.controller.Controller;
import pos.controller.MyScanner;
import pos.model.entity.pos.PoS;

/**
 * Created by Oleksii_Onysymchuk on 3/9/2017.
 */
public class Runner {
    public static void main(String[] args) {
        new Controller(
                new PoS(),
                new View()
        ).operate();
    }
}
