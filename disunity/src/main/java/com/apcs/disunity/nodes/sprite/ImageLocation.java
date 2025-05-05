package com.apcs.disunity.nodes.sprite;

import com.apcs.disunity.math.Vector2;

public class ImageLocation {
    public final String PATH;
    public final Vector2 POS;
    public final Vector2 SIZE;

    public ImageLocation(String path, int x, int y, int w, int h) {
        PATH = path;
        POS = new Vector2(x,y);
        SIZE = new Vector2(w,h);
    }

    public ImageLocation(String path) {
        PATH = path;
        POS = SIZE = null;
    }
}
