package Rich.Command;

import Rich.GameMap;
import Rich.GameMarks;
import Rich.GameRole.GameRole;
import Rich.Tool.GameTool;


public class RobotCommand extends Command {
    GameMarks gameMarks = new GameMarks();
    private String robot = "robot";

    public RobotCommand(){
    }

    public String getCommand(){
        return robot;
    }

    public void executeCommand(GameMap mapWithoutRole, GameMap mapWithRole,GameRole gameRole){
        if (hasTool(gameRole)) return;
        int tempPosition = gameRole.getPosition() + 1;
        for (; tempPosition < gameRole.getPosition() + 11; tempPosition ++){
            IsMeetTool(mapWithoutRole, mapWithRole, tempPosition, gameMarks.bomb);
            IsMeetTool(mapWithoutRole, mapWithRole, tempPosition, gameMarks.block);
        }
    }

    private boolean hasTool(GameRole gameRole) {
        int i = 0;
        for (; i < gameRole.getTools().size(); i ++){
            GameTool tool = gameRole.getTools().get(i);
            if (tool.tag().equals("炸弹")){
                gameRole.getTools().remove(i);
                break;
            }
        }
        if (i == gameRole.getTools().size()){
            System.out.println("你没有该道具!");
            return true;
        }
        return false;
    }

    private void IsMeetTool(GameMap mapWithoutRole, GameMap mapWithRole, int tempPosition, String toolMark) {
        if (mapWithRole.getLand(tempPosition).getMark().equals(toolMark)){
            mapWithRole.getLand(tempPosition).setMark(mapWithoutRole.getLand(tempPosition).getMark());
            return;
        }
    }

    public String getHelp(){
        return "robot          清扫前方路面上10步以内（包括10）的道具，炸弹和路障";
    }
}
