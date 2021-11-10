package stargame.gb.ru.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import stargame.gb.ru.base.BaseButton;
import stargame.gb.ru.math.Rect;
import stargame.gb.ru.screen.GameScreen;

public class NewGameButton extends BaseButton {

    private static final float HEIGHT = 0.03f;
    private final GameScreen gameScreen;

    public NewGameButton(GameScreen gameScreen, TextureAtlas atlas) {
        super(atlas.findRegion("button_new_game"));
        setBottom(-0.3f);
        this.gameScreen = gameScreen;
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(HEIGHT);
    }

    @Override
    public void action() {
        gameScreen.newGame();
    }


}
