<!DOCTYPE html>
<?php
session_start();

if(isset($_POST["creation"]))
{
    $file=fopen("../users.csv",'r+');
    $check=0;
    if($_POST["nom"]!="" && $_POST["prenom"]!="" && $_POST["mdp"]!="" )
    {
      rewind($file);
      while(($buffer=fgets($file,1024)) && $check==0)
      {
        $line=explode(";",$buffer);
        if($_POST["nom"]==$line[0])
        {
          echo "nom";
          if($_POST["prenom"]==$line[1])
          {
            echo "Nom de compte déjà existant";
            $check = 1;
          }
        }
      }
      if($check != 1)
      {
        $content=$_POST["nom"].";".$_POST["prenom"].";".$_POST["mdp"]."\r\n";
        fseek($file, 0, SEEK_END);
        fwrite($file,$content);
        echo "Création de compte réussie";
      }
      fclose($file);
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
    echo load_head("Création d'un compte");
  ?>
  <body>
    <form action="creation.php" method="post">
      <p>
        <label for="nom">Nom :</label></br>
        <input type="text" name="nom"></br>
        <label for="prenom">Prénom :</label></br>
        <input type="text" name="prenom"></br>
        <label for="mdp">Mot de passe :</label></br>
        <input type="password" name="mdp"></br></br>
        <input type="submit" value="Créer compte" name="creation">
      </p>
    </form>
  </body>
</html>
