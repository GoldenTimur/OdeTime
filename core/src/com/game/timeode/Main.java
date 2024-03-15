package com.game.timeode;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import com.game.timeode.Actors.Player;
import com.game.timeode.Background.Name;
import com.game.timeode.Background.Scene;
import com.game.timeode.Background.Start;
import com.game.timeode.Screens.GameSc;
import com.game.timeode.Tools.Fight;
import com.game.timeode.Tools.Joystick;
import com.game.timeode.Tools.PlayButton;

public class Main extends Game implements Interface {
	public static SpriteBatch batch;
	public static int WIDTH,HEIGHT;
	public static boolean time = false;
	public static GameSc gameSc;

	public static Texture PlayOut,Play , Name, Scene1, Scene2, circle1, circle2, circle3, actor1, actor1_1, actor2, actor3, actor4, actor5, actorFight1, actorFight1_1, actorFight2, actorFight2_1, actorFight3, actorFight3_1, actorFight4, actorFight5, Load, Box1, Box2, Box3, Box4, Box5, Box6, WallLiane1, Plane1, Water1, Water2, Pit1, Pit2;

	@Override
	public void create () {
		batch = new SpriteBatch();
		WIDTH = Gdx.graphics.getWidth();
		HEIGHT = Gdx.graphics.getHeight();
		circle1 = new Texture("Joystick1.png");
		circle2 = new Texture("Joystick2.png");
		circle3 = new Texture("Fightstick1.png");
		actor1 = new Texture("Run1.png");
		actor1_1 = new Texture("Run1_1.png");
		actorFight1 = new Texture("Fight1.png");
		actorFight1_1 =  new Texture("Fight1_1.png");
		actorFight2 = new Texture("Fight2.png");
		actorFight2_1 = new Texture("Fight2_1.png");
		actorFight3 = new Texture("Fight3.png");
		actorFight3_1 = new Texture("Fight3_1.png");
		Scene1 = new Texture("Grass1.png");
		Scene2 = new Texture("Grass2.png");
		Play = new Texture("Play1.png");
		Name = new Texture("Time_Ode.png");
		PlayOut = new Texture("TimeOut1.png");
		Load = new Texture("Loading2.png");
		Box1 = new Texture("Box2.png");
		Box2 = new Texture("Box1.png");
		Box3 = new Texture("Box4.png");
		Box4 = new Texture("Box3.png");
		Box5 = new Texture("Box5.png");
		Box6 = new Texture("Box6.png");
		WallLiane1 = new Texture("WallLiane1.png");
		Plane1 = new Texture("ForBox1.png");
		Water1 = new Texture("Water1.png");
		Water2 = new Texture("Water2.png");
		Pit1 = new Texture("Hole1.png");
		Pit2 = new Texture("Hole2.png");
		gameSc = new GameSc(this, joy, fig, player, scene, scene2, playButton, name, start, load, box, wallLiane, plate, pit);
		setScreen(gameSc);
	}


	@Override
	public void dispose () {
		batch.dispose();
	}
}