package com.king.king.util;

/**
 * @author neo.pan
 * @since 2018/04/19
 */
public class EdpOpException extends RuntimeException {
    private final OpResult result;
    private Object body;

//    public static void throwIf(Collection<EdpValidationError> errors) {
//        if (!errors.isEmpty()) {
//            throw new EdpOpException(EdpCrudOp.NG_VALIDATION, errors);
//        }
//    }

    public OpResult getResult() {
        return this.result;
    }

    public Object getBody() {
        return this.body;
    }

    public EdpOpException(final OpResult result, final Object body) {
        super(body.toString());
         this.result = result;
        this.body = body;
    }

    public EdpOpException(final OpResult result) {
        this.result = result;
    }
}
