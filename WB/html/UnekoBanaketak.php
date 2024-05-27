<?php
   require_once "../PHP/konektatu.php";
//  SQL kontsulta, erabiltzailearen entregatutako paketeak lortzeko
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

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    if (isset($_POST['selected_package'])) {
        $idPaketea = $_POST['selected_package'];

        // Hautatutako paketearen informazioa lortu
        $sql_paquete = "SELECT * FROM paketea WHERE idPaketea = ?";
        $stmt_paquete = $conn->prepare($sql_paquete);
        $stmt_paquete->bind_param("i", $idPaketea);
        $stmt_paquete->execute();
        $result_paquete = $stmt_paquete->get_result();

        if ($result_paquete->num_rows > 0) {
            $row_paquete = $result_paquete->fetch_assoc();

            // Kanpoko gakoaren murrizketak desaktibatzea aldaketak egiteko
            $conn->query("SET foreign_key_checks = 0");

           // Entregatuta taulan informazioa sartu
            $sql_insert = "INSERT INTO entregatuta (Bezero_zenbaki, Entrega_data, Entrega_Ordua, Helbidea, Pakete_Tamaina, idPaketea, erabiltzailea_idErabiltzailea)
            VALUES (?, CURDATE(), CURTIME(), ?, ?, ?, ?)";
            $stmt_insert = $conn->prepare($sql_insert);
            $stmt_insert->bind_param("sssii", $row_paquete['Bezero_zenbakia'], $row_paquete['Helbidea'], $row_paquete['Pakete_Tamaina'], $row_paquete['idPaketea'], $row_paquete['Erabiltzailea_idErabiltzailea']);

            if ($stmt_insert->execute()) {
                $stmt_insert->close();

                // Pakete taulan aukeratutako paketea ezabatu insert agindua egiten bada
                $sql_delete = "DELETE FROM paketea WHERE idPaketea = ?";
                $stmt_delete = $conn->prepare($sql_delete);
                $stmt_delete->bind_param("i", $idPaketea);
                $stmt_delete->execute();
                $stmt_delete->close();

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

        $stmt_paquete->close();
    } elseif (isset($_POST['Arazoa']) && !empty($_POST['selected_issue'])) {
        $idPaketea = $_POST['Arazoa'];
        $titulo_arazo = $_POST['selected_issue'];
        
        // Lorut arazoaren ID tituloan oinarrituta
        $sql_arazo_id = "SELECT idArazoak FROM arazoak WHERE Tituloa = ?";
        $stmt_arazo_id = $conn->prepare($sql_arazo_id);
        $stmt_arazo_id->bind_param("s", $titulo_arazo);
        $stmt_arazo_id->execute();
        $result_arazo_id = $stmt_arazo_id->get_result();

        if ($result_arazo_id->num_rows > 0) {
             // 'Entregatzen' motako balioa eguneratzeko SQL kontsulta

            $sql_update_mota = "UPDATE paketea SET Mota = 'arazoa' WHERE idPaketea = ?";
            $stmt_update_mota = $conn->prepare($sql_update_mota);
            $stmt_update_mota->bind_param("i", $idPaketea);
            $stmt_update_mota->execute();

            $row_arazo_id = $result_arazo_id->fetch_assoc();
            $idArazoak = $row_arazo_id['idArazoak'];

            // Izan taulan datuak sartu
            $sql_insert_izan = "INSERT INTO izan (idPaketea, idArazoak, Arazo_Ordua) VALUES (?, ?, CURTIME())";
            $stmt_insert_izan = $conn->prepare($sql_insert_izan);
            $stmt_insert_izan->bind_param("ii", $idPaketea, $idArazoak);

            if ($stmt_insert_izan->execute()) {
               
            } else {
              
            }

            $stmt_insert_izan->close();
            header("Location: ../html/arazoak.php");
        } else {
            echo "Arazoa ez da aurkitu.";
        }

        $stmt_arazo_id->close();
    }
}
// SQL kontsulta arazoak taulan Tituloak lortzeko
$sql_arazoak = "SELECT Tituloa FROM arazoak";
$result_arazoak = $conn->query($sql_arazoak);

// Emaitzak aurkitu diren egiaztatu
$titulos_arazoak = [];
if ($result_arazoak->num_rows > 0) {
    while ($row = $result_arazoak->fetch_assoc()) {
        $titulos_arazoak[] = $row['Tituloa'];
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
            <a href="../html/banaketa.php" class="login-button ">Banaketak</a>
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
                        echo "<th>Paketea ID</th>";
                        echo "<th>Bezero Zenbakia</th>";
                        echo "<th>Helbidea</th>";
                        echo "<th>Tamaina</th>";
                        echo "<th></th>";
                        echo "<th>Arazoa</th>";
                        echo " </tr>";
                        // Mostrar los paquetes entregados en una tabla
                        while ($row_paquete = $result_paquetes_entregados->fetch_assoc()) {
                            echo "<tr>";
                            echo "<td>" . htmlspecialchars($row_paquete["idPaketea"]) . "</td>";
                            echo "<td>" . htmlspecialchars($row_paquete["Bezero_zenbakia"]) . "</td>";
                            echo "<td>" . htmlspecialchars($row_paquete["Helbidea"]) . "</td>";
                            echo "<td>" . htmlspecialchars($row_paquete["Pakete_Tamaina"]) . "</td>";
                            echo "<td><button type='submit' name='selected_package' value='" . htmlspecialchars($row_paquete["idPaketea"]) . "'style='background-color:Green;'>Entregatuta</button></td>";
                            echo "<td> 
                            <select name='selected_issue'>
                            <option value=''>Arazoa aukeratu</option>";
                            foreach ($titulos_arazoak as $titulo) {
                                echo "<option value='" . htmlspecialchars($titulo) . "'>" . htmlspecialchars($titulo) . "</option>";
                            }
                            echo "</select>
                            <br>
                            <br>
                        <button type='submit' name='Arazoa' value='" . htmlspecialchars($row_paquete["idPaketea"]) . "' style='background-color:red;'>Arazoa bidali</button>
                            </td>";
                            echo "</tr>";
                        }
                        echo "</table>";
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
$stmt_paquetes_entregados->close();
$conn->close();
?>
