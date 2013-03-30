package Rich.Command;

import Rich.GameMap;
import Rich.GameRole;
import Rich.Tool.GameTool;

/**
 * Created with IntelliJ IDEA.
 * User: flocl
 * Date: 13-2-13
 * Time: 下午12:27
 * To change this template use File | Settings | File Templates.
 */
public class SellToolCommand extends Command {
    private int num;
    public String SellTool = "selltool";

    public SellToolCommand(){
    }

    public void setToolNum(int num) {
        this.num = num;
    }

    public void executeCommand(GameMap mapWithoutRole, GameMap mapWithRole, GameRole gameRole){
        if (gameRole.getTools().size() == 0){
            System.out.println("您没有可出售的道具");
            return;
        }
        int i = 0;
        for (; i < gameRole.getTools().size(); i ++){
            GameTool tool = gameRole.getTools().get(i);
            if (tool.getNum() == num){
                gameRole.setPoints(gameRole.getPoints() + tool.getPoints());
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
        return "SellTool x     出售已有的道具，x为道具编号。";
    }
}
