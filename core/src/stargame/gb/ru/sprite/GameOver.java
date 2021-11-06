package stargame.gb.ru.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import stargame.gb.ru.base.Sprite;
import stargame.gb.ru.math.Rect;

public class GameOver extends Sprite {

    private static final float HEIGHT = 0.05f;

    public GameOver(TextureAtlas atlas) {
        super(atlas.findRegion("message_game_over"));
    }

    public void resize(Rect worldBounds) {
        setHeightProportion(HEIGHT);
    }

}
