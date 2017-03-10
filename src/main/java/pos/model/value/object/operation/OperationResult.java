package pos.model.value.object.operation;

/**
 * Created by Oleksii_Onysymchuk on 3/9/2017.
 */
public class OperationResult {
    public final OperationResultType resultType;
    public final String resultMessage;

    public OperationResult(OperationResultType resultType, String resultMessage) {
        this.resultType = resultType;
        this.resultMessage = resultMessage;
    }
}
