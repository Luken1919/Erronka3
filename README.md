# Proiektua Eklipsera inportatzeko eta SQL konektorea gehitzeko jarraibideak

Fitxategi honek jarraibide zehatzak ematen ditu Erronka3 proiektua Eclipse-ra inportatzeko eta SQL konektorea modulepath-era gehitzeko, behar bezala funtziona dezan. Web aplikazioko datu base berdina erabiltzen du, beraz ez da berriro instalatu behar.

## Aldez aurreko baldintzak

- Eclipse IDE instalatuta gure sisteman [Hemen](https://eclipseide.org/)
- Mysql Konektorea, githueko errepositorioan eskuragarri

## Proiektua inportatzeko urratsak

1. Klonatu edo deskargatu errepositorioa hemendikan [Hemen](https://github.com/Luken1919/Erronka3).
    
2. Ireki Eclipse IDE.
    
3. Aukeratu `File > Import` menu nagusian.
    
4. Importatzeko aukeretan `Existing Projects into Workspace` eta aukeratuakoan  `Next`.
    
5. Azkenik finish sakatuko dugu.
    
6. Inportatu ondoren gure working set-ean ezarri dezakegu.
    

## SQL konektorea modulepath-era gehitu

1. Deskargatu MySQL konektorea Javarako MySQL webgune ofizialetik edo beste edozein leku fidagarritatik edo errepositoriotik aukeratu.
    
2. Eclipse-n, egin klik zuzen Erronka3 proiektuan Package Explorer-en ikuspegian, eta hautatu `Build Path > Configure Build Path`.
    
3. Agertzen ari den leihoan, zoaz `Libraries`eta sakatu `Add External JARs...`.
    
4. Nabigatu MySQL konektorea Javarako deskargatu zenuen tokiraino eta hautatu JAR fitxategia. Sakatu `Open` proiektura gehitzeko.
    
5. Azkenik, Sakatu `Apply and Close` lehioa ixteko.
    

