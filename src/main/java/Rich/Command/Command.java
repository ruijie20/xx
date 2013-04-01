package Rich.Command;

import Rich.GameMap;
import Rich.GameRole;

/**
 * Created with IntelliJ IDEA.
 * User: flocl
 * Date: 13-2-7
 * Time: 下午5:05
 * To change this template use File | Settings | File Templates.
 */
public abstract class Command {

    public Command() {
    }
    public abstract String getCommand();
    public abstract void executeCommand(GameMap mapWithoutRole, GameMap mapWithRole, GameRole gameRole);
    public String getHelp(){
        return "";
    }
}
