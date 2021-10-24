package stargame.gb.ru.screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import stargame.gb.ru.base.BaseScreen;
import stargame.gb.ru.sprite.Background;
import stargame.gb.ru.sprite.Clouds;


public class GameScreen extends BaseScreen {

    private Texture textureBackground;
    private Background background;
    private TextureAtlas atlas;
    private Clouds clouds;

    @Override
    public void show() {
        super.show();
        textureBackground = new Texture("textures/background_game.jpg");
        atlas = new TextureAtlas("textures/mainAtlas.pack");
        background = new Background(textureBackground);
        clouds = new Clouds(atlas);
        addSprites(background);
        addSprites(clouds.getArray());
    }


    @Override
    public void dispose() {
        super.dispose();
        textureBackground.dispose();
        atlas.dispose();
    }


}
