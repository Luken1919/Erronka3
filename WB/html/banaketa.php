<?php
   require_once "../PHP/konektatu.php";
// SQL kontsulta erabiltzailearen paketea taularen datuak lortzeko 
$sql_paquetes = "SELECT p.idPaketea, p.Bezero_zenbakia, p.Helbidea, p.Pakete_Tamaina, p.Erabiltzailea_idErabiltzailea
                FROM paketea p
                INNER JOIN erabiltzailea e ON p.Erabiltzailea_idErabiltzailea = e.idErabiltzailea
                WHERE e.Erab_Izena = ? AND p.Mota='entregatu Gabe'";
                
$stmt_paquetes = $conn->prepare($sql_paquetes);
$stmt_paquetes->bind_param("s", $_SESSION['username']);
$stmt_paquetes->execute();
$result_paquetes = $stmt_paquetes->get_result();
$stmt_paquetes->close();

//  Egiaztatu formularioa bidali den eta mota aldatzeko botoian klik egin den
if(isset($_POST['change_mota'])) {
    $idPaketea = $_POST['change_mota'];

    // 'Entregatzen' motako balioa eguneratzeko SQL kontsulta

    $sql_update_mota = "UPDATE paketea SET Mota = 'entregatzen' WHERE idPaketea = ?";
    $stmt_update_mota = $conn->prepare($sql_update_mota);
    $stmt_update_mota->bind_param("i", $idPaketea);
    $stmt_update_mota->execute();
    header("Location: ../html/UnekoBanaketak.php");

    $stmt_update_mota->close();
    }
    
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
            <a href="#" class="login-button selected">Banaketak</a>
            <a href="../html/UnekoBanaketak.php" class="login-button">Uneko Banaketak</a>
            <a href="../html/arazoak.php" class="login-button">Arazoak</a>
            <a href="../html/BanaketarenHistoria.php" class="login-button">Banaketaren Historiala</a>
            <a href="../html/index.html" class="login-button">Saioa Itxi</a>
        </nav>
    </header>
    <main id="hasitamain">
        <section class="section" id="section3">
            <div class="content">
                <h1>Banaketak:</h1>
                <form id="myForm" method="post" action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]); ?>">
                    <?php
                    // Egiaztatu paketeen emaitzak aurkitu diren
                    if ($result_paquetes->num_rows > 0) {
                        // Paketeak taulan erakutsi
                        echo "<table>";
                        echo "<tr><th>Paketea ID</th><th>Bezero Zenbakia</th><th>Helbidea</th><th>Tamaina</th><th></th></tr>";
                        while ($row_paquete = $result_paquetes->fetch_assoc()) {
                            echo "<tr>";
                            echo "<td>" . $row_paquete["idPaketea"] . "</td>";
                            echo "<td>" . $row_paquete["Bezero_zenbakia"] . "</td>";
                            echo "<td>" . $row_paquete["Helbidea"] . "</td>";
                            echo "<td>" . $row_paquete["Pakete_Tamaina"] . "</td>";
                            echo "<td><button type='submit' name='change_mota' value='".$row_paquete["idPaketea"]."'>Aukeratu</button></td>";
                            echo "</tr>";
                        }
                        echo "</table>";
                        echo "<br>";
                    } else {
                        // Paketeak aurkitu ez dierean
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
