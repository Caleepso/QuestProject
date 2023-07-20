package com.quest;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(name = "MainServlet", value = "/quest")
public class MainServlet extends HttpServlet {
    public static final Logger logger = LoggerFactory.getLogger(MainServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // получаем текущую сессию
        HttpSession currentSession = req.getSession();
        // получаем введенное пользователем имя
        if (currentSession.getAttribute("user").equals("empty")) {
            String user = getUserName(req);
            logger.debug("Квест инициировал(а) пользователь [{}]", user);
            // обновляем данные пользователя
            currentSession.setAttribute("user", user);
        } else {
            if (req.getParameter("iter") != null) {
                currentSession.setAttribute("iter", req.getParameter("iter"));
                logger.debug("[{}] - раунд завершен", currentSession.getAttribute("user"));
                logger.debug("[{}] - на шаге [1]", currentSession.getAttribute("user"));
            } else {
                currentSession.setAttribute("iter", (Integer.parseInt(currentSession.getAttribute("iter").toString()) + 1));
                logger.debug("[{}] - на шаге [{}]", currentSession.getAttribute("user"), currentSession.getAttribute("iter"));
            }
            currentSession.setAttribute("step", req.getParameter("stepId"));
        }
        resp.sendRedirect("/index.jsp");
    }
    private String getUserName(HttpServletRequest request) {
        String user = request.getParameter("user");
        return user.isEmpty() ? "Анонимус" : user;
    }
}
