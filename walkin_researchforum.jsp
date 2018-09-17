<html>
   <head>
      <title>Walk-In</title>
      <link rel="stylesheet" type="text/css" href="css/custom.css" >
      <link rel="stylesheet" type="text/css" href="css/button.css" >
      <link rel="stylesheet" type="text/css" href="css/walkin.css" >
      <link rel="stylesheet" href="css/bootstrap.css">
      <meta charset="utf-8">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
      <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
      <link rel="shortcut icon" href="https://captlife.com/wp-content/uploads/2017/12/cropped-CAPT_Logo_Vertical-32x32.png">

      <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
      <c:if test="${empty sessionScope.username}"><c:redirect url="index.jsp" /></c:if>
   </head>
   <body class="maincontent">
      <jsp:include page="header_navbar.jsp" />
      <div class="container">
         <div class="row">
         </div>
         <div class="col-md-6">
            <img src = "images/mainlogo2.png" alt="" class="mainlogo walkinresearch" >
            </div>
            <div class="col-md-6 rightcol">
               <form action = "WalkInResearchForum" method = "POST" class="walkin">
                  <span class="walkinhead">Walk-In Registration</span>
                  <br />
                  Salutation:
                  <select name = "title" class="select" autofocus>
                     <option value="prof">Prof</option>
                     <option value="assocprof">Assoc Prof</option>
                     <option value="dr">Dr</option>
                     <option value="mr">Mr</option>
                     <option value="mrs">Mrs</option>
                     <option value="ms">Ms</option>
                  </select>
                  <br />
                  Name:
                  <input type = "text" name = "name" autocomplete="off" autofocus class="nametext">
                  <br />
                  Organization:
                  <input type = "text" name = "org" autocomplete="off" placeholder="(e.g CAPT, RC4, YALE-NUS, ARI, FASS, etc)" class="org">
                  <br />
                  <div class="col-md-4 col-md-offset-4">
                     <a href="#" class="btn btn-sm animated-button victoria-one ">
                     <input type = "submit" value = "Submit" / class="submitbutton"></a>
                  </div>
               </form>
            </div>
            <div class="col-md-4 col-md-offset-4">
               <div class="row requestrow" >
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
