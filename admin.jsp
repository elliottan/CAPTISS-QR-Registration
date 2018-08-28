<html>
   <head><title>Server</title></head>
    <META HTTP-EQUIV="Refresh" CONTENT="5">
    <body>
        <!-- Call the Admin servlet to write updates to a file -->

        <h1>IP address to connect to server is: ${ipaddress}</h1>

        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <table>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Time-In</th>
            </tr>
            <c:forEach items="${masterstearecords.values()}" var="record" varStatus="status">
                <tr>
                    <td>${masterstearecords.get(status.count.toString()).get("id")}</td>
                    <td>${masterstearecords.get(status.count.toString()).get("name")}</td>
                    <td>${masterstearecords.get(status.count.toString()).get("email")}</td>
                    <td>${registrationtime.get(masterstearecords.get(status.count.toString()).get("id"))}</td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>