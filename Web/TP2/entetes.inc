<?php
  function load_head($title){
    include("./bandeau.inc");
    $bandeau = new Bandeau();
    $headers = '<head>
          <title>'.$title.'</title>
      <meta charset="'."utf-8".'">
      <link rel="stylesheet" href="./nav/nav.css" type="text/css">';
        if(isset($_SESSION["stylesheet"]))
        {
          $headers .= '<link rel="stylesheet" href="'.$_SESSION["stylesheet"].'">';
        }

    $headers .= $bandeau->genereBarNav();
    $headers .= "</head>";
    return $headers;
  }
?>
