package stargame.gb.ru;

import com.badlogic.gdx.Game;

import stargame.gb.ru.screen.GameScreen;
import stargame.gb.ru.screen.MenuScreen;

public class StarGame extends Game {

	@Override
	public void create() {
		setScreen(new MenuScreen());
	}

}
