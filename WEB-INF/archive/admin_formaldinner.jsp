<html>
   <head><title>Server</title></head>
    <!-- META HTTP-EQUIV="Refresh" CONTENT="5" -->
    <body>
        <h1>IP address to connect to server is: ${ipaddress}</h1>
        <form action = "Admin" method = "POST">
            <input type="submit" value="Refresh and backup" />
        </form>

        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <table border="3">
            <tr>
                <th>No</th>
                <th>Time-In</th>
                <th>Name</th>
                <th>House</th>
                <th>Dietary</th>
                <th>Halal</th>admin.jsp
            </tr>
            <c:forEach items="${registrationrecords.values()}" var="record" varStatus="status">
                <tr>
                    <td>${status.count}</td>
                    <td>${registrationtime.get(record.get("id"))}</td>
                    <td>${record.get("name")}</td>
                    <td>${record.get("house")}</td>
                    <td>${record.get("dietary")}</td>
                    <td>${record.get("halal")}</td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>