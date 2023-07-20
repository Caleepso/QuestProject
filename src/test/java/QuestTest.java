import com.quest.Quest;
import com.quest.Step;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class QuestTest {
    @Test
    public void nullSteps(){
        assertThrows(IllegalArgumentException.class, () -> new Quest(null));
    }
    @Test
    public void returnSteps(){
        Map<String, Integer> map = Map.of(
                "a", 1,
                "c", 2
        );
        Step step1 = new Step(1,"text","N", map);
        Step step2 = new Step(2,"text2","Y", map);
        ArrayList<Step> steps = new ArrayList<>(Arrays.asList(step1, step2));
        Quest quest = new Quest(steps);
        assertEquals("text", quest.getSteps().get(0).getText());
    }
}
