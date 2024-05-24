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
$sql_paquetes_entregados = "SELECT paketea.idPaketea, paketea.Bezero_zenbakia, paketea.Helbidea, paketea.Pakete_Tamaina, paketea.Mota, paketea.Erabiltzailea_idErabiltzailea
                            FROM paketea 
                            INNER JOIN erabiltzailea 
                            ON paketea.Erabiltzailea_idErabiltzailea = erabiltzailea.idErabiltzailea 
                            WHERE erabiltzailea.Erab_Izena = ? 
                            AND paketea.Mota = 'entregatzen'";

$stmt_paquetes_entregados = $conn->prepare($sql_paquetes_entregados);
$stmt_paquetes_entregados->bind_param("s", $_SESSION['username']);
$stmt_paquetes_entregados->execute();
$result_paquetes_entregados = $stmt_paquetes_entregados->get_result();

if ($_SERVER['REQUEST_METHOD'] === 'POST' && isset($_POST['selected_package'])) {
    $idPaketea = $_POST['selected_package'];

    // Obtener detalles del paquete seleccionado
    $sql_paquete = "SELECT * FROM paketea WHERE idPaketea = ?";
    $stmt_paquete = $conn->prepare($sql_paquete);
    $stmt_paquete->bind_param("i", $idPaketea);
    $stmt_paquete->execute();
    $result_paquete = $stmt_paquete->get_result();

    if ($result_paquete->num_rows > 0) {
        $row_paquete = $result_paquete->fetch_assoc();

        // Desactivar las restricciones de clave externa
        $conn->query("SET foreign_key_checks = 0");

        // Insertar en entregatuta
        $sql_insert = "INSERT INTO entregatuta (Bezero_zenbaki, Entrega_data, Entrega_Ordua, Helbidea, Pakete_Tamaina, idPaketea, erabiltzailea_idErabiltzailea)
        VALUES (?, CURDATE(), CURTIME(), ?, ?, ?, ?)";
        $stmt_insert = $conn->prepare($sql_insert);
        $stmt_insert->bind_param("sssii", $row_paquete['Bezero_zenbakia'], $row_paquete['Helbidea'], $row_paquete['Pakete_Tamaina'], $row_paquete['idPaketea'], $row_paquete['Erabiltzailea_idErabiltzailea']);

        if ($stmt_insert->execute()) {
            $stmt_insert->close();

            // Eliminar de paketea solo si la inserción en entregatuta fue exitosa
            $sql_delete = "DELETE FROM paketea WHERE idPaketea = ?";
            $stmt_delete = $conn->prepare($sql_delete);
            $stmt_delete->bind_param("i", $idPaketea);
            $stmt_delete->execute();
            $stmt_delete->close();

            // Reactivar las restricciones de clave externa
            $conn->query("SET foreign_key_checks = 1");

            // Redirigir para evitar reenvío del formulario
            header("Location: BanaketarenHistoria.php");
            exit;
        } else {
            $stmt_insert->close();
            // Manejar error de inserción
            echo "Error al insertar en entregatuta.";
        }
    }


    $stmt_paquete->close();
}

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
            <h2 class="espazioa">Erabiltzailea: <?php echo htmlspecialchars($izena . ' ' . $abizena); ?></h2>
            <div class="spacer"></div>
            <a href="../html/Hasita.php" class="login-button ">Banaketak</a>
            <a href="#" class="login-button selected">Uneko Banaketak</a>
            <a href="../html/arazoak.php" class="login-button">Arazoak</a>
            <a href="../html/BanaketarenHistoria.php" class="login-button">Banaketaren Historiala</a>
            <a href="../html/index.html" class="login-button">Saioa Itxi</a>
        </nav>
    </header>
    <main id="hasitamain">
        <section class="section" id="section3">
            <div class="content">
                <h1>Uneko Banaketak:</h1>
                <form method="post" action="">
                <?php
                if ($result_paquetes_entregados->num_rows > 0) {
                    echo "<table>";
                    echo "<tr>";
                    echo "<th></th>";
                    echo "<th>Paketea ID</th>";
                    echo "<th>Bezero Zenbakia</th>";
                    echo "<th>Helbidea</th>";
                    echo "<th>Tamaina</th>";
                    echo " </tr>";
                        // Mostrar los paquetes entregados en una tabla
                        while ($row_paquete = $result_paquetes_entregados->fetch_assoc()) {
                            echo "<tr>";
                            echo "<td><input type='radio' name='selected_package' value='" . htmlspecialchars($row_paquete["idPaketea"]) . "'></td>";
                            echo "<td>" . htmlspecialchars($row_paquete["idPaketea"]) . "</td>";
                            echo "<td>" . htmlspecialchars($row_paquete["Bezero_zenbakia"]) . "</td>";
                            echo "<td>" . htmlspecialchars($row_paquete["Helbidea"]) . "</td>";
                            echo "<td>" . htmlspecialchars($row_paquete["Pakete_Tamaina"]) . "</td>";
                            echo "</tr>";
                        }
                    echo "</table>";
                    echo "<br>";
                    echo "<input type='submit' name='submit' value='Entregatuta'>";
                    echo "<input type='submit' name='submit_arazoa' value='Arazoa'>";
                } else {
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

<?php
// Cerrar la consulta de paquetes entregados
$stmt_paquetes_entregados->close();

// Cerrar la conexión
$conn->close();
?>
