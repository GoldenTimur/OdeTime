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
import com.game.timeode.Tools.Again;
import com.game.timeode.Tools.Fight;
import com.game.timeode.Tools.Joystick;
import com.game.timeode.Tools.PlayButton;
import com.game.timeode.Tools.TimeButton;

public interface Interface {
    Joystick joy = null;
    Fight fig = null;
    Player player = null;
    Scene scene = null;
    Scene scene2 = null;
    PlayButton playButton = null;
    Name name = null;
    Start start = null;
    Load load = null;
    Boxes box = null;
    WallLiane wallLiane = null;
    Plate plate = null;
    GameSc gameSc = null;
    Pit pit = null;
    Water water = null;
    Time time = null;
    Paradoxes paradox = null;
    Again again = null;
    TimeButton timeButton = null;

}
