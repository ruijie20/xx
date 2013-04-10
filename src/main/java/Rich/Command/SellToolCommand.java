package Rich.Command;

import Rich.GameMap;
import Rich.GameRole.GameRole;
import Rich.Tool.GameTool;

public class SellToolCommand extends Command {
    private int num;
    private String sellTool = "selltool";

    public SellToolCommand(){
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getCommand(){
        return sellTool;
    }

    public void executeCommand(GameMap mapWithoutRole, GameMap mapWithRole, GameRole gameRole){
        if (gameRole.getTools().size() == 0){
            System.out.println("您没有可出售的道具");
            return;
        }
        int i = 0;
        for (; i < gameRole.getTools().size(); i ++){
            GameTool tool = gameRole.getTools().get(i);
            if (tool.num() == num){
                gameRole.setPoints(gameRole.getPoints() + tool.points());
                gameRole.getTools().remove(i);
                return;
            }
        }
        if ((i != 0) && (i == gameRole.getLands().size())){
            System.out.println("你没有该道具!");
            return;
        }
    }

    public String getHelp(){
        return "sellTool x     出售已有的道具，x为道具编号。";
    }
}
