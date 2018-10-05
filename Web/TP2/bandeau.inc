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

    private function genererLien($href,$name){
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
      $barrenav .= '</div>';
      return $barrenav;
    }
  }
    ?>
