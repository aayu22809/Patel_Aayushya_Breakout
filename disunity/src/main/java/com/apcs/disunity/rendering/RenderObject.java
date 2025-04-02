package com.apcs.disunity.rendering;

import com.apcs.disunity.math.Vector2;

public final class RenderObject {
    public final String name;
    public final Vector2 offset;
    public final Vector2 scale;

    public RenderObject(String name, Vector2 offset, Vector2 scale) {
        this.name = name;
        this.offset = offset;
        this.scale = scale;
    }
}