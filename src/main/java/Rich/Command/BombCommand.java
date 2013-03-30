package Rich.Command;

import Rich.*;
import Rich.Tool.BombTool;
import Rich.Tool.GameTool;

/**
 * Created with IntelliJ IDEA.
 * User: flocl
 * Date: 13-2-10
 * Time: 下午4:00
 * To change this template use File | Settings | File Templates.
 */
public class BombCommand extends Command {
    public static final int MAP_LOWER_RANGE = 0;
    public static final int MAP_UPPER_RANGE = 69;
    GameMarks gameMarks = new GameMarks();
    private int offset;
    BombTool bombTool = new BombTool();
    public String bomb = "bomb";

    public BombCommand(){
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public void executeCommand(GameMap mapWithoutRole, GameMap mapWithRole,GameRole gameRole){
        if (HasTool(gameRole)) return;
        int temp = gameRole.getPosition() + offset;
        if (IsInMap(temp) &&IsNoToolsAndRoles(mapWithoutRole, mapWithRole, temp)){
            mapWithRole.getLand(temp).setMark(gameMarks.bomb);
            return;
        }
        else{
            System.out.println("此处不可设置" + bombTool.name);
            return;
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
        if (i == gameRole.getLands().size()){
            System.out.println("你没有该道具!");
            return true;
        }
        return false;
    }

    private boolean IsInMap(int temp) {
        return (temp >= MAP_LOWER_RANGE && temp <= MAP_UPPER_RANGE);
    }

    private boolean IsNoToolsAndRoles(GameMap mapWithoutRole, GameMap mapWithRole, int temp) {
        return mapWithRole.getLand(temp).getMark().equals(mapWithoutRole.getLand(temp).getMark());
    }

    public String getHelp(){
        return "bomb n         在距离自己位置的n处设置炸弹，n在[-10,10]内，负数表示后方。";
    }
}
