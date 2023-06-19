

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bazengatech Technologies</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <div class="main">
        <div class="navbar">
            <div class="icon">
                <h2 class="logo">BAZENGATECH</h2>
            </div>
            <div class="menu">
                <ul>
                    <li> <a href="Login.php">HOME</a></li>
                    <li> <a  href="About.php">ABOUT</a></li>
                    <li> <a href="Services.php">SERVICES</a></li>
                    <li> <a href="Contact.php">CONTACT</a></li>

                    
                </ul>
            </div>
            <div class="search">
                <input class="srch" type="search"name=""placeholder="Type here to Search">
                <a href="#"><button class="btn">Search</button></a>
            </div>
        </div>
        <div class="content">
            <h1>Welcome to the Best Online shopping Site in The world</h1>
             <p class="par"> Bazengatech is an Online designed Hardware <br> where customers  can make various purchases <br> and orders and have them being delivered <br>at the comfort of their home with only<br> a dial of a phone</p>
          
             <button class="btn1"><a href="Services.php">Tell Me More</a></button>
             <form method="Post">
            <div class="form">
               <h2 id="Login">Login  Here</h2>
               <input class="Email1" type="Email"name="Email"placeholder="Enter Email here">
               <input class="password1" type="password"name="Password"placeholder="Enter Valid Passoword"><br>
              <button class="Login-button">Login</button>
             <h1 class="loginSub">Doesn't have an Account?</h1><br>
            <button class="SignUpbutton"> <a href="Signup.php">Create an Account</a></button>
            </form>

            
            </div>
        </div>
        

        
    </div>
   
    <script src="main.js"></script>
    <?php
session_start();
include("connection.php");
include("functions.php");


if ($_SERVER["REQUEST_METHOD"] == "POST") {
 
    // collect value of input field
    $data = $_REQUEST['Email'];
    $password=$_REQUEST['Password'];
 
    if (!empty($data&&$password)) {
        
            $query="select*from  users where Email='$data'and Password='$password'";
            mysqli_query($conn,$query);
            if($query==true){
                header("Location: Services.php");
                exit;
            }else{
                echo('Please enter the coreect credentials');
            }
        
    } 
}

 
// Closing the connection.
$conn->close();
 
?>
  
</body>
</html>