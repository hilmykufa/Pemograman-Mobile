<?php

if ($_SERVER['REQUEST_METHOD']=='POST') {

    $name = $_POST['name'];
    $id = $_POST['id'];
    $size=$_POST['size'];
    $sampah=$_POST['sampah'];
    $terima='no'

    require_once 'connect.php';
    $sql="SELECT s_harga FROM sampah WHERE name='$sampah' ";
    $response = mysqli_query($conn, $sql);
    $s_harga=$response['s_harga'];
    $result = array();
    $result['login'] = array();

    if(mysqli_num_rows($response)===1){
        $total=$s_harga*$size;
        $sql = "INSERT INTO h_merchant (m_id,m_name,size,terima) VALUES ('$id', '$name', '$size','$total','$terima')";

        $response = mysqli_query($conn, $sql);
    
        if ( mysqli_query($conn, $sql) {
        
            $row = mysqli_fetch_assoc($response);
            
                $index[]
                $index['name'] = $row['gc_name'];
                $index['email'] = $row['gc_email'];

                array_push($result['login'], $index);

                $result['success'] = "1";
                $result['message'] = "success";
                echo json_encode($result);

                mysqli_close($conn);

            } else {

            $result['success'] = "0";
            $result['message'] = "error";
            echo json_encode($result);

            mysqli_close($conn);

             }

        }

    }else {

        $result['success'] = "0";
        $result['message'] = "error";
        echo json_encode($result);

        mysqli_close($conn);

         }
    

}

?>