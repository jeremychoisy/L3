<!DOCTYPE html>
<?php
/*Initialisation des tableaux */
  $tab=[
    "date.php"=> [
                    "01-10-2018",
                    "1856"
                  ],
    "tableau.php"=> [
                      "01-10-2018",
                      "1875"
                    ]
  ];

  $titre=[
            "Nom du fichier",
            "Date de modification",
            "taille"
  ];
  /* Fonction genere_tableau
  function genere_tableau($title,$body){
    $taille_tableau = count($body);
    echo "<table>
          <caption> Nombre de ligne du tableau : $taille_tableau </caption>
          <thead>


  }*/
  ?>

<html>
  <head>
    <title> Tableau en php </title>
    <meta charset="utf-8">
  </head>
  <body>
    <pre>
      <?php
        var_dump($tab);
        print_r($titre);
        echo count($tab);
      ?>
    </pre>
  </body>
</html>
