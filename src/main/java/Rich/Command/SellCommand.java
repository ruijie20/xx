package Rich.Command;

import Rich.GameMap;
import Rich.GameMarks;
import Rich.GameRole;
import Rich.Land;

/**
 * Created with IntelliJ IDEA.
 * User: flocl
 * Date: 13-2-13
 * Time: 下午12:01
 * To change this template use File | Settings | File Templates.
 */
public class SellCommand extends Command {
    public static final int NOOWNER = 0;
    GameMarks gameMarks = new GameMarks();
    private int num;
    private int sellTimes = 2;
    private String sell = "sell";

    public SellCommand(){
    }

    public void setLandNum(int num) {
        this.num = num;
    }

    public String getCommand(){
        return sell;
    }

    public void executeCommand(GameMap mapWithoutRole, GameMap mapWithRole, GameRole gameRole){
        if (gameRole.getLands().size() == 0){
            System.out.println("您没有地产可出售");
            return;
        }
        int i = 0;
        for (; i < gameRole.getLands().size(); i ++){
            Land land = gameRole.getLands().get(i);
            if (land.getLandNum() == num){
                int money = sellTimes*land.getPrice() * (Integer.valueOf(land.getMark())+ 1);
                gameRole.setFund(gameRole.getFund() + money);
                setBlankLand(mapWithoutRole, i);
                gameRole.getLands().remove(i);
                System.out.println("您已出售一处房产，获得资金"+ money + "元");
                return;
            }
        }
        if ((i == gameRole.getLands().size()) && (i != 0)){
            System.out.println("输入地产编号错误!");
            return;
        }
    }

    private void setBlankLand(GameMap mapWithoutRole, int i) {
        mapWithoutRole.getLand(i).setOwner(NOOWNER);
        mapWithoutRole.getLand(i).setMark(gameMarks.blankLand);
    }

    public String getHelp(){
        return "sell x         出售自己拥有的地产，x为地产编号。";
    }
}
