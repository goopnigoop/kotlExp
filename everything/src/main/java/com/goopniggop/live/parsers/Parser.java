package com.goopniggop.live.parsers;

public interface Parser<T> {
    T parse(String input);
}
