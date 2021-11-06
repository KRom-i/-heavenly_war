package stargame.gb.ru.sprite;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

import stargame.gb.ru.base.Plane;
import stargame.gb.ru.math.Rect;
import stargame.gb.ru.poll.BulletPool;
import stargame.gb.ru.poll.ExplosionPool;
import stargame.gb.ru.util.Regions;

public class UserPlane extends Plane {

    public static final float BULLET_HEIGHT = 0.1f;
    public static float HEIGHT = 0.1f;

    private static final float PADDING = 0.005f;
    private static final float PADDING_BOTTOM = 0.005f;
    private static final float SPEED = 0.15f;

    private boolean pressedLeft;
    private boolean pressedRight;

    private static final int  INVALID_POINTER = -1;
    private int leftPointer;
    private int rightPointer;

    public UserPlane(TextureAtlas atlas, ExplosionPool explosionPool, BulletPool bulletPool) {
        this.regions = Regions.get(atlas, "userPlane");
        setHeightProportion(HEIGHT);
        v = new Vector2(0, 0);
        this.bulletPool = bulletPool;
        this.bulletRegion = atlas.findRegion("bullet");
        this.bulletV = new Vector2(0, 0.7f);
        this.bulletHeight = HEIGHT * BULLET_HEIGHT;
        this.damage = 1;
        this.hp = 1;
        this.shotsSecond = 5;
        this.bulletPos = new Vector2();
        this.explosionPool = explosionPool;
        start();
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        setBottom(worldBounds.getBottom() + PADDING_BOTTOM);
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        bulletPos.set(this.pos.x, getTop());

        if (getLeft() < (worldBounds.getLeft() + PADDING)){
            if (!pressedLeft) {
                moveRight();
            } else {
                stop();
            }
        } else if (getRight() > (worldBounds.getRight() - PADDING)){
            if (!pressedRight) {
                moveLeft();
            } else {
                stop();
            }
        }
        pos.mulAdd(v, delta);

    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        if (touch.x < worldBounds.pos.x){
            moveLeft();
            pressedLeft = true;
            leftPointer = pointer;
        } else {
            moveRight();
            pressedRight = true;
            rightPointer = pointer;
        }
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        if (pointer == leftPointer){
            leftPointer = INVALID_POINTER;
            pressedLeft = false;
        } else if (pointer == rightPointer){
            rightPointer = INVALID_POINTER;
            pressedRight = false;
        }
        return false;
    }

    public boolean keyDown(int keycode) {
        switch (keycode){
            case Input.Keys.LEFT:
            case Input.Keys.A:
                moveLeft();
                pressedLeft = true;
                break;
            case Input.Keys.RIGHT:
            case Input.Keys.D:
                moveRight();
                pressedRight = true;
                break;

        }
        return false;
    }

    public boolean keyUp(int keycode) {
        switch (keycode){
            case Input.Keys.LEFT:
            case Input.Keys.A:
                pressedLeft = false;
                if (pressedRight){
                    moveRight();
                }
                break;
            case Input.Keys.RIGHT:
            case Input.Keys.D:
                pressedRight = false;
                if (pressedLeft){
                    moveLeft();
                }
                break;
        }
        return false;
    }

    private void start(){
        if (new Random().nextInt() > 0){
            moveRight();
        } else {
            moveLeft();
        }
    }

    private void moveLeft(){
        v.set(-SPEED, 0);
    }

    private void moveRight(){
        v.set(SPEED, 0);
    }

    private void stop(){
        v.setZero();
    }

    public boolean isBulletCollision(Bullet bullet) {
        return !(bullet.getRight() < getLeft()
                || bullet.getLeft() > getRight()
                || bullet.getBottom() > pos.y
                || bullet.getTop() < getBottom());
    }

    @Override
    public void destroy() {
        super.destroy();

        System.out.println("GAME OVER");

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("NEW GAME");
                destroyed = false;
                hp = 1;
                start();
            }
        }).start();

    }
}
