package stargame.gb.ru.screen;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

import stargame.gb.ru.base.BaseScreen;

public class GameScreen extends BaseScreen {

    private Texture userPlane;
    private Vector2 userPlanePos, endPos, move;
    private int sizePlane, curSpeed;

    @Override
    public void show() {
        userPlane = new Texture("user_plane.png");
        backgroundImgFileName = "background_img.jpg";
        userPlanePos = new Vector2();
        endPos = new Vector2();
        move = new Vector2();
        curSpeed = 5;
        sizePlane = 200;
        super.show();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(1, 0, 0, 1);
        batch.begin();
        batch.draw(backgroundImg, 0, 0, width, height);
        batch.draw(userPlane, userPlanePos.x,userPlanePos.y, sizePlane,sizePlane);
        batch.end();

        if (!endPos.epsilonEquals(userPlanePos, sizePlane * 0.05f)){
            userPlanePos.add(move);
        }
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        int size = sizePlane / 2;

        endPos.set(screenX - size, (height - screenY) - size);

        move.set(endPos.cpy().sub(userPlanePos));
        move.scl(curSpeed / move.len());

        return super.touchDown(screenX, screenY, pointer, button);
    }
}
