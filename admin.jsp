<html>
   <head>
      <title>Admin</title>
      <link rel="stylesheet" type="text/css" href="css/custom.css" >
      <link rel="stylesheet" type="text/css" href="css/button.css" >
      <link rel="stylesheet" type="text/css" href="css/admin.css" >
      <link rel="stylesheet" href="css/bootstrap.css">
      <link rel="stylesheet" href="css/w3.css">
      <meta charset="utf-8">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
      <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
      <script src="js/w3.js"></script>
      <script src="js/tableFilter.js"></script>
      <link rel="shortcut icon" href="https://captlife.com/wp-content/uploads/2017/12/cropped-CAPT_Logo_Vertical-32x32.png">

      <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
      <c:if test="${empty sessionScope.username}"><c:redirect url="index.jsp" /></c:if>
   </head>
   <body class="maincontent">
      <nav class="navbar navbar-default">
         <div class="container-fluid">
            <div class="navbar-header">
               <a class="navbar-brand" href="index.jsp"><img src="images/captlogo.png" class = "headerlogo"/></a>
            </div>
            <ul class="nav navbar-nav">
               <!-- <li><a href="index.jsp">Home</a></li>
               <li><a href="captiss.jsp">CAPTISS</a></li>
               <li><a href="checkin.jsp">Register</a></li>
               <li><a href="walkin.jsp">WalkIn</a></li>
               <li><a href="admin.jsp">Admin</a></li>
               <li><a href="Logout">Logout</a></li> -->
               <jsp:include page="header.jsp" />
            </ul>
         </div>
      </nav>
      <div class="container">
         <div class="row">
            <div class="col-md-12">
               <h1>IP address to connect to server is: ${ipaddress}</h1>
               <form action = "Admin" method = "POST">
                  <a href="#" class="btn btn-sm animated-button victoria-one ">
                  <input type = "submit" value = "Refresh and backup" / class="submitbutton"></a>
               </form>

               <!-- Filter by names -->
               <input class="w3-input w3-border w3-padding" type="text" placeholder="Search for name..." id="nameFilterInput" onkeyup="sortTableByName()">

               <table border="3" class="admintable" id="myTable">
                  <tr>
                     <th onclick="w3.sortHTML('#myTable','.item', 'td:nth-child(1)')" style="cursor:pointer">No</th>
                     <th onclick="w3.sortHTML('#myTable','.item', 'td:nth-child(2)')" style="cursor:pointer">Name</th>
                     <th onclick="w3.sortHTML('#myTable','.item', 'td:nth-child(3)')" style="cursor:pointer">Email</th>
                     <th onclick="w3.sortHTML('#myTable','.item', 'td:nth-child(4)')" style="cursor:pointer">Time-In</th>
                  </tr>
                  <c:forEach items="${registrationrecords_masterstea.values()}" var="record" varStatus="status">
                     <tr class="item">
                        <td>${record.get("id")}</td>
                        <td>${record.get("name")}</td>
                        <td>${record.get("email")}</td>
                        <td>${registrationtime_masterstea.get(record.get("id"))}</td>
                     </tr>
                  </c:forEach>
               </table>
            </div>
         </div>
      </div>
   </body>
   <div class="footer">
      <p class="copyright">&copy Copyright 2018 College of Alice & Peter Tan. All Rights Reserved.</p>
   </div>
</html>
