package com.apcs.disunity.server;

import java.util.LinkedList;
import java.util.List;

public final class Sync {

    private static final List<Sync> syncs = new LinkedList<>();
    
    { syncs.add(this); }
    final byte idPrefix;

    public Sync() {
        idPrefix = 0;
    }

    public Sync(int idPrefix) {
        this.idPrefix = (byte) idPrefix;
    }
    
}
