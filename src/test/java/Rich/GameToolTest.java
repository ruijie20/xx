package Rich;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


/**
 * Created with IntelliJ IDEA.
 * User: flocl
 * Date: 13-2-7
 * Time: 下午2:17
 * To change this template use File | Settings | File Templates.
 */
public class GameToolTest {
    @Test
    public void should_get_name_when_query() throws Exception {
        BombTool bombTool = new BombTool();
        assertThat(bombTool.getName(),is("炸弹"));
    }

    @Test
    public void should_get_mark_when_query() throws Exception {
        BlockTool blockTool = new BlockTool();
        assertThat(blockTool.getMark(),is("#"));
    }

    @Test
    public void should_get_points_when_query() throws Exception {
        RobotTool robotTool = new RobotTool();
        assertThat(robotTool.getPoints(),is(30));
    }
}
