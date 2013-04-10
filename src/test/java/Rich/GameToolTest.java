package Rich;

import org.junit.Test;
import Rich.Tool.GameTool;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;



public class GameToolTest {
    @Test
    public void should_get_name_when_query() throws Exception {
        assertThat(GameTool.BOMB.tag(),is("炸弹"));
    }

    @Test
    public void should_get_mark_when_query() throws Exception {
        assertThat(GameTool.BLOCK.mark(),is("#"));
    }

    @Test
    public void should_get_points_when_query() throws Exception {
        assertThat(GameTool.ROBOT.points(),is(30));
    }
}
