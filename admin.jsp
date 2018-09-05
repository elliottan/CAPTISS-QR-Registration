<html>
   <head><title>Server</title></head>
    <!-- META HTTP-EQUIV="Refresh" CONTENT="5" -->
    <body>
        <h1>IP address to connect to server is: ${ipaddress}</h1>
        <form action = "Admin" method = "POST">
            <input type="submit" value="Refresh and backup" />
        </form>
        <!-- <form action = "Email" method = "POST">
            <input type="submit" value="Send emails" />
        </form> -->
        <form action = "MastersTea" method = "GET">
            <input type="submit" value="Masters Tea Registration" />
        </form>
        <form action = "WalkIn" method = "GET">
            <input type="submit" value="Walk-In Registration" />
        </form>
        <form action = "Logout" method = "POST">
            <input type="submit" value="Logout" />
        </form>

        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <table border="3">
            <tr>
                <th>No</th>
                <th>Name</th>
                <th>Email</th>
                <th>Time-In</th>
            </tr>
            <c:forEach items="${registrationrecords.values()}" var="record" varStatus="status">
                <tr>
                    <td>${record.get("id")}</td>
                    <td>${record.get("name")}</td>
                    <td>${record.get("email")}</td>
                    <td>${registrationtime.get(record.get("id"))}</td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>