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

// Consulta SQL para obtener los paquetes entregados del usuario actual
$sql_paquetes_entregados = "SELECT paketea.idPaketea, paketea.Bezero_zenbakia, paketea.Helbidea, paketea.Pakete_Tamaina 
                            FROM paketea 
                            INNER JOIN erabiltzailea 
                            ON paketea.Erabiltzailea_idErabiltzailea = erabiltzailea.idErabiltzailea 
                            WHERE erabiltzailea.Erab_Izena = ? 
                            AND paketea.Mota = 'entregatzen'";

$stmt_paquetes_entregados = $conn->prepare($sql_paquetes_entregados);
$stmt_paquetes_entregados->bind_param("s", $_SESSION['username']);
$stmt_paquetes_entregados->execute();
$result_paquetes_entregados = $stmt_paquetes_entregados->get_result();

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
            <a href="../html/Hasita.php" class="login-button ">Banaketak</a>
            <a href="#" class="login-button selected">Uneko Banaketak</a>
            <a href="../html/BanaketarenHistoria.php" class="login-button">Banaketare Historiala</a>
            <a href="../html/index.html" class="login-button">Saioa Itxi</a>
        </nav>
    </header>
    <main id="hasitamain">
        <section class="section" id="section3">
            <div class="content">
                <h1>Banaketak:</h1>
                <table>
                    <tr>
                        <th>Paketea ID</th>
                        <th>Bezero Zenbakia</th>
                        <th>Helbidea</th>
                        <th>Tamaina</th>
                    </tr>
                    <?php
                    // Mostrar los paquetes entregados en una tabla
                    while ($row_paquete = $result_paquetes_entregados->fetch_assoc()) {
                        echo "<tr>";
                        echo "<td>" . $row_paquete["idPaketea"] . "</td>";
                        echo "<td>" . $row_paquete["Bezero_zenbakia"] . "</td>";
                        echo "<td>" . $row_paquete["Helbidea"] . "</td>";
                        echo "<td>" . $row_paquete["Pakete_Tamaina"] . "</td>";
                        echo "</tr>";
                    }
                    ?>
                </table>
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

<?php
// Cerrar la consulta de paquetes entregados
$stmt_paquetes_entregados->close();

// Cerrar la conexión
$conn->close();
?>
