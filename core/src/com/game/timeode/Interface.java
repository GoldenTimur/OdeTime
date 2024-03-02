package com.game.timeode;

import com.game.timeode.Actors.Boxes;
import com.game.timeode.Actors.Plate;
import com.game.timeode.Actors.Player;
import com.game.timeode.Background.Load;
import com.game.timeode.Background.Name;
import com.game.timeode.Background.Scene;
import com.game.timeode.Background.Start;
import com.game.timeode.Tools.Fight;
import com.game.timeode.Tools.Joystick;
import com.game.timeode.Tools.PlayButton;

public interface Interface {
    Joystick joy = null;
    Fight fig = null;
    Player player = null;
    Scene scene = null;
    PlayButton playButton = null;
    Name name = null;
    Start start = null;
    Load load = null;
    Boxes box = null;
    Boxes wallLiane = null;
    Plate plate = null;

}
