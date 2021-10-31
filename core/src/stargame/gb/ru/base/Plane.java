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
        if (getTop() > worldBounds.getTop()){
            pos.mulAdd(new Vector2(0, -0.4f), delta);
        } else {
            pos.mulAdd(v, delta);
            shotControl++;
            if (shotControl >= (60 / shotsSecond)){
                shoot();
                shotControl = 0;
            };
        }
        bulletPos.set(pos);
    }

    protected void shoot() {
        Bullet bullet = bulletPool.obtain();
        bullet.set(this, bulletRegion, bulletPos, bulletV, worldBounds, bulletHeight, damage);
    }
}
