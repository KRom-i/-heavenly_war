package stargame.gb.ru.poll;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import stargame.gb.ru.math.Rect;
import stargame.gb.ru.sprite.Cloud;

public class CloudPoll {

    private Cloud[] clouds;

    public CloudPoll(TextureAtlas atlas, int counter, float size, float minVectorX) {
        this.clouds = new Cloud[counter];
        for (int i = 0; i < clouds.length; i++) {
            clouds[i] = new Cloud(atlas, size, minVectorX);
        }
    }
   public void update(float delta){
       for (Cloud cloud: clouds) {
           cloud.update(delta);
       }
   }

    public void draw(SpriteBatch batch){
        for (Cloud cloud: clouds) {
            cloud.draw(batch);
        }
    }

    public void resize(Rect rect){
        for (Cloud cloud: clouds) {
            cloud.resize(rect);
        }
    }

}
