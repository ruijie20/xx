package Rich.GameRole;

import Rich.GameRole.GameRole;

import java.util.ArrayList;

public class GameRolesBuilt {
    private static final String RED = "red";
    private static final String GREEN = "green";
    private static final String YELLOW = "yellow";
    private static final String BLUE = "blue";
    public ArrayList<GameRole> gameRoles = new ArrayList<GameRole>();


    public ArrayList<GameRole> initializeGameRoles(){
        gameRoles.add(new GameRole(1,"钱夫人","Q",RED));
        gameRoles.add(new GameRole(2,"阿土伯","A",BLUE));
        gameRoles.add(new GameRole(3,"孙小美","S",GREEN));
        gameRoles.add(new GameRole(4,"金贝贝","J",YELLOW));
        return gameRoles;
    }

}
