<%@ page import="com.quest.Quest" %>
<%@ page import="com.quest.Step" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head><link href="static/style.css" rel="stylesheet"></head>
<body>
    <h2>Java Junior Dev Quest</h2>

    <% if (session.getAttribute("user") == "empty") { %>
    <p>
        <form action="/quest">
            <input type="text" id="name" name="user" placeholder = "Введи своё имя">
            <button type="submit" class="registerbtn">Начать квест</button>
        </form>
    </p>
    <% } else {%>
    <div id="steps">
        <h5><span>Шаг <%=session.getAttribute("iter") %></span></h5>
    </div>
    <%  Quest quest = (Quest) session.getAttribute("quest");
        ArrayList<Step> steps = quest.getSteps();
        Step step = steps.get(Integer.parseInt(session.getAttribute("step").toString()));
        Map<String,Integer> actions = step.getActions();
    %>
    <div id="history">
        <h4><%=step.getText() %></h4>
    </div>
    <br>
    <br>
    <form action="/quest">
    <% for (Map.Entry<String, Integer> action : actions.entrySet()) {
        int j = 1;
    %>
    <div id="actions">
        <input type="radio"
               onclick="enableSubmit()"
               id="way-<%=j%>"
               name="stepId"
               value="<%=action.getValue()%>"><%=action.getKey()%></input>
    </div>
    <br>
    <% j++;
    } %>

    <% if (step.getLast().equals("N")) { %>
        <button id="continue" type="submit" class="registerbtn" disabled="disabled">Продолжить</button>
        </form>
    <% } else {%>
        <form action="/quest">
            <input type="hidden" name="iter" value="1" />
            <button class="retrybtn" name="stepId" value="0" >Попробовать еще раз</button>
        </form>
        <br>
        <br>
        <div id="stats">
            <h7><b>Пользователь:</b> <%=session.getAttribute("user") %></h7>
            <br>
            <h7><b>Набранные баллы:</b> <%=((Integer.parseInt(session.getAttribute("iter").toString()))-1)*10 %></h7>
        </div>
    <% } %>

    <% } %>
    <script>
        function enableSubmit() {
            document.getElementById("continue").disabled = false;
        }
    </script>
</body>
</html>
