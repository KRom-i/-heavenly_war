package stargame.gb.ru.poll;


import com.badlogic.gdx.audio.Sound;

import stargame.gb.ru.base.SpritesPool;
import stargame.gb.ru.sprite.Bullet;

public class BulletPool extends SpritesPool<Bullet> {

    private Sound bulletSound;

    public BulletPool(Sound bulletSound) {
        this.bulletSound = bulletSound;
    }

    @Override
    protected Bullet newObject() {
        return new Bullet(bulletSound);
    }
}
