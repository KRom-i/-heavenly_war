package stargame.gb.ru.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import stargame.gb.ru.base.Sprite;
import stargame.gb.ru.math.Rect;
import stargame.gb.ru.math.Rnd;

public class Cloud extends Sprite {

    private float size;
    private Rect worldBounds;
    private Vector2 v;

    public Cloud(TextureAtlas atlas, float size, float minVectorX) {
        super(atlas.findRegion("cloud"));
        v = new Vector2(Rnd.nextFloat(-0.005f, 0.005f), Rnd.nextFloat(-0.3f, minVectorX));
        this.size = size;
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        float height = - (v.y * size);
        setHeightProportion(height);
        float x = Rnd.nextFloat(worldBounds.getLeft(), worldBounds.getRight());
        float y = Rnd.nextFloat(worldBounds.getBottom(), worldBounds.getTop());
        pos.set(x, y);
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(v, delta);
        checkBounds();
    }

    private void checkBounds() {
        if (getRight() < worldBounds.getLeft()) {
            setLeft(worldBounds.getRight());
        }
        if (getLeft() > worldBounds.getRight()) {
            setRight(worldBounds.getLeft());
        }
        if (getTop() < worldBounds.getBottom()) {
            setBottom(worldBounds.getTop());
        }
    }

}
