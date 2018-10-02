<!DOCTYPE html>
<?php
  session_name("Jeremy");
  session_start();

  $date = date("j F y");
  $heure = date("H\h i\m s\s");
  $chaine_date="Nous sommes le $date, il est $heure.";

  function comparaison_date($date_1){
    $date_str=strtotime("now");
    $date_1_str=strtotime($date_1);
    if($date_str>$date_1_str){
      $duree=$date_str-$date_1_str;
      $jours=floor($duree/86400);
      $secondes=$duree%86400;
      return "Il est passé $jours jours et $secondes secondes depuis  $date_1.";
    }elseif($date_str<$date_1_str){
      $duree=$date_1_str-$date_str;
      $jours=floor($duree/86400);
      $secondes=$duree%86400;
      return "Il reste $jours jours et $secondes secondes jusqu'au $date_1.";
    }
    return "Il s'agit de la date d'aujourd'hui.";
  }

  $mois=[
    "01"=>"Janvier",
    "02"=>"Février",
    "03"=>"Mars",
    "04"=>"Avril",
    "05"=>"Mai",
    "06"=>"Juin",
    "07"=>"Juillet",
    "08"=>"Août",
    "09"=>"Septembre",
    "10"=>"Octobre",
    "11"=>"Novembre",
    "12"=>"Décembre"
  ];
?>
<html>
<?php
  include("./entetes.inc");
  echo load_head("Date en php");
?>
  <body>
    <?php
      echo $chaine_date;
      echo "<br/>";
      echo comparaison_date("1 November 2018");
    ?>
    <form action="date.php" method="post">
      <p>
        <label for="date">Entrez une date</label> : </br> <input type="date" name="date"/>
        <input type="submit" value="Valider"/>
      </p>
    </form>
    <?php
      if(isset($_POST["date"])){
        $list=explode("-",$_POST["date"]);
        echo "Nous sommes le ".$list[2]." ".str_replace($list[1],$mois[$list[1]],$list[1])." ".$list[0].".";
        echo "<br/>";
        echo comparaison_date($_POST["date"]);
      }
     ?>
  </body>
</html>
