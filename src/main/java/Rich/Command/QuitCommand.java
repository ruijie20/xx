package Rich.Command;

import Rich.Command.Command;
import Rich.GameMap;
import Rich.GameRole;

/**
 * Created with IntelliJ IDEA.
 * User: flocl
 * Date: 13-2-13
 * Time: 下午2:49
 * To change this template use File | Settings | File Templates.
 */
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
