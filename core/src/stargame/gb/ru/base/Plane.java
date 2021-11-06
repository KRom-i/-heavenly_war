package stargame.gb.ru.base;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import stargame.gb.ru.math.Rect;
import stargame.gb.ru.poll.BulletPool;
import stargame.gb.ru.poll.ExplosionPool;
import stargame.gb.ru.sprite.Bullet;
import stargame.gb.ru.sprite.Explosion;

public class Plane extends Sprite{

    private static final float DAMAGE_ANIMATE_INTERVAL = 0.1f;

    protected ExplosionPool explosionPool;
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

    private float damageAnimateTimer = DAMAGE_ANIMATE_INTERVAL;

    public Plane() {
    }

    public Plane(TextureRegion region) {
        super(region);
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(v, delta);

        shotControl++;

        if (shotControl >= (60 / shotsSecond)){
            shoot(90);
            shotControl = 0;
        };

        damageAnimateTimer += delta;
        if (damageAnimateTimer >= DAMAGE_ANIMATE_INTERVAL) {
            frame = 0;
        }
    }

    public void damage(int hp) {
        this.hp -= hp;
        if (this.hp <= 0) {
            this.hp = 0;
            destroy();
        }
        damageAnimateTimer = 0f;
        frame = 1;
    }

    public int getDamage() {
        return damage;
    }

    @Override
    public void destroy() {
        super.destroy();
        boom();
    }

    protected void shoot(int angle) {
        Bullet bullet = bulletPool.obtain();
        bullet.angle = angle;
        bullet.set(this, bulletRegion, bulletPos, bulletV, worldBounds, bulletHeight, damage);
    }

    private void boom() {
        Explosion explosion = explosionPool.obtain();
        explosion.set(this.pos, getHeight());
    }
}
