<!DOCTYPE html>
<html>
   <head>
        <meta http-equiv="Refresh" content="5;url=Print"> <!-- Reload Print page every 5 seconds -->
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
        <script language="javascript" type="text/javascript">
            <!--
            if (${printstring.isEmpty()}) {
                console.log("print queue is empty");
            } else {
                console.log("try creating activeXobject");
                var objDoc;
                try {
                    objDoc = new ActiveXObject("bpac.Document");
                } catch (e) {
                    console.log(e);
                }
                console.log(objDoc);
                console.log("created activeXobject");

                // console.log("${labeltemplatepath}");
                // if (objDoc.Open("${labeltemplatepath}") != false) {
                if (objDoc.Open("C:/apache-tomcat-9.0.10/webapps/captiss/labelprinting/label_template.lbx") != false) {
                    console.log("process print string elements");
                    <c:set var = "splitprintstring" value = "${fn:split(printstring, ';')}" />
                    <c:forEach var="record" items="${splitprintstring}" varStatus="status">
                        <c:set var="splitrecord" value="${fn:split(record, '|')}" />
                        // 0:id, 1:name, 2:p1, 3:p2, 4:p3, 5:p4

                        if (${fn:length(splitrecord)} >= 6) {
                            objDoc.GetObject("name").Text = "${splitrecord[1]}";
                            objDoc.GetObject("qrcode").Text = "${splitrecord[0]}";
                            objDoc.GetObject("p1").Text = "${splitrecord[2]}";
                            objDoc.GetObject("p2").Text = "${splitrecord[3]}";
                            objDoc.GetObject("p3").Text = "${splitrecord[4]}";
                            objDoc.GetObject("p4").Text = "${splitrecord[5]}";
                            console.log("${splitrecord[1]}, ${splitrecord[2]}, ${splitrecord[3]}, ${splitrecord[4]}, ${splitrecord[5]}");
                        } else {
                            objDoc.Close();
                            // if (objDoc.Open("${labeltemplatepath2}") != false) {
                            if (objDoc.Open("C:/apache-tomcat-9.0.10/webapps/captiss/labelprinting/label_template_2.lbx") != false) {
                                objDoc.GetObject("name").Text = "${splitrecord[1]}";
                                objDoc.GetObject("qrcode").Text = "${splitrecord[0]}";
                                objDoc.GetObject("p1").Text = "${splitrecord[2]}";
                                console.log("${splitrecord[1]}, ${splitrecord[2]}");
                            } else {
                                console.log("failed to open template 2");
                            }
                        }

                        objDoc.StartPrint("",0);
                        objDoc.PrintOut(1,0);
                        objDoc.EndPrint();
                    </c:forEach>
                    objDoc.Close();
                } else {
                    console.log("failed to open template");
                }
            }
            -->
        </script>
      <title>Print Queue</title>
      <link rel="stylesheet" type="text/css" href="css/custom.css" >
      <link rel="stylesheet" type="text/css" href="css/button.css" >
      <link rel="stylesheet" type="text/css" href="css/admin.css" >
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
   </head>
   <body class="maincontent">
      <jsp:include page="header_navbar.jsp" />
      <div class="container">
         <div class="row">
            <div class="col-md-12">
               <h1>CAPTISS Print Page</h1>
            </div>
         </div>
      </div>
   </body>
   <div class="footer">
      <p class="copyright">&copy; Copyright 2018 College of Alice & Peter Tan. All Rights Reserved.</p>
   </div>
</html>