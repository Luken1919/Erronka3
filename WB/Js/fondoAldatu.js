document.addEventListener("DOMContentLoaded", () => {
    const body = document.getElementById("indexbody");
    const backgrounds = [
        '../irudiak/fondo1.png',
        '../irudiak/fondo2.png',
        '../irudiak/fondo3.png',
        '../irudiak/fondo4.png'
    
    ];
    let currentBackgroundIndex = 0;

    function changeBackground() {
        currentBackgroundIndex = (currentBackgroundIndex + 1) % backgrounds.length;
        body.style.backgroundImage = `url(${backgrounds[currentBackgroundIndex]})`;
    }

    setInterval(changeBackground, 4000); //Aldatu 4 segunduka
});
