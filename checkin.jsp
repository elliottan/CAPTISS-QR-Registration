<html>
   <head>
      <title>Registration</title>
      <link rel="stylesheet" type="text/css" href="css/custom.css" >
      <link rel="stylesheet" type="text/css" href="css/button.css" >
      <link rel="stylesheet" href="css/bootstrap.css">
      <meta charset="utf-8">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
      <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
      <link rel="shortcut icon" href="https://captlife.com/wp-content/uploads/2017/12/cropped-CAPT_Logo_Vertical-32x32.png">
      <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Tangerine">

      <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
      <c:if test="${empty sessionScope.username}"><c:redirect url="index.jsp" /></c:if>
   </head>
   <body class="maincontent">
      <jsp:include page="header_navbar.jsp" />
      <div class="container">
         <div class="row">
         </div>
         <div class="col-md-4 col-md-offset-4">
            <img src = "images/mainlogo2.png" alt="" class="mainlogo walkinresearch" >
         </div>
      </div>
      <div class="container">
         <div class="row">
            <form action = "CheckIn" method = "POST" class="QR">
            <input type="hidden" name="jsppage" value="/checkin.jsp">
            <p class="QRCODE">Please Scan Your QR Code</p>
            <input type = "text" name = "qrcode" autofocus autocomplete="off">
            <input type = "submit" value = "Submit" class="hiddenbutton" />
         </div>
         <div class="row requestrow" >
            ${requestScope["responsemessage"]}
         </div>
      </div>
      </div>
      </div>
   </body>
   <div class="footer">
      <p class="copyright">&copy Copyright 2018 College of Alice & Peter Tan. All Rights Reserved.</p>
   </div>
</html>
