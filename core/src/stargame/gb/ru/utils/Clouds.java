package stargame.gb.ru.utils;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import stargame.gb.ru.sprite.Cloud;

public class Clouds {

    private Cloud[] clouds;

    public Clouds(TextureAtlas atlas, int counter, float size, float minVectorX) {
        this.clouds = new Cloud[counter];
        for (int i = 0; i < clouds.length; i++) {
            clouds[i] = new Cloud(atlas, size, minVectorX);
        }
    }

    public Cloud[] getSprites() {
        return clouds;
    }

}
