package com.quest;
import java.util.ArrayList;
import static java.util.Objects.isNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Quest {
    public static final Logger logger = LoggerFactory.getLogger(Quest.class);
    private ArrayList<Step> steps;
    public Quest(ArrayList<Step> steps) {
        if (isNull(steps)) {
            logger.error("Steps is null");
            throw new IllegalArgumentException("Steps cannot be null.");
        }
        this.steps = steps;
    }
    @Override
    public String toString() {
        return "Quest{" +
                "steps=" + steps +
                '}';
    }

    public ArrayList<Step> getSteps() {
        return steps;
    }
}
