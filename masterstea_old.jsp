<html>
   <head><title>Masters Tea Registration</title></head>
   
   <body>
      <form action = "MastersTea" method = "POST">
        QR Code: <input type = "text" name = "qrcode" autofocus>
        <br />
        <input type = "submit" value = "Submit" />
      </form>

      ${requestScope["responsemessage"]}
   </body>
</html>