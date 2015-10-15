package com.uvg.chemi;

import com.badlogic.gdx.Game;

import javafx.stage.Stage;
import screens.MenuScreen;

public class ChemistryTriviaGame extends Game {

	@Override
	public void create () {
        MenuScreen menu = new MenuScreen();
        setScreen(menu);
    }

}
