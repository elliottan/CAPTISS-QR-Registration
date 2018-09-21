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
                   <jsp:include page="header_navbar.jsp" />

                    <div class="container">

                      <div class="row">
                        <img src="images/captisslogo.jpg" class="captisslogo">
                        <div class="col-md-3"></div>
                        <div class="col-md-6 qrstart">

                        <form action = "CheckIn" method = "POST" class="QR">
                          <input type="hidden" name="jsppage" value="/captiss.jsp">
                          <p class="QRCODE">QR Code.</p> <input type = "text" name = "qrcode" autofocus autocomplete="off">
                          <input type = "submit" value = "Submit" / class="hiddenbutton">
                        </div>
                      </div>
                      <div class= "container">
                        <div class="row requestrow" >
                          <div class="col-md-8">
                          ${requestScope["responsemessage"]}
                        </div>
                        </div>
                      </div>
                    </div>
                    </div>
                    </div>
                  </div>

                </body>
                <%-- <div class="footer">
                  <p class="copyright">&copy Copyright 2018 College of Alice & Peter Tan. All Rights Reserved.</p>
                </div> --%>
<style>
@media all and (max-width: 600px){
  .requestrow{
    margin-top:10% !important;
  }
  .captisslogo{
    width:70%;
    margin-left:15%;
    margin-top:10%;
    margin-bottom:-10%;
  }
}
@media only screen and (min-device-width : 768px) and (max-device-width : 1024px)  {
  .captisslogo{
    width:70% !important;
    margin-left:15% !important;
    margin-top:10%;
  }
  .requestrow{
    font-size:44px !important;
    margin-top:10%;
  }
  .requestrow h3{
    font-size:50px !important;
  }
  .QRCODE{
    margin-top:-5%;
  }
}
</style>
            </html>
