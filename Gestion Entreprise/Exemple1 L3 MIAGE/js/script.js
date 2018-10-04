let canvas, ctx;
let lc, hc;

window.onload = function () {
    // Appelé quand la page est prête et a chargé
    // toutes ses ressources (images, vidéos etc.)
    console.log("pret")
    canvas = document.querySelector("#myCanvas");
    lc = canvas.width;
    hc = canvas.height;

    ctx = canvas.getContext("2d");
    drawRectangle(10, 10, 100, 100, "red");
    drawRectangle(200, 150, 50, 100, "green");


    // PAS BON POUR ANIMATION FLUIDE A 60 im/s
    // Ok si changer de couleur 2 fois par seconde
    // par exemple, mais sinon non....
    this.setInterval(clignoteRectangle, 400);

    // Pour animation à 60 im/s
    requestAnimationFrame(anime);
}
let x = 0;
let y = 150;
let color = "orange";
let color1 = "red"
let vitesse = 10;

function anime() {
    // 1 On efface le canvas
    ctx.clearRect(0, 0, lc, hc);

    // 2 On dessine
    drawRectangle(x, y, 100, 100, color);
    drawRectangle(x, 0, 100, 100, color1);

    // 3 On change l'état (position, couleur, taille etc.)
    y += vitesse;
    x += vitesse;
    testeCollisionAvecMurs();

    // 4 on demande au browser de rappeler la fonction
    // dans 1/60ème de seconde
    requestAnimationFrame(anime);
}

function clignoteRectangle() {
    if (color === "orange")
        color = "green";
    else
        color = "orange";
    
    if(color1 === "red")
        color1 = "blue";
    else 
        color1 = "red";
}

function testeCollisionAvecMurs() {
    if((((x+100) > lc) || (x < 0)) || (((y+100)>hc) || (y<0)))
    vitesse = -vitesse;

}

function drawRectangle(x, y, l, h, c) {
    ctx.fillStyle = c;
    ctx.fillRect(x, y, l, h);
}
// DIFF entre var et let

function f() {
    if(x > 2) {
        var y = 0;
        let z = 0;
    }

    // marche, x est visible dans toute la fonction
    console.log(y);

    //marche pas, z est local au bloc if
    console.log(z);
}
