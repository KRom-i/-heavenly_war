package stargame.gb.ru.screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import stargame.gb.ru.base.BaseScreen;
import stargame.gb.ru.sprite.Background;
import stargame.gb.ru.utils.CloudMove;
import stargame.gb.ru.utils.Clouds;
import stargame.gb.ru.sprite.UserPlane;


public class GameScreen extends BaseScreen {

    private Texture textureBackground;
    private Background background;
    private TextureAtlas atlas;
    private UserPlane userPlane;
    private Clouds cloudsBottom, cloudsTopSmall, cloudsTopBig;
    private CloudMove cloudMove;

    @Override
    public void show() {
        super.show();
        textureBackground = new Texture("textures/background_game.jpg");
        atlas = new TextureAtlas("textures/mainAtlas.pack");
        background = new Background(textureBackground);

        cloudsBottom = new Clouds(atlas, 20, 5f, -0.1f);
        cloudsTopBig  = new Clouds(atlas, 5, 1.5f, -0.2f);
        cloudsTopSmall = new Clouds(atlas, 5, 1.5f, -0.3f);
        cloudMove = new CloudMove(cloudsBottom, cloudsTopSmall, cloudsTopBig);

        userPlane = new UserPlane(atlas, cloudMove);

        addSprites(background);
        addSprites(cloudsBottom.getSprites());
        addSprites(userPlane);
        addSprites(cloudsTopBig.getSprites());
        addSprites(cloudsTopSmall.getSprites());
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
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        userPlane.touchUp(touch, pointer, button);
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        userPlane.keyDown(keycode);
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        userPlane.keyUp(keycode);
        return false;
    }
}
