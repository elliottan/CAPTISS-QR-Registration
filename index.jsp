<html>
   <head>
      <title>Home</title>
      <link rel="stylesheet" type="text/css" href="css/custom.css" >
      <link rel="stylesheet" type="text/css" href="css/button.css" >
      <link rel="stylesheet" href="css/bootstrap.css">
      <meta charset="utf-8">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
      <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
      <link rel="shortcut icon" href="https://captlife.com/wp-content/uploads/2017/12/cropped-CAPT_Logo_Vertical-32x32.png">

      <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
      <c:if test="${!empty sessionScope.username}"><c:redirect url="admin.jsp" /></c:if>
   </head>
   <body class="maincontent">
      <nav class="navbar navbar-default">
         <div class="container-fluid">
            <div class="navbar-header">
               <a class="navbar-brand" href="index.jsp"><img src="images/captlogo.png" class = "headerlogo loginlogout"/></a>
            </div>
            <ul class="nav navbar-nav">

            </ul>
         </div>
      </nav>
      <div class="container">
         <div class="row">
         </div>
         <div class="col-md-6">
            <img src = "images/mainlogo2.png" alt="" class="mainlogo" >
         </div>
            <div class="col-md-6 rightcol">
               <form action = "Login" method = "POST" class="walkin">
                  <span class="walkinhead">CAPTISS Admin Login</span>
                  <br />
                  Username:<input type = "text" name = "username" autofocus>
                  <br />
                  Password: <input type = "password" name = "password" autocomplete="off" class="pw">
                  <br />
                  <div class="col-md-4 col-md-offset-4">
                     <a href="#" class="btn btn-sm animated-button victoria-one ">
                     <input type = "submit" value = "Submit" / class="submitbutton"></a>
                  </div>
               </form>

                  <div class="requestrow request2 loginresponse" >
                     ${requestScope["responsemessage"]}
                  </div>
            </div>

         </div>
      </div>
      </div>
   </body>
   <div class="footer">
      <p class="copyright">&copy Copyright 2018 College of Alice & Peter Tan. All Rights Reserved.</p>
   </div>
</html>
