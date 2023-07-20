package com.quest;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(name = "InitServlet", value = "/start")
public class InitServlet extends HttpServlet {
    public static final Logger logger = LoggerFactory.getLogger(InitServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession currentSession = req.getSession(true);
        currentSession.setAttribute("user", "empty");
        // Прогрузка текстового квеста в объекты
        Quest quest = null;
        try {
            quest = getQuest();
            logger.debug ("Текстовый квест успешно прогружен");
        } catch (Exception e) {
            logger.error("Проблемы с прогрузкой файла квеста: {}", e);
        }
        currentSession.setAttribute("quest", quest);
        currentSession.setAttribute("iter", 1);
        currentSession.setAttribute("step", 0);
        logger.debug("Инициализирующие параметры сессии установлены");
        // Перенаправление запроса на страницу index.jsp
        getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    private Quest getQuest() throws IOException, ParseException {
        JSONArray a = readFile();
        ArrayList<Step> stepArray = new ArrayList<>();
        for (Object o : a)
        {
            JSONObject step = (JSONObject) o;
            String text = (String) step.get("text");
            String last  = (String) step.get("last");
            Long id = (Long) step.get("id");
            Integer intId = id.intValue();

            JSONArray actions = (JSONArray) step.get("actions");
            Map<String,Integer> acts = new HashMap<String,Integer>();
            for (Object act : actions)
            {
                JSONObject action = (JSONObject) act;
                String descr = (String) action.get("descr");
                Long stepId = (Long) action.get("stepId");
                Integer intStepId = stepId.intValue();
                acts.put(descr,intStepId);
            }
            stepArray.add(new Step(intId, text, last, acts));
        }
        return new Quest(stepArray);
    }
    public JSONArray readFile() throws IOException, ParseException{
        JSONParser parser = new JSONParser();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream stream = loader.getResourceAsStream("/quest.json");
        BufferedReader streamReader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
        StringBuilder responseStrBuilder = new StringBuilder();
        String inputStr;
        while ((inputStr = streamReader.readLine()) != null)
            responseStrBuilder.append(inputStr);
        String s = responseStrBuilder.toString();
        stream.close();
        return (JSONArray) parser.parse(s);
    }
}
