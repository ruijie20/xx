package Rich.Command;

import Rich.GameMap;
import Rich.GameRole.GameRole;


public class CommandBuilt {
    public Command[] commandInitialize(){
        Command[] commands = new Command[9];
        int i = 0;
        commands[i++] = new RollCommand();
        commands[i++] = new RobotCommand();
        commands[i++] = new QueryCommand();
        commands[i++] = new QuitCommand();
        commands[i++] = new HelpCommand();
        commands[i++] = new BlockCommand();
        commands[i++] = new BombCommand();
        commands[i++] = new SellCommand();
        commands[i++] = new SellToolCommand();
        return commands;
    }
    public void handleCommand(GameRole role, String lowercaseCommand, GameMap mapWithoutRoles, GameMap mapWithRoles){
        Command[] commands = commandInitialize();
        for (int i = 0; i < commands.length; i ++){
            String [] command = lowercaseCommand.split(" ");
            if (command.length < 1 || command.length > 2){
                return;
            }

        if (command[0].equals(commands[i].getCommand())){
            if (command.length == 2){
                commands[i].setNum(Integer.parseInt(command[1]));
            }
            commands[i].executeCommand(mapWithoutRoles, mapWithRoles, role);
            return;
        }
    }

   }

}
