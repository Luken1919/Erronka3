

---

# pakAG Logistika

Sistema informatiko bat inplementatzeko beharra identifikatu dugu, gure egoitzaren eta gidarien arteko komunikazioa hobetzeko eta paketeen banaketa dinamizatzeko. Sistema hori irisgarria izango da gidarientzat gailu mugikorretan, eta paketeak egoitzatik kudeatzea eta antolatzea erraztuko du.  
  
Egoitzan, langile bat arduratuko da paketeak esleitzeaz, bidalketa berriak kudeatzeaz eta langileei administratzeaz. Bitartean, banatzaileek denbora errealean ikusi ahal izango dituzte esleitutako paketeak, eta modu eraginkorrean kudeatu ahal izango dute haien banaketa.

## Requisitos

- [XAMPP](https://www.apachefriends.org/index.html) ( Apache, MySQL y PHP)
- Nabigatzailea (Chrome, Firefox, etc.)

## Instalazioa

### 1. Repositorioa klonatu
Klonatu errepositiorio hau gure htdocs karpetan 

```bash
cd /ruta/a/xampp/htdocs
git clone https://github.com/Luken1919/Erronka3
```

### 2. Itsatsi web aplikazioa htdocs-ean

Git erabiltzen ez baduzu, zure web aplikazioaren fitxategiak kopiatu eta itsatsi ditzakezu XAMPPren `htdocs` karpetan.

```bash
Kopiatu fitxategiak  `C:\xampp\htdocs\tu_proyecto` Windowsean,
```

### 3. Aldatu konexio fitxategia

Aldatu php konexio fitxategia behrraezkoa izanez gero (por ejemplo, `config.php`).

```php
<?php
// config.php

define('DB_SERVER', 'localhost');
define('DB_USERNAME', 'root');
define('DB_PASSWORD', '');
define('DB_DATABASE', 'nombre_de_tu_base_de_datos');
?>
```

### 4. XAMPP Hasieratu

Xampp ireki eta auekratu apache eta mysql zerbitzuak .

### 5. Datu basea inportatu

#### Metodo 1: phpMyadmin

1. Ireki phpMyadmin nabigatzailean: [http://localhost/phpmyadmin](http://localhost/phpmyadmin)
2. Sortu datu base berri bat.
3. Aukeratu sortutako datu basea.
4. Sakatu `Importar`.
5. Aukeratu sql fitxategia  (`database.sql`) eta jarraitu sakatu.


### 6. Aplikaziora Sartu

Ireki zure nabigatzailea eta sartu :  [http://localhost/tu_proyecto](http://localhost/tu_proyecto).

## Estructura 

- `/public`: Archivos públicos (HTML, CSS, JS)
- `/database`: Archivos de base de datos (SQL)

Erabilpena

Web aplikazio honen bidez gure banatzileen beraien paketeak ikus ditzazkete
