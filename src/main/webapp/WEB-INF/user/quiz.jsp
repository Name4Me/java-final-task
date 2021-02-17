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
        button.add{
            float: right;
        }
        .new-quiz{
            margin: 10px 0;
            position: relative;
        }
        .loader{
            position: absolute;
            height: 100%;
            width: 100%;
            text-align: center;
            padding-top: 3px;
        }
        .disabled {
            pointer-events: none;
            opacity: 0.4;
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

<!-- Page Content -->

<div class="container" id="assignments">
    <div class="row">
        <div class="col-md-2">
            <ul class="list-group questions" style="list-style-position: inside;">
                <c:forEach items="${quiz.quiz.questions}" var="question" varStatus="loopCounter">
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
        <c:forEach items="${quiz.quiz.questions}" var="question" varStatus="loopCounter">
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
    <button class="btn btn-outline-success complete" data-id="${quiz.quizId}"> <fmt:message key="complete" bundle="${bundle}"/> </button>
</div>
<!-- /.container -->


<script>
    let hours = Math.floor(('${quiz.quiz.time}'*1)/60);
    let minutes = Math.floor(('${quiz.quiz.time}'*1)%60);
    console.log("hours: "+hours);
    console.log("minutes: "+minutes);
    function complete(){
        const quizId = this.dataset.id;
        $.post("${pageContext.request.contextPath}/controller/user/assignments/assignment", {quizId : quizId, action : "complete"},
            function (){window.location.href="${pageContext.request.contextPath}/controller/user/assignments";});
    }
    //search.onkeyup = event => event.key === 'Enter' && getArticles();
    function updateListener(){
        $(".complete").off('click').on('click', complete);
        $("li.question_item").off('click').on('click', showQuestion);
        $(".answer").off('change').on('change', saveAnswer);
    }
    function showQuestion(){
        const id = this.dataset.id
        $(".question_content").html($('div#question'+id).html());
        updateListener();
    }

    function getResult(e){
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
    document.getElementById("timer").innerHTML = (hours.toString().length === 1 ? "0" + hours: hours) + ":" + minutes + ":00";
    startTimer();
    function startTimer() {
        var timer = document.getElementById("timer");
        var time = timer.innerHTML;
        var arr = time.split(":");
        var hh = arr[0];
        var mm = arr[1];
        var ss = arr[2];
        if (ss == 0) {
            if (mm == 0) {
                if (hh == 0) {
                    alert("Время вышло");
                    window.location.reload();
                    return;
                }
                hh--;
                mm = 60;
                if (hh < 10)
                    hh = "0" + hh;
            }
            mm--;
            if (mm < 10)
                mm = "0" + mm;
            ss = 59;
        } else ss--;
        if (ss < 10)
            ss = "0" + ss;
        document.getElementById("timer").innerHTML = hh+":"+mm+":"+ss;
        setTimeout(startTimer, 1000);
    }

</script>

</body>
</html>