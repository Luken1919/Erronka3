<?php
//saioa hasi
session_start();

// Datu basearekin konexioak sortu
$servername = "localhost:33066";
$username = "root";
$password = "";
$dbname = "erronka_pakag";

//Konexioa sortu
$conn = new mysqli($servername, $username, $password, $dbname);

// konexioa balidatu
if ($conn->connect_error) {
    die("Conexión fallida: " . $conn->connect_error);
}
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $erab_izena = $_POST['username'];
    $pasahitza = $_POST['password'];

    // SQL kontsulta prestatu
    $sql = "SELECT * FROM erabiltzailea WHERE Erab_Izena = ? AND Pasahitza = ?";
    $stmt = $conn->prepare($sql);
    $stmt->bind_param("ss", $erab_izena, $pasahitza);
    $stmt->execute();
    $result = $stmt->get_result();

    //Egiaztatu erabiltzaile bat aurkitu den
    if ($result->num_rows > 0) {
        // Erabiltzailea aurkituta, saioa hasi
        $_SESSION['loggedin'] = true;
        $_SESSION['username'] = $erab_izena;
        header("Location: ../html/banaketa.php");
        exit();
    } else {
        // Ez da erabiltzailea aurkitu, errore mezua
        $error = urlencode("Erabiltzailea edo pasahitza ez dira zuzenak, mesedez saiatu berriro.");
        header("Location: ../html/SaioaHasi.php?error=$error");
        exit();
    }
}

$conn->close();
?>
