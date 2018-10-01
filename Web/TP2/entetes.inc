<?php
  function load_head($title){
    echo '<head>
          <title>'.$title.'</title>
      <meta charset="'."utf-8".'">';
        if(isset($_SESSION["stylesheet"]))
        {
          echo '<link rel="stylesheet" href="'.$_SESSION["stylesheet"].'">';
        }

    echo "</head>";
  }
?>
