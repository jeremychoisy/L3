/*

Ce script permet de mettre en place une application bancaire
cette application comporte trois tables : compte, client,
 transaction.

Il existe deux types de comptes : COMPTE CHEQUE et COMPTE EPARGNE. Le solde d’un compte ne peut être négatif pour un compte épargne.
Une OPERATION dans une transaction peut être de type DEBIT ou 
CREDIT. Une opération à toujours lieu à une date donnée et elle*
 implique un montant donné.
Un client peut avoir un ou plusieurs comptes. Un compte 
appartient à un et un seul client.
Sur un compte on peut effectuer zéro, une ou plusieurs 
transactions. Une transaction est effectuée sur un et un 
seul compte.
Nota : Les opérations sur un compte passent par la création 
de lignes dans la table TRANSCTION puis la mise à jour 
appropriée du solde du compte (DEBIT ou CREDIT).

*/
drop table transaction cascade constraint;
drop table compte cascade constraint;
drop table client cascade constraint;

Create table client (
clientId    number(8) constraint pk_client_clientId  primary key,
nom         varchar2(50),
prenom      varchar2(50),
telephone   varchar2(12),
photo	   blob	
) ;

Create table compte(
compteId number(8) constraint pk_compte_compteId primary key,
clientId number(8) constraint fk_trans_clientId  references client(clientid),
type_compte varchar2(30) constraint nl_compte_typeCpt not null
		constraint chk_compte_type_cpt 
		check(type_compte IN('COMPTE CHEQUE' , 'COMPTE EPARGNE')),
libelle varchar2(100),
solde number(9,2)
) ;


Create table transaction(
transactionId number(8) constraint pk_transaction_trId primary key,
operation varchar2(30) constraint chk_transaction_op check(operation IN('DEBIT' , 'CREDIT')),
montant number(9,2),
date_operation 	date,
compteId number(8) constraint fk_trans_compteId references compte(compteid)
) ;


 create or replace trigger tr_compteSolde
    after update or insert or delete on transaction for each row
 declare
 tr_montant number:=0;
 begin


	if inserting then	
		if :new.operation='DEBIT' then
			tr_montant:=-:new.montant;
		else
			tr_montant:=:new.montant;
			
		end if;
		

		update compte set solde=solde+ tr_montant 
		where compteid=:new.compteid ;
		
	else 
		raise_application_error(-20000, 'Update not allowed');
	end if;
	
   EXCEPTION 
	WHEN OTHERS THEN
                        
	raise;
   
   end;
/

-- 1point pour les 2 lignes
insert into client values(100, 'Akim', 'Leroi', '11111', null);
insert into client values(101, 'Bleck', 'Leroc', '22222', null);

-- 1 point pour les 2 lignes
insert into compte 
values(1000, 100, 'COMPTE CHEQUE', 'Mon compte chèque', 1000);
insert into compte 
values(1001, 101, 'COMPTE EPARGNE', 'Mon compte épargne', 2000);

---

insert into transaction
values(1, 'DEBIT', 400, sysdate, 1000);
insert into transaction
values(2, 'CREDIT', 400, sysdate, 1001);
insert into transaction
values(3, 'DEBIT', 200, sysdate, 1000);
insert into transaction
values(4, 'CREDIT', 200, sysdate, 1001);


-- validation
commit;



