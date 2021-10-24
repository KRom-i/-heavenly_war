package stargame.gb.ru.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

import stargame.gb.ru.base.Sprite;
import stargame.gb.ru.math.Rect;

public class UserPlane extends Sprite{

    private static final float HEIGHT = 0.15f;
    private static final float PADDING = 0.01f;
    private static final float PADDING_BOTTOM = 0.03f;
    private static final float SPEED = 0.003f;

    private static final int LEFT = 29;
    private static final int RIGHT = 32;
    private int move;

    private final Vector2 v;
    private Rect woldBounds;


    public UserPlane(TextureAtlas atlas) {
        super(atlas.findRegion("userPlane"));
        setHeightProportion(HEIGHT);
        v = new Vector2();
        start();
    }

    private void start(){
        if (new Random().nextInt() > 0){
            moveRight();
        } else {
            moveLeft();
        }
    }

    @Override
    public void resize(Rect worldBounds) {
        this.woldBounds = worldBounds;
        setBottom(worldBounds.getBottom() + PADDING_BOTTOM);
    }

    @Override
    public void update(float delta) {
        if (move == LEFT){
            if (getLeft() > woldBounds.getLeft() + PADDING){
                pos.add(v);
            } else {
                moveRight();
            }

        } else if (move == RIGHT){
            if (getRight() < woldBounds.getRight() - PADDING){
                pos.add(v);
            } else {
                moveLeft();
            }

        }
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        if (touch.x < (getRight() - getHalfWidth())){
            moveLeft();
        } else if (touch.x > (getLeft() + getHalfWidth())) {
            moveRight();
        }
        return false;
    }

    public boolean keyDown(int keycode) {
        if (keycode == LEFT){
            moveLeft();
        } else if (keycode == RIGHT) {
            moveRight();
        }
        return false;
    }

    private void moveLeft(){
        v.set(- SPEED, 0);
        move = LEFT;
    }

    private void moveRight(){
        move = RIGHT;
        v.set(SPEED, 0);
    }
}
