<!DOCTYPE html>
<?php
class Bandeau{
  public static $map=[
        [
          "index.php",
          "Connexion"
        ],
        [
          "index.php",
          "Déconnexion"
        ],
        [
          "tableau.php",
          "Plan du site"
        ],
        [
          "preference.php",
          "Choix du style"
        ],
        [
          "date.php",
          "Date"
        ],
        [
          "creation.php",
          "Création d'un compte"
        ]
    ];

  public static $publicAccess=[

          "Connexion" => "accessible si deconnecté",

          "Plan du site" => "toujours accessible",

          "Choix du style" => "accessible si connecté",

          "Date" => "accessible si deconnecté",

          "Déconnexion" => "accessible si connecté",

          "Création d'un compte" => "accessible si deconnecté"
  ];

  public function genererLien($href,$name){
      $filename = basename($_SERVER['PHP_SELF']);
      if($filename==$href)
      {
        return '<div class="lien"><a class="act" href="'.$href.'">'.$name.'</a></div>';
      }
      else
      {
        return '<div class="lien"><a href="'.$href.'">'.$name.'</a></div>';
      }
  }

  public function est_affichable($name){
    switch(Bandeau::$publicAccess[$name]){
      case "toujours accessible":
        return true;
      case "accessible si connecté":
        if(isset($_SESSION['nom']))
        {
          return true;
        }
        return false;
      case "accessible si deconnecté":
        if(isset($_SESSION['nom']))
        {
        return false;
        }
      return true;
    }
  }

  public function genereBarNav(){
      $barrenav = '<div class="barrenavigation">';
      for($i=0;$i<count(Bandeau::$map);$i++)
      {
        if($this->est_affichable(Bandeau::$map[$i][1]))
        {

        $barrenav .= $this->genererLien(Bandeau::$map[$i][0],Bandeau::$map[$i][1]);
        }
      }
      if(isset($_SESSION['nom']) && $_SESSION['nom']!="")
      {
        $barrenav .= '<div class="droite">Bienvenue '.$_SESSION['nom'].', nous sommes le '.date("d/m/y").' et il est '.date("H\h i\m s\s").'.</div>';
      }
      else
      {
        $barrenav .= '<div class="droite">Bienvenue cher visiteur, nous sommes le '.date("d/m/y").' et il est '.date("H\h i\m s\s").'.</div>';
      }
      $barrenav .= '</div>';
      return $barrenav;
  }
}
 ?>
