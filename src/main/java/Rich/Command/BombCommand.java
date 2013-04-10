package Rich.Command;

import Rich.*;
import Rich.GameRole.GameRole;
import Rich.Tool.GameTool;


public class BombCommand extends Command {
    public static final int MAP_LOWER_RANGE = 0;
    public static final int MAP_UPPER_RANGE = 69;
    GameMarks gameMarks = new GameMarks();
    private int offset;
    private String bomb = "bomb";

    public BombCommand(){
    }

    public void setNum(int offset) {
        this.offset = offset;
    }

    public String getCommand(){
        return bomb;
    }

    public void executeCommand(GameMap mapWithoutRole, GameMap mapWithRole,GameRole gameRole){
        if (HasTool(gameRole)) return;
        int temp = gameRole.getPosition() + offset;
        if (IsInMap(temp) &&IsNoToolsAndRoles(mapWithoutRole, mapWithRole, temp)){
            mapWithRole.getLand(temp).setMark(gameMarks.bomb);
            return;
        }
        else{
            System.out.println("此处不可设置" + GameTool.BOMB.tag());
            return;
        }
    }

    private boolean HasTool(GameRole gameRole) {
        int i = 0;
        for (; i < gameRole.getTools().size(); i ++){
            GameTool tool = gameRole.getTools().get(i);
            if (tool.tag().equals("炸弹")){
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
