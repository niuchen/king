package com.king.king.error;

public class DevError extends Error {
    public DevError(String message) {
        super(message);
    }

    public DevError(String message, Throwable cause) {
        super(message, cause);
    }

    public static DevError todo() {
        return new DevError("Not Impl.");
    }

    public static DevError unexpected() {
        return unexpected("Unexpected.");
    }

    public static DevError unexpected(String message) {
        return new DevError(message);
    }
}
