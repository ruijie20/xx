package Rich.Command;

import Rich.GameMap;
import Rich.GameRole.GameRole;


public abstract class Command {

    public Command() {
    }
    public abstract String getCommand();
    public abstract void executeCommand(GameMap mapWithoutRole, GameMap mapWithRole, GameRole gameRole);
    public abstract String getHelp();
    public void setNum(int num) {}
}
