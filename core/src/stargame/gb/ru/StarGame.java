package stargame.gb.ru;

import com.badlogic.gdx.Game;

import stargame.gb.ru.screen.GameScreen;

public class StarGame extends Game {

	@Override
	public void create() {
		setScreen(new GameScreen());
	}

}
