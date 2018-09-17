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
      <jsp:include page="header_navbar.jsp" />
      <div class="container">
         <div class="row">
            <div class="col-md-12">
               <h1>Research Forum Registration List</h1>
               <form action = "Admin" method = "POST">
                  <input type="hidden" name="jsppage" value="/admin_researchforum.jsp">
                  <a href="#" class="btn btn-sm animated-button victoria-one ">
                  <input type = "submit" value = "Refresh and backup" class="submitbutton" /></a>
               </form>

               <!-- Filter by names -->
               <input class="w3-input w3-border w3-padding" type="text" placeholder="Search for name..." id="nameFilterInput" onkeyup="sortTableByName()">
               <br />

               <table border="3" class="admintable" id="myTable">
                  <tr>
                     <th onclick="w3.sortHTML('#myTable','.item', 'td:nth-child(1)')" style="cursor:pointer">No</th>
                     <th onclick="w3.sortHTML('#myTable','.item', 'td:nth-child(2)')" style="cursor:pointer">Title</th>
                     <th onclick="w3.sortHTML('#myTable','.item', 'td:nth-child(3)')" style="cursor:pointer">Name</th>
                     <th onclick="w3.sortHTML('#myTable','.item', 'td:nth-child(4)')" style="cursor:pointer">Organization</th>
                     <th onclick="w3.sortHTML('#myTable','.item', 'td:nth-child(5)')" style="cursor:pointer">Email</th>
                     <th onclick="w3.sortHTML('#myTable','.item', 'td:nth-child(6)')" style="cursor:pointer">Lunch</th>
                     <th onclick="w3.sortHTML('#myTable','.item', 'td:nth-child(7)')" style="cursor:pointer">Afternoon Tea</th>
                     <th onclick="w3.sortHTML('#myTable','.item', 'td:nth-child(8)')" style="cursor:pointer">Time-In</th>
                  </tr>
                  <c:forEach items="${registrationrecords.values()}" var="record" varStatus="status">
                     <tr class="item">
                        <td>${record.get("id")}</td>
                        <td>${record.get("title")}</td>
                        <td>${record.get("name")}</td>
                        <td>${record.get("org")}</td>
                        <td>${record.get("email")}</td>
                        <td>${record.get("lunch")}</td>
                        <td>${record.get("tea")}</td>
                        <!-- <td>${registrationtime.get(record.get("id"))}</td> -->
                        <td>
                            <c:choose>
                                <c:when test="${empty registrationtime.get(record.get(\"id\"))}">
                                    <form action = "CheckInResearchForum" method = "POST">
                                        <a href="#" class="btn">
                                            <input type="text" name="qrcode" style="display:none" value="${record.get('id')}" />
                                            <input type="submit" value="Manual" class="adminbutton" />
                                        </a>
                                    </form>
                                </c:when>
                                <c:otherwise>${registrationtime.get(record.get("id"))}</c:otherwise>
                            </c:choose>
                        </td>
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
