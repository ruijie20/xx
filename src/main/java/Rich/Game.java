package Rich;


import java.util.ArrayList;
import java.util.Scanner;
import java.util.Iterator;

import Rich.Command.*;

/**
 * Created with IntelliJ IDEA.
 * User: flocl
 * Date: 13-2-10
 * Time: 下午5:02
 * To change this template use File | Settings | File Templates.
 */
public class Game {
    public static final String RED = "red";
    public static final String GREEN = "green";
    public static final String YELLOW = "yellow";
    public static final String BLUE = "blue";
    private ArrayList<GameRole> gameRoles;
    private ArrayList<GameRole> gameRolesChosen;
    private GameMarks gameMarks = new GameMarks();
    private GameMap mapWithoutRoles;
    private GameMap mapWithRoles;
    private RollCommand rollCommand;

    public static final int NOOWNER = 0;
    public static final int GOVERNMENT = 100;
    Scanner scanner = new Scanner(System.in);
    int fund;

    CommandCreater commandCreater = new CommandCreater();


    public Game(){
        gameRoles = new ArrayList<GameRole>();
        gameRolesChosen = new ArrayList<GameRole>();
        mapWithoutRoles = new GameMap();
        mapWithRoles = new GameMap();
    }

    public void run(){
        System.out.println("大富翁游戏开始");
        buildGameRoles();
        while (true){
            try{
                initializeFund();
                break;
            }catch (Exception e){
                System.out.println(e.getMessage());
            }

        }

        while (true){
            try{
                initializeGameRoles();
                break;
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }

        Iterator<GameRole> roleIter;
        rollCommand = new RollCommand();
        while (true){
            roleIter = gameRolesChosen.iterator();
            Loop: while (roleIter.hasNext()){
                GameRole role = roleIter.next();
                if (IsSkipRound(role)) continue;
                mapWithRoles.getLand(role.getPosition()).setMark(role.getSymbol());
                mapWithRoles.print(gameRolesChosen);
                while (true){
                    System.out.println(role.getName() + ">");
                    String lowercaseCommand = getCommand();
                    if (lowercaseCommand.equals(rollCommand.getCommand())){
                        rollCommand.executeCommand(mapWithoutRoles,mapWithRoles,role);
                        if (IsWinner(role)) return;
                        IsPayFee(role);
                        if (role.getFund() < 0) {
                            System.out.println(role.getName() + "已破产");
                            ArrayList<Land> lands = role.getLands();
                            for (int j = 0; j < lands.size(); j++) {
                                int landNum = lands.get(j).getLandNum();
                                mapWithoutRoles.getLand(landNum).setMark(gameMarks.blankLand);
                                mapWithoutRoles.getLand(landNum).setOwner(NOOWNER);
                                mapWithRoles.getLand(landNum).setOwner(NOOWNER);
                                mapWithRoles.getLand(landNum).setMark(gameMarks.blankLand);
                            }
                            gameRolesChosen.remove(role);
                            break Loop;
                        }
                        break;
                    }
                    commandCreater.handleCommand(role, lowercaseCommand, mapWithoutRoles, mapWithRoles);
                }
            }
            if (gameRolesChosen.size() < 2){
                System.out.println("只剩一位玩家，游戏结束");
                System.out.println("赢家为" + gameRolesChosen.get(0).getName());
                return;
            }
        }
    }

    private boolean IsWinner(GameRole role) {
        if (role.winner == true){
            return true;
        }
        return false;
    }



    private void IsPayFee(GameRole role) {
        int position = role.getPosition();
        int owner = mapWithoutRoles.getLand(role.getPosition()).getOwner();
        for (int i = 0; i < gameRolesChosen.size(); i ++) {
            GameRole currentRole = gameRolesChosen.get(i);
            if (currentRole.getRoleNum() == owner && owner != role.getRoleNum()) {
                int currentPosition = currentRole.getPosition();
                if (IsOwnerInPrison(currentPosition)) return;
                if (IsOwnerInHospital(currentPosition)) return;
                if (role.getMascotRound() != 0) {
                    role.setMascotRound(role.getMascotRound() - 1);
                    System.out.println("福神附身，免过路费!");
                    return;
                }
                Land land = mapWithoutRoles.getLand(position);
                int fee = land.getPrice() * (Integer.valueOf(land.getMark()) + 1) / 2;
                System.out.println("支付过路费" + fee + "元。");
                gameRolesChosen.get(i).setFund(gameRolesChosen.get(i).getFund() + fee);
                role.setFund(role.getFund() - fee);

            }
        }
        IsMascot(role);
    }

    private boolean IsOwnerInHospital(int currentPosition) {
        if (mapWithoutRoles.getLand(currentPosition).equals(gameMarks.hospital)){
            System.out.println("拥有者在医院，免过路费");
            return true;
        }
        return false;
    }

    private boolean IsOwnerInPrison(int currentPosition) {
        if (mapWithoutRoles.getLand(currentPosition).getMark().equals(gameMarks.prison)){
            System.out.println("拥有者在监狱，免过路费");
            return true;
        }
        return false;
    }


    private String getCommand() {
        String command = scanner.next();
        return command.toLowerCase();
    }





    private boolean IsSkipRound(GameRole role) {
        if (role.getHospitalDays() != 0){
            IsMascot(role);
            System.out.println("你还需在医院休养" + role.getHospitalDays() + "天");
            role.setHospitalDays(role.getHospitalDays() - 1);
            return true;
        }
        if (role.getPrisonDays() != 0){
            IsMascot(role);
            System.out.println("你还在监狱服刑中，还有" + role.getPrisonDays() + "天");
            role.setPrisonDays(role.getPrisonDays() - 1);
            return true;
        }
        return false;
    }


    private void IsMascot(GameRole role) {
        if (role.getMascotRound() != 0){
            role.setMascotRound(role.getMascotRound() - 1);
            System.out.println("福神附身还有" + role.getMascotRound() + "天");
        }
    }

    private void initializeGameRoles() {
        System.out.println("请选择2~4位不重复玩家，输入编号即可。(1.钱夫人; 2.阿土伯; 3.孙小美; 4.金贝贝):");
        try {
            Scanner scanner = new Scanner(System.in);
            String roleNum = scanner.nextLine();
            if (roleNum.length() < 2){
                throw new IllegalArgumentException("输入玩家编号不足，至少2名");
            }
            if (roleNum.length() > 4){
                throw new IllegalArgumentException("输入玩家编号过多，最多4名");
            }
            for (int i = 0; i < roleNum.length(); i ++){
                 //gameRolesChosen.add(gameRoles.get(Integer.valueOf(roleNum.substring(i,1)) - 1));
                 gameRolesChosen.add(gameRoles.get(roleNum.charAt(i) - 49));
                 gameRolesChosen.get(i).setFund(fund);
            }
            CheckDuplicated();
        }
        catch (Exception e){
            gameRolesChosen.clear();
            throw new IllegalArgumentException("输入玩家编号错误");
        }
    }

    private void CheckDuplicated() {
        for (int i = 0; i < gameRolesChosen.size() - 1; i ++){
            for (int j = i + 1; j < gameRolesChosen.size(); j ++){
                if (gameRolesChosen.get(i).getName().equals(gameRolesChosen.get(j).getName())){
                    throw new IllegalArgumentException("输入重复玩家编号");
                }
            }
        }
    }

    private void initializeFund() {
        System.out.println("请设置初始资金，1000~50000，默认10000");
        String fundString;
        try {
             fundString = scanner.nextLine();
            if (!fundString.equals("")){
                fund = Integer.valueOf(fundString);
                if (fund < 1000 || fund >50000){
                    throw new IllegalArgumentException("初始金钱输入错误，请重新输入：（1000~50000）");
                }
            }
            else{
                fund = 10000;
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("初始金钱输入错误，请重新输入：（1000~50000）");
        }
    }

    private void buildGameRoles() {
        gameRoles.add(new GameRole(1,"钱夫人","Q",RED));
        gameRoles.add(new GameRole(2,"阿土伯","A",BLUE));
        gameRoles.add(new GameRole(3,"孙小美","S",GREEN));
        gameRoles.add(new GameRole(4,"金贝贝","J",YELLOW));
    }
}
