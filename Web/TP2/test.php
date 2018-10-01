<?php
  $date="<p>Nous sommes le ".date("j F Y")." et il est ".date("G \h\\e\\u\\r\\e\s , i \m\i\\n\u\\t\\e\s \\e\\t s \s\\e\c\o\\n\d\\e\s.")."<p>";
  $jours_restants="<p>Il reste ".date("j F Y",strtotime("01/11/18")-strtotime("29/09/18"))." avant le 1er Novembre 2017<p>";
?>
<html>
<head>
 <title>Date en PHP</title>
</head>
<body>
<?php
  echo $date;
  echo $jours_restants;
?>

</body>
</html>
