package com.example.something;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;

import static com.example.something._10_higher_orderKt.repeat;

public class _10_higher_order {

    public static void main(String[] args) {
        repeat(3, new Function0<Unit>() {
            public Unit invoke() {
                System.out.println("Hello world");
                return Unit.INSTANCE;
            }
        });
    }

}
