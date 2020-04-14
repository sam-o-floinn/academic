<?php
$conn = mysqli_connect("localhost", "root", "", "test");

if (!$conn) {
	die("Failed to connect to database: " . mysqli_connect_error());
}
?>