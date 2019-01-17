<!DOCTYPE html>
<?php
  session_start();

  if(isset($_POST["deconnexion"])){
    session_unset();
    session_destroy();
    header ('location: index.php');
  }

  if(isset($_POST["connexion"]))
  {
      $content=file("../users.csv",FILE_IGNORE_NEW_LINES);
      $check = 0;
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
                $_SESSION['nom']=$_POST["prenom"]." ".$_POST["nom"];
                $check = 1;
              }
            }
          }
        }
        if($check == 0)
        {
          echo "Aucune correspondance trouvée.";
        }
      }
    else
    {
        echo "Remplissez tous les champs.";
    }
  }

  if(isset($_SESSION['nom']))
  {
    echo generer_form_deconnexion();
  }
  else
  {
    echo generer_form_connexion();
  }


  function generer_form_connexion(){
    include("./entetes.inc");
    $codehtml='<html>'.load_head("Connexion").'
      <body>
        <form action="index.php" method="post">
          <p>
            <label for="nom">Nom :</label></br>
            <input type="text" name="nom"></br>
            <label for="prenom">Prénom :</label></br>
            <input type="text" name="prenom"></br>
            <label for="mdp">Mot de passe :</label></br>
            <input type="password" name="mdp"></br></br>
            <input type="submit" value="Se connecter" name="connexion">
          </p>
        </form>
      </body>
    </html>';
   return $codehtml;
  }

  function generer_form_deconnexion(){
    include("./entetes.inc");
    $codehtml2='<html>'.load_head("Déconnexion").'
      <body>
        <form action="index.php" method="post">
          <p>
            <input type="submit" value="Se Déconnecter" name="deconnexion">
          </p>
        </form>
      </body>
    </html>';
    return $codehtml2;
  }
?>
