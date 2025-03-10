package foi.air.szokpt.transactionmng.exceptions;

import foi.air.szokpt.transactionmng.dtos.responses.ApiResponse;
import foi.air.szokpt.transactionmng.util.ApiResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiResponse<Object>> handleTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        if (ex.getRequiredType() != null && ex.getRequiredType().equals(LocalDateTime.class)) {
            return ResponseEntity.badRequest().body(ApiResponseUtil.failure("Invalid date format"));
        }
        return ResponseEntity.badRequest().body(ApiResponseUtil.failure("Invalid parameter value"));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException ex) {
        return new ResponseEntity<>(ApiResponseUtil.failure("Resource not found"),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Object> handleValidationException(ValidationException ex) {
        return new ResponseEntity<>(ApiResponseUtil.failure(ex.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TokenValidationException.class)
    public ResponseEntity<Object> handleValidationException(TokenValidationException ex) {
        return new ResponseEntity<>(ApiResponseUtil.failure("Invalid or expired token."),
                HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ExternalServiceException.class)
    public ResponseEntity<Object> handleValidationException(ExternalServiceException ex) {
        return new ResponseEntity<>(ApiResponseUtil.failure("External service unavailable"),
                HttpStatus.SERVICE_UNAVAILABLE);
    }

}
