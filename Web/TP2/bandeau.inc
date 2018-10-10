<!DOCTYPE html>
<?php
class Bandeau{
  public static $map=[
        [
          "index.php",
          "Connexion"
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
        ]
    ];

  public static $publicAccess=[
        [
          "index.php",
          "toujours accessible"
        ],
        [
          "tableau.php",
          "toujours accessible"
        ],
        [
          "preference.php",
          "accessible si connecte"
        ],
        [
          "date.php",
          "accessible si deconnecte"
        ]
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

  public function genereBarNav(){
      $barrenav = '<div class="barrenavigation">';
      for($i=1;$i<count(Bandeau::$map);$i++)
      {
        $barrenav .= $this->genererLien(Bandeau::$map[$i][0],Bandeau::$map[$i][1]);
      }
      //$barrenav .= '</div>';
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
