package com.king.king.cig;

import com.king.king.util.EdpCrudOp;
import com.king.king.util.EdpOpException;
import com.king.king.util.EdpValidationError;
import com.king.king.util.EdpWebUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.NestedServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


/**shihao.ma 2019/12/31 猫哆哩迁移**/
@ControllerAdvice
public class EdpWebExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(EdpWebExceptionHandler.class);

    public EdpWebExceptionHandler() {
    }

    @ExceptionHandler({EdpOpException.class})
    public ResponseEntity<Object> handleOpException(EdpOpException ex) {
        Map<String,Object> map=new HashMap<>();
        map.put("errMessage",ex.getBody());
        return EdpWebUtil.opBuilder(ex.getResult()).body(map);
    }

    // TODO 引入shiro 的jar包 打开注释的代码
//    @ExceptionHandler({UnauthenticatedException.class})
//    public ResponseEntity<Object> handleUnauthenticatedException(UnauthenticatedException ex) {
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(HttpStatus.UNAUTHORIZED.getReasonPhrase());
//    }
//
//    @ExceptionHandler({UnauthorizedException.class})
//    public ResponseEntity<Object> handleUnauthorizedException(UnauthorizedException ex) {
//        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
//    }

    @ExceptionHandler({IllegalArgumentException.class, IllegalStateException.class})
    public ResponseEntity<Object> handleBadRequest(Exception ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler({NestedServletException.class})
    public ResponseEntity<Object> handleNestedServletException(NestedServletException ex, WebRequest request) throws Exception {
        Exception cause = (Exception)ex.getCause();
        return !(cause instanceof IllegalArgumentException) && !(cause instanceof IllegalStateException) ? this.handleException(cause, request) : this.handleBadRequest(cause);
    }

    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return EdpWebUtil.opBuilder(EdpCrudOp.NG_VALIDATION).body(toValidationErrors(ex));
    }

    private static List<EdpValidationError> toValidationErrors(MethodArgumentNotValidException ex) {
        return (List<EdpValidationError>)ex.getBindingResult().getAllErrors().stream().filter((e) -> {
            return e instanceof FieldError;
        }).map((e) -> {
            return (FieldError)e;
        }).map((e) -> {
            return EdpValidationError.of(e.getField(), e.getDefaultMessage());
        }).collect(Collectors.toList());
     //   return null;
    }

    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return super.handleExceptionInternal(ex, ex.getMessage(), headers, status, request);
    }

    @ExceptionHandler({MultipartException.class})
    public ModelAndView handleMultipartException(MultipartException ex, HttpServletResponse response) {
        if (log.isWarnEnabled()) {
            Throwable cause = (Throwable) Optional.ofNullable(ex.getRootCause()).orElse(ex);
            log.warn("[EDP-WEB-E{}] BAD file upload request: {}", HttpStatus.BAD_REQUEST, cause.getMessage());
        }

        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return new ModelAndView();
    }

    @ExceptionHandler({MaxUploadSizeExceededException.class})
    public ModelAndView handleMaxUploadSizeExceededException(MaxUploadSizeExceededException ex, HttpServletResponse response) {
        HttpStatus sts = HttpStatus.PAYLOAD_TOO_LARGE;
        if (log.isInfoEnabled()) {
            Throwable cause = (Throwable)Optional.ofNullable(ex.getRootCause()).orElse(ex);
            log.info("[EDP-WEB-E{}] TOO LARGE file uploaded: {}", sts, cause.getMessage());
        }

        response.setStatus(sts.value());
        return new ModelAndView();
    }

    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<Object> handleDataIntegrityViolationException(Exception ex, HttpServletRequest request) {

        return this.handleError(HttpStatus.CONFLICT, ex, request);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleUnexpectedException(Exception ex, HttpServletRequest request) {
        return this.handleError(HttpStatus.INTERNAL_SERVER_ERROR, ex, request);
    }

    private ResponseEntity<Object> handleError(HttpStatus status, Exception ex, HttpServletRequest request) {
        String msg = "[EDP-WEB-E" + status + "] " + EdpWebUtil.toTraceInfo(request);
        if (log.isDebugEnabled()) {
            log.error(msg, ex);
        } else {
            log.error(msg + " - " + ex.getMessage());
        }

        return ResponseEntity.status(status).body(ex.getMessage());
    }
}
