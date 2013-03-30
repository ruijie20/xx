package Rich;

import Rich.Tool.GameTool;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: flocl
 * Date: 13-2-6
 * Time: 下午4:14
 * To change this template use File | Settings | File Templates.
 */
public class GameRole {
    public static final int initializeFund = 10000;
    public static final int initializePoints = 0;
    public static final int initializePosition = 0;
    private static final int initializePrisonDays = 0;
    private static final int initializeHospitalDays = 0;
    private static final int initializeMascot = 0;
    public static final int BLOCK_NUM = 1;
    public static final int ROBOT_NUM = 2;
    public static final int BOMB_NUM = 3;
    public boolean winner = false;
    private String name;
    private int roleNum;
    private int fund = initializeFund;
    private String symbol;
    private String color;
    private int points = initializePoints;
    private int position = initializePosition;
    private int hospitalDays = initializeHospitalDays;
    private int prisonDays = initializePrisonDays;
    private int mascotRound = initializeMascot;
    private ArrayList<Land> lands;
    private ArrayList<GameTool> tools;
    private GameMarks gameMarks = new GameMarks();

    public GameRole(){
        lands = new ArrayList<Land>();
        tools = new ArrayList<GameTool>();
    }

    public GameRole(int roleNum, String name, String symbol, String color) {
        this.roleNum = roleNum;
        this.name = name;
        this.symbol = symbol;
        this.color = color;
        lands = new ArrayList<Land>();
        tools = new ArrayList<GameTool>();
    }

    public String getColor() {
        return color;
    }

    public int getFund() {
        return fund;
    }


    public String getName() {
        return name;
    }

    public int getRoleNum() {
        return roleNum;
    }

    public String getSymbol() {
        return symbol;
    }

    public int getBlankLandAmount() {
        if (CheckHasNoLands()) return 0;
        return CountLandAmount(gameMarks.blankLand);
    }

    public String getBlankLandNum(){
        return getLandNum(gameMarks.blankLand);
    }

    public String getBothyLandNum(){
        return getLandNum(gameMarks.bothyLand);
    }

    public String getHouseLandNum(){
        return getLandNum(gameMarks.houseLand);
    }

    public String getSkyscraperLandNum(){
        return getLandNum(gameMarks.skyscraperLand);
    }

    private String getLandNum(String landMark) {
        String blankLandNum = "";
        for (int i = 0; i < lands.size(); i ++){
            if (CheckLandLevel(i, landMark)){
                blankLandNum += Integer.toString(lands.get(i).getLandNum()) + ".";
            }
        }
        return blankLandNum;
    }

    public int getBothyAmount() {
        if (CheckHasNoLands()) return 0;
        return CountLandAmount(gameMarks.bothyLand);
    }

    public int getHouseLandAmount(){
        if (CheckHasNoLands()) return 0;
        return CountLandAmount(gameMarks.houseLand);
    }

    public int getSkyscraperLandAmount(){
        if (CheckHasNoLands()) return 0;
        return CountLandAmount(gameMarks.skyscraperLand);
    }

    private int CountLandAmount(String landMark) {
        int num = 0;
        for (int i = 0; i < lands.size(); i ++){
            if (CheckLandLevel(i, landMark)){
                num ++;
            }
        }
        return num;
    }

    private boolean CheckLandLevel(int i, String landMark) {
        return lands.get(i).getMark().equals(landMark);
    }

    private boolean CheckHasNoLands() {
        return lands == null;
    }

    public int getPosition() {
        return position;
    }

    public int getMascotRound() {
        return mascotRound;
    }

    public ArrayList<Land> getLands() {
        return lands;
    }
    public ArrayList<GameTool> getTools(){
        return tools;
    }

    public void setFund(int fund) {
        this.fund = fund;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setHospitalDays(int hospitalDays) {
        this.hospitalDays = hospitalDays;
    }

    public int getHospitalDays() {
        return hospitalDays;
    }

    public void setPrisonDays(int prisonDays) {
        this.prisonDays = prisonDays;
    }

    public int getPrisonDays() {
        return prisonDays;
    }

    public void setMascotRound(int mascotRound) {
        this.mascotRound = mascotRound;
    }


    public int getBlockToolAmount(){
        if (CheckHasNoTools()) return 0;
        return CountToolAmount(BLOCK_NUM);
    }

    public int getRobotToolAmount(){
        if (CheckHasNoTools()) return 0;
        return CountToolAmount(ROBOT_NUM);

    }

    public int getBombToolAmount(){
        if (CheckHasNoTools()) return 0;
        return CountToolAmount(BOMB_NUM);
    }

    private int CountToolAmount(int toolNum) {
        int num = 0;
        for (int i = 0; i < tools.size(); i ++){
             if (tools.get(i).getNum() == toolNum){
                 num ++;
             }
        }
        return num;
    }

    private boolean CheckHasNoTools() {
        if (tools == null){
            return true;
        }
        return false;
    }
}
