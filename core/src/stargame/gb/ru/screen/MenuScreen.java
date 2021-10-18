package stargame.gb.ru.screen;

import com.badlogic.gdx.utils.ScreenUtils;

import stargame.gb.ru.base.BaseScreen;

public class MenuScreen extends BaseScreen {

    @Override
    public void show() {
        backgroundImgFileName = "background_img.jpg";
        super.show();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(1, 0, 0, 1);
        batch.begin();
        batch.draw(backgroundImg, 0, 0, width, height);
        batch.end();
    }
}
