package stargame.gb.ru;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;


public class StarGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture backgroundImg;
	int backgroundWidth, backgroundHeight;
	final float BACK_X = 0;
	final float BACK_Y = 0;

	@Override
	public void create () {
		batch = new SpriteBatch();
		backgroundImg = new Texture("background_img.jpg");
		backgroundWidth = Gdx.graphics.getWidth();
		backgroundHeight = Gdx.graphics.getHeight();
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		batch.setColor(Color.BLACK);
		batch.draw(backgroundImg, BACK_X, BACK_Y, backgroundWidth, backgroundHeight);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		backgroundImg.dispose();
	}
}
