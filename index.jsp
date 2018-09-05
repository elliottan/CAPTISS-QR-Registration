<html>
   <head><title>CAPTISS Administration System</title></head>
   
   <body>
      <form action = "Login" method = "POST">
        CAPTISS Administration System
        <br />
        Username: <input type = "text" name = "username" autofocus>
        <br />
        Password: <input type = "password" name = "password" autocomplete="off">
        <br />
        <input type = "submit" value = "Submit" />
      </form>

      ${requestScope["responsemessage"]}
   </body>
</html>