import com.quest.Quest;
import com.quest.Step;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StepTest {
    Map<String, Integer> actions = Map.of(
            "a", 1,
            "c", 2
    );
    Step step = new Step(1,"text","Y", actions);
    @Test
    public void negativeId(){
        assertThrows(IllegalArgumentException.class, () -> new Step(-1,"test", "Y", actions));
    }
    @Test
    public void nullText(){
        assertThrows(IllegalArgumentException.class, () -> new Step(1,null, "Y", actions));
    }
    @Test
    public void nullLast(){
        assertThrows(IllegalArgumentException.class, () -> new Step(1,"test", null, actions));
    }
    @Test
    public void nullSteps(){
        assertThrows(IllegalArgumentException.class, () -> new Step(1,"test", "N", null));
    }
    @Test
    public void idReturn(){
        assertEquals(1, step.getId());
    }
    @Test
    public void textReturn(){
        assertEquals("text", step.getText());
    }
    @Test
    public void lastReturn(){
        assertEquals("Y", step.getLast());
    }
    @Test
    public void actionsReturn(){
        assertEquals(1, step.getActions().get("a"));
    }
}
