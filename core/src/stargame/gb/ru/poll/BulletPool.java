package stargame.gb.ru.poll;


import stargame.gb.ru.base.SpritesPool;
import stargame.gb.ru.screen.Bullet;

public class BulletPool extends SpritesPool<Bullet> {
    @Override
    protected Bullet newObject() {
        return new Bullet();
    }
}
