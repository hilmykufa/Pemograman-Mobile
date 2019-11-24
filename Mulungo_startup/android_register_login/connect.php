<?php
$conn = mysqli_connect("localhost","root","")or exit("Gagal Memilih DB.");
mysqli_select_db($conn,"mulungo")or exit("Gagal Memilih DB.");
?>