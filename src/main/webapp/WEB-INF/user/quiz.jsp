<%--@elvariable id="page" type="com.example.app.util.Page"--%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ page import="com.example.app.model.question.QuestionType" %>
<jsp:useBean id="date" class="java.util.Date"/>
<c:set var="title" value="true" />
<c:set var="isTest" value="true" />
<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
    <style>
        .table thead th{
            text-align: center;
            vertical-align: middle;
        }
        .questions>li {
            padding-left: 25px;
            text-align: center;
        }
        .questions>li::before {
            position: absolute;
            content: "";
            background-color: rgba(255,0,0,.5);
            display: block;
            border-radius: 50%;
            width: 5px;
            height: 5px;
            top: 22px;
            left: 15px;
        }

        p { color:  navy; }
        pre code {
            background-color: #eee;
            border: 1px solid #999;
            display: block;
            padding: 20px;
            margin: auto;
        }
        .container{
            margin: auto;
        }
        div.question{
            border: 1px solid #999;
            display: flow-root;
            border-radius: 5px;
            padding: 10px;
            margin: 10px 0;
        }
        input{
            margin: 5px 10px 0 20px;
            display: block;
            float: left;
        }
        label{
            display: block;
            width: 90%;
            float: left;
        }
        h3{
            margin: 0 0 10px 20px;
        }
    </style>
<body>
<navbar:navbar isTest="true"/>
<div class="container" id="assignments">
    <div class="row">
        <div class="col-md-2">
            <ul class="list-group questions" style="list-style-position: inside;">
                <%--@elvariable id="assignment" type="com.example.app.model.assignment.Assignment"--%>
                <c:forEach items="${assignment.quiz.questions}" var="question" varStatus="loopCounter">
                    <li class="list-group-item question_item" data-id="${question.id}">
                        <c:out value="${loopCounter.count}"/>
                        <fmt:message key="question" bundle="${bundle}"/>
                    </li>
                </c:forEach>
            </ul>
        </div>
        <div class="col-md-8 question_content">
        </div>
    </div>
    <div>
        <c:forEach items="${assignment.quiz.questions}" var="question" varStatus="loopCounter">
            <div style="display: none" class="question" id="question${question.id}">
                <h3><c:out value="${question.text}" escapeXml="true"/></h3>
                <hr>
                    <%--<div><pre><code></code></pre></div>--%>
                <c:forEach items="${question.choices}" var="choice">
                    <div>
                        <input class="answer" name="name_${question.id}" id="id_${choice.id}" data-id="${loopCounter.count}"
                               type="${question.type == QuestionType.Multiple ? "checkbox" : "radio"}">
                        <label for="id_${choice.id}"><c:out value="${choice.text}" escapeXml="true"/></label>
                    </div>
                </c:forEach>
            </div>
        </c:forEach>
    </div>
    <button id="completeBtn" class="btn btn-outline-success complete" data-id="${assignment.quizId}">
        <fmt:message key="complete" bundle="${bundle}"/>
    </button>
</div>
<script>
    const timer = document.getElementById("timer");
    const completeBtn = $('#completeBtn');
    let minutes = ${assignment.quiz.time};
    let seconds = 0;

    function complete(){
        const quizId = this.dataset.id;
        $.post("${pageContext.request.contextPath}/controller/user/assignments/assignment", {quizId : quizId, action : "complete"},
            function (){window.location.href="${pageContext.request.contextPath}/controller/user/assignments";});
    }

    function updateListener(){
        completeBtn.off('click').on('click', complete);
        $("li.question_item").off('click').on('click', showQuestion);
        $(".answer").off('change').on('change', saveAnswer);
    }

    function showQuestion(){
        const id = this.dataset.id
        $(".question_content").html($('div#question'+id).html());
        updateListener();
    }

    function getResult(){
        const inputs = $("div.question_content").find("input");
        let result = 0;
        $.each(inputs, function(index, element){ result += (element.checked ? 1 : 0) << index });
        return result;
    }

    function saveAnswer(){
        const id = this.dataset.id;
        const result = getResult(this);
        $.post("${pageContext.request.contextPath}/controller/user/assignments/assignment", {id : id, result : result,  action : "saveAnswer"} , function(){ console.log("+")});
    }

    updateListener();

    const updateTimer = () => {
        timer.innerHTML = (minutes < 10 ? "0" + minutes : minutes) + ":" + (seconds < 10 ? "0" + seconds : seconds);
    };


    startTimer();
    function startTimer() {
        if (--seconds === 0) {
            if (--minutes === 0) return completeBtn.click();
            seconds = 59;
        }
        updateTimer();
        setTimeout(startTimer, 1000);
    }

</script>

</body>
</html>