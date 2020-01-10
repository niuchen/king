package com.king.king.util;

import lombok.Data;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@Data
public class ExcelFieldMethod {
    private Method method;
    private Field field;
}
