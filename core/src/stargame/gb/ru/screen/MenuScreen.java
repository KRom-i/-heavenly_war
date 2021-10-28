package stargame.gb.ru.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.List;

import stargame.gb.ru.base.BaseScreen;
import stargame.gb.ru.base.Sprite;
import stargame.gb.ru.math.Rect;
import stargame.gb.ru.sprite.Background;
import stargame.gb.ru.sprite.ExitButton;
import stargame.gb.ru.sprite.Logo;
import stargame.gb.ru.sprite.PlayButton;

public class MenuScreen extends BaseScreen {

    private Texture textureBackground;
    private Background background;

    private TextureAtlas atlas;
    private ExitButton exit;
    private PlayButton play;

    private final Game game;

    public MenuScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        super.show();
        textureBackground = new Texture("textures/background_menu.jpg");
        atlas = new TextureAtlas("textures/menuAtlas.pack");
        background = new Background(textureBackground);
        exit = new ExitButton(atlas);
        play = new PlayButton(atlas, game);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        draw();
    }

    private void update(float delta) {
        background.update(delta);
        exit.update(delta);
        play.update(delta);
    }

    private void draw() {
        batch.begin();
        background.draw(batch);
        exit.draw(batch);
        play.draw(batch);
        batch.end();
    }

    @Override
    public void resize(Rect worldBounds) {
        background.resize(worldBounds);
        exit.resize(worldBounds);
        play.resize(worldBounds);
    }

    @Override
    public void dispose() {
        super.dispose();
        textureBackground.dispose();
        atlas.dispose();
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        exit.touchDown(touch, pointer, button);
        play.touchDown(touch, pointer, button);
        return super.touchDown(touch, pointer, button);
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        exit.touchUp(touch, pointer, button);
        play.touchUp(touch, pointer, button);
        return super.touchUp(touch, pointer, button);
    }

}
