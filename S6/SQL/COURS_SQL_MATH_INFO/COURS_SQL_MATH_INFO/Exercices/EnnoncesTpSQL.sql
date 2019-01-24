-- Exercice série 1
/*
1.1 Alias des attributs
	Ecrire la requête qui présente tous les pilotes de la compagnie avec 
	le listing suivant:
	
	Numéro	Nom	Adresse	Salaire Mensuel

	SELECT PL#,PLNOM,ADR,SAL FROM PILOTE;

1.2 Redisposition des attributs
	Ecrire la requête qui présente tous les pilotes de la compagnie avec le listing suivant
	
	Nom		Salaire Mensuel	Numéro	Adresse

	SELECT PLNOM,SAL,PL#,ADR FROM PILOTE;

1.3 Alias d'une table
	Ecrire la requête qui renomme(alias) la relation PILOTE en P dans une requête.
	
	SELECT P.* FROM PILOTE P;

1.4 Calculs horizontaux
	Ecrire la requête qui calcule la durée d'un vol.

	SELECT VOL#,HA-HD "DUREE DE VOL" FROM VOL;

	Ecrire une requête qui calcule le salaire annuel SAL_ANN, pour chaque pilote.

	SELECT PL#,SAL*12 "SALAIRE ANNUEL" FROM PILOTE;

1.5 Calculs verticaux
	Ecrire une requête qui calcule la somme des salaires des pilotes.

	SELECT SUM(SAL) "SOMME DES SALAIRES" FROM PILOTE;

1.6 Distinct
	Donner tous les types d'avions de la compagnie

	SELECT DISTINCT AVTYPE FROM AVION;
*/	
	
	
	
/*
 Exercice série 2

2.1 "Numéros et type d'avions de capacité supérieure à 300"
	
	SELECT AV#,AVTYPE FROM AVION
	WHERE CAP > 300;


2.2 "Nom des pilotes habitants Nice ou Paris"

	SELECT PLNOM FROM PILOTE
	WHERE ADR = 'Nice' OR ADR = 'Paris';

2.3 "Quels sont les noms de pilotes comportant un 't' en quatrième position ou dont le nom se prononce 'Bodri'.

	SELECT PLNOM FROM PILOTE
	WHERE PLNOM LIKE '___t%' OR SOUNDEX(PLNOM)=SOUNDEX('Bodri');

2.4 "Quels sont les vols au départ de Nice, Paris ou Bordeaux ?"
	
	SELECT * FROM VOL
	WHERE VD IN ('Nice','Paris','Bordeaux');

2.5 "Quels sont les avions dont la capacité est comprise entre 250 et 310 ?"
	
	SELECT * FROM AVION
	WHERE CAP BETWEEN 250 AND 310;

2.6 "Quels sont les pilotes dont l'adresse ou le téléphone sont inconnus ?"

	SELECT * FROM PILOTE
	WHERE TEL IS NULL OR ADR IS NULL;

2.7 "Nom des pilotes ayant au moins un 'a' et un 'e' dans leur nom"

	SELECT * FROM PILOTE
	WHERE PLNOM LIKE '%a%' AND PLNOM LIKE '%e%';

2.8 "Nom des pilotes ayant 2 'o' dans leur nom "

	SELECT PLNOM FROM PILOTE
	WHERE PLNOM LIKE '%o%o%';

2.9 "Nom des pilotes dont le numéro de téléphone est renseigné"

	SELECT PLNOM FROM PILOTE
	WHERE TEL IS NOT NULL;
*/

/*
Exercice série 3
3.1 "Lister les pilotes avec leur salaire tronqués au millier"

3.2 "Lister les pilotes avec leur salaire. Pour ceux gagnant 17000,6
 remplacer le salaire par '****' "

3.3 "Sélectionner les pilotes et leur téléphone. Pour ceux dont le télephone n'est pas renseigné, mettre ? "…

3.4 Calculer la durée d'un vol de façon plus exacte
*/

/*
Exercice Série 4
4.1 "Ecrire une requête qui donne le salaire du pilote qui gagne le plus  :
		<valeur à calculer>  "Max salaire Pilote "

4.2 "Quels sont les noms, l'adresse et le salaire des pilotes de la compagnie, triés en ordre croissant sur l'adresse, et pour une même adresse en ordre décroissant sur le salaire ? "

4.3 "Ecrire une requête qui recherche si l'utilisateur courant d'Oracle est un pilote ?"

4.4 "Ecrire une requête qui rend ROWID, USER, SYSDATE, Numéros de vol de tous les vols effectués à la date d'aujourd'hui par le pilote Numéro 4 ?". L'heure de départ et d'arrivée doivent apparaître dans la liste des colonnes de projection 

*/

/*
Exercice série 5
5.1 Requêtes avec alias obligatoires (auto-jointure) et préfixage d'attributs(naming)
	
5.1.1 "Donner toutes les paires de noms de pilotes distincts, habitant la même ville"
		
5.2 Requêtes effectuant une jointure syntaxique

5.2.1 "Donner tous les noms des pilotes qui ont des noms d'avions ?"

5.2.2 "Ecrire la requête qui donne les noms des pilotes qui conduisent un A300 ou B727 ?".

5.2.3 "Tester la requête suivante : 
		(SELECT PILOTE#, VD, VA
		FROM vol)
			INTERSECT
		(SELECT AVION#, VD, VA
		FROM VOL
		);
		Quel est sa signification en langage naturel ?
		
5.3 Sous-requêtes connectées par les opérateurs ANY, ALL, EXISTS, IN.

5.3.1 "Quel est le nom des avions dont la capacité est supérieure à la capacité de chaque avion localisé à Nice ?"

5.3.2 "Quel est le nom des avions dont la capacité est au moins égale à celle d'un avion localisé à Nice ? (jamais > ou <) "

5.3.3 "Quel est le nom des pilotes assurant un vol au départ de Nice ?"

5.3.4 "Quel est le nom des pilotes assurant au moins un vol ?"


5.4 Requêtes multi-relations avec sous-requêtes indépendantes ou jointure externe

5.4.1 "Quel est le nom des pilotes dont le salaire est supérieure au salaire maximum de tous les pilotes effectuant un vol au départ de Paris ?"

5.4.2 "Quels sont les noms des pilotes qui gagnent plus que le pilote nr. 5?"

5.4.3 "Donner le nom des pilotes, et pour ceux qui sont en service, la liste des numéros de vols qu'ils assurent ?"

	
		
*/

/*
Exercice Série 6

6.1 "Quels sont les vols en correspondance (direct ou indirecte) au départ de Paris ?"

Note :  
	NICE ne doit pas être une escale de départ.

6.2 « Afficher la hiérarchie des  employés dans une l’entreprise en partant du PDG dont le nom est KING »

Note : Vous devez pour cela installer le script demobld.sql pour avoir la table EMP table des employés.

*/



/*
Exercice série 7
7.1 "Pour chaque ville de localisation d'avions de la compagnie (sauf "Paris") donner le nombre, les capacités minimales et maximales d'avions qui s'y trouvent ?"

7.2 "Quels sont les pilotes (avec leur nombre de vols ) parmi les pilotes N° 1, 2, 3 , 4 et 13 qui assurent au moins 2 vols ?"

7.3 "Quelle est la capacité moyenne des avions par ville et par type ? "


*/

/*
Exercice Série 8

8.1 Effectuer des insertions respectivement dans pilote, avion et vol. Vérifier si les contraintes l'intégrités structurelles (entitité, domaine et de référence) sont prises en comptes. Vérifier aussi les valeurs nulles.

Note : insérer un pilote ayant votre nom de login oracle et 2 vols effectués par ce pilote.

8.2 Effectuer une insertion dans la table PILOTE2 via une sous-requête sur PILOTE.

8.3 Mettre à jour le salaire du pilote numéro 3 à 19000 F et Valider.

8.4 Supprimer le pilote numéro 11 et invalider.

8.5 Supprimer les lignes de la tables PILOTE2 via TRUNCATE. Tentez un ROLLBACK.	



*/

/*
EXERCICES Série 9
9.1 "Créer une relation FORMATION, qui contiendra les renseignements suivants :
		- le numéro de pilote ,
		- le type de formation (ATT, VDN, PAI, ...)
		- type d'appareil
		- date de la formation "
Attention :	- un pilote à une date donnée participe à une formation
		- un type d'appareil doit être : 'A300', 'A310', 'A320', 'B707', 'Caravelle', 'B727' 
		ou 'Concorde'
	La clé primaire doit être définie sur le numéro du pilote le type de formation et la date de formation.

9.2 Créer un index unique sur la colonne  PLNOM de PILOTE. Que constatez  vous. 

9.3 Créer également un index  sur la colonne AVTYPE de la table FORMATION. 

9.4 insérer deux lignes dans la table Formation (2 formations pour le pilote Nr 3)

*/


/*
Exercice serie 10
10.1 "Ajouter la colonne AGE à la table PILOTE. Un pilote doit avoir entre 25 et  60 ans.

10.2 "Ajouter une contrainte d'intégrité de référence au niveau table à la relation FORMATION (colonne PILOTE#)"

10.3 "Modifier la colonne PL# de la table PILOTE en number(5).

10.4 Ajouter une valeur par défaut à la colonne VD dans VOL.

10.5 "Associer à l'attribut SAL (salaire)  d'un pilote un commentaire puis s'assurer de son existence. Comment supprime - t-on un commentaire ?"

10.6 "Consulter la liste des colonnes de la table FORMATION"

10.7 "Attribuer un synonyme "Conducteurs" à la table PILOTE.

*/

/*
EXERCICES Série 11
11.1 "Quels sont les noms des colonnes de la table VOL ?" (voir : user_tab_columns, user_tables,…)
Notes :
	-cols est un synonyme de user_tab_columns

11.2 "Quels sont les tables et les vues de votre schéma ?" (voir tab, cat, user_tables et user_views)
Notes :
	-cat est un synonyme de user_catalog 
	-Tabletyp est le type de la colonne (une table, une vue...)
		
11.3 "Quelles sont les tables qui contiennent une colonne PL# ?"

11.4 "Quelles sont les vues du dictionnaire d'Oracle (voir DICT ou DICTIONARY) ? "

11.5 "Quels sont les tables appartenant à l'utilisateur SCOTT ?"

11.6 "Quels sont les contraintes existant dans votre schéma pour la table PILOTE ?"

*/


/*
EXERCICES Série 12
-- T1 : 
	INSERT INTO pilote 
		values(25, 'Conficias', '19-SEP-42', 'Pekin', '13548254', 39000.0,null); 
	select * from pilote where pl#=25;
	COMMIT ;
	select * from pilote where pl#=25;
-- T2 :	
	UPDATE pilote SET plnom='Conficios' WHERE plnom='Conficias';
	select * from pilote where pl#=25;
	ROLLBACK ;
	select * from pilote where pl#=25;
--T3 :	
	UPDATE pilote SET plnom='Conficies' WHERE plnom='Conficias';
	select * from pilote where pl#=25;

	SAVEPOINT updt_conf1;
	
	UPDATE pilote SET	
 	plnom='Conficius' WHERE plnom='Conficies';
	
	SAVEPOINT updt_conf2 ;
	select * from pilote where pl#=25;

	UPDATE pilote SET plnom='Conficios' WHERE plnom='Conficius';
	select * from pilote where pl#=25;

	ROLLBACK TO updt_conf1 ;
	select * from pilote where pl#=25;

	UPDATE pilote SET plnom='Conficius' WHERE plnom='Conficies';
	select * from pilote where pl#=25;
		
	UPDATE pilote SET sal=40000.0 WHERE plnom='Conficius';
	select * from pilote where pl#=25;

	COMMIT ;
	select * from pilote where pl#=25;


*/

/*
EXERCICES Série 13

Pour effectuer ces tests il est nécessaire d'ouvrir deux sessions. Commentez les étapes où il y a un ?

*/

-- *** Transaction 1 
-- 1
LOCK TABLE pilote
IN ROW SHARE MODE ;

-- *** Transaction 2
-- 2
DROP TABLE pilote ;

? 
-- 3
LOCK TABLE pilote
IN EXCLUSIVE MODE 
NOWAIT;

?
-- 4
select sal from pilote
where pilote.adr= 'Paris'
FOR UPDATE OF sal nowait ;

-- *** Transaction 1
-- 5
UPDATE pilote
set sal = 12000.9
where adr = 'Paris';
(attente de T2) 

-- *** Transaction 2
-- 6
ROLLBACK ;
(libération des lignes verrouillées par T2) 

-- *** Transaction 1
-- 7
ROLLBACK; 
?

-- 8
LOCK TABLE pilote
IN ROW EXCLUSIVE MODE;


-- *** Transaction 2
-- 9
LOCK TABLE pilote
IN EXCLUSIVE MODE NOWAIT;
?

-- 10
LOCK TABLE pilote
 IN SHARE ROW EXCLUSIVE 
MODE NOWAIT ;
?

-- 11
LOCK TABLE pilote
 IN SHARE MODE NOWAIT; 
?

-- 12
UPDATE pilote set sal = 12000.9
where adr = 'Paris';
?

-- 13
ROLLBACK;
?

-- *** Transaction 1
-- 14
SELECT sal
FROM pilote
WHERE adr='Paris'
FOR UPDATE of sal;
?

-- *** Transaction 2
-- 15
UPDATE pilote set sal = 12000.9
where adr = 'Paris';
(attente T1) 

-- *** Transaction 1

-- 16
ROLLBACK; 

-- *** Transaction 2
-- 17
ROLLBACK; 
?

-- *** Transaction 1
-- 18
LOCK TABLE pilote
IN SHARE MODE ;
?

-- *** Transaction 2
-- 19
LOCK TABLE pilote
IN EXCLUSIVE MODE 
NOWAIT ;
?

-- 20
LOCK TABLE pilote
IN SHARE ROW EXCLUSIVE 
MODE NOWAIT ;
?
-- 21
LOCK TABLE pilote
IN SHARE MODE nowait ;
?

-- 21a
LOCK TABLE pilote
IN ROW EXCLUSIVE MODE nowait ;
?
-- 21b
LOCK TABLE pilote
IN ROW SHARE MODE nowait;
?

-- 22
select sal from pilote 
where adr= 'Paris';
?

-- 23
select sal from pilote where adr= 'Paris'
FOR UPDATE OF sal nowait ;
?

-- 24
UPDATE pilote set sal = 12000.9
where adr = 'Paris';
(attente T1)

-- *** Transaction 1
-- 25
ROLLBACK ; 
?

-- *** Transaction 2
-- 26
X lignes modifiées
(après libération du verrou par T1)
ROLLBACK ; 

-- *** Transaction 1
-- 27
LOCK TABLE pilote
IN SHARE ROW EXCLUSIVE MODE;
?

-- *** Transaction 2
-- 28
LOCK TABLE pilote
in EXCLUSIVE MODE
NOWAIT;
?

-- 29
LOCK TABLE pilote
in SHARE ROW EXCLUSIVE MODE NOWAIT;
?

-- 30
LOCK TABLE pilote
in SHARE MODE NOWAIT;
?

-- 31
LOCK TABLE pilote
in ROW EXCLUSIVE MODE
NOWAIT;
?
-- 32
LOCK TABLE pilote
IN ROW SHARE MODE;
?

-- 33
select sal from pilote
where adr= 'Paris';
?

-- 34
select sal from pilote
where adr= 'Paris'
FOR UPDATE OF sal nowait ;
?

-- *** Transaction 2
-- 35
UPDATE VOL SET VD='Nice'
WHERE VOL#=310; 
?

-- 36
UPDATE pilote set sal = 12000.9
where adr = 'Paris';
(attente T1)

-- *** Transaction 1
-- 37
UPDATE VOL
SET VD='Toulouse'
WHERE VOL#=310;
(ATTENTE de T2) 

-- *** Transaction 2
-- 37
(attente verrou mortel)

-- ***  Transaction 2
-- 38
(dead lock détecté,
Transaction la plus récente est arrêtée)
ROLLBACK; 

-- *** Transaction 1
-- 39
ROLLBACK
?

-- 40
LOCK TABLE pilote
IN EXCLUSIVE MODE;
?

-- *** Transation 2
-- 41
LOCK TABLE pilote
IN EXCLUSIVE MODE 
NOWAIT;
?
-- 42
LOCK TABLE pilote
IN SHARE ROW EXCLUSIVE MODE NOWAIT;
?

-- 43
LOCK TABLE pilote
IN SHARE MODE NOWAIT;
?

-- 44
LOCK TABLE pilote
IN ROW EXCLUSIVE MODE 
NOWAIT;
?

-- 45
LOCK TABLE pilote
IN ROW SHARE MODE 
NOWAIT;
?

-- 46
select sal from pilote
where adr= 'Paris';
?

-- 47
select sal from pilote
where adr= 'Paris'
FOR UPDATE OF sal  nowait;

-- *** Transaction 1

-- 48
UPDATE pilote
set sal = 12000.9

-- 49
ROLLBACK; 

-- *** Transaction 2
-- 50
ROLLBACK; 



/*
EXERCICES Série 13b

 Ecrire une transaction  SQL (à ne pas confondre avec les transactions bancaires) qui permet d’effectuer une opération de type DEBIT d’un compte et CREDIT d’un autre compte  (on parlera aussi de virement)  avec mise à jour des soldes. 
Cas 1: verrous implicites uniquement
Cas 2 :Vous devez poser les verrous de façon appropriée avant de commencer les opérations sur les comptes. Le choix des verrous doit favoriser au maximum la concurrence d’accès en mise à jour
Cas 3: avec verrous explicites mais on doit débiter un compte épargne et créditer un compte chèque. Attention: le compte épargne ne doit pas devenir négatif

*/

/*
EXERCICES Série 14

Indépendance logique/externe : vue de sélection
14.1 - "Créer une vue AVA300 qui donne tous les A300 de la compagnie"
14.2 - "Que se passe - t-il à l'insertion d'un "B707" dans la vue ?"

Indépendance logique/externe : renommage et ré-ordonnancement des colonnes
14.3 - "Créer une vue PAYE qui donne pour chaque pilote son salaire mensuel et annuel"
14.4 - "Créer une vue AVPLUS qui donne tous les numéros d'avions conduits par plus d'un pilote."
14.5 - "Créer une vue PILPARIS qui donne les noms, les numéros de vols, des pilotes qui assurent un vol au départ de Paris"

Création d'une vue pour assurer la confidentialité
14.6 "Créer une vue PILSANS qui donne les renseignements concernant les pilotes, sans le salaire."


Vues issues d'une table
14.7 "Créer une vue AVIONNICE : Ensemble des avions localisés à Nice"
	Modification à travers une vue
		1) Lister l'extension de la vue AVIONNICE
		2) Mise à jour d'un tuple dans cette vue : localiser l'avion de n° 5 à Paris
		3) Mise à jour d'un tuple dans cette vue : localiser l'avion n° 7 à Nice
		4) Lister la table de base AVION. Que constatez-vous ?
	Insertion dans la vue
		1) Insérer le tuple (11, 'A300', 220, 'Nice', 'EN service');
		2) lister l'extension de la vue AVIONNICE
		3) lister la table de base AVION.
	
	Suppression dans la vue
		1) Suppression de l'avion N° 11
		2) lister l'extension de la vue AVIONNICE
		3) lister la table AVION.
	
Vues issues de plusieurs tables
14.8 "Créer une vue AVSERVPARIS : Ensemble des avions en service localisés à Paris"
	Modification de la vue
		1) lister l'extension de la vue AVSERVPARIS
		2) mise à jour d'un tuple de cette vue. Que remarque-t-on ?"
	Insertion dans la vue
		1) recréez la vue avec jointure
		2) insertion d'un tuple dans la vue AVSERVPARIS. Que remarque-t-on?

	suppression dans la vue
		1) suppression de tous les pilotes de n° inférieur à 7 dans AVSERVPARIS
Vues contenant des colonnes virtuelles
14.9 "Reprendre la vue PAYE  et lister son contenu"
			
	Modification via la vue
		1) Mise à jour  d'un tuple dans cette vue : mettre le salaire du pilote 1 à 0
		2) lister le contenu de cette vue. Que remarque--on ?
	Insertion via la vue
		1) insertion d'un tuple dans la vue PAYE . Que remarque-t-on ?
	Suppression via la vue
		1) suppression de tous les pilotes dont le salaire annuel est supérieur à 180000.
	
	Vues contenant une clause GROUP BY
"Reprenons la vue AVPLUS. Llister cette vue"
"Quels sont le n° d'avions conduits par plus d'un pilote et localisés à Paris ?


*/





