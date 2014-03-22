<%--
  User: Taldykin V.S.
  Date: 16.03.14
  Time: 22:17
--%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Add profile</title>
    <link rel="stylesheet" type="text/css" href="/resources/stylesheets/style.css"/>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <div id="#templateScript">
        <script>
            $( document ).ready(function() {

                $("#templateArea").trigger('keyup');

                $("#templateArea").on('keydown keyup', function (e) {
                    adaptiveheight(this);
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
</head>
<body>
<h1>Add profile</h1>
<p>Here you can add a new profile.</p>
<form:form method="POST" commandName="profile" action="/settings/add">
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
            <td><input type="submit" value="Add" /></td>
            <td></td>
        </tr>
        </tbody>
    </table>
</form:form>
<p><a href="<c:url value="/settings/"/>">Back to profile settings</a></p>
</body>
</html>