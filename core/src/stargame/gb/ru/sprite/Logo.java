package stargame.gb.ru.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import stargame.gb.ru.base.Sprite;
import stargame.gb.ru.math.Rect;

public class Logo extends Sprite {

    private static final float HEIGHT = 0.3f;

    public Logo(TextureAtlas atlas) {
        super(atlas.findRegion("logo"));
        pos.set(0, 0.3f);
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(HEIGHT);
    }
}
