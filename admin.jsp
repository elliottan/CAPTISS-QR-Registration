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
               <h1>CAPTISS Registration List</h1>
               <form action = "Admin" method = "POST">
                  <input type="hidden" name="jsppage" value="/admin.jsp">
                  <a href="#" class="btn btn-sm animated-button victoria-one ">
                  <input type = "submit" value = "Refresh and backup" class="submitbutton" /></a>
               </form>
               <c:choose>
                   <c:when test="${showabsentonly == 'true'}">
                       <form action = "Admin" method = "POST">
                          <input type="hidden" name="jsppage" value="/admin.jsp">
                          <input type="hidden" name="showabsentonly" value="false">
                          <a href="#" class="btn btn-sm animated-button victoria-one ">
                          <input type = "submit" value = "Show All" class="submitbutton submitbutton2" /></a>
                       </form>
                   </c:when>
                   <c:otherwise>
                       <form action = "Admin" method = "POST">
                          <input type="hidden" name="jsppage" value="/admin.jsp">
                          <input type="hidden" name="showabsentonly" value="true">
                          <a href="#" class="btn btn-sm animated-button victoria-one ">
                          <input type = "submit" value = "Show Absent" class="submitbutton submitbutton2" /></a>
                       </form>
                  </c:otherwise>
               </c:choose>

               <!-- Filter by names -->
               <input class="w3-input w3-border w3-padding" type="text" placeholder="Search for name..." id="nameFilterInput" onkeyup="sortTableByName(2, 'nameFilterInput')">
               <br />

               <table border="3" class="admintable" id="myTable">
                  <tr>
                     <th onclick="w3.sortHTML('#myTable','.item', 'td:nth-child(1)')" style="cursor:pointer">#</th>
                     <th>QR</th>
                     <th onclick="w3.sortHTML('#myTable','.item', 'td:nth-child(3)')" style="cursor:pointer">Name</th>
                     <th>Organization</th>
                     <th onclick="w3.sortHTML('#myTable','.item', 'td:nth-child(5)')" style="cursor:pointer">Category</th>
                     <th onclick="w3.sortHTML('#myTable','.item', 'td:nth-child(5)')" style="cursor:pointer">P1</th>
                     <th onclick="w3.sortHTML('#myTable','.item', 'td:nth-child(6)')" style="cursor:pointer">P2</th>
                     <th onclick="w3.sortHTML('#myTable','.item', 'td:nth-child(7)')" style="cursor:pointer">P3</th>
                     <th onclick="w3.sortHTML('#myTable','.item', 'td:nth-child(8)')" style="cursor:pointer">P4</th>
                     <th>Time-In</th>
                     <th>Print</th>
                  </tr>
                  <c:forEach items="${registrationrecords.values()}" var="record" varStatus="status">
                     <c:if test="${showabsentonly == 'false' || empty registrationtime.get(record.get(\"id\"))}">
                         <tr class="item">
                            <td>${status.index + 1}</td>
                            <td>${record.get("id")}</td>
                            <td>${record.get("name")}</td>
                            <td>${record.get("org")}</td>
                            <td>${record.get("cat")}</td>
                            <td>${record.get("p1")}</td>
                            <td>${record.get("p2")}</td>
                            <td>${record.get("p3")}</td>
                            <td>${record.get("p4")}</td>
                            <!-- <td>${registrationtime.get(record.get("id"))}</td> -->
                            <td>
                                <c:choose>
                                    <c:when test="${empty registrationtime.get(record.get(\"id\"))}">
                                        <form action = "CheckIn" method = "POST">
                                            <a href="#" class="btn">
                                                <input type="text" name="qrcode" style="display:none" value="${record.get('id')}" />
                                                <input type="submit" value="Manual" class="adminbutton" />
                                            </a>
                                        </form>
                                    </c:when>
                                    <c:otherwise>${registrationtime.get(record.get("id"))}</c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${empty registrationtime.get(record.get(\"id\"))}">-</c:when>
                                    <c:otherwise>
                                        <form action = "CheckIn" method = "POST">
                                            <a href="#" class="btn">
                                                <input type="text" name="qrcode" style="display:none" value="${record.get('id')}" />
                                                <input type="text" name="print" style="display:none" value="print" />
                                                <input type="submit" value="Print" class="adminbutton" />
                                            </a>
                                        </form>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                         </tr>
                     </c:if>
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
