package stargame.gb.ru.screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import stargame.gb.ru.base.BaseScreen;
import stargame.gb.ru.sprite.Background;
import stargame.gb.ru.sprite.Clouds;
import stargame.gb.ru.sprite.UserPlane;


public class GameScreen extends BaseScreen {

    private Texture textureBackground;
    private Background background;
    private TextureAtlas atlas;
    private Clouds clouds;
    private UserPlane userPlane;

    @Override
    public void show() {
        super.show();
        textureBackground = new Texture("textures/background_game.jpg");
        atlas = new TextureAtlas("textures/mainAtlas.pack");
        background = new Background(textureBackground);
        clouds = new Clouds(atlas);
        userPlane = new UserPlane(atlas);
        addSprites(background);
        addSprites(clouds.getArray());
        addSprites(userPlane);
    }


    @Override
    public void dispose() {
        super.dispose();
        textureBackground.dispose();
        atlas.dispose();
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        userPlane.touchDown(touch, pointer, button);
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        userPlane.keyDown(keycode);
        return false;
    }
}
