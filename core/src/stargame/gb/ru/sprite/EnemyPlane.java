package stargame.gb.ru.sprite;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import stargame.gb.ru.base.Plane;
import stargame.gb.ru.math.Rect;
import stargame.gb.ru.poll.BulletPool;
import stargame.gb.ru.poll.ExplosionPool;

public class EnemyPlane extends Plane {

    public EnemyPlane(BulletPool bulletPool, Rect worldBounds, ExplosionPool explosionPool, Sound bulletSound, UserPlane userPlane) {
        this.bulletPool = bulletPool;
        this.explosionPool = explosionPool;
        this.worldBounds = worldBounds;
        this.bulletSound = bulletSound;
        this.bulletV = new Vector2();
        this.bulletPos = new Vector2();
        this.v = new Vector2();
        this.v0 = new Vector2();
        this.angle = 180;
    }

    @Override
    public void update(float delta) {
        bulletPos.set(pos);

        super.update(delta);

        if (getTop() < worldBounds.getTop()) {
            v.set(v0);
        }

        if (getBottom() < worldBounds.getBottom()) {
            destroy();
        }

    }

    public void set(
            TextureRegion[] regions,
            Vector2 v,
            TextureRegion bulletRegion,
            float bulletHeight,
            Vector2 bulletV,
            int damage,
            int hp,
            float shotsSecond,
            float height
    ) {
        this.regions = regions;
        this.v0.set(v);
        this.bulletRegion = bulletRegion;
        this.bulletHeight = bulletHeight;
        this.bulletV.set(bulletV);
        this.damage = damage;
        this.hp = hp;
        this.shotsSecond = shotsSecond;
        setHeightProportion(height);
        this.v.set(0, -0.5f);
    }

    public boolean isBulletCollision(Bullet bullet) {
        return !(bullet.getRight() < getLeft()
                || bullet.getLeft() > getRight()
                || bullet.getBottom() > getTop()
                || bullet.getTop() < pos.y);
    }
}
