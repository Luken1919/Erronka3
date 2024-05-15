<?php
// Inicia la sesión
session_start();

// Verifica si el usuario está autenticado
if (!isset($_SESSION['loggedin'])) {
    header('Location: login.html');
    exit;
}

// Conexión a la base de datos
$servername = "localhost:33066";
$username = "root";
$password = "";
$dbname = "erronka_pakag";

// Crea la conexión
$conn = new mysqli($servername, $username, $password, $dbname);


// Crea la conexión
$conn = new mysqli($servername, $username, $password, $dbname);

// Verifica la conexión
if ($conn->connect_error) {
    die("Conexión fallida: " . $conn->connect_error);
}

// Consulta SQL para obtener los detalles del usuario
$sql = "SELECT Izena, Abizena FROM erabiltzailea WHERE Erab_Izena = ?";
$stmt = $conn->prepare($sql);
$stmt->bind_param("s", $_SESSION['username']);
$stmt->execute();
$result = $stmt->get_result();

// Verifica si se encontraron resultados
if ($result->num_rows > 0) {
    // Usuario encontrado, obtiene los detalles
    $row = $result->fetch_assoc();
    $izena = $row['Izena'];
    $abizena = $row['Abizena'];
} else {
    // Si no se encuentra el usuario, muestra un mensaje de error
    $izena = "Errorea";
    $abizena = "Errorea";
}

$stmt->close();
$conn->close();
?>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>PakAG</title>
    <link rel="stylesheet" href="../css/hasita.css">
    <link rel="icon" href="../irudiak/OIG4.png" type="image/png">
</head>
<body id="indexbody">
    <header>
        <nav class="nav-container">
            <a href="#" class="logo">
                <img src="../irudiak/OIG4.png" alt="Logo">
            </a>
            <h2 class="espazioa">Erabiltzailea: <?php echo $izena . ' ' . $abizena; ?></h2>
            <div class="spacer"></div>
            <a href="../html/index.html" class="login-button">Saioa Itxi</a>
        </nav>
    </header>
    <main>
        <section class="section" id="section1">
            <div class="content">
                <h1>Banaketak:</h1>
                <p>Pakete1</p>
            </div>
        </section>
        <section class="section" id="section2">
            <div class="content">
                <h1>Uneko Banaketak:</h1>
                <p>Pakete2</p>
            </div>
        </section>
        <section class="section" id="section3">
            <div class="content">
                <h1>Banaketaren historiala:</h1>
                <p>Past banaketa</p>
            </div>
        </section>
    </main>
    <footer>
        <div class="container">
            <h2>Harremanetarako:</h2>
            <br>
            <p>© 2024 pakAG.</p>
            <p>Telefonoa: +34 660 064 045</p>
            <p>Email: <a href="mailto:pakAG@gmail.com" target="_blank">pakAG@gmail.com</a></p>
        </div>
    </footer>
</body>
</html>
