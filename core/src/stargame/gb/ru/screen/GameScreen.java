package stargame.gb.ru.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import java.util.List;

import stargame.gb.ru.base.BaseScreen;
import stargame.gb.ru.math.Rect;
import stargame.gb.ru.poll.BulletPool;
import stargame.gb.ru.poll.CloudPoll;
import stargame.gb.ru.poll.EnemyPool;
import stargame.gb.ru.poll.ExplosionPool;
import stargame.gb.ru.sprite.Background;
import stargame.gb.ru.sprite.Bullet;
import stargame.gb.ru.sprite.EnemyPlane;
import stargame.gb.ru.sprite.GameOver;
import stargame.gb.ru.sprite.NewGameButton;
import stargame.gb.ru.sprite.UserPlane;
import stargame.gb.ru.util.EnemyEmitter;

public class GameScreen extends BaseScreen {

    private Texture textureBackground;
    private Background background;
    private TextureAtlas atlas;
    private TextureAtlas atlas2;

    private UserPlane userPlane;

    private CloudPoll cloudsBottom, cloudsTopSmall, cloudsTopBig;

    private BulletPool bulletPool;
    private Sound bulletSound;
    private Music music;

    private EnemyPool enemyPool;
    private EnemyEmitter enemyEmitter;

    private ExplosionPool explosionPool;
    private Sound explosionSound;

    private GameOver gameOver;
    private NewGameButton newGameButton;

    @Override
    public void show() {
        super.show();
        bulletSound = Gdx.audio.newSound(Gdx.files.internal("sounds/bullet_2.wav"));
        music = Gdx.audio.newMusic(Gdx.files.internal("sounds/plane.mp3"));
        explosionSound = Gdx.audio.newSound(Gdx.files.internal("sounds/explosion.wav"));

        music.setVolume(0.5f);
        music.setLooping(true);
        music.play();

        textureBackground = new Texture("textures/background_game.jpg");
        atlas = new TextureAtlas("textures/mainAtlas.pack");
        atlas2 = new TextureAtlas("textures/Atlas.tpack");

        background = new Background(textureBackground);

        cloudsBottom = new CloudPoll(atlas, 20, 5f, -0.1f);
        cloudsTopBig  = new CloudPoll(atlas, 5, 1.5f, -0.2f);
        cloudsTopSmall = new CloudPoll(atlas, 5, 1.5f, -0.3f);

        bulletPool = new BulletPool(bulletSound);

        explosionPool = new ExplosionPool(atlas2, explosionSound);

        userPlane = new UserPlane(atlas, explosionPool, bulletPool);

        enemyPool = new EnemyPool(bulletPool, explosionPool, worldBounds, bulletSound, userPlane);

        enemyEmitter = new EnemyEmitter(enemyPool, worldBounds, atlas);

        newGameButton = new NewGameButton(this, atlas2);
        gameOver = new GameOver(atlas2);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        checkCollisions();
        freeAllDestroyed();
        draw();
    }

    private void update(float delta) {
        background.update(delta);
        cloudsBottom.update(delta);
        cloudsTopBig.update(delta);
        cloudsTopSmall.update(delta);
        explosionPool.updateActiveObjects(delta);

        if (!userPlane.isDestroyed()){
            userPlane.update(delta);
            bulletPool.updateActiveObjects(delta);
            enemyPool.updateActiveObjects(delta);
            enemyEmitter.generate(delta);
        } else {
            gameOver.update(delta);
            newGameButton.update(delta);
        }

    }

    private void freeAllDestroyed() {
        bulletPool.freeAllDestroyed();
        explosionPool.freeAllDestroyed();
        enemyPool.freeAllDestroyed();
    }

    private void draw() {
        batch.begin();
        background.draw(batch);
        cloudsBottom.draw(batch);

        if (!userPlane.isDestroyed()){
            bulletPool.drawActiveObjects(batch);
            enemyPool.drawActiveObjects(batch);
            userPlane.draw(batch);
        } else {
            gameOver.draw(batch);
            newGameButton.draw(batch);
        }
//        cloudsTopBig.draw(batch);
//        cloudsTopSmall.draw(batch);
        explosionPool.drawActiveObjects(batch);
        batch.end();
    }

    private void checkCollisions() {
        if (userPlane.isDestroyed()) {
            return;
        }
        List<EnemyPlane> enemyPlaneList = enemyPool.getActiveObjects();
        for (EnemyPlane enemyPlane : enemyPlaneList) {
            float minDist = userPlane.getWidth();
            if (!enemyPlane.isDestroyed()
                    && userPlane.pos.dst(enemyPlane.pos) < minDist) {
                enemyPlane.destroy();
                userPlane.damage(enemyPlane.getDamage() * 2);
            }
        }
        List<Bullet> bulletList = bulletPool.getActiveObjects();
        for (Bullet bullet : bulletList) {
            if (bullet.isDestroyed()) {
                continue;
            }
            if (bullet.getOwner() != userPlane) {
                if (userPlane.isBulletCollision(bullet)) {
                    userPlane.damage(bullet.getDamage());
                    bullet.destroy();
                }
                continue;
            }
            for (EnemyPlane enemyPlane : enemyPlaneList) {
                if (enemyPlane.isBulletCollision(bullet)) {
                    enemyPlane.damage(bullet.getDamage());
                    bullet.destroy();
                }
            }
        }
    }

    @Override
    public void resize(Rect worldBounds) {
        background.resize(worldBounds);
        userPlane.resize(worldBounds);
        cloudsBottom.resize(worldBounds);
        cloudsTopBig.resize(worldBounds);
        cloudsTopSmall.resize(worldBounds);
        gameOver.resize(worldBounds);
        newGameButton.resize(worldBounds);
    }

    @Override
    public void dispose() {
        super.dispose();
        textureBackground.dispose();
        atlas.dispose();
        enemyPool.dispose();
        bulletPool.dispose();
        bulletSound.dispose();
        music.dispose();
        atlas2.dispose();
        explosionSound.dispose();
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        newGameButton.touchDown(touch, pointer, button);
        userPlane.touchDown(touch, pointer, button);
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        newGameButton.touchUp(touch, pointer, button);
        userPlane.touchUp(touch, pointer, button);
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        userPlane.keyDown(keycode);
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        userPlane.keyUp(keycode);
        return false;
    }

    public void newGame() {
        bulletPool.destroy();
        explosionPool.destroy();
        enemyPool.destroy();
        userPlane.restore();
    }
}
