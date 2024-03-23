package com.game.timeode;

import com.game.timeode.Actors.Boxes;
import com.game.timeode.Actors.Paradoxes;
import com.game.timeode.Actors.Pit;
import com.game.timeode.Actors.Plate;
import com.game.timeode.Actors.Player;
import com.game.timeode.Actors.Time;
import com.game.timeode.Actors.WallLiane;
import com.game.timeode.Actors.Water;
import com.game.timeode.Background.Load;
import com.game.timeode.Background.Name;
import com.game.timeode.Background.Scene;
import com.game.timeode.Background.Start;
import com.game.timeode.Screens.GameSc;
import com.game.timeode.Tools.Fight;
import com.game.timeode.Tools.Joystick;
import com.game.timeode.Tools.PlayButton;

public abstract class Objects implements Interface{
    protected Joystick joy;
    protected Fight fig;
    protected Player player;
    protected Scene scene;
    protected Scene scene2;
    protected PlayButton playButton;
    protected Name name;
    protected Start start;
    protected Load load;
    protected Boxes box;
    protected WallLiane wallLiane;
    protected Plate plate;
    protected Main main;
    protected Pit pit;
    protected Time time;
    protected Water water;
    protected Paradoxes paradox;


    public Objects(Main main, Joystick joy, Fight fig, Player player, Scene scene, Scene scene2, PlayButton playButton, Name name, Start start, Load load, Boxes box, WallLiane wallLiane, Plate plate, Pit pit, Time time, Water water, Paradoxes paradox) {
        this.joy = joy;
        this.fig = fig;
        this.player = player;
        this.scene = scene;
        this.scene2 = scene2;
        this.playButton = playButton;
        this.name = name;
        this.start = start;
        this.load = load;
        this.box = box;
        this.wallLiane = wallLiane;
        this.plate = plate;
        this.main = main;
        this.pit = pit;
        this.water = water;
        this.time = time;
        this.paradox = paradox;
    }


    public Joystick getJoy() {
        return joy;
    }

    public Fight getFig() {
        return fig;
    }

    public Player getPlayer() {
        return player;
    }

    public Scene getScene() {
        switch (GameSc.getA()) {
            case (1):
            case (3):
                return scene;
            case (2):
            case (4):
                return scene2;
        }
        return scene;
    }

    public PlayButton getPlayButton() {
        return playButton;
    }

    public Name getName() {
        return name;
    }

    public Start getStart() {
        return start;
    }

    public Load getLoad() {
        return load;
    }

    public Boxes getBox() {
        switch (GameSc.getA()) {
            case (1):
                return box;
            case (2):
                return box;
        }
        return box;
    }

    public WallLiane getWallLiane() {
        switch (GameSc.getA()) {
            case (1):
                return wallLiane;
            case (2):
                return wallLiane;
        }
        return wallLiane;
    }
    public Plate getPlate() {
        switch (GameSc.getA()) {
            case (1):
                return plate;
            case (2):
                return plate;
        }
        return plate;
    }

    public Pit getPit() {
        return pit;
    }

    public Water getWater() {
        return water;
    }

    public Time getTime() {
        return time;
    }

    public Paradoxes getParadox() {
        return paradox;
    }
}