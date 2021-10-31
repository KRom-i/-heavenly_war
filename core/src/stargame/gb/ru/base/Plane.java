package stargame.gb.ru.base;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import stargame.gb.ru.math.Rect;
import stargame.gb.ru.poll.BulletPool;
import stargame.gb.ru.sprite.Bullet;

public class Plane extends Sprite{

    protected BulletPool bulletPool;
    protected TextureRegion bulletRegion;
    protected Vector2 bulletV;
    protected Vector2 bulletPos;
    protected float bulletHeight;
    protected int damage;
    protected int hp;
    protected Rect worldBounds;
    protected Vector2 v;
    protected Vector2 v0;
    protected Sound bulletSound;
    protected float shotsSecond;
    protected float shotControl;

    public Plane() {
    }

    public Plane(TextureRegion region) {
        super(region);
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(v, delta);
        bulletPos.set(pos);
    }

    protected void shoot() {
        Bullet bullet = bulletPool.obtain();
        bullet.set(this, bulletRegion, bulletPos, bulletV, worldBounds, bulletHeight, damage);
    }
}
