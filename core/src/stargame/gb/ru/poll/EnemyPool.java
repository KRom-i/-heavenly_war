package stargame.gb.ru.poll;

import com.badlogic.gdx.audio.Sound;

import stargame.gb.ru.base.SpritesPool;
import stargame.gb.ru.math.Rect;
import stargame.gb.ru.sprite.EnemyPlane;

public class EnemyPool extends SpritesPool<EnemyPlane> {

    private final BulletPool bulletPool;
    private final Rect worldBounds;
    private final Sound bulletSound;

    public EnemyPool(BulletPool bulletPool, Rect worldBounds, Sound bulletSound) {
        this.bulletPool = bulletPool;
        this.worldBounds = worldBounds;
        this.bulletSound = bulletSound;
    }

    @Override
    protected EnemyPlane newObject() {
        return new EnemyPlane(bulletPool, worldBounds, bulletSound);
    }
}
