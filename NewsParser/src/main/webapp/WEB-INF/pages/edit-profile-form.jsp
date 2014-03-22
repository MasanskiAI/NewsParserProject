<%--
  User: Taldykin V.S.
  Date: 16.03.14
  Time: 22:51
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Edit profile</title>
    <link rel="stylesheet" type="text/css" href="/resources/stylesheets/style.css"/>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <div id="#templateScript">
        <script>
            $( document ).ready(function() {
                $("#templateArea").on('keydown keyup', function (e) {
                    adaptiveheight(this);
                });

                $("#templateArea").trigger('keyup');
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
</head>
<body>
<h1>Edit profile</h1>
<p>Here you can edit profile.</p>
<form:form method="POST" commandName="profile" action="/settings/edit/${profile.id}">
    <table>
        <tbody>
        <tr>
            <td>Name:</td>
            <td><form:input path="name" /></td>
        </tr>
        <tr>
            <td>Template:</td>
            <td><form:textarea id="templateArea" path="text" /></td>
        </tr>
        <tr>
            <td><input type="submit" value="Edit" /></td>
            <td></td>
        </tr>
        </tbody>
    </table>
</form:form>
<p><a href="<c:url value="/settings/"/>">Back to profile settings</a></p>
</body>
</html>