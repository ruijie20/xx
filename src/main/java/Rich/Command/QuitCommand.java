package Rich.Command;

import Rich.GameMap;
import Rich.GameRole.GameRole;


public class QuitCommand extends Command {
    private String quit = "quit";

    public QuitCommand(){
    }

    public void executeCommand(GameMap mapWithoutRole, GameMap mapWithRole,GameRole gameRole){
        System.exit(0);
    }

    public String getCommand(){
        return quit;
    }
    public String getHelp(){
        return "quit           强制退出。";
    }
}
