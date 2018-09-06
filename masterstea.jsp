<html>
<head>
  <title>Masters Tea Registration</title>
  <link rel="stylesheet" type="text/css" href="css/custom.css" >
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
        <a class="navbar-brand" href="#!"><img src="images/captlogo.png" class = "headerlogo"/></a>
      </div>
      <ul class="nav navbar-nav">
        <!-- <li class="active"><a href="#">Home</a></li> -->
        <form action = "Admin" method = "GET">
            <input type="submit" value="Admin" />
        </form>
        <form action = "WalkIn" method = "GET">
            <input type="submit" value="Walk-In Registration" />
        </form>
        <form action = "Logout" method = "POST">
            <input type="submit" value="Logout" />
        </form>
      </ul>
    </div>
  </nav>
  <div class="container">
      <div class="row mainrow">

        </div>
        <div class="col-md-8 col-md-offset-2">
          <img src = "images/mainlogo.png" alt="" class="mainlogo" >

          <div class="row">
            <div class="col-md-offset-1 col-md-8 inputrow">

              <form action = "MastersTea" method = "POST" class="QR">
               <p class="QRCODE">QR Code:</p> <input type = "text" name = "qrcode" autofocus autocomplete="off">
                <!-- <a href="#" class="btn btn-sm animated-button victoria-one" value="Submit">Submit</a> -->
              </div>
              <input type = "submit" value = "Submit" / class="hiddenbutton">
            </form>
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
<style>

.requestrow{
    font-family:arial !important;
    color:white !important;
    font-size:30px !important;
    width:80%;
}
.mainrow{
    margin-left:30px;
    }
</style>
</html>
