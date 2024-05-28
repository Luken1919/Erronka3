<?php
//saioa Hasi
session_start();

if (!isset($_SESSION['loggedin'])) {
    header('Location: login.php');
    exit;
}

// Datu basearekin konektatu
$servername = "localhost:33066";
$username = "root";
$password = "";
$dbname = "erronka_pakag";

// Konexioa sortu
$conn = new mysqli($servername, $username, $password, $dbname);

// Konexioa balidatu
if ($conn->connect_error) {
    die("Conexión fallida: " . $conn->connect_error);
}

// Erabiltzailearen datuak lortzeko SQL kontsulta
$sql = "SELECT Izena, Abizena FROM erabiltzailea WHERE Erab_Izena = ?";
$stmt = $conn->prepare($sql);
$stmt->bind_param("s", $_SESSION['username']);
$stmt->execute();
$result = $stmt->get_result();


if ($result->num_rows > 0) {
    // Erabiltzailea aurkituta
    $row = $result->fetch_assoc();
    $izena = $row['Izena'];
    $abizena = $row['Abizena'];
} else {
    //Erabiltzailea aurkitu ez badu errorea.
    $izena = "Errorea";
    $abizena = "Errorea";
}

$stmt->close();