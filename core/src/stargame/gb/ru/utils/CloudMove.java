package stargame.gb.ru.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import stargame.gb.ru.sprite.Cloud;

public class CloudMove {

    private enum Move{
        LEFT, RIGHT, DOWN;
    }

    private Move move;

    private List<Cloud> cloudList;

    public CloudMove(Clouds... clouds) {
        move = Move.DOWN;
        cloudList = new ArrayList<>();
        addClouds(clouds);
    }

    private void addClouds(Clouds... c){
        for (Clouds clouds: c){
            for (Cloud cloud: clouds.getSprites()){ ;
                cloudList.add(cloud);
            }
        }
    }

    public void left(){
        if (move == Move.LEFT){
            return;
        }
        move = Move.LEFT;
        for (Cloud cloud: cloudList){ ;
            cloud.left();
        }
    }

    public void right(){
        if (move == Move.RIGHT) {
            return;
        }
        move = Move.RIGHT;
        for (Cloud cloud: cloudList){ ;
            cloud.right();
        }
    }

    public void down(){
        if (move == Move.DOWN) return;
        switch (move){
            case LEFT:
                for (Cloud cloud: cloudList){ ;
                    cloud.right();
                }
                break;
            case RIGHT:
                for (Cloud cloud: cloudList){ ;
                    cloud.left();
                }
                break;
        }
        move = Move.DOWN;
    }
}
