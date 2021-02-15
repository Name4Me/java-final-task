<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="navbar" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="header"%>
<jsp:useBean id="date" class="java.util.Date"/>
<%@ page import="com.example.app.model.question.QuestionType" %>

<html>
<head>
    <header:header title="${msg.admin} - ${msg.quizzes}"/>

    <%--Localization--%>
    <c:if test="${sessionScope.locale == null}">
        <fmt:setLocale value="ru"/>
    </c:if>
    <c:if test="${sessionScope.locale != null}">
        <fmt:setLocale value="${sessionScope.locale}"/>
    </c:if>

    <fmt:setBundle basename="localization" var="bundle"/>
    <%----%>


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
</head>
<body>

<navbar:navbar/>

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
    function complete(){
        const quizId = this.dataset.id;
        $.post("${pageContext.request.contextPath}/user/assignments/assignment", {quizId : quizId, action : "complete"},
            function (){window.location.href="${pageContext.request.contextPath}/user/assignments";});
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
        $.post("${pageContext.request.contextPath}/user/assignments/assignment", {id : id, result : result,  action : "saveAnswer"} , function(){ console.log("+")});
    }
    updateListener();

</script>

</body>
</html>