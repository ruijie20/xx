package Rich;

/**
 * Created with IntelliJ IDEA.
 * User: flocl
 * Date: 13-2-8
 * Time: 下午2:37
 * To change this template use File | Settings | File Templates.
 */
public class RobotCommand extends Command{
    GameMarks gameMarks = new GameMarks();
    public String robot = "robot";

    public RobotCommand(){
    }

    public void executeCommand(GameMap mapWithoutRole, GameMap mapWithRole,GameRole gameRole){
        if (HasTool(gameRole)) return;
        int tempPosition = gameRole.getPosition() + 1;
        for (; tempPosition < gameRole.getPosition() + 11; tempPosition ++){
            IsMeetTool(mapWithoutRole, mapWithRole, tempPosition, gameMarks.bomb);
            IsMeetTool(mapWithoutRole, mapWithRole, tempPosition, gameMarks.block);
        }
    }

    private boolean HasTool(GameRole gameRole) {
        int i = 0;
        for (; i < gameRole.getTools().size(); i ++){
            GameTool tool = gameRole.getTools().get(i);
            if (tool.getName().equals("炸弹")){
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
