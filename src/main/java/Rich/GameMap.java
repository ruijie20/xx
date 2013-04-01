package Rich;
import org.fusesource.jansi.AnsiConsole;
import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;

import java.util.ArrayList;

public class GameMap {

    public static final int WIDTH = 29;
    public static final int HEIGHT = 8;
    public static int LAND_AMOUNT;
    private ArrayList<Land> map;
    private GameMarks gameMarks = new GameMarks();
    public static final int NOOWNER = 0;
    public static final int GOVERNMENT = 100;
    GameMap(){
        LAND_AMOUNT = 70;
        initializeMap();
    }

    private void initializeMap() {
        map = new ArrayList<Land>(LAND_AMOUNT);
        for(int i = 0; i < LAND_AMOUNT; i++){
                switch (i) {
                    case 0:
                        map.add(i, new Land(i, 0, GOVERNMENT, gameMarks.startLand));
                        break;
                    case 14:
                        map.add(i, new Land(i, 0, GOVERNMENT, gameMarks.hospital));
                        break;
                    case 28:
                        map.add(i, new Land(i, 0, GOVERNMENT, gameMarks.toolHouse));
                        break;
                    case 35:
                        map.add(i, new Land(i, 0, GOVERNMENT, gameMarks.giftHouse));
                        break;
                    case 49:
                        map.add(i, new Land(i, 0, GOVERNMENT, gameMarks.prison));
                        break;
                    case 63:
                        map.add(i, new Land(i, 0, GOVERNMENT, gameMarks.magicHouse));
                    default:
                        if ((i > 0 && i<14)||(i > 14 && i<28)){
                            map.add(i, new Land(i, 200, NOOWNER, gameMarks.blankLand));
                        }
                        if (i > 28 && i < 35){
                            map.add(i, new Land(i, 500, NOOWNER, gameMarks.blankLand));
                        }
                        if ((i > 35 && i < 49) || (i > 49 && i < 63)){
                            map.add(i, new Land(i, 300, NOOWNER, gameMarks.blankLand));
                        }
                        if(i == 64){
                            map.add(i, new Land(i, 20, GOVERNMENT, gameMarks.pit));
                        }
                        if (i == 65){
                            map.add(i, new Land(i, 80, GOVERNMENT, gameMarks.pit));
                        }
                        if (i == 66){
                            map.add(i, new Land(i, 100, GOVERNMENT, gameMarks.pit));
                        }
                        if (i == 67){
                            map.add(i, new Land(i, 40, GOVERNMENT, gameMarks.pit));
                        }
                        if (i == 68){
                            map.add(i, new Land(i, 80, GOVERNMENT, gameMarks.pit));
                        }
                        if (i == 69){
                            map.add(i, new Land(i, 60, GOVERNMENT, gameMarks.pit));
                        }
                }
        }
    }

    public void print(ArrayList<GameRole> gameRoles){
        printFirstRow(gameRoles);
        printMiddleRows(gameRoles);
        printLastRow(gameRoles);
    }

    private void printLastRow(ArrayList<GameRole> gameRoles) {
        for (int i = 0; i < WIDTH; i ++){
            int temp = LAND_AMOUNT - HEIGHT - i + 1;
            int owner = map.get(temp).getOwner();
            String mark = map.get(temp).getMark();
            printMark(gameRoles,owner,mark);
        }
        System.out.println();
    }

    private void printMiddleRows(ArrayList<GameRole> gameRoles) {
        for (int j = 1; j < HEIGHT - 1; j ++){
            int owner = map.get(LAND_AMOUNT - j).getOwner();
            String mark = map.get(LAND_AMOUNT - j).getMark();
            printMark(gameRoles, owner, mark);
            for (int i = 1; i < WIDTH - 1; i ++){
                System.out.print(" ");
            }
            owner = map.get(WIDTH + j - 1).getOwner();
            mark = map.get(WIDTH + j - 1).getMark();
            printMark(gameRoles, owner, mark);
            System.out.println();
        }
    }

    private void printFirstRow(ArrayList<GameRole> gameRoles) {
        for (int i = 0; i < WIDTH; i ++){
            int owner = map.get(i).getOwner();
            String mark = map.get(i).getMark();
            printMark(gameRoles, owner, mark);
        }
        System.out.println();
    }

    private void printMark(ArrayList<GameRole> gameRoles, int owner, String mark) {
        if (isNoOwner(owner)){
            System.out.print(mark);
        }
        else {
            String color = getOwnerColor(gameRoles, owner);
            printWithColor(mark, color);
        }
    }

    private String getOwnerColor(ArrayList<GameRole> gameRoles, int owner) {
        for (int i = 0; i < gameRoles.size(); i ++){
            if (gameRoles.get(i).getRoleNum() == owner){
                return gameRoles.get(i).getColor();
            }
        }
        return "";
    }


    private void printWithColor(String mark, String color) {
        AnsiConsole.systemInstall();
        Color ansiColor = WHITE;
        if (color == "red"){
            ansiColor= RED;
        }
        if (color == "yellow"){
            ansiColor = YELLOW;
        }
        if (color == "blue"){
            ansiColor = BLUE;
        }
        if (color == "green"){
            ansiColor = GREEN;
        }
        System.out.print(ansi().fg(ansiColor).a(mark));
        AnsiConsole.systemUninstall();
    }

    private boolean isNoOwner(int owner) {
        return owner == NOOWNER || owner == GOVERNMENT;
    }

    public Land getLand(int landNum) {
        return map.get(landNum);
    }

}
