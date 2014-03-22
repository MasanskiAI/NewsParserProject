<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>
    <link rel="stylesheet" type="text/css" href="/resources/stylesheets/style.css"/>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script src="/resources/scripts/search.js"></script>
    <script src="/resources/scripts/speak/speakClient.js"></script>
    <%--<script src="/resources/scripts/speak/speakGenerator.js"></script>
    <script src="/resources/scripts/speak/speakWorker.js"></script>--%>
    <script>
        $(document).ready(function() {
            //speak('hello world', { pitch: 20 });
            speak($("#outputTextArea").val(), { speed: 150 , pitch: 50, wordgap: 1 });
        });
    </script>
</head>
<body>
//todo
//добавить настройки для событий
//использовать документ в качестве шаблона
//добавить все токены из документа(сложные и составные)
<div id="wholePanel">
    <div id="workingPanel">
        <div class="center">
            <h2>News parser</h2>
        </div>
        <div id="mainPanel">
            <form:form method="get" action="search">

                <br/>
                <div id="profileInfo" class="center">
                    Profile:
                    <c:if test="${!empty profileList}">
                        <select name="profileID">
                            <c:forEach items="${profileList}" var="profileVar">
                                <c:choose>
                                    <c:when test="${profileVar.id == selectedProfileID}">
                                        <option value="${profileVar.id}" selected>${profileVar.name}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${profileVar.id}">${profileVar.name}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </c:if>
                    <a href=<c:url value="/settings/"/>>
                        <img src="/resources/images/cogwheel2.png" alt="Go to profile settings"
                             title="Go to profile settings"/>
                    </a>
                </div>
                <br/>
                <div class="center">
                        <button id="voiceSearchButton" name="voiceSearch">
                            Get report!
                        </button>
                </div>
                <br>
            </form:form>
        </div>
        <div class="center">
            <textarea id="outputTextArea" class="heightByContent" readonly>  ${resultText} </textarea>
            <div id="audio"></div>
        </div>
    </div>
</div>
</body>
</html>