package stargame.gb.ru.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import stargame.gb.ru.base.Sprite;
import stargame.gb.ru.math.Rect;

public class Logo extends Sprite {

    private final static float proportion = 0.3f;
    private final static float speed = 0.01f;
    private Vector2 touch;
    private Vector2 v;

    public Logo(Texture texture) {
        super(new TextureRegion(texture));
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(proportion);
        touch = new Vector2();
        v = new Vector2();
    }

    @Override
    public void draw(SpriteBatch batch) {
        if (pos.dst(touch) <= v.len()){
            pos.set(touch);
        } else {
            pos.add(v);
        }
        super.draw(batch);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        this.touch = touch;
        v.set(touch.cpy().sub(pos)).setLength(speed);
        return super.touchDown(touch, pointer, button);
    }

    @Override
    public String toString() {
        return String.format(
                "LOGO: pos.X=%s pos.Y=%s proportion=%s",
                pos.x, pos.y, proportion);
    }
}
