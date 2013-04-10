package Rich.Tool;


public enum GameTool {
    BLOCK(50,"路障","#",1),
    ROBOT(30,"机器娃娃","",2),
    BOMB(50,"炸弹","@",3);

    private final int points;
    private final String tag;
    private final String mark;
    private final int num;

    GameTool(int points, String tag, String mark, int num){
        this.points = points;
        this.tag = tag;
        this.mark = mark;
        this.num = num;
    }
    public int points() {
        return points;
    }
    public String tag(){
        return tag;
    }
    public String mark(){
        return mark;
    }

    public int num() {
        return num;
    }
}
