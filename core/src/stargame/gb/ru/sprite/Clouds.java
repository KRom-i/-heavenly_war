package stargame.gb.ru.sprite;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import stargame.gb.ru.math.Rect;

public class Clouds {

    private Cloud[] clouds;
    private static final int COULD_COUNT = 50;

    public Clouds(TextureAtlas atlas) {
        this.clouds = new Cloud[COULD_COUNT];
        for (int i = 0; i < clouds.length; i++) {
            clouds[i] = new Cloud(atlas);
        }
    }

    public Cloud[] getArray() {
        return clouds;
    }

    public void resize(Rect worldBounds) {
        for (Cloud cloud: clouds
             ) {
            cloud.resize(worldBounds);
        }
    }

    public void update(float delta) {
        for (Cloud cloud: clouds
        ) {
            cloud.update(delta);
        }
    }

    public void draw(SpriteBatch batch) {
        for (Cloud cloud: clouds
        ) {
            cloud.draw(batch);
        }
    }


}
