package Rich;


import Rich.Tool.GameTool;

public class GameMarks {
    public String blankLand = "0";
    public String bothyLand = "1";
    public String houseLand = "2";
    public String skyscraperLand = "3";
    public String block;
    public String bomb;
    public String giftHouse = "G";
    public String magicHouse = "M";
    public String toolHouse = "T";
    public String hospital = "H";
    public String prison = "P";
    public String startLand = "S";
    public String pit = "$";

    public GameMarks() {
        this.block = GameTool.BLOCK.mark();
        this.bomb = GameTool.BOMB.mark();
    }

}
