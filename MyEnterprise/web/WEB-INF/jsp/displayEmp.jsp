<%-- 
    Document   : displayEmp
    Created on : Jan 1, 2017, 7:00:44 PM
    Author     : David
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>

        <title>JSP Page</title>
    </head>
    <body>
        <h1>Search an emp!</h1>
        <!-- <form action="getEmpById" method="get"><-->
        Enter the emp id: <input id="empId" type="text" name="id" /><br>

        <button onclick="sendParam()">Send</button>
        <!-- </form>-->
        <div id="dataDiv"></div>

        <script>
            function sendParam() {
                var id = document.getElementById("empId").value;
                var xhr = new XMLHttpRequest();
                xhr.open("get", "http://localhost:8080/MyEnterprise/getEmpById?id=" + id, false);
                xhr.setRequestHeader("Content-Type", "application/json");
                xhr.send(null);
                var data = xhr.responseText;
                var employee = JSON.parse(data);
                document.getElementById("dataDiv").innerHTML = employee["firstname"];
            }

        </script>
        <div>
            *********************
        </div><h3>Send json string which is about to be read without a mapping class</h3>
        <form action="jsonParser">
            <textarea cols="50" rows="20" name="jsonString">
            </textarea>
            <input type="submit" value="Send JSON" />
        </form>
        <div> *********************</div>
        <h3>Send json to jackson which is about to be read and mapped to a corresponding class</h3>
        <form action="jacksonParser" >
            <textarea cols="50" rows="20" name="stringForJackson">                
            </textarea>
            <input type="submit" value="Send to Jackson" />
        </form><br>
        <h2>Send a faculty to jackson - circular reference partially solved with transient</h2>
        <form action="jacksonParser2">
            <textarea cols="50" rows="20" name="stringForJackson2">                
            </textarea>
            <input type="submit" value="Send to Jackson 2" />
        </form>

        <h2>Send a student for JSONification</h2>
        <form action="jsonify">
            Firstname: <input type="text" name="firstname" /><br>
            Lastname : <input type="text" name="lastname" /><br>
            Age: <input type="text" name="age" /><br>            
            Faculty name:<input type="text" name="facultyName" /><br>
            Faculty rate: <input type="text" name="rate" /><br>
            High school name:<input type="text" name="highSchoolName" /><br>
            High school rate:<input type="text" name="highSchoolRate" /><br>
            <input type="submit" value="JSONify" /><br>
        </form>
        ******************<br><br><br>

        <br>
        Enter Emp id to jsonify him/her: <input type="text" id="empIdJSON" />
        <button onclick="sendToJsonify()">Fetch JSON</button>
        <script>
            function sendToJsonify() {
                $("#EmpList").html("");
                var empIdJson = document.getElementById("empIdJSON").value;
                var xhr = new XMLHttpRequest();
                xhr.open("get", "http://localhost:8080/MyEnterprise/getEmpAsJson?id=" + empIdJson, false);
                xhr.setRequestHeader("Content-Type", "application/json");
                xhr.send(null);
                var data = xhr.responseText;
                var jsonObj = JSON.parse(data);
                var firstname = jsonObj["firstname"];
                var lastname = jsonObj["lastname"];
                var position = jsonObj["position"];/*
                 document.getElementById("firstname").innerHTML = "Firstname: " + firstname;
                 document.getElementById("lastname").innerHTML = "Lastname: " + lastname;
                 document.getElementById("position").innerHTML = "Position: " + position;*/
                $("#firstname").html("Firstname: " + firstname);
                $("#lastname").html("Lastname: " + lastname);
                $("#position").html("Position: " + position);

                //iterate over json object
                var json = $.parseJSON(data);
                $(json).each(function (i, val) {
                    $.each(val, function (k, v) {
                        if (k === "id") {
                            return;
                        }
                        $("#EmpList").append(k + " : " + v + "<br>");
                    });
                });
            }
        </script>
        <div id="firstname" > Firstname: </div>
        <div id="lastname" > Lastname: </div> 
        <div id="position" > Position: </div>
        <div>******************</div>
        <div id="EmpList">

        </div>
        <div>************************</div>
        <button onclick="getAllEmps()"> Get All Emps</button>
        <script>
            function getAllEmps() {
               // $("#newDiv").html("");
                $("#allEmpsList").html("");
                var xhr = new XMLHttpRequest();
                xhr.open("get", "http://localhost:8080/MyEnterprise/getAllEmps", false);
                xhr.setRequestHeader("Content-Type", "application/json");
                xhr.send(null);
                
                var allEmps = xhr.responseText; 
                var arrayOfStrings = allEmps.split("},");
                for (var i = 0; i < arrayOfStrings.length; i++) {
                    var element = arrayOfStrings[i] + "},";
                    if (i == 4) {
                        element = arrayOfStrings[i];
                    }
                    var jsonObj = JSON.parse(element.substring(1, element.length - 1));
                    $(jsonObj).each(function (i, val) {
                        $.each(val, function (k, v) {
                            if (k === "id") {
                                return;
                            }
                            $("#allEmpsList").append(k + " : " + v + "<br>");
                        });
                        $("#allEmpsList").append("**********<br>");
                    });
                    console.log(element);
                }
                
                /*
                       
                var allEmps = eval("(" + xhr.responseText + ")");  
                for (var i = 0; i < allEmps.length; i++) {
                    $("#newDiv").append(allEmps[i].firstname);
                    $("#newDiv").append("<br>");
                }*/
            }
        </script>    
        <div id="allEmpsList">

        </div>
        <div>-------------------</div>
        <div id="newDiv"></div>


    </body>
</html>
