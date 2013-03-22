package Rich;

import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: flocl
 * Date: 13-2-6
 * Time: 下午2:23
 * To change this template use File | Settings | File Templates.
 */
public class GameMapTest {

    GameMap gameMap = new GameMap();

    @Test
    public void should_get_J_when_reset_owner() throws Exception {
        GameRole jinBeibei = new GameRole(4, "JinBeibei","J","Yellow");
        gameMap.getLand(13).setOwner(4);
        assertThat(gameMap.getLand(13).getOwner(),is(4));
    }

    @Test
    public void should_print_owner_color_when_belongs_owner() throws Exception {
        ArrayList<GameRole> gameRoles;
        gameRoles = new ArrayList<GameRole> (1);
        gameRoles.add(0,new GameRole(1, "JinBeibei", "J","Yellow"));
        gameMap.getLand(2).setOwner(1);
        gameMap.getLand(7).setMark(gameRoles.get(0).getSymbol());
        gameMap.print(gameRoles);
    }
}
