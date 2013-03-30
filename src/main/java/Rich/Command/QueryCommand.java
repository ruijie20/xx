package Rich.Command;

import Rich.Command.Command;
import Rich.GameMap;
import Rich.GameRole;

/**
 * Created with IntelliJ IDEA.
 * User: flocl
 * Date: 13-2-13
 * Time: 下午1:45
 * To change this template use File | Settings | File Templates.
 */
public class QueryCommand extends Command {
    public String query = "query";

    public QueryCommand(){

    }

    public void executeCommand(GameMap mapWithoutRole, GameMap mapWithRole,GameRole gameRole){
        System.out.println("资金：" + gameRole.getFund() + "元");
        System.out.println("点数：" + gameRole.getPoints() + "点");
        System.out.println("空地" + gameRole.getBlankLandAmount() + "处；茅屋"
                + gameRole.getBothyAmount() + "处；洋房" + gameRole.getHouseLandAmount()
                + "处；摩天楼" + gameRole.getSkyscraperLandAmount()+ "处。");
        System.out.println("路障" + gameRole.getBlockToolAmount() + "个；炸弹"+ gameRole.getBombToolAmount()
                + "个；机器娃娃" + gameRole.getRobotToolAmount() + "个。");
        if (gameRole.getBlankLandAmount() != 0){
            System.out.println("空地编号:"+gameRole.getBlankLandNum());
        }
        if (gameRole.getBothyAmount() != 0){
            System.out.println("茅屋编号:"+gameRole.getBothyLandNum());
        }
        if (gameRole.getHouseLandAmount() != 0){
            System.out.println("洋房编号:"+gameRole.getHouseLandNum());
        }
        if (gameRole.getSkyscraperLandAmount() != 0){
            System.out.println("摩天楼编号:"+gameRole.getSkyscraperLandNum());
        }
    }

    public String getHelp(){
        return "query          显示自己的资产信息。";
    }
}
