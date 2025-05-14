package com.apcs.ljaag.nodes.indexed;

import com.apcs.disunity.math.Vector2;
import com.apcs.disunity.game.selector.Indexed;

import java.util.function.Supplier;

/// a selector entree that generates a velocity
public interface VectorSupplier extends Supplier<Vector2>, Indexed<String> {}
