package Rich;
/**
 * Created with IntelliJ IDEA.
 * User: flocl
 * Date: 13-2-13
 * Time: 下午2:51
 * To change this template use File | Settings | File Templates.
 */
public class HelpCommand extends Command {
    public RollCommand rollCommand = new RollCommand();
    public BombCommand bombCommand = new BombCommand();
    public BlockCommand blockCommand = new BlockCommand();
    public SellCommand sellCommand = new SellCommand();
    public SellToolCommand sellToolCommand = new SellToolCommand();
    public QueryCommand queryCommand = new QueryCommand();
    public QuitCommand quitCommand = new QuitCommand();
    public RobotCommand robotCommand = new RobotCommand();
    public String help = "help";
    public HelpCommand(){
    }
    public void executeCommand(GameMap mapWithoutRole, GameMap mapWithRole,GameRole gameRole){
        System.out.println(rollCommand.getHelp());
        System.out.println(blockCommand.getHelp());
        System.out.println(bombCommand.getHelp());
        System.out.println(robotCommand.getHelp());
        System.out.println(sellCommand.getHelp());
        System.out.println(sellToolCommand.getHelp());
        System.out.println(queryCommand.getHelp());
        System.out.println(getHelp());
        System.out.println(quitCommand.getHelp());
    }
    public String getHelp(){
        return "help           查看命令帮助。";
    }
}
