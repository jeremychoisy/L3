<!DOCTYPE html>
<?php
  if(isset($_POST["ok"]))
  {
    $succes = 0;
    $content=file("../users.csv",FILE_IGNORE_NEW_LINES);
    if($_POST["nom"]!="" && $_POST["prenom"]!="" && $_POST["mdp"]!="" )
    {
      for($i=0;$i<count($content);$i++){
        $line=explode(";",$content[$i]);
        if($_POST["nom"]==$line[0])
        {
          if($_POST["prenom"]==$line[1])
          {
            if($_POST["mdp"]==$line[2])
            {
              echo "Vous êtes maintenant connecté.";
              $succes=1;
            }
          }
        }
      }
      if($succes=0)
      {
      echo "Aucune correspondance trouvée.";
      }
    }
    else
    {
      echo "Remplissez tous les champs.";
    }
 }
?>
<html>
  <?php
    include("./entetes.inc");
    echo load_head("Index");
  ?>
  <body>
    <form action="index.php" method="post">
      <p>
        <label for="nom">Nom :</label></br>
        <input type="text" name="nom"></br>
        <label for="prenom">Prénom :</label></br>
        <input type="text" name="prenom"></br>
        <label for="mdp">Mot de passe :</label></br>
        <input type="password" name="mdp"></br></br>
        <input type="submit" value="Se connecter" name="ok">
      </p>
    </form>
  </body>
</html>
