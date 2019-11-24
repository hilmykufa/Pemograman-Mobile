<?php
 require 'connect.php';
    $name = $_POST['name'];
    $email = $_POST['email'];
    $password = $_POST['password'];

    $password = password_hash($password, PASSWORD_DEFAULT);

    $sql = "INSERT INTO g_collector (gc_name, gc_email, gc_password) VALUES ('$name', '$email', '$password')";
   $query= mysqli_query($conn, $sql);

    if ( $query ) {
        $result["success"] = "1";
        $result["message"] = "success";

        echo json_encode($result);
        mysqli_close($conn);

    } else {

        $result["success"] = "0";
        $result["message"] = "error";

        echo json_encode($result);
        mysqli_close($conn);
    }

?>