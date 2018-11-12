<!DOCTYPE html>
<?php
if(isset($_POST['recherche']))
{
  $rue = enlever_accent($_POST['rue'] );
  $ville = enlever_accent($_POST['ville'] );
  $string = "http://nominatim.openstreetmap.org/search?q=".$_POST['numero'] .",".urlencode($rue).",".urlencode($ville).",%20France,&format=json";
  $opts = array('http'=>array('header'=>"User-Agent: StevesCleverAddressScript 3.7.6\r\n"));
  $context = stream_context_create($opts);
  $json_source = file_get_contents($string,false,$context);
  $json_result = json_decode($json_source);


  function display_iframe($json_result){
    for($i=0;$i<sizeof($json_result);$i++){
      $url_frame = "http://www.openstreetmap.org/export/embed.html?bbox=".$json_result[$i]->{'boundingbox'}[2]."%2C".$json_result[$i]->{'boundingbox'}[0]."%2C".$json_result[$i]->{'boundingbox'}[3]."%2C".$json_result[$i]->{'boundingbox'}[1]."&amp;layer=mapnik";
      $url_meteo = "http://www.infoclimat.fr/public-api/gfs/json?_ll=".$json_result[$i]->{'lat'}.",".$json_result[$i]->{'lon'}."&_auth=ARtWQQF%2FXX8FKFRjUyUHLgJqDjsIfgQjA39QM186An8EbwNiDm4AZgdpWicGKQQyU35TMAswADAKYQpyXC5UNQFrVjoBal06BWpUMVN8BywCLA5vCCgEIwNhUDRfNgJ%2FBGIDbg5zAGMHbVo9BigEMlNlUzALKwAnCmgKaFw1VDYBZlYwAWRdNwVjVDBTfAcsAjQOPAhiBDwDZFBiX2YCYQRvAzEOaAAxB2BaOgYoBDJTZFM0CzEAOwpqCm1cOFQoAX1WSwERXSIFKlR0UzYHdQIsDjsIaQRo&_c=320734776d9112fd4d4f59efbf2ec533";
      $str='<div class="frame"><iframe
              style="border: none;box-shadow: 1px 1px 3px black;float: left; margin: 0 2em 2em 0;width:600px; height:480px;"
              src='.$url_frame.
            '>
            <p> Non supporté par votre navigateur </p>
            </iframe>';
      $str.= '<p><b>'.$json_result[$i]->{'display_name'}.'</b><p>';
      echo $str;
      echo display_meteo($url_meteo);
    }
  }
}

function display_meteo($url){
  $opts = array('http'=>array('header'=>"User-Agent: StevesCleverAddressScript 3.7.6\r\n"));
  $context = stream_context_create($opts);
  $json_source = file_get_contents($url,false,$context);
  $json_result = json_decode($json_source);
  $str = '<span>';
  for($i=2;$i<20;$i++){
    $i = $i +5 ;
    if($i==7){
      $date = date("Y-m-d ").'0'.$i.':00:00';
    }
    else {
      $date = date("Y-m-d ").$i.':00:00';
    }
    $str .= '<b> Météo au '.$date.'</b></br> Température : '.($json_result->{$date}->{'temperature'}->{'sol'}-273.15).'°</br>Humidité : '.$json_result->{$date}->{'humidite'}->{'2m'}.'%</br>Précipitation : '.$json_result->{$date}->{'pluie'}.'mm</br>Vitesse du vent: '.$json_result->{$date}->{'vent_moyen'}->{'10m'}.' km/h</br>';
    if($json_result->{$date}->{'pluie'}>0){
      $str .= '<img src="./images/rain.png" alt="Rain">';
    }
    else {
      $str .= '<img src="./images/sunny.png" alt="Rain">';
    }
    $str  .= '</span></div>';
  }
  return $str;
}

function enlever_accent($str)
{
  $a = array('À', 'Á', 'Â', 'Ã', 'Ä', 'Å', 'Æ', 'Ç', 'È', 'É', 'Ê', 'Ë', 'Ì', 'Í', 'Î', 'Ï', 'Ð',
                'Ñ', 'Ò', 'Ó', 'Ô', 'Õ', 'Ö', 'Ø', 'Ù', 'Ú', 'Û', 'Ü', 'Ý', 'ß', 'à', 'á', 'â', 'ã',
                'ä', 'å', 'æ', 'ç', 'è', 'é', 'ê', 'ë', 'ì', 'í', 'î', 'ï', 'ñ', 'ò', 'ó', 'ô', 'õ',
                'ö', 'ø', 'ù', 'ú', 'û', 'ü', 'ý', 'ÿ', 'Ā', 'ā', 'Ă', 'ă', 'Ą', 'ą', 'Ć', 'ć', 'Ĉ',
                'ĉ', 'Ċ', 'ċ', 'Č', 'č', 'Ď', 'ď', 'Đ', 'đ', 'Ē', 'ē', 'Ĕ', 'ĕ', 'Ė', 'ė', 'Ę', 'ę',
                'Ě', 'ě', 'Ĝ', 'ĝ', 'Ğ', 'ğ', 'Ġ', 'ġ', 'Ģ', 'ģ', 'Ĥ', 'ĥ', 'Ħ', 'ħ', 'Ĩ', 'ĩ', 'Ī', 'ī',
                'Ĭ', 'ĭ', 'Į', 'į', 'İ', 'ı', 'Ĳ', 'ĳ', 'Ĵ', 'ĵ', 'Ķ', 'ķ', 'Ĺ', 'ĺ', 'Ļ', 'ļ', 'Ľ', 'ľ',
                'Ŀ', 'ŀ', 'Ł', 'ł', 'Ń', 'ń', 'Ņ', 'ņ', 'Ň', 'ň', 'ŉ', 'Ō', 'ō', 'Ŏ', 'ŏ', 'Ő', 'ő', 'Œ',
                'œ', 'Ŕ', 'ŕ', 'Ŗ', 'ŗ', 'Ř', 'ř', 'Ś', 'ś', 'Ŝ', 'ŝ', 'Ş', 'ş', 'Š', 'š', 'Ţ', 'ţ', 'Ť',
                'ť', 'Ŧ', 'ŧ', 'Ũ', 'ũ', 'Ū', 'ū', 'Ŭ', 'ŭ', 'Ů', 'ů', 'Ű', 'ű', 'Ų', 'ų', 'Ŵ', 'ŵ', 'Ŷ',
                'ŷ', 'Ÿ', 'Ź', 'ź', 'Ż', 'ż', 'Ž', 'ž', 'ſ', 'ƒ', 'Ơ', 'ơ', 'Ư', 'ư', 'Ǎ', 'ǎ', 'Ǐ', 'ǐ',
                'Ǒ', 'ǒ', 'Ǔ', 'ǔ', 'Ǖ', 'ǖ', 'Ǘ', 'ǘ', 'Ǚ', 'ǚ', 'Ǜ', 'ǜ', 'Ǻ', 'ǻ', 'Ǽ', 'ǽ', 'Ǿ', 'ǿ');

  $b = array('A', 'A', 'A', 'A', 'A', 'A', 'AE', 'C', 'E', 'E', 'E', 'E', 'I', 'I', 'I', 'I', 'D', 'N', 'O',
                'O', 'O', 'O', 'O', 'O', 'U', 'U', 'U', 'U', 'Y', 's', 'a', 'a', 'a', 'a', 'a', 'a', 'ae', 'c',
                'e', 'e', 'e', 'e', 'i', 'i', 'i', 'i', 'n', 'o', 'o', 'o', 'o', 'o', 'o', 'u', 'u', 'u', 'u',
                'y', 'y', 'A', 'a', 'A', 'a', 'A', 'a', 'C', 'c', 'C', 'c', 'C', 'c', 'C', 'c', 'D', 'd', 'D',
                'd', 'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'G', 'g', 'G', 'g', 'G', 'g', 'G', 'g',
                'H', 'h', 'H', 'h', 'I', 'i', 'I', 'i', 'I', 'i', 'I', 'i', 'I', 'i', 'IJ', 'ij', 'J', 'j', 'K',
                'k', 'L', 'l', 'L', 'l', 'L', 'l', 'L', 'l', 'L', 'l', 'N', 'n', 'N', 'n', 'N', 'n', 'n', 'O', 'o',
                'O', 'o', 'O', 'o', 'OE', 'oe', 'R', 'r', 'R', 'r', 'R', 'r', 'S', 's', 'S', 's', 'S', 's', 'S',
                's', 'T', 't', 'T', 't', 'T', 't', 'U', 'u', 'U', 'u', 'U', 'u', 'U', 'u', 'U', 'u', 'U', 'u', 'W',
                'w', 'Y', 'y', 'Y', 'Z', 'z', 'Z', 'z', 'Z', 'z', 's', 'f', 'O', 'o', 'U', 'u', 'A', 'a', 'I', 'i',
                'O', 'o', 'U', 'u', 'U', 'u', 'U', 'u', 'U', 'u', 'U', 'u', 'A', 'a', 'AE', 'ae', 'O', 'o');
  return str_replace($a, $b, $str);
}


 ?>

 <html>
  <head>
    <title>WeatherMap</title>
    <meta charset="'."utf-8".'">
    <link rel="stylesheet" href="./style.css" type="text/css">
    <h1> WeatherMap </h1>
  </head>
   <body>
     <form action="nomitamim.php" method="post">
       <p>
         <label for="numero">Numéro de rue :</label></br>
         <input type="text" name="numero"></br>
         <label for="rue">Nom de la rue :</label></br>
         <input type="text" name="rue"></br>
         <label for="ville">Ville :</label></br>
         <input type="text" name="ville"></br></br>
         <input type="submit" value="Rechercher" name="recherche">
       </p>
     </form>
     <?php
     if(isset($_POST['recherche']))
     {
      display_iframe($json_result);
     }
     ?>
   </body>
 </html>
