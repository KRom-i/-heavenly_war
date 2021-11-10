package stargame.gb.ru.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;

import java.util.List;

import stargame.gb.ru.base.BaseScreen;
import stargame.gb.ru.base.Font;
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

    private static final String FRAGS = "Frags: ";
    private static final String HP = "HP: ";
    private static final String LEVEL = "Level: ";
    private static final float FONT_SIZE = 0.02f;
    private static final float MARGIN = 0.01f;

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

    private Font font;
    private int frags;
    private StringBuilder sbFrags;

    private StringBuilder sbHp;

    private StringBuilder sbLevel;

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
        cloudsTopBig  = new CloudPoll(atlas, 2, 1.5f, -0.2f);
        cloudsTopSmall = new CloudPoll(atlas, 3, 1.5f, -0.3f);

        bulletPool = new BulletPool(bulletSound);

        explosionPool = new ExplosionPool(atlas2, explosionSound);

        userPlane = new UserPlane(atlas, explosionPool, bulletPool);

        enemyPool = new EnemyPool(bulletPool, explosionPool, worldBounds, bulletSound, userPlane);

        enemyEmitter = new EnemyEmitter(enemyPool, worldBounds, atlas);

        newGameButton = new NewGameButton(this, atlas2);
        gameOver = new GameOver(atlas2);


        font = new Font("font/font.fnt", "font/font.png");
        font.setSize(FONT_SIZE);
        sbFrags = new StringBuilder();
        sbHp = new StringBuilder();
        sbLevel = new StringBuilder();
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
            enemyEmitter.generate(delta, frags);
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
                    if (enemyPlane.isDestroyed()){
                        frags ++;
                    }
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
        font.dispose();
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

        explosionPool.drawActiveObjects(batch);
//        cloudsTopBig.draw(batch);
        cloudsTopSmall.draw(batch);
        printInfo();
        batch.end();
    }

    private void printInfo(){
        sbFrags.setLength(0);
        font.draw(
                batch,
                sbFrags.append(FRAGS).append(frags),
                worldBounds.getLeft() + MARGIN,
                worldBounds.getTop() - MARGIN,
                Align.left
        );

        sbHp.setLength(0);
        font.draw(
                batch,
                sbHp.append(HP).append(userPlane.getHp()),
                worldBounds.pos.x,
                worldBounds.getTop() - MARGIN,
                Align.center
        );

        sbLevel.setLength(0);
        font.draw(
                batch,
                sbLevel.append(LEVEL).append(enemyEmitter.getLevel()),
                worldBounds.getRight() - MARGIN,
                worldBounds.getTop() - MARGIN,
                Align.right
        );
    }

    public void newGame() {
        bulletPool.destroy();
        explosionPool.destroy();
        enemyPool.destroy();
        userPlane.restore();
        frags = 0;
    }
}
