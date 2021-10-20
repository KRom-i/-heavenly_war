package stargame.gb.ru.screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import stargame.gb.ru.base.BaseScreen;
import stargame.gb.ru.math.Rect;
import stargame.gb.ru.sprite.Background;
import stargame.gb.ru.sprite.Logo;

public class MenuScreen extends BaseScreen {

    private Texture textureBackground;
    private Texture textureLogo;

    private Background background;
    private Logo logo;

    @Override
    public void show() {
        super.show();
        textureBackground = new Texture("textures/background_menu.jpg");
        textureLogo = new Texture("textures/logo.png");
        background = new Background(textureBackground);
        logo = new Logo(textureLogo);
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        logo.resize(worldBounds);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(1, 0, 0, 1);
        batch.begin();
        background.draw(batch);
        logo.draw(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        super.dispose();
        textureBackground.dispose();
        textureLogo.dispose();
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        logo.touchDown(touch, pointer, button);
        return super.touchDown(touch, pointer, button);
    }
}
