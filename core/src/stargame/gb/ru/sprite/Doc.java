package stargame.gb.ru.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import stargame.gb.ru.base.Sprite;
import stargame.gb.ru.math.Rect;
import stargame.gb.ru.math.Rnd;

public class Doc extends Sprite {

    private static float HEIGHT = 0.05f;
    private Rect worldBounds;
    private Sound sound;
    private int hp;
    private Vector2 v;
    private boolean show;


    public Doc(TextureAtlas textureAtlas, Rect worldBounds) {
        super(textureAtlas.findRegion("doc"));
        sound = Gdx.audio.newSound(Gdx.files.internal("sounds/doc.wav"));
        this.worldBounds = worldBounds;
        hp = 10;
        v = new Vector2(0f, -0.4f);
        setHeightProportion(HEIGHT);
    }

    public void show(){
        initPos();
        show = true;
    }

    public int getHp(){
        initPos();
        sound.play();
        show = false;
        return hp;
    }

    private void initPos(){
        float x = Rnd.nextFloat(worldBounds.getLeft(), worldBounds.getRight());
        pos.set(x, 0.5f);
    }

    @Override
    public void update(float delta) {
        if (!show) return;

        pos.mulAdd(v, delta);
        if (getTop() < worldBounds.getBottom()){
            show = false;
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        if (!show) return;
        super.draw(batch);
    }

    public void dispose(){
        sound.dispose();
    }
}
