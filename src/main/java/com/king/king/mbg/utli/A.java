package com.king.king.mbg.utli;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
public class A {

    private String name;
    private String code;
    private String id;
    private List<C> listc;

}
