package com.apcs.ljaag.nodes.body;

import com.apcs.disunity.game.nodes.FieldChild;
import com.apcs.disunity.game.nodes.sprite.AnimationSprite;
import com.apcs.disunity.math.Vector2;
import com.apcs.disunity.game.nodes.twodim.Body;
import com.apcs.disunity.game.nodes.selector.SelectorNode;
import com.apcs.disunity.game.nodes.twodim.Collider;
import com.apcs.disunity.game.physics.CollisionInfo;
import com.apcs.disunity.app.network.packet.SyncHandler;
import com.apcs.ljaag.LJAAG;
import com.apcs.ljaag.nodes.indexed.InputVector;
import com.apcs.ljaag.nodes.indexed.VectorSupplier;

/// base character of LJAAG. can be controlled by player.
/// demo of disunity
public class LJCharacter extends Body {
    @FieldChild
    private final SelectorNode<String, AnimationSprite> spriteSelector = new SelectorNode<>(
        new AnimationSprite("stand", "player/player.png", Double.MAX_VALUE),
        new AnimationSprite("run", "player/run.png", 0.15, 0.15, 0.15, 0.15, 0.15, 0.15)
    );
    @FieldChild
    private final SelectorNode<String, VectorSupplier> vectorSelector = new SelectorNode<>(
        VectorSupplier.ZERO,
        new InputVector("input")
    );

    public LJCharacter(int x, int y, int owner) { this(Vector2.of(x,y), owner); }
    public LJCharacter(Vector2 pos, int owner) {
        super(new Collider(8,20));
        LJAAG.own(this, owner);
        setPos(pos);
    }

    @Override
    public void update(double delta) {
        // movement
        if(isPlayer()) {
            setVel(vectorSelector.select("input").get());
        }

        // sprite
        if(Math.abs(getVel().x) + Math.abs(getVel().y) < 0.1 ) {
            spriteSelector.select("stand");
        } else {
            spriteSelector.select("run");
        }

        if(Math.abs(getVel().x) >= 0.1 ){
            spriteSelector.getSelected().setScale(
                Vector2.of(getVel().x > 0 ? 1 : -1, 1)
            );
        }

        super.update(delta);
    }

    @Override
    public void onCollision(CollisionInfo info) {
        Vector2 vel = getVel().mul(info.delta);
        addPos(vel.mul(-1));

        while (vel.length() > 0) {
            vel = vel.mul(0.5);
            addPos(vel);
            if(info.you.isColliding(info.me.setPos(getPos()))) {
                addPos(vel.mul(-1));
            }
        }
        spriteSelector.select("stand");
    }

    private boolean isPlayer() {
        return SyncHandler.getInstance().getEndpointId() == owner;
    }
}