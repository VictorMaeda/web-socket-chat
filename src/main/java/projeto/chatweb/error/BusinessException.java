package projeto.chatweb.error;

public class BusinessException extends RuntimeException{
    public BusinessException(String error) {
        super(error);
    }
}
