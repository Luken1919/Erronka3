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

// Consulta SQL para obtener los datos de entregatuta del usuario actual
$sql_entregatuta = "SELECT entregatuta.idEntregatuta, entregatuta.Entrega_data, entregatuta.Entrega_Ordua, entregatuta.Helbidea, entregatuta.Pakete_Tamaina
                    FROM entregatuta
                    INNER JOIN erabiltzailea 
                    ON entregatuta.erabiltzailea_idErabiltzailea = erabiltzailea.idErabiltzailea 
                    WHERE erabiltzailea.Erab_Izena = ?";

$stmt_entregatuta = $conn->prepare($sql_entregatuta);
$stmt_entregatuta->bind_param("s", $_SESSION['username']);
$stmt_entregatuta->execute();
$result_entregatuta = $stmt_entregatuta->get_result();
?>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>PakAG</title>
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
            <a href="../html/Hasita.php" class="login-button">Banaketak</a>
            <a href="../html/UnekoBanaketak.php" class="login-button">Uneko Banaketak</a>
            <a href="../html/arazoak.php" class="login-button">Arazoak</a>
            <a href="#" class="login-button selected">Banaketaren Historiala</a>
            <a href="../html/index.html" class="login-button">Saioa Itxi</a>
        </nav>
    </header>
    <main id="hasitamain">
        <section class="section" id="section3">
            <div class="content">
                <h1>Banaketaren historiala:</h1>
                
                <table>
                <?php
                    if ($result_entregatuta->num_rows > 0) {
                        echo "<thead>";
                            echo "<tr>";
                                echo "<th>Entregatuta ID</th>";
                                echo "<th>Entrega Data</th>";
                                echo "<th>Entrega Ordua</th>";
                                echo "<th>Helbidea</th>";
                                echo "<th>Pakete Tamaina</th>";
                            echo "</tr>";
                        echo "</thead>";
                        echo "<tbody>";
                        // Bistaratu banaketaren historiala
                        while ($row_entregatuta = $result_entregatuta->fetch_assoc()) {
                            echo "<tr>";
                                echo "<td>" . $row_entregatuta["idEntregatuta"] . "</td>";
                                echo "<td>" . $row_entregatuta["Entrega_data"] . "</td>";
                                echo "<td>" . $row_entregatuta["Entrega_Ordua"] . "</td>";
                                echo "<td>" . $row_entregatuta["Helbidea"] . "</td>";
                                echo "<td>" . $row_entregatuta["Pakete_Tamaina"] . "</td>";
                            echo "</tr>";
                            }
                        } else {
                            echo "<tr><td colspan='5'>Ez daude banaketa historiarik</td></tr>";
                        }
                        ?>
                    </tbody>
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