package stargame.gb.ru.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import stargame.gb.ru.base.BaseScreen;
import stargame.gb.ru.math.Rect;
import stargame.gb.ru.poll.BulletPool;
import stargame.gb.ru.poll.CloudPoll;
import stargame.gb.ru.poll.EnemyPool;
import stargame.gb.ru.sprite.Background;
import stargame.gb.ru.sprite.UserPlane;
import stargame.gb.ru.util.EnemyEmitter;

public class GameScreen extends BaseScreen {

    private Texture textureBackground;
    private Background background;
    private TextureAtlas atlas;
    private UserPlane userPlane;
    private CloudPoll cloudsBottom, cloudsTopSmall, cloudsTopBig;
    private BulletPool bulletPool;
    private Sound bulletSound;
    private Music music;
    private EnemyPool enemyPool;
    private EnemyEmitter enemyEmitter;

    @Override
    public void show() {
        super.show();
        bulletSound = Gdx.audio.newSound(Gdx.files.internal("sounds/bullet_2.wav"));
        music = Gdx.audio.newMusic(Gdx.files.internal("sounds/plane.mp3"));
        music.setVolume(0.5f);
        music.setLooping(true);
        music.play();
        textureBackground = new Texture("textures/background_game.jpg");
        atlas = new TextureAtlas("textures/mainAtlas.pack");
        background = new Background(textureBackground);
        cloudsBottom = new CloudPoll(atlas, 20, 5f, -0.1f);
        cloudsTopBig  = new CloudPoll(atlas, 5, 1.5f, -0.2f);
        cloudsTopSmall = new CloudPoll(atlas, 5, 1.5f, -0.3f);
        bulletPool = new BulletPool(bulletSound);
        userPlane = new UserPlane(atlas, bulletPool);
        enemyPool = new EnemyPool(bulletPool, worldBounds, bulletSound);
        enemyEmitter = new EnemyEmitter(enemyPool, worldBounds, atlas);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        freeAllDestroyed();
        draw();
    }

    private void update(float delta) {
        background.update(delta);
        cloudsBottom.update(delta);
        userPlane.update(delta);
        bulletPool.updateActiveObjects(delta);
        cloudsBottom.update(delta);
        cloudsTopBig.update(delta);
        cloudsTopSmall.update(delta);
        enemyPool.updateActiveObjects(delta);
        enemyEmitter.generate(delta);
    }

    private void freeAllDestroyed() {
        bulletPool.freeAllDestroyed();
        enemyPool.freeAllDestroyed();
    }

    private void draw() {
        batch.begin();
        background.draw(batch);
        cloudsBottom.draw(batch);
        bulletPool.drawActiveObjects(batch);
        enemyPool.drawActiveObjects(batch);
        userPlane.draw(batch);
//        cloudsTopBig.draw(batch);
//        cloudsTopSmall.draw(batch);
        batch.end();
    }

    @Override
    public void resize(Rect worldBounds) {
        background.resize(worldBounds);
        userPlane.resize(worldBounds);
        cloudsBottom.resize(worldBounds);
        cloudsTopBig.resize(worldBounds);
        cloudsTopSmall.resize(worldBounds);
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
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        userPlane.touchDown(touch, pointer, button);
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
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
}
