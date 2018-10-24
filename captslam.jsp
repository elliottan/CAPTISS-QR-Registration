<html>
   <head>
      <title>CAPTSLAM</title>
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
   </head>
   <body class="maincontent">
      <div class="container">
         <div class="row">
            <form action = "Captslam" method = "POST" class="QR">
            <input type="hidden" name="jsppage" value="/captslam.jsp">
            <input type="hidden" name="stationnumber" value="${param.stationnumber}">
            <span class="walkinhead"><p class="QRCODE">Station: <c:out value = "${stationdescriptions[param.stationnumber-1][0]}" /></p></span>
            <p class="QRCODE"><c:out value = "${stationdescriptions[param.stationnumber-1][1]}" />: ${stationdescriptions[param.stationnumber-1][3]}</p>
            <br />
            <p class="QRCODE"><c:out value = "${stationdescriptions[param.stationnumber-1][2]}" />: </p>
            <input type = "text" name = "numberdone" autofocus autocomplete="off">
            <br /><br />
            <input type = "submit" value = "Submit" />

            <br /><br />
            <a href="captslam.jsp?stationnumber=${param.stationnumber == 1 ? 11 : param.stationnumber-1}">
                <input type="button" value="Prev Station"></input></a>
            <a href="captslam.jsp?stationnumber=${param.stationnumber == 11 ? 1 : param.stationnumber+1}">
                <input type="button" value="Next Station"></input></a>
            </form>
         </div>
      </div>
      </div>
      </div>
   </body>
   <div class="footer">
      <p class="copyright">&copy Copyright 2018 College of Alice & Peter Tan. All Rights Reserved.</p>
   </div>
</html>
