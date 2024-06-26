<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Saioa Hasi</title>
    <link rel="stylesheet" href="../css/index.css">
    <link rel="icon" href="../irudiak/OIG4.png" type="image/png">
</head>
<body>
    <header>
        <nav class="nav-container">
            <a href="../html/index.html" class="logo">
                <img src="../irudiak/OIG4.png" alt="Logo">
            </a>
        </nav>
    </header>
    <main>
        <div class="login-container">
            <div class="login-image">
                <img src="../irudiak/saioahasi.png" alt="Imagen de inicio de sesión">
            </div>
            <div class="login-form">
                <h2>Saioa Hasi:</h2>
                <form action="../php/login.php" method="POST">
                    <label for="username">Erabitzailea:</label>
                    <input type="text" id="username" name="username" required>
                    
                    <label for="password">Pasahitza:</label>
                    <input type="password" id="password" name="password" required>
                    
                    <button type="submit">Sartu</button>
                </form>
                <br>
                <?php
                if (isset($_GET['error'])) {
                    echo '<p style="color:red;">' . htmlspecialchars($_GET['error']) . '</p>';
                }
                ?>
            </div>
        </div>
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
