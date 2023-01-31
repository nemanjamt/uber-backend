package faculty.project.uber.exceptions.handler;


import faculty.project.uber.constants.ErrorConstants;
import faculty.project.uber.exceptions.BadFileType;
import faculty.project.uber.exceptions.EmailAlreadyExistsException;
import faculty.project.uber.exceptions.UsernameAlreadyExistsException;
import faculty.project.uber.exceptions.model.ErrorResponse;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionsHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, final HttpHeaders headers, final HttpStatus status,
                                                                  final WebRequest request) {
//        logger.error("400 Status Code", ex);
        final BindingResult result = ex.getBindingResult();

        String error = result.getAllErrors().stream().map(e -> {
            if (e instanceof FieldError) {
                return ((FieldError) e).getField() + " : " + e.getDefaultMessage();
            } else {
                return e.getObjectName() + " : " + e.getDefaultMessage();
            }
        }).collect(Collectors.joining(", "));
        return handleExceptionInternal(ex, new ErrorResponse(error), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }



    @ExceptionHandler(value={EntityNotFoundException.class})
    protected ResponseEntity handleEntityNotFoundException(RuntimeException ex, WebRequest request){
        return handleExceptionInternal(ex, new ErrorResponse(ex.getMessage()), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value={FileSizeLimitExceededException.class})
    protected ResponseEntity handleFileSizeLimitException(RuntimeException ex, WebRequest request){
        return handleExceptionInternal(ex, ErrorConstants.MAX_FILE_SIZE, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }



    @ExceptionHandler(value={AuthenticationException.class})
    protected ResponseEntity handleBadCredentials(RuntimeException ex, WebRequest request){
        return handleExceptionInternal(ex, new ErrorResponse(ex.getMessage()), new HttpHeaders(), HttpStatus.FORBIDDEN, request);
    }

    @ExceptionHandler(value={UsernameAlreadyExistsException.class})
    protected ResponseEntity handleUsernameAlreadyExists(RuntimeException ex, WebRequest request){
        return handleExceptionInternal(ex, new ErrorResponse(ex.getMessage()), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value={EmailAlreadyExistsException.class})
    protected ResponseEntity handleEmailAlreadyExists(RuntimeException ex, WebRequest request){
        return handleExceptionInternal(ex, new ErrorResponse(ex.getMessage()), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }


}
