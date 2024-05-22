<?php
session_start();

if (!isset($_SESSION['loggedin'])) {
    header('Location: login.html');
    exit;
}

$servername = "localhost:33066";
$username = "root";
$password = "";
$dbname = "erronka_pakag";

$conn = new mysqli($servername, $username, $password, $dbname);

if ($conn->connect_error) {
    die("Conexión fallida: " . $conn->connect_error);
}

$sql = "SELECT Izena, Abizena FROM erabiltzailea WHERE Erab_Izena = ?";
$stmt = $conn->prepare($sql);
$stmt->bind_param("s", $_SESSION['username']);
$stmt->execute();
$result = $stmt->get_result();

if ($result->num_rows > 0) {
    $row = $result->fetch_assoc();
    $izena = $row['Izena'];
    $abizena = $row['Abizena'];
} else {
    $izena = "Errorea";
    $abizena = "Errorea";
}

$stmt->close();

// Verificar si se envió el paquete seleccionado desde hasita.php
if(isset($_POST['selected_package'])) {
    $selected_package_id = $_POST['selected_package'];

    // Consulta SQL para obtener los detalles del paquete seleccionado
    $sql_package = "SELECT * FROM paketea WHERE idPaketea = ?";
    $stmt_package = $conn->prepare($sql_package);
    $stmt_package->bind_param("i", $selected_package_id);
    $stmt_package->execute();
    $result_package = $stmt_package->get_result();

    if ($result_package->num_rows > 0) {
        $row_package = $result_package->fetch_assoc();
        // Guardar los detalles del paquete en una sesión
        $_SESSION['selected_package_details'] = $row_package;
        // Mostrar los detalles del paquete seleccionado
        $selected_package_details = $row_package;
    } else {
        $selected_package_details = null;
    }

    $stmt_package->close();
} elseif(isset($_SESSION['selected_package_details'])) {
    // Si no hay un paquete seleccionado en el formulario pero hay detalles de paquete almacenados en la sesión
    // Mostrar los detalles del paquete almacenados en la sesión
    $selected_package_details = $_SESSION['selected_package_details'];
}

$conn->close();
?>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>PakAG-Arazoak</title>
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
            <a href="../html/BanaketarenHistoria.php" class="login-button">Banaketare Historiala</a>
            <a href="#" class="login-button selected">Arazoak</a>
            <a href="../html/index.html" class="login-button">Saioa Itxi</a>
        </nav>
    </header>
    <main id="hasitamain">
        <section class="section" id="section2">
            <div class="content">
                <h1>Arazoak:</h1>
                <?php
                if(isset($selected_package_details)) {
                    echo "<table>";
                    echo "<tr><th></th><th>ID del Paquete</th><th>Bezero Zenbakia</th><th>Helbidea</th><th>Tamaina</th></tr>";
                    echo "<tr>";
                    echo "<td><input type='radio' name='selected_package' '></td>";
                    echo "<td>" . $selected_package_details['idPaketea'] . "</td>";
                    echo "<td>" . $selected_package_details['Bezero_zenbakia'] . "</td>";
                    echo "<td>" . $selected_package_details['Helbidea'] . "</td>";
                    echo "<td>" . $selected_package_details['Pakete_Tamaina'] . "</td>";
                    echo "</tr>";
                    echo "</table>";
                    echo "<br>";
                    echo "<input type='submit' name='submit' value='Eginda'>";
                    echo "<input type='submit' name='submit' value='Arazoa'>";
                } else {
                    echo "<p>Ez da paquetea hautatu.</p>";
                }
                ?>
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