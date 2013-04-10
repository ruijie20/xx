package Rich.GameRole;

import Rich.GameMarks;
import Rich.Land;
import Rich.Tool.GameTool;

import java.util.ArrayList;


public class GameRole {
    public static final int initializeFund = 10000;
    public static final int initializePoints = 100;
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
        if (checkHasNoLands()) return 0;
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
            if (checkLandLevel(i, landMark)){
                blankLandNum += Integer.toString(lands.get(i).getLandNum()) + ".";
            }
        }
        return blankLandNum;
    }

    public int getBothyAmount() {
        if (checkHasNoLands()) return 0;
        return CountLandAmount(gameMarks.bothyLand);
    }

    public int getHouseLandAmount(){
        if (checkHasNoLands()) return 0;
        return CountLandAmount(gameMarks.houseLand);
    }

    public int getSkyscraperLandAmount(){
        if (checkHasNoLands()) return 0;
        return CountLandAmount(gameMarks.skyscraperLand);
    }

    private int CountLandAmount(String landMark) {
        int num = 0;
        for (int i = 0; i < lands.size(); i ++){
            if (checkLandLevel(i, landMark)){
                num ++;
            }
        }
        return num;
    }

    private boolean checkLandLevel(int i, String landMark) {
        return lands.get(i).getMark().equals(landMark);
    }

    private boolean checkHasNoLands() {
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
        if (checkNoTools()) return 0;
        return countToolAmount(BLOCK_NUM);
    }

    public int getRobotToolAmount(){
        if (checkNoTools()) return 0;
        return countToolAmount(ROBOT_NUM);

    }

    public int getBombToolAmount(){
        if (checkNoTools()) return 0;
        return countToolAmount(BOMB_NUM);
    }

    private int countToolAmount(int toolNum) {
        int num = 0;
        for (int i = 0; i < tools.size(); i ++){
             if (tools.get(i).num() == toolNum){
                 num ++;
             }
        }
        return num;
    }

    private boolean checkNoTools() {
        if (tools == null){
            return true;
        }
        return false;
    }
}
