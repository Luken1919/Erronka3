<?php
// Inicia la sesión
session_start();
$servername = "localhost:33066";
$username = "root";
$password = "";
$dbname = "erronka_pakag";

// Crea la conexión
$conn = new mysqli($servername, $username, $password, $dbname);



if ($conn->connect_error) {
    die("Conexión fallida: " . $conn->connect_error);
}

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $erab_izena = $_POST['username'];
    $pasahitza = $_POST['password'];

    // Prepara la consulta SQL
    $sql = "SELECT * FROM erabiltzailea WHERE Erab_Izena = ? AND Pasahitza = ?";
    $stmt = $conn->prepare($sql);
    $stmt->bind_param("ss", $erab_izena, $pasahitza);
    $stmt->execute();
    $result = $stmt->get_result();

    // Verifica si se encontró un usuario
    if ($result->num_rows > 0) {
        // Usuario encontrado, iniciar sesión
        $_SESSION['loggedin'] = true;
        $_SESSION['username'] = $erab_izena;
        header("Location: ../html/Hasita.php"); // Redirige a otra página
        exit();
    } else {
        // Usuario no encontrado, redirigir con mensaje de error
        $error = urlencode("Erabiltzailea edo pasahitza okerra da.");
        header("Location: ../html/SaioaHasi.php");
        exit();
    }

}

$conn->close();
?>
