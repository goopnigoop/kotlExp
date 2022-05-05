package com.goopniggop.live.tasks.medelement;

import java.util.Collection;

public interface Searchable<E extends Collection, T> {
    T findIn(E data);
}
