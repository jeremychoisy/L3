<!DOCTYPE html>
<?php
  session_name("Jeremy");
  session_start();

  if(isset($_POST['select_style']))
  {
    $_SESSION["stylesheet"]=$_POST['select_style'];
  }
?>
<html>
  <?php
    include("./entetes.inc");
    load_head("Préférence en php");
  ?>
  <body>
    <form action="preference.php" method="post">
      <p>
        <select name="select_style">
          <?php foreach(glob("*.css") as $filename)
          {
            if(isset($_POST['select_style']) && $_POST['select_style']=="./".$filename)
            {
              echo '<option value="./'.$filename.'"selected>'.$filename."</option>";
            }
            else
            {
              echo '<option value="./'.$filename.'">'.$filename."</option>";
            }
          }
          ?>
        </select>
        <input type="submit" value="Validez"/>
      </p>
    </form>
  </body>
</html>
