package pos.model;

import pos.model.value.object.operation.OperationResult;

/**
 * Created by Oleksii_Onysymchuk on 3/9/2017.
 */
public interface Command {
    OperationResult execute();
}
