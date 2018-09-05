<html>
   <head><title>CAPTISS Administration System</title></head>
   
   <body>
    <form action = "MastersTea" method = "GET">
        <input type="submit" value="Masters Tea Registration" />
    </form>
    <form action = "Admin" method = "GET">
        <input type="submit" value="Admin" />
    </form>
    <form action = "Logout" method = "POST">
        <input type="submit" value="Logout" />
    </form>

      <form action = "WalkIn" method = "POST">
        Walk-In Registration
        <br />
        Name: <input type = "text" name = "name" autofocus autocomplete="off">
        <br />
        Email: <input type = "text" name = "email" autocomplete="off">
        <br />
        <input type = "submit" value = "Submit" />
      </form>

      ${requestScope["responsemessage"]}
   </body>
</html>