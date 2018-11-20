// Objet litteraux ou objet singleton
var r1 = {
    // propriété : valeur
    position: {
      x:10,
      y:20
    },
    l:100,
    h:200,
    couleur:"blue",
    decrisToi : function() {
      return "Je suis un rectangle de couleur : " + this.couleur;
    },
    quiEsTu : function() {
      return this.decrisToi();
    }
  }
  
  var r2 = {
    // propriété : valeur
    x:10,
    y:20,
    l:100,
    h:200,
    couleur:"pink",
    decrisToi : function() {
      return "Je suis un rectangle de couleur : " + this.couleur;
    },
    quiEsTu : function() {
      return this.decrisToi();
    }
  }
  
  // Depuis JavaScript 6 / ES2015 on a des classes
  // LA CLASSE DOIT ETRE DEFINIE AVANT SON UTILISATION
  class Rectangle {
    // PLUS JAMAIS LE MOT FUNCTION !!!
    constructor(x, y, couleur) {
      // on définit les propriétés qu'on veut avoir à la construction
      this.x = x;
      this.y = y;
      this.couleur = couleur;
    }
    
    decrisToi() {
      return "Je suis un rectangle de couleur : " + this.couleur;
    }
  }
  
  let tableauRectangles = [];
  
  function creerDesRectangles(n) {
    //console.log("n = " + n);
    
    for(let i = 0; i < n; i++) {
      let r = new Rectangle(Math.random() * 300,
                           Math.random() * 300,
                           "red");
        // On ajoute au tableau
        tableauRectangles.push(r) 
    }
  }
  
  function afficheTousLesRectangles() {
    // Il y a plusieurs manières d'itérer sur un tableau
    
    // 1 avec l'itérateur forEach
    tableauRectangles.forEach((elementCourant, index, tab) => {
      console.log("Rectangle x = " + elementCourant.x);
    });
    
    // 2 avec une boucle classique
    /*
    for(let i = 0; i < tableauRectangles.length; i++) {
      console.log("Rectangle x = " + tableauRectangles[i].x);
    }
    */
  }