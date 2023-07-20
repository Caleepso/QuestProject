package com.quest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Map;
import static java.util.Objects.isNull;

public class Step {
    public static final Logger logger = LoggerFactory.getLogger(Step.class);
    private Integer id;
    private String text;
    private String last;
    private Map<String,Integer> actions;

    public Step(Integer id, String text, String last, Map<String,Integer> actions) {
        if (isNull(text)) {
            logger.error("Text is null");
            throw new IllegalArgumentException("Text cannot be null.");
        } else if (text.isBlank()) {
            logger.error("Text is blank");
            throw new IllegalArgumentException("Text cannot be blank.");
        }
        if (id < 0) {
            logger.error("Id is negative");
            throw new IllegalArgumentException("Id cannot be negative.");
        }
        if (isNull(last)) {
            logger.error("Last is null");
            throw new IllegalArgumentException("Last cannot be null.");
        } else if (last.isBlank()) {
            logger.error("Last is blank");
            throw new IllegalArgumentException("Last cannot be blank.");
        }
        if (isNull(actions)) {
            logger.error("Actions is null");
            throw new IllegalArgumentException("Actions cannot be null.");
        }

        this.id = id;
        this.text = text;
        this.last = last;
        this.actions = actions;
    }

    public Integer getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Map<String, Integer> getActions() {
        return actions;
    }

    public String getLast() {return last; }
}
