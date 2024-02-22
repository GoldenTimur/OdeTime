package com.game.timeode;

import com.game.timeode.Actors.Boxes;
import com.game.timeode.Actors.Player;
import com.game.timeode.Background.Load;
import com.game.timeode.Background.Name;
import com.game.timeode.Background.Scene;
import com.game.timeode.Background.Start;
import com.game.timeode.Tools.Fight;
import com.game.timeode.Tools.Joystick;
import com.game.timeode.Tools.PlayButton;

public abstract class Objects implements Interface{
    protected Joystick joy;
    protected Fight fig;
    protected Player player;
    protected Scene scene;
    protected PlayButton playButton;
    protected Name name;
    protected Start start;
    protected Load load;
    protected Boxes box;

    public Objects(Joystick joy, Fight fig, Player player, Scene scene, PlayButton playButton, Name name, Start start, Load load, Boxes box) {
        this.joy = joy;
        this.fig = fig;
        this.player = player;
        this.scene = scene;
        this.playButton = playButton;
        this.name = name;
        this.start = start;
        this.load = load;
        this.box = box;
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
        return box;
    }
}