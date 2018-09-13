<html>
  <head>
    <title>CAPTISS Registration</title>
    <link rel="stylesheet" type="text/css" href="css/custom.css" >
      <link rel="stylesheet" type="text/css" href="css/button.css" >
        <link rel="stylesheet" type="text/css" href="css/captiss.css" >


      <link rel="stylesheet" href="css/bootstrap.css">
        <meta charset="utf-8">
          <meta name="viewport" content="width=device-width, initial-scale=1">
            <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
              <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
              <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
              <link rel="shortcut icon" href="https://captlife.com/wp-content/uploads/2017/12/cropped-CAPT_Logo_Vertical-32x32.png">
                <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Tangerine">
                </head>

                <body class="maincontent">
                  <nav class="navbar navbar-default">
                    <div class="container-fluid">
                      <div class="navbar-header">
                        <a class="navbar-brand" href="index.jsp"><img src="images/captisslogo.png" class = "headerlogo"/></a>
                      </div>
                      <ul class="nav navbar-nav">
                        <!-- <li><a href="captiss.jsp">CAPTISS</a></li>
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
                        <div class="col-md-4"></div>
                        <div class="col-md-6 qrstart">

                        <form action = "CheckIn" method = "POST" class="QR">
                          <p class="QRCODE">QR Code.</p> <input type = "text" name = "qrcode" autofocus autocomplete="off">
                          <input type = "submit" value = "Submit" / class="hiddenbutton">
                        </div>
                        <div class="row requestrow" >
                          ${requestScope["responsemessage"]}
                        </div>
                      </div>
                    </div>
                    </div>
                  </div>

                </body>
                <%-- <div class="footer">
                  <p class="copyright">&copy Copyright 2018 College of Alice & Peter Tan. All Rights Reserved.</p>
                </div> --%>
            </html>
