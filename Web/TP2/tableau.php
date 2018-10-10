<!DOCTYPE html>
<?php
  session_name("Jeremy");
  session_start();

/*Initialisation des tableaux */
  $tab=[
    [
            "date.php",
            "01-10-2018",
            "1856"
                  ],
    [
            "tableau.php",
            "01-10-2018",
            "1875"
                    ]
  ];

  $titre=[
            "Nom du fichier",
            "Date de modification",
            "taille"
  ];
  /* Fonction genere_tableau */
  function genere_tableau($title,$body){
    $code_html ='<table border="1px">
                 <caption align="bottom"> Nombre de ligne du tableau : '.count($body)."</caption>
                 <thead>
                <tr>";
    for ($i=0;$i<count($title);$i++)
    {
      $code_html .= "<th>".$title[$i]."</th>";
    }
    $code_html .= "</tr>
          </thead>
          <tbody>";
    for($i=0;$i<count($body);$i++)
    {
      $code_html .= "<tr>";
      for($j=0;$j<count($body[$i]);$j++)
      {
        $code_html .= "<td>".$body[$i][$j]."</th>";
      }
      $code_html .= "</tr>";
    }
    $code_html .= "</tbody>
          </table>";
    return $code_html;
  }

  function genere_listeFichier(){
    $i=0;
    foreach(glob("*.*") as $filename){
      $listeFichier[$i][0]='<a href="./'.$filename.'">'.$filename."</a>";
      $listeFichier[$i][1]=date("d-m-Y",filemtime($filename));
      if(is_file($filename))
      {
        $listeFichier[$i][2]=filesize($filename);
      }
      else
      {
        $listeFichier[$i][2]="";
      }
      $i+=1;
    }
    if(isset($_GET['sort']))
    {
      if($_GET['sort']=="ascending")
      {
        sort($listeFichier);
      }
      else if($_GET['sort']=="descending")
      {
        rsort($listeFichier);
      }

    }
    return $listeFichier;
  }
  ?>

<html>
<?php
  include("./entetes.inc");
  echo load_head("Tableau en php");
?>
  <body>
      <?php
      echo '</br>';
      echo genere_tableau($titre,genere_listeFichier());
      ?>
      <a href="./tableau.php?sort=ascending"> Tri croissant </a></br>
      <a href="./tableau.php?sort=descending"> Tri décroissant </a>
  </body>
</html>
