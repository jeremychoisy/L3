





---

create or replace package pk_pilote is

TYPE refCursorType IS REF CURSOR;

bad_salaire EXCEPTION;
pragma exception_init(bad_salaire, -2290);

no_data_updated EXCEPTION;
pragma exception_init(no_data_updated, -20001);

mess	varchar2(1000);

function getPiloteByID(numpil IN number) return pilote%rowtype;
procedure insertPilote(lp IN	pilote%rowtype) ;
procedure updatePiloteSalaireByAdr(adresse IN varchar2, salaire IN number) ;
function getPiloteByAdr(adresse IN varchar2) return pk_pilote.refCursorType ;

end pk_pilote;
/








create or replace package body pk_pilote is



-- select qui ramène 1 ligne

function getPiloteByID(numpil IN number) return pilote%rowtype IS

lignePilote	pilote%rowtype;

begin
select * INTO lignePilote
from pilote
where pl#=numpil;

EXCEPTION 
	WHEN NO_DATA_FOUND THEN
		raise;

	WHEN OTHERS THEN
		raise;
end getPiloteByID;



-- insert
procedure insertPilote(lp IN	pilote%rowtype) IS


begin

insert into pilote
values (lp.pl#, lp.plnom, lp.dnaiss, lp.adr, lp.tel, lp.sal);

EXCEPTION 

	WHEN PK_PILOTE.BAD_SALAIRE THEN
		raise;
	WHEN DUP_VAL_ON_INDEX THEN
		raise;	
	WHEN OTHERS THEN
		raise;
end;



-- update
procedure updatePiloteSalaireByAdr(adresse IN varchar2, salaire IN number) is

begin
	update pilote
	set sal=salaire
	where adr=adresse;

	IF SQL%ROWCOUNT = 0 THEN
		pk_pilote.mess:='Aucune ligne modifié';
		raise_application_error(-20001, mess);
		raise pk_pilote.no_data_updated;
	END IF;

EXCEPTION
	WHEN pk_pilote.no_data_updated THEN
		raise;
	WHEN OTHERS THEN
		raise;
end;



-- select qui ramène N Lignes

function getPiloteByAdr(adresse IN varchar2) return pk_pilote.refCursorType  IS

cursEmp pk_pilote.refCursorType;


begin


	open cursEmp for
	
	select * 
	FROM pilote
	where adr=adresse; 

	return 	cursEmp ;
	
end getPiloteByAdr;


end pk_pilote; 
/



-- test de la fonction updatePiloteByadr ...
select * from pilote where adr='Paris';
declare

begin

	pk_pilote.updatePiloteSalaireByAdr('Paris', 13200);


	dbms_output.put_line('Mise à jour effectuée avec succès');
EXCEPTION
	WHEN pk_pilote.no_data_updated THEN
	dbms_output.put_line('Aucune mise à jour effectuée');
	dbms_output.put_line('sqlcode='|| sqlcode);
	dbms_output.put_line('sqlerrm='|| sqlerrm);

	WHEN OTHERS THEN
	dbms_output.put_line('Problème grave');
	dbms_output.put_line('sqlcode='|| sqlcode);
	dbms_output.put_line('sqlerrm='|| sqlerrm);
end;
/

select * from pilote where adr='Paris';


-- test du select ramenant N lignes


declare
	

	pilByAdr pk_pilote.refCursorType;
	lignePilote 	pilote%rowtype;
begin


	pilByAdr:= pk_pilote.getPiloteByAdr('Paris') ;

	loop 
		fetch pilByAdr INTO lignePilote;
		EXIT WHEN pilByAdr%notfound;

		-- Affichier les informations sur le pilote extrait du curseur
		DBMS_OUTPUT.PUT_LINE(' Numéro='||lignePilote.pl#
		|| ' Nom='||lignePilote.plnom 
		|| ' salaire='||lignePilote.sal);

	end loop;
	

	EXCEPTION 
		WHEN NO_DATA_FOUND THEN
			DBMS_OUTPUT.PUT_LINE('AUCUN PILOTE TROUVE A CETTE ADRESSE');		
			dbms_output.put_line('sqlcode='|| sqlcode);
			dbms_output.put_line('sqlerrm='|| sqlerrm);

		WHEN OTHERS THEN
			dbms_output.put_line('sqlcode='|| sqlcode);
			dbms_output.put_line('sqlerrm='|| sqlerrm);

end;
/ 

