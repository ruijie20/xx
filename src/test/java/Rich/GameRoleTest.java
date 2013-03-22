package Rich;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;


/**
 * Created with IntelliJ IDEA.
 * User: flocl
 * Date: 13-2-6
 * Time: 下午4:08
 * To change this template use File | Settings | File Templates.
 */
public class GameRoleTest {
    GameRole ATuBo = new GameRole(1,"ATuBo","A","Blue");

    @Test
    public void should_get_points_when_start_game() throws Exception {
        assertThat(ATuBo.getPoints(),is(0));

    }


}
