package Rich.Command;

import Rich.*;
import Rich.GameRole.GameRole;


public class HelpCommand extends Command {
    private String help = "help";

    public HelpCommand(){
    }

    public String getCommand(){
        return help;
    }
    public void executeCommand(GameMap mapWithoutRole, GameMap mapWithRole,GameRole gameRole){
        CommandBuilt commandBuilt = new CommandBuilt();
        Command[] commands = commandBuilt.commandInitialize();
        for (int i = 0; i < commands.length; i ++){
            System.out.println(commands[i].getHelp());
        }
    }
    public String getHelp(){
        return "help           查看命令帮助。";
    }
}
