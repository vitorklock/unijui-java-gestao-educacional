package shared.exceptions;

public class ForbiddenOperationException extends DomainException {
    public ForbiddenOperationException(String msg) { super(msg); }
}
