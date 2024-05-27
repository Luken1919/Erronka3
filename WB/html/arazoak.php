<?php
   require_once "../PHP/konektatu.php";
// SQL kontsulta
$sql_arazoa_query = "SELECT
    paketea.idPaketea, 
    paketea.Bezero_zenbakia, 
    paketea.Helbidea, 
    paketea.Pakete_Tamaina, 
    arazoak.Tituloa AS Titulo, 
    arazoak.Describapena AS deskribapena 
FROM 
    paketea 
JOIN 
    izan ON paketea.idPaketea = izan.idPaketea 
JOIN 
    arazoak ON izan.idArazoak = arazoak.idArazoak 
JOIN 
    erabiltzailea ON paketea.Erabiltzailea_idErabiltzailea = erabiltzailea.idErabiltzailea 
WHERE 
    erabiltzailea.Erab_Izena = ? 
 AND paketea.Mota = 'arazoa'";

$stmt_arazoa = $conn->prepare($sql_arazoa_query);
$stmt_arazoa->bind_param("s", $_SESSION['username']);
$stmt_arazoa->execute();
$result_arazoa = $stmt_arazoa->get_result();
$stmt_arazoa->close();

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    if (isset($_POST['selected_package'])) {
        $idPaketea = $_POST['selected_package'];

        // Hautatutako paketearen informazioa lortu
        $sql_arazoa = "SELECT * FROM paketea WHERE idPaketea = ?";
        $stmt_arazoa = $conn->prepare($sql_arazoa);
        $stmt_arazoa->bind_param("i", $idPaketea);
        $stmt_arazoa->execute();
        $result_arazoa = $stmt_arazoa->get_result();
        if ($result_arazoa->num_rows > 0) {
            $row = $result_arazoa->fetch_assoc();

            // Kanpoko gakoaren murrizketak desaktibatzea aldaketak egiteko
            $conn->query("SET foreign_key_checks = 0");

            // Entregatuta taulan informazioa sartu
            $sql_insert = "INSERT INTO entregatuta (Bezero_zenbaki, Entrega_data, Entrega_Ordua, Helbidea, Pakete_Tamaina, idPaketea, erabiltzailea_idErabiltzailea)
            VALUES (?, CURDATE(), CURTIME(), ?, ?, ?, ?)";
            $stmt_insert = $conn->prepare($sql_insert);
            $stmt_insert->bind_param("sssii", $row['Bezero_zenbakia'], $row['Helbidea'], $row['Pakete_Tamaina'], $row['idPaketea'], $row['Erabiltzailea_idErabiltzailea']);

            if ($stmt_insert->execute()) {
                $stmt_insert->close();

                // Pakete taulan aukeratutako paketearen informazioa ezabatu insert agindua egiten bada
                $sql_delete = "DELETE FROM paketea WHERE idPaketea = ?";
                $stmt_delete = $conn->prepare($sql_delete);
                $stmt_delete->bind_param("i", $idPaketea);
                $stmt_delete->execute();
                $stmt_delete->close();
                // Izan taulan aukeratutako paketearen informazioa ezabatu insert agindua egiten bada
                $sql_delete_izan = "DELETE FROM Izan WHERE idPaketea = ?";
                $stmt_delete_izan = $conn->prepare($sql_delete_izan);
                $stmt_delete_izan->bind_param("i", $idPaketea);
                $stmt_delete_izan->execute();
                $stmt_delete_izan->close();

                // Kanpoko gakoaren murrizketak aktibatu
                $conn->query("SET foreign_key_checks = 1");

                // Botoia ematerakoan orrialdea aldatu
                header("Location: BanaketarenHistoria.php");
                exit;
            } else {
                $stmt_insert->close();
                // Errorea
                echo "Arazoa entregatuta taulan datuak sartzerakoan.";
            }
        }

        $stmt_arazoa->close();
    
    } elseif (isset($_POST['ez_entregatuta'])) {
        $idPaketea = $_POST['ez_entregatuta'];

        // pakete taulan erabiltzailearen id-a 1 da Kudeaketa delako
        $sql_update = "UPDATE paketea SET erabiltzailea_idErabiltzailea = 1 WHERE idPaketea = ?";
        $stmt_update = $conn->prepare($sql_update);
        $stmt_update->bind_param("i", $idPaketea);

        if ($stmt_update->execute()) {
            header("Location: Hasita.php");
            exit;
        } else {
            // Manejar error de actualización
            echo "Arazoa aldatzerakoan.";
        }

        $stmt_update->close();
    }
}
?>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>PakAG - Uneko Banaketa</title>
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
            <a href="../html/UnekoBanaketak.php" class="login-button">Uneko Banaketak</a>
            <a href="../html/arazoak.php" class="login-button selected">Arazoak</a>
            <a href="../html/BanaketarenHistoria.php" class="login-button">Banaketaren Historiala</a>
            <a href="../html/index.html" class="login-button">Saioa Itxi</a>
        </nav>
    </header>
    <main id="hasitamain">
        <section class="section" id="section3">
            <div class="content">
                <h1>Arazoak:</h1>
                <form method="post" action="">
                <table>
                <?php
                if ($result_arazoa->num_rows > 0) {
                    echo "<tr>";
                        echo "<th>Pakete ID</th>";
                        echo "<th>Bezero Zenbakia</th>";
                        echo "<th>Helbidea</th>";
                        echo "<th>Pakete Tamaina</th>";
                        echo "<th>Arazo tituloa</th>";
                        echo "<th>Deskribapena</th>";
                        echo "<th></th>";
                        echo "<th></th>";
                    echo "</tr>";
                     
                    // Imprimir los resultados en la tabla
                    while ($row = $result_arazoa->fetch_assoc()) {
                        echo "<tr>";
                            echo "<td>" . $row['idPaketea'] . "</td>";
                            echo "<td>" . $row['Bezero_zenbakia'] . "</td>";
                            echo "<td>" . $row['Helbidea'] . "</td>";
                            echo "<td>" . $row['Pakete_Tamaina'] . "</td>";
                            echo "<td>" . $row['Titulo'] . "</td>";
                            echo "<td>" . $row['deskribapena'] . "</td>";
                            echo "<td><button type='submit' name='selected_package' value='" . htmlspecialchars($row["idPaketea"]) . "'style='background-color:Green;'>Entregatuta</button></td>";
                            echo "<td><button type='submit' name='ez_entregatuta' value='" . htmlspecialchars($row["idPaketea"]) . "'style='background-color:Red;'>Ez entrega</button></td>";
                        echo "</tr>";
                        }
                }
                else {
                    // Si no se encontraron paquetes
                    echo "<p>Ez da aurkitu paketerik.</p>";
                }
                        ?>
                    </table>
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
