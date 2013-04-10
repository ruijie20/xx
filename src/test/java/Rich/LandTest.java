package Rich;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class LandTest {
    Land land = new Land(14,100,0,"H");
    @Test
    public void should_get_game_when_game_start() throws Exception {
        assertThat(land.getOwner(), is(0));
    }

    @Test
    public void should_get_H_when_get_hospital() throws Exception {
        assertThat(land.getMark(), is("H"));
    }
}
