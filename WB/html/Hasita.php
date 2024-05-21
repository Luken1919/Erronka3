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

// Verifica la conexión
if ($conn->connect_error) {
    die("Conexión fallida: " . $conn->connect_error);
}

// Consulta SQL para obtener los datos del usuario
$sql_usuario = "SELECT Izena, Abizena FROM erabiltzailea WHERE Erab_Izena = ?";
$stmt_usuario = $conn->prepare($sql_usuario);
$stmt_usuario->bind_param("s", $_SESSION['username']);
$stmt_usuario->execute();
$result_usuario = $stmt_usuario->get_result();

// Verifica si se encontraron resultados de usuario
if ($result_usuario->num_rows > 0) {
    // Usuario encontrado, obtiene los detalles
    $row_usuario = $result_usuario->fetch_assoc();
    $izena = $row_usuario['Izena'];
    $abizena = $row_usuario['Abizena'];
} else {
    // Si no se encuentra el usuario, muestra un mensaje de error
    $izena = "Errorea";
    $abizena = "Errorea";
}

$stmt_usuario->close();

// Consulta SQL para obtener los paquetes del usuario actual
$sql_paquetes = "SELECT p.idPaketea, p.Bezero_zenbakia, p.Helbidea, p.Pakete_Tamaina, p.Erabiltzailea_idErabiltzailea
                FROM paketea p
                INNER JOIN erabiltzailea e ON p.Erabiltzailea_idErabiltzailea = e.idErabiltzailea
                WHERE e.Erab_Izena = ?";
                
$stmt_paquetes = $conn->prepare($sql_paquetes);
$stmt_paquetes->bind_param("s", $_SESSION['username']);
$stmt_paquetes->execute();
$result_paquetes = $stmt_paquetes->get_result();

// Cerrar la consulta de paquetes
$stmt_paquetes->close();

// Cerrar la conexión
$conn->close();
?>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>PakAG - Banaketa</title>
    <link rel="stylesheet" href="../css/index.css">
    <link rel="icon" href="../irudiak/OIG4.png" type="image/png">
</head>
<body id="hasitabody">
    <header>
        <nav class="nav-container">
            <a href="#" class="logo">
                <img src="../irudiak/OIG4.png" alt="Logo">
            </a>
            <h2 class="espazioa">Erabiltzailea: <?php echo $izena . ' ' . $abizena; ?></h2>
            <div class="spacer"></div>
            <a href="#" class="login-button  selected">Banaketak</a>
            <a href="../html/UnekoBanaketak.php" class="login-button">Uneko Banaketak</a>
            <a href="../html/BanaketarenHistoria.php" class="login-button">Banaketare Historiala</a>
            <a href="../html/index.html" class="login-button">Saioa Itxi</a>
        </nav>
    </header>
    <main id="hasitamain">
        <section class="section" id="section3">
            <div class="content">
                <h1>Banaketak:</h1>
                <form method="post" action="../html/UnekoBanaketak.php">
                    <?php
                    // Verifica si se encontraron resultados de paquetes
                    if ($result_paquetes->num_rows > 0) {
                        // Mostrar los paquetes en una tabla
                        echo "<table>";
                        echo "<tr><th></th><th>Paketea ID</th><th>Bezero Zenbakia</th><th>Helbidea</th><th>Tamaina</th></tr>";
                        while ($row_paquete = $result_paquetes->fetch_assoc()) {
                            echo "<tr>";
                            echo "<td><input type='radio' name='selected_package' value='" . $row_paquete["idPaketea"] . "'></td>";
                            echo "<td>" . $row_paquete["idPaketea"] . "</td>";
                            echo "<td>" . $row_paquete["Bezero_zenbakia"] . "</td>";
                            echo "<td>" . $row_paquete["Helbidea"] . "</td>";
                            echo "<td>" . $row_paquete["Pakete_Tamaina"] . "</td>";
                            echo "</tr>";
                        }
                        echo "</table>";
                        echo "<br>";
                        echo "<input type='submit' name='submit' value='Aukeratu'>";
                    } else {
                        // Si no se encontraron paquetes
                        echo "<p>Ez da aurkitu paketerik.</p>";
                    }
                    ?>
                </form>
            </div>
        </section>
    </main>
    <footer>
        <div class="container">
            <p>© 2024 pakAG.</p>
            <p>Telefonoa: +34 660 064 045</p>
            <p>Email: <a href="mailto:pakAG@gmail.com" target="_blank">pakAG@gmail.com</a></p>
        </div>
        <div class="logotxuri-beltza">
            <img src="../irudiak/OIG4-modified.png" alt="Logo">
        </div>
    </footer>
</body>
</html>
