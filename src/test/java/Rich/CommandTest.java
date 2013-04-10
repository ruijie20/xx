package Rich;

import Rich.Command.HelpCommand;
import Rich.Command.QueryCommand;
import Rich.Command.RobotCommand;
import Rich.Command.RollCommand;
import Rich.GameRole.GameRole;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;


public class CommandTest {

    GameMarks gameMarks = new GameMarks();
    GameMap mapWithoutRole = new GameMap();
    GameMap mapWithRole = new GameMap();




    @Test
    public void should_between_0_and_7_when_use_roll() throws Exception {
        assertTrue(( (int) (Math.random()*5+1)<7)&&( (int) (Math.random()*5+1)>0));
        //assertThat((int)(Math.random()*5+1),is(6));
    }

    @Test
    public void should_without_tool_when_use_robot() throws Exception {
        GameRole ATuBo = new GameRole(1,"ATuBo","A","Blue");
        mapWithoutRole.getLand(7).setMark(gameMarks.block);
        RobotCommand robotCommand = new RobotCommand();
        robotCommand.executeCommand(mapWithoutRole,mapWithRole,ATuBo);
        assertThat(mapWithRole.getLand(7).getMark(),is(gameMarks.blankLand));

    }

    @Test
    public void should_get_help_info_when_ues_getHelp() throws Exception {
        RollCommand rollCommand = new RollCommand();
        System.out.println(rollCommand.getHelp());
    }



    @Test
    public void test_query_command() throws Exception {
        QueryCommand queryCommand = new QueryCommand();
        GameRole ATuBo = new GameRole(1,"ATuBo","A","Blue");
        queryCommand.executeCommand(mapWithoutRole,mapWithRole,ATuBo);
    }

    @Test
    public void test_help_command() throws Exception {
        GameRole ATuBo = new GameRole(1,"ATuBo","A","Blue");
        HelpCommand helpCommand = new HelpCommand();
        helpCommand.executeCommand(mapWithoutRole,mapWithRole,ATuBo);

    }

    @Test
    public void test_String_subString() throws Exception {
        String s = "1Hello World";
        int i = Integer.valueOf(s.substring(0,1));
        System.out.println(i);
        assertThat(i,is(1));

    }


}
