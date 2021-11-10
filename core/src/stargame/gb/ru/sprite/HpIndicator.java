package stargame.gb.ru.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import stargame.gb.ru.base.Sprite;

public class HpIndicator extends Sprite {

    private static final float MARGIN = 0.01f;
    private EnemyPlane enemyPlane;

    public HpIndicator(TextureAtlas atlas, EnemyPlane enemyPlane) {
        super(atlas.findRegion("hp"));
        this.enemyPlane = enemyPlane;
        setHeightProportion(0.005f);
    }

    @Override
    public void update(float delta) {
        setLeft(enemyPlane.getLeft());
        setBottom(enemyPlane.getTop() + MARGIN);
        setWidth(calculateWidth());
        super.update(delta);
    }

    private float calculateWidth() {
        float i = (float) enemyPlane.getHp() / enemyPlane.getDefaultHp();
        return enemyPlane.getWidth() * i;
    }
}
