package Rich.Command;

import Rich.*;
import Rich.Tool.BlockTool;
import Rich.Tool.BombTool;
import Rich.Tool.GameTool;
import Rich.Tool.RobotTool;

import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: flocl
 * Date: 13-2-7
 * Time: 下午5:08
 * To change this template use File | Settings | File Templates.
 */
public class RollCommand extends Command {
    public static final int HOSPITAL_DAYS = 3;
    public static final int PRISON_DAYS = 2;
    public static final int HOSPITAL_LAND_NUM = 14;
    GameMarks gameMarks = new GameMarks();
    public static final int GOVERNMENT = 100;
    public static final int NOOWNER = 0;
    public String roll = "roll";
    public RollCommand() {
    }
    public void executeCommand(GameMap mapWithoutRoles, GameMap mapWithRoles,GameRole gameRole){
        int steps = (int) (Math.random() * 5 + 1);
        System.out.println("您掷出点数为:"+steps);
        int currentPosition = gameRole.getPosition();
        if(currentPosition + steps >69){
            System.out.println(gameRole.getName() + "已经到达终点，获得胜利");
            gameRole.setPosition(0);
            gameRole.winner = true;
            return;
        }
        mapWithRoles.getLand(currentPosition).setMark(mapWithoutRoles.getLand(currentPosition).getMark());
        for (int i = 0; i < steps; i ++){
            currentPosition ++;
            if (IsMeetBlock(mapWithRoles, gameRole,currentPosition)) break;
            if (IsMeetBomb(mapWithRoles, mapWithoutRoles, gameRole, currentPosition)){
                currentPosition = HOSPITAL_LAND_NUM;
                break;
            }
        }
        gameRole.setPosition(currentPosition);
        mapWithRoles.getLand(currentPosition).setMark(gameRole.getSymbol());
        if (IsStoppedAtPrison(mapWithoutRoles, gameRole, currentPosition)) return;
        Land currentLand = mapWithoutRoles.getLand(gameRole.getPosition());
        if (IsBuyBlankLand(mapWithoutRoles, gameRole, currentLand)) return;
        if (IsUpdateLand(mapWithoutRoles, gameRole, currentLand)) return;
        if (currentLand.getOwner() == GOVERNMENT){
            if (IsAtPitLand(gameRole, currentLand)) return;
            IsInMagicHouse(currentLand);
            IsInGiftHouse(gameRole, currentLand);
            IsInToolHouse(gameRole, currentLand);
            return;
        }
    }

    private void IsInToolHouse(GameRole gameRole, Land currentLand) {
        if (currentLand.getMark().equals(gameMarks.toolHouse)){
            if (gameRole.getPoints() < 30){
                System.out.println("点数不足，退出工具屋");
                return;
            }
            PrintToolHouseMessage();
            while (true) {
                if (gameRole.getPoints() < 30) {
                    System.out.println("点数不足，退出工具屋");
                    break;
                }
                if (gameRole.getTools().size() > 10){
                    System.out.println("道具袋已满，不可再买入");
                    break;
                }
                Scanner scanner = new Scanner(System.in);
                String toolNum = scanner.next();
                if (toolNum.equals("F")) break;
                if (BuyBlockTool(gameRole, toolNum)) continue;
                if (BuyRobotTool(gameRole, toolNum)) continue;
                if (BuyBombTool(gameRole, toolNum))continue;
            }
            return;
        }
    }

    private boolean BuyBombTool(GameRole gameRole, String toolNum){
        if (toolNum.equals("3")){
            BombTool bombTool = new BombTool();
            getTool(gameRole,bombTool);
            return true;
        }
        return false;
    }

    private boolean BuyRobotTool(GameRole gameRole, String toolNum) {
        if (toolNum.equals("2")) {
            RobotTool robotTool = new RobotTool();
            getTool(gameRole, robotTool);
            return true;
        }
        return false;
    }

    private boolean BuyBlockTool(GameRole gameRole, String toolNum) {
        if (toolNum.equals("1")) {
            BlockTool blockTool = new BlockTool();
            getTool(gameRole, blockTool);
            return true;
        }
        return false;
    }

    private void PrintToolHouseMessage() {
        System.out.println("欢迎光临道具屋，请选择您所需要的道具(输入编号,F可退出)：");
        System.out.println("道具    编号  价值（点数）  显示方式   功用");
        System.out.println("路障      1    50           #       将路障放置到离当前位置前后10步的距离，任一玩家经过路障将被拦截");
        System.out.println("机器娃娃  2     30                   清除前方10内的道具");
        System.out.println("炸弹      3    50           @       将炸弹放置到离当前位置前后10步的距离，任一玩家经过该位置将被炸伤，送往医院住院三天");
    }

    private void getTool(GameRole gameRole, GameTool gameTool) {
        if (gameRole.getPoints() < gameTool.getPoints()) {
            System.out.println("点数不足不可购买");
            return;
        }

        gameRole.getTools().add(gameTool);
        gameRole.setPoints(gameRole.getPoints() - gameTool.getPoints());
    }

    private void IsInGiftHouse(GameRole gameRole, Land currentLand) {
        if (currentLand.getMark().equals(gameMarks.giftHouse)){
            PrintGiftHouseMessage();
            Scanner scanner = new Scanner(System.in);
            int giftNum = scanner.nextInt();
            if (giftNum == 1){
                gameRole.setFund(gameRole.getFund() + 2000);
                return;
            }
            if (giftNum == 2){
                gameRole.setPoints(gameRole.getPoints() + 200);
                return;
            }
            if (giftNum == 3){
                gameRole.setMascotRound(gameRole.getMascotRound() + 6);
                return;
            }
            return;
        }
    }

    private void PrintGiftHouseMessage() {
        System.out.println("欢迎光临礼品屋，请选择一件您喜欢的礼品(输入编号)：");
        System.out.println("礼品    编号     功用");
        System.out.println("奖金      1     资金增加2000元");
        System.out.println("点数卡    2      点数增加200点");
        System.out.println("福神      3      路过其它玩家地盘免费，5轮有效，监狱医院中也轮次");
    }

    private void IsInMagicHouse(Land currentLand) {
        if (currentLand.getMark().equals(gameMarks.magicHouse)){
            System.out.println("进入魔法屋，可施展魔法！");
            return;
        }
    }

    private boolean IsAtPitLand(GameRole gameRole, Land currentLand) {
        if (currentLand.getMark().equals(gameMarks.pit)){
            System.out.println("到达矿井，获得点数" + currentLand.getPrice() + "点");
            gameRole.setPoints(gameRole.getPoints() + currentLand.getPrice());
            return true;
        }
        return false;
    }

    private boolean IsUpdateLand(GameMap mapWithoutRoles, GameRole gameRole, Land currentLand) {
        if (currentLand.getOwner() == gameRole.getRoleNum() && currentLand.getMark() != gameMarks.skyscraperLand){
            System.out.println("是否升级该处房产，" + currentLand.getPrice() + "元，（Y/N）");
            Scanner scanner = new Scanner(System.in);
            String answer = scanner.next();
            String upperAnswer = answer.toUpperCase();
            if (upperAnswer.equals("Y")){
                if (gameRole.getFund() < currentLand.getPrice()) {
                    System.out.println("资金不足，无法升级该处房产");
                    return true;
                }
                for (int j = 0; j < gameRole.getLands().size(); j ++){
                    Land land = gameRole.getLands().get(j);
                    if (land.getLandNum() == currentLand.getLandNum()){
                        gameRole.getLands().get(j).setMark(land.getMark() + 1);
                        mapWithoutRoles.getLand(land.getLandNum()).setMark(land.getMark() + 1);
                    }
                }
                gameRole.setFund(gameRole.getFund() - currentLand.getPrice());
                return true;
            }
            else {
                return true;
            }
        }
        return false;
    }

    private boolean IsBuyBlankLand(GameMap mapWithoutRoles, GameRole gameRole, Land currentLand) {
        if (currentLand.getOwner() == NOOWNER){
            System.out.println("是否购买该空地,"+currentLand.getPrice()+"元（Y/N）");
            Scanner scanner = new Scanner(System.in);
            String answer = scanner.next();
            String upperAnswer = answer.toUpperCase();
            if (upperAnswer.equals("Y")){
                if (gameRole.getFund() < currentLand.getPrice()){
                    System.out.println("资金不足，不可购买");
                    return true;
                }
                mapWithoutRoles.getLand(currentLand.getLandNum()).setOwner(gameRole.getRoleNum());
                gameRole.getLands().add(mapWithoutRoles.getLand(currentLand.getLandNum()));
                gameRole.setFund(gameRole.getFund() - currentLand.getPrice());
                return true;
            }
            else {
                return true;
            }
        }
        return false;
    }

    private boolean IsStoppedAtPrison(GameMap mapWithoutRoles, GameRole gameRole, int currentPosition) {
        if (mapWithoutRoles.getLand(currentPosition).getMark().equals(gameMarks.prison)){
            gameRole.setPrisonDays(PRISON_DAYS);
            System.out.println("糟糕！进入监狱了！被扣留两天！");
            return true;
        }
        return false;
    }

    private boolean IsMeetBomb(GameMap mapWithRole, GameMap mapWithoutRole, GameRole gameRole, int currentPosition) {
        if (mapWithRole.getLand(currentPosition).getMark().equals(gameMarks.bomb)){
            mapWithRole.getLand(currentPosition).setMark(mapWithoutRole.getLand(currentPosition).getMark());
            mapWithRole.getLand(HOSPITAL_LAND_NUM).setMark(gameRole.getSymbol());
            gameRole.setHospitalDays(HOSPITAL_DAYS);

            System.out.println("您路遇炸弹，被炸伤了！送入医院养伤三天");
            return true;
        }
        return false;
    }

    private boolean IsMeetBlock(GameMap mapWithRole, GameRole gameRole, int currentPosition) {
        if (mapWithRole.getLand(currentPosition).getMark().equals(gameMarks.block)){
            mapWithRole.getLand(currentPosition).setMark(gameRole.getSymbol());
            System.out.println("您遇到路障，在此停下！");
            return true;
        }
        return false;
    }

    public String getHelp(){
        return "roll           掷骰子，点数为1~6，决定前进步数";
    }
}
