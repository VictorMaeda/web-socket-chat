package projeto.chatweb.error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<String> handleException(final Throwable ex) {
        return ResponseEntity.internalServerError().body("Ocorreu um erro inesperado no servidor");
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<String> handleBusinessException(final BusinessException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

}
