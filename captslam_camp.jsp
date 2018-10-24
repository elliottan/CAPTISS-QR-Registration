<html>
   <head>
      <title>CAPTSLAM</title>
      <link rel="stylesheet" type="text/css" href="css/custom.css" >
        <link rel="stylesheet" type="text/css" href="css/button.css" >
        <link rel="stylesheet" type="text/css" href="css/admin_table.css" >
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

      <script language="javascript" type="text/javascript">
        function start() {
            var groupNumber = document.getElementById("groupnumber").value;
            console.log(groupNumber);
            location.href = "captslam.jsp?stationnumber=" + groupNumber;
        }
      </script>
   </head>
   <body class="maincontent">
      <div class="container">
         <div class="row">
            <form action = "Captslam" method = "POST" class="QR">
            <span class="walkinhead"><p class="QRCODE">What group are you from?</p></span>
            <br />
            <input type="text" id="groupnumber" autocomplete="off"></input>
            <br /><br />
            <a href="javascript:start()"><input type="button" value="Start"></input></a>

            <br /><br /><br />
            <span class="walkinhead"><p class="QRCODE">Scoreboard</p></span>

            <table border="3" class="admintable" id="myTable">
                <tr>
                 <th>No</th>
                 <th>Name</th>
                 <th>Left</th>
                </tr>
                <c:forEach begin="0" end="10" varStatus="status">
                 <tr>
                     <td>${status.index + 1}</td>
                     <td>${stationdescriptions[status.index][0]}</td>
                     <td>${stationdescriptions[status.index][3]}</td>
                 </tr>
                </c:forEach>
            </table>
            </form>
         </div>
      </div>
   </body>
   <div class="footer">
      <p class="copyright">&copy Copyright 2018 College of Alice & Peter Tan. All Rights Reserved.</p>
   </div>
</html>
