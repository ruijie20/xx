package Rich;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Iterator;

import Rich.Command.*;
import Rich.GameRole.*;

public class Game {

    private ArrayList<GameRole> gameRolesChosen;
    private GameMarks gameMarks = new GameMarks();
    private GameMap mapWithoutRoles;
    private GameMap mapWithRoles;
    private RollCommand rollCommand;
    ArrayList<GameRole> gameRoles = new ArrayList<GameRole>();
    GameRolesBuilt gameRolesBuilt = new GameRolesBuilt();
    public static final int NOOWNER = 0;
    Scanner scanner = new Scanner(System.in);
    int fund;
    CommandBuilt commandBuilt;


    public Game(){
        gameRolesChosen = new ArrayList<GameRole>();
        mapWithoutRoles = new GameMap();
        mapWithRoles = new GameMap();
    }

    public void run(){
        System.out.println("大富翁游戏开始");

        while (true){
            try{
                initializeFund();
                break;
            }catch (Exception e){
                System.out.println(e.getMessage());
            }

        }
        gameRoles = gameRolesBuilt.initializeGameRoles();
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

        commandBuilt = new CommandBuilt();
        while (true){
            roleIter = gameRolesChosen.iterator();
            Loop: while (roleIter.hasNext()){
                GameRole role = roleIter.next();
                if (isSkipRound(role)) continue;
                mapWithRoles.getLand(role.getPosition()).setMark(role.getSymbol());
                mapWithRoles.print(gameRolesChosen);
                while (true){
                    System.out.println(role.getName() + ">");
                    String lowercaseCommand = getCommand();
                    if (lowercaseCommand.equals(rollCommand.getCommand())){
                        rollCommand.executeCommand(mapWithoutRoles,mapWithRoles,role);
                        if (isWinner(role)) return;
                        isPayFee(role);
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
                    commandBuilt.handleCommand(role, lowercaseCommand, mapWithoutRoles, mapWithRoles);
                }
            }
            if (gameRolesChosen.size() < 2){
                System.out.println("只剩一位玩家，游戏结束");
                System.out.println("赢家为" + gameRolesChosen.get(0).getName());
                return;
            }
        }
    }

    private boolean isWinner(GameRole role) {
        if (role.winner == true){
            return true;
        }
        return false;
    }



    private void isPayFee(GameRole role) {
        int position = role.getPosition();
        int owner = mapWithoutRoles.getLand(role.getPosition()).getOwner();
        for (int i = 0; i < gameRolesChosen.size(); i ++) {
            GameRole currentRole = gameRolesChosen.get(i);
            if (currentRole.getRoleNum() == owner && owner != role.getRoleNum()) {
                int currentPosition = currentRole.getPosition();
                if (isOwnerInPrison(currentPosition)) return;
                if (isOwnerInHospital(currentPosition)) return;
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
        isMascot(role);
    }

    private boolean isOwnerInHospital(int currentPosition) {
        if (mapWithoutRoles.getLand(currentPosition).equals(gameMarks.hospital)){
            System.out.println("拥有者在医院，免过路费");
            return true;
        }
        return false;
    }

    private boolean isOwnerInPrison(int currentPosition) {
        if (mapWithoutRoles.getLand(currentPosition).getMark().equals(gameMarks.prison)){
            System.out.println("拥有者在监狱，免过路费");
            return true;
        }
        return false;
    }


    private String getCommand() {
        String command = scanner.nextLine();
        return command.toLowerCase();
    }





    private boolean isSkipRound(GameRole role) {
        if (role.getHospitalDays() != 0){
            isMascot(role);
            System.out.println("你还需在医院休养" + role.getHospitalDays() + "天");
            role.setHospitalDays(role.getHospitalDays() - 1);
            return true;
        }
        if (role.getPrisonDays() != 0){
            isMascot(role);
            System.out.println("你还在监狱服刑中，还有" + role.getPrisonDays() + "天");
            role.setPrisonDays(role.getPrisonDays() - 1);
            return true;
        }
        return false;
    }


    private void isMascot(GameRole role) {
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
                 gameRolesChosen.add(gameRoles.get(roleNum.charAt(i) - 49));
                 gameRolesChosen.get(i).setFund(fund);
            }
            checkDuplicated();
        }
        catch (Exception e){
            gameRolesChosen.clear();
            throw new IllegalArgumentException("输入玩家编号错误");
        }
    }

    private void checkDuplicated() {
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


}
