package Rich.Command;

import Rich.GameMap;
import Rich.GameRole;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: flocl
 * Date: 13-4-1
 * Time: 下午3:39
 * To change this template use File | Settings | File Templates.
 */
public class CommandBuilt {
    public Command[] commandInitialize(){
        Command[] commands = new Command[9];
        int i = 0;
        commands[i++] = new RollCommand();
        commands[i++] = new RobotCommand();
        commands[i++] = new QueryCommand();
        commands[i++] = new BlockCommand();
        commands[i++] = new BombCommand();
        commands[i++] = new HelpCommand();
        commands[i++] = new SellCommand();
        commands[i++] = new SellToolCommand();
        commands[i++] = new QuitCommand();
        return commands;
    }
    public void handleCommand(GameRole role, String lowercaseCommand, GameMap mapWithoutRoles, GameMap mapWithRoles){
        Command[] commands = commandInitialize();
        for (int i = 0; i < commands.length; i ++){
        if (lowercaseCommand.equals(commands[i].getCommand())){
            commands[i].executeCommand(mapWithoutRoles, mapWithRoles, role);
            return;
        }
    }

   }

}
