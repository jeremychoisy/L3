let canvas, ctx;
let lc, hc;

class Rectangle {
  // PLUS JAMAIS LE MOT FUNCTION !!!
  constructor(x, y, l, h, vx, vy, couleur) {
    // on définit les propriétés qu'on veut avoir à la construction
    this.x = x;
    this.y = y;
    this.l = l;
    this.h = h;let canvas, ctx;
let lc, hc;

class Rectangle {
  // PLUS JAMAIS LE MOT FUNCTION !!!
  constructor(x, y, l, h, vx, vy, couleur) {
    // on définit les propriétés qu'on veut avoir à la construction
    this.x = x;
    this.y = y;
    this.l = l;
    this.h = h;
    this.vx = vx;
    this.vy = vy;
    this.couleur = couleur;
  }
  
  draw(ctx) {
    ctx.fillStyle = this.couleur;
    ctx.fillRect(this.x, this.y, this.l, this.h);
  }
  
  move() {
    this.x += this.vx;
    this.y += this.vy;
  }
  
  decrisToi() {
    return "Je suis un rectangle de couleur : " + this.couleur;
  }
}

let tableauDesRectangles = [];

window.onload = function () {
    // Appelé quand la page est prête et a chargé
    // toutes ses ressources (images, vidéos etc.)
    console.log("pret")
    canvas = document.querySelector("#myCanvas");
    lc = canvas.width;
    hc = canvas.height;

    ctx = canvas.getContext("2d");
  
    creerDesRectangles(10);
  
    // Pour animation à 60 im/s
    requestAnimationFrame(anime);
}

function creerDesRectangles(n) {
  for(let i = 0; i < n; i++) {
    let l = 10 + Math.random() * 200;
    let h = 10 + Math.random() * 200;
    let x = Math.random() * (lc-l);
    let y  = Math.random() * (hc-h);
    let r = Math.round(Math.random() * 255);
    let g = Math.round(Math.random() * 255);
    let b = Math.round(Math.random() * 255);
    let vx = 2 + Math.random()*10;
    let vy = 2 + Math.random()*10;
    let c = "rgb(" + r + "," + g + "," + b + ")";
    
    let rect = new Rectangle(x, y, l, h, vx, vy, c);
    
    tableauDesRectangles.push(rect);
  }
}
function dessinerLesRectangles() {
  tableauDesRectangles.forEach((r) => {
    r.draw(ctx);
  })
}

function deplacerLesRectangles() {
  tableauDesRectangles.forEach((r) => {
    r.move();
  });
}

function testeCollisionAvecMurs() {
  tableauDesRectangles.forEach((r) => {
    if(((r.x+r.l) > lc) || (r.x < 0)) 
    r.vx = -r.vx;
    
     if(((r.y+r.h) > hc) || (r.y < 0)) 
    r.vy = -r.vy;
  });
    
}

function anime() {
    // 1 On efface le canvas
    ctx.clearRect(0, 0, lc, hc);

    // 2 On dessine
    dessinerLesRectangles();

    // 3 On change l'état (position, couleur, taille etc.)
    deplacerLesRectangles();
  
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
    this.vx = vx;
    this.vy = vy;
    this.couleur = couleur;
  }
  
  draw(ctx) {
    ctx.fillStyle = this.couleur;
    ctx.fillRect(this.x, this.y, this.l, this.h);
  }
  
  move() {
    this.x += this.vx;
    this.y += this.vy;
  }
  
  decrisToi() {
    return "Je suis un rectangle de couleur : " + this.couleur;
  }
}

let tableauDesRectangles = [];

window.onload = function () {
    // Appelé quand la page est prête et a chargé
    // toutes ses ressources (images, vidéos etc.)
    console.log("pret")
    canvas = document.querySelector("#myCanvas");
    lc = canvas.width;
    hc = canvas.height;

    ctx = canvas.getContext("2d");
  
    creerDesRectangles(10);
  
    // Pour animation à 60 im/s
    requestAnimationFrame(anime);
}

function creerDesRectangles(n) {
  for(let i = 0; i < n; i++) {
    let l = 10 + Math.random() * 200;
    let h = 10 + Math.random() * 200;
    let x = Math.random() * (lc-l);
    let y  = Math.random() * (hc-h);
    let r = Math.round(Math.random() * 255);
    let g = Math.round(Math.random() * 255);
    let b = Math.round(Math.random() * 255);
    let vx = 2 + Math.random()*10;
    let vy = 2 + Math.random()*10;
    let c = "rgb(" + r + "," + g + "," + b + ")";
    
    let rect = new Rectangle(x, y, l, h, vx, vy, c);
    
    tableauDesRectangles.push(rect);
  }
}
function dessinerLesRectangles() {
  tableauDesRectangles.forEach((r) => {
    r.draw(ctx);
  })
}

function deplacerLesRectangles() {
  tableauDesRectangles.forEach((r) => {
    r.move();
  });
}

function testeCollisionAvecMurs() {
  tableauDesRectangles.forEach((r) => {
    if(((r.x+r.l) > lc) || (r.x < 0)) 
    r.vx = -r.vx;
    
     if(((r.y+r.h) > hc) || (r.y < 0)) 
    r.vy = -r.vy;
  });
    
}

function anime() {
    // 1 On efface le canvas
    ctx.clearRect(0, 0, lc, hc);

    // 2 On dessine
    dessinerLesRectangles();

    // 3 On change l'état (position, couleur, taille etc.)
    deplacerLesRectangles();
  
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
