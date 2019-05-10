/*==============================================================*/
/* Nom de SGBD :  SAP SQL Anywhere 17                           */
/* Date de création :  10/05/2019 14:47:05                      */
/*==============================================================*/


if exists(select 1 from sys.sysforeignkey where role='FK_BIEN_POSSEDE_CLIENT') then
    alter table BIEN
       delete foreign key FK_BIEN_POSSEDE_CLIENT
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_TRANSACT_EST_L_OBJ_BIEN') then
    alter table TRANSACTION
       delete foreign key FK_TRANSACT_EST_L_OBJ_BIEN
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_TRANSACT_LOUE_ACHE_CLIENT') then
    alter table TRANSACTION
       delete foreign key FK_TRANSACT_LOUE_ACHE_CLIENT
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_TRANSACT_MET_EN_PL_AGENT') then
    alter table TRANSACTION
       delete foreign key FK_TRANSACT_MET_EN_PL_AGENT
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_TRANSACT_VEND_LOUE_CLIENT') then
    alter table TRANSACTION
       delete foreign key FK_TRANSACT_VEND_LOUE_CLIENT
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_VISITE_FAIT_L_OB_BIEN') then
    alter table VISITE
       delete foreign key FK_VISITE_FAIT_L_OB_BIEN
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_VISITE_FAIT_VISI_AGENT') then
    alter table VISITE
       delete foreign key FK_VISITE_FAIT_VISI_AGENT
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_VISITE_VISITE_CLIENT') then
    alter table VISITE
       delete foreign key FK_VISITE_VISITE_CLIENT
end if;

drop index if exists AGENT.AGENT_PK;

drop table if exists AGENT;

drop index if exists BIEN.POSSEDE_FK;

drop index if exists BIEN.BIEN_PK;

drop table if exists BIEN;

drop index if exists CLIENT.CLIENT_PK;

drop table if exists CLIENT;

drop index if exists TRANSACTION.MET_EN_PLACE_FK;

drop index if exists TRANSACTION.LOUE_ACHETE_FK;

drop index if exists TRANSACTION.EST_L_OBJET__FK;

drop index if exists TRANSACTION.VEND_FK;

drop index if exists TRANSACTION.TRANSACTION_PK;

drop table if exists TRANSACTION;

drop index if exists VISITE.VISITE_FK;

drop index if exists VISITE.FAIT_L_OBJET_FK;

drop index if exists VISITE.FAIT_VISITER_FK;

drop index if exists VISITE.VISITE_PK;

drop table if exists VISITE;

/*==============================================================*/
/* Table : AGENT                                                */
/*==============================================================*/
create or replace table AGENT 
(
   ID_AGENT             integer                        not null
   	constraint CKC_ID_AGENT_AGENT check (ID_AGENT >= 1),
   NOM                  varchar(20)                    not null,
   PRENOM               varchar(20)                    not null,
   TEL                  varchar(10)                    not null,
   ADRESSE              varchar(50)                    not null,
   EMAIL                varchar(50)                    null,
   constraint PK_AGENT primary key clustered (ID_AGENT)
);

/*==============================================================*/
/* Index : AGENT_PK                                             */
/*==============================================================*/
create unique clustered index AGENT_PK on AGENT (
ID_AGENT ASC
);

/*==============================================================*/
/* Table : BIEN                                                 */
/*==============================================================*/
create or replace table BIEN 
(
   ID_BIEN              integer                        not null
   	constraint CKC_ID_BIEN_BIEN check (ID_BIEN >= 1),
   ID_CLIENT            integer                        not null
   	constraint CKC_ID_CLIENT_BIEN check (ID_CLIENT >= 1),
   TYPE_TRANSACTION     varchar(10)                    not null
   	constraint CKC_TYPE_TRANSACTION_BIEN check (TYPE_TRANSACTION in ('Vente','Location')),
   TYPE                 varchar(15)                    not null
   	constraint CKC_TYPE_BIEN check (TYPE in ('Maison','Terrain','Appartement')),
   NB_PIECES            integer                        not null
   	constraint CKC_NB_PIECES_BIEN check (NB_PIECES >= 1),
   VALEUR               numeric(8,2)                   not null
   	constraint CKC_VALEUR_BIEN check (VALEUR >= 1),
   ADRESSE              varchar(50)                    not null,
   constraint PK_BIEN primary key clustered (ID_BIEN)
);

/*==============================================================*/
/* Index : BIEN_PK                                              */
/*==============================================================*/
create unique clustered index BIEN_PK on BIEN (
ID_BIEN ASC
);

/*==============================================================*/
/* Index : POSSEDE_FK                                           */
/*==============================================================*/
create index POSSEDE_FK on BIEN (
ID_CLIENT ASC
);

/*==============================================================*/
/* Table : CLIENT                                               */
/*==============================================================*/
create or replace table CLIENT 
(
   ID_CLIENT            integer                        not null
   	constraint CKC_ID_CLIENT_CLIENT check (ID_CLIENT >= 1),
   NOM                  varchar(20)                    not null,
   PRENOM               varchar(20)                    not null,
   TEL                  varchar(10)                    not null,
   ADRESSE              varchar(50)                    not null,
   EMAIL                varchar(50)                    null,
   constraint PK_CLIENT primary key clustered (ID_CLIENT)
);

/*==============================================================*/
/* Index : CLIENT_PK                                            */
/*==============================================================*/
create unique clustered index CLIENT_PK on CLIENT (
ID_CLIENT ASC
);

/*==============================================================*/
/* Table : TRANSACTION                                          */
/*==============================================================*/
create or replace table TRANSACTION 
(
   ID_TRANSACTION       integer                        not null
   	constraint CKC_ID_TRANSACTION_TRANSACT check (ID_TRANSACTION >= 1),
   ID_CLIENT            integer                        not null
   	constraint CKC_ID_CLIENT_TRANSACT check (ID_CLIENT >= 1),
   ID_BIEN              integer                        not null
   	constraint CKC_ID_BIEN_TRANSACT check (ID_BIEN >= 1),
   CLI_ID_CLIENT        integer                        not null
   	constraint CKC_CLI_ID_CLIENT_TRANSACT check (CLI_ID_CLIENT >= 1),
   ID_AGENT             integer                        not null
   	constraint CKC_ID_AGENT_TRANSACT check (ID_AGENT >= 1),
   "DATE"               date                           not null default current date,
   MONTANT              numeric(8,2)                   not null
   	constraint CKC_MONTANT_TRANSACT check (MONTANT >= 1),
   constraint PK_TRANSACTION primary key clustered (ID_TRANSACTION)
);

/*==============================================================*/
/* Index : TRANSACTION_PK                                       */
/*==============================================================*/
create unique clustered index TRANSACTION_PK on TRANSACTION (
ID_TRANSACTION ASC
);

/*==============================================================*/
/* Index : VEND_FK                                              */
/*==============================================================*/
create index VEND_FK on TRANSACTION (
ID_CLIENT ASC
);

/*==============================================================*/
/* Index : EST_L_OBJET__FK                                      */
/*==============================================================*/
create index EST_L_OBJET__FK on TRANSACTION (
ID_BIEN ASC
);

/*==============================================================*/
/* Index : LOUE_ACHETE_FK                                       */
/*==============================================================*/
create index LOUE_ACHETE_FK on TRANSACTION (
CLI_ID_CLIENT ASC
);

/*==============================================================*/
/* Index : MET_EN_PLACE_FK                                      */
/*==============================================================*/
create index MET_EN_PLACE_FK on TRANSACTION (
ID_AGENT ASC
);

/*==============================================================*/
/* Table : VISITE                                               */
/*==============================================================*/
create or replace table VISITE 
(
   ID_VISITE            integer                        not null
   	constraint CKC_ID_VISITE_VISITE check (ID_VISITE >= 1),
   ID_AGENT             integer                        not null
   	constraint CKC_ID_AGENT_VISITE check (ID_AGENT >= 1),
   ID_BIEN              integer                        not null
   	constraint CKC_ID_BIEN_VISITE check (ID_BIEN >= 1),
   ID_CLIENT            integer                        not null
   	constraint CKC_ID_CLIENT_VISITE check (ID_CLIENT >= 1),
   "DATE"               date                           not null,
   constraint PK_VISITE primary key clustered (ID_VISITE)
);

/*==============================================================*/
/* Index : VISITE_PK                                            */
/*==============================================================*/
create unique clustered index VISITE_PK on VISITE (
ID_VISITE ASC
);

/*==============================================================*/
/* Index : FAIT_VISITER_FK                                      */
/*==============================================================*/
create index FAIT_VISITER_FK on VISITE (
ID_AGENT ASC
);

/*==============================================================*/
/* Index : FAIT_L_OBJET_FK                                      */
/*==============================================================*/
create index FAIT_L_OBJET_FK on VISITE (
ID_BIEN ASC
);

/*==============================================================*/
/* Index : VISITE_FK                                            */
/*==============================================================*/
create index VISITE_FK on VISITE (
ID_CLIENT ASC
);

alter table BIEN
   add constraint FK_BIEN_POSSEDE_CLIENT foreign key (ID_CLIENT)
      references CLIENT (ID_CLIENT)
      on update restrict
      on delete restrict;

alter table TRANSACTION
   add constraint FK_TRANSACT_EST_L_OBJ_BIEN foreign key (ID_BIEN)
      references BIEN (ID_BIEN)
      on update restrict
      on delete restrict;

alter table TRANSACTION
   add constraint FK_TRANSACT_LOUE_ACHE_CLIENT foreign key (CLI_ID_CLIENT)
      references CLIENT (ID_CLIENT)
      on update restrict
      on delete restrict;

alter table TRANSACTION
   add constraint FK_TRANSACT_MET_EN_PL_AGENT foreign key (ID_AGENT)
      references AGENT (ID_AGENT)
      on update restrict
      on delete restrict;

alter table TRANSACTION
   add constraint FK_TRANSACT_VEND_LOUE_CLIENT foreign key (ID_CLIENT)
      references CLIENT (ID_CLIENT)
      on update restrict
      on delete restrict;

alter table VISITE
   add constraint FK_VISITE_FAIT_L_OB_BIEN foreign key (ID_BIEN)
      references BIEN (ID_BIEN)
      on update restrict
      on delete restrict;

alter table VISITE
   add constraint FK_VISITE_FAIT_VISI_AGENT foreign key (ID_AGENT)
      references AGENT (ID_AGENT)
      on update restrict
      on delete restrict;

alter table VISITE
   add constraint FK_VISITE_VISITE_CLIENT foreign key (ID_CLIENT)
      references CLIENT (ID_CLIENT)
      on update restrict
      on delete restrict;

