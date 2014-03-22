<%--
  User: Taldykin V.S.
  Date: 31.01.14
  Time: 13:38
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="/resources/stylesheets/style.css"/>
    <link rel="stylesheet" type="text/css" href="/resources/stylesheets/profilesSettings.css"/>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script src="/resources/scripts/profilesSettings.js"></script>
    <div id="#templateScript">
        <script>
            $( document ).ready(function() {
                $('#eventSelect').data('previousEventID', 1);

                $("#templateField").on('keydown keyup', function (e) {
                    adaptiveheight(this);
                });

                $("#templateField").trigger('keyup');

                $("#eventSelect").on('change', function (e) {
                    var templateText;
                    var eventID;
                    eventID = $('#eventSelect').find(":selected").val();

                    $.data(this, 'previousEventID', eventID);
                });
            })

            function adaptiveheight(a) {
                $(a).height(0);
                var scrollval = $(a)[0].scrollHeight;
                $(a).height(scrollval);
                if (parseInt(a.style.height) > $(window).height()) {
                    $(document).scrollTop(parseInt(a.style.height));
                }
            }
        </script>
    </div>
    <title>
        Profiles settings
    </title>
</head>
<body>
<h2>
    Profile settings
</h2>
<table>
    <div class="center">
    <tr>
        <td>
            <label name="profile">
                profile:
            </label>
        </td>
        <td>
            <select id="profileSelect" class="settingSelect" name="profileID">
                <c:if test="${!empty profileList}">
                    <c:forEach items="${profileList}" var="profileVar">
                        <c:choose>
                            <c:when test="${profileVar.id == currentProfile.id}">
                                <option value="${profileVar.id}" selected>${profileVar.name}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${profileVar.id}">${profileVar.name}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </c:if>
            </select>
            <a id=addLink href=<c:url value="/settings/add"/>><img
                    src="/resources/images/plus2.png"
                    alt="Add profile"
                     title="Add profile"/></a>
            <a id=deleteLink href=<c:url value="/settings/delete/${currentProfile.id}"/>><img
                    src="/resources/images/minus.png"
                    alt="Remove profile"
                     title="Remove profile"/></a>
            <a id=editLink href=<c:url value="/settings/edit/${currentProfile.id}"/>><img
                    src="/resources/images/edit.png"
                    alt="Edit profile"
                    title="Edit profile"/></a>
        </td>
        <td>

        </td>
    </tr>
    </div>
    <tr class="templateRow">
        <td>
            <label name="template">
                template:
            </label>
        </td>
        <div class="heightByContent">
            <td>
                <textarea id="templateField"
                          readonly="true"
                          class="heightByContent"
                          name="template"
                          placeholder="Type template text here">${templateText}</textarea>
            </td>
        </div>
    </tr>
</table>
<p><a href="<c:url value="/index"/>">Home page</a></p>
</body>
</html>