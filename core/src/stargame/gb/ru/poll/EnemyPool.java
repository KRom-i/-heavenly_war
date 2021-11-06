package stargame.gb.ru.poll;

import com.badlogic.gdx.audio.Sound;

import stargame.gb.ru.base.SpritesPool;
import stargame.gb.ru.math.Rect;
import stargame.gb.ru.sprite.EnemyPlane;
import stargame.gb.ru.sprite.UserPlane;

public class EnemyPool extends SpritesPool<EnemyPlane> {

    private final BulletPool bulletPool;
    private final Rect worldBounds;
    private final Sound bulletSound;
    private final UserPlane userPlane;
    private final ExplosionPool explosionPool;

    public EnemyPool(BulletPool bulletPool, ExplosionPool explosionPool, Rect worldBounds, Sound bulletSound, UserPlane userPlane) {
        this.bulletPool = bulletPool;
        this.worldBounds = worldBounds;
        this.bulletSound = bulletSound;
        this.userPlane = userPlane;
        this.explosionPool = explosionPool;
    }

    @Override
    protected EnemyPlane newObject() {
        return new EnemyPlane(bulletPool, worldBounds, explosionPool, bulletSound, userPlane);
    }
}
