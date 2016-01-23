/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     23/1/2016 11:08:22                           */
/* Database Name:	  quipus	                                */
/*==============================================================*/

/*==============================================================*/
/* Table: FACTURA                                               */
/*==============================================================*/
create table FACTURA
(
   ID_FACTURA           INT(11) NOT NULL AUTO_INCREMENT,
   ID_USUARIO           int,
   FECHAFACTURA         date not null,
   primary key (ID_FACTURA)
);

/*==============================================================*/
/* Table: FACTURA_RUBRO                                         */
/*==============================================================*/
create table FACTURA_RUBRO
(
   ID_DET_RUB          INT(11) NOT NULL AUTO_INCREMENT,
   ID_FACTURA           int,
   ID_RUBRO             int,
   VALOR                decimal(8,3) not null,
   primary key (ID_DET_RUB)
);

/*==============================================================*/
/* Table: PROVEEDOR                                             */
/*==============================================================*/
create table PROVEEDOR
(
   ID_PROVEEDOR         INT(11) NOT NULL AUTO_INCREMENT,
   RUC_PROVEEDOR        char(13) not null,
   NOMBRE_PROVEEDOR     char(30) not null,
   primary key (ID_PROVEEDOR)
);

/*==============================================================*/
/* Table: PROVEEDOR_FACTURA                                     */
/*==============================================================*/
create table PROVEEDOR_FACTURA
(
   ID_PROVEE_FACTURADOR INT(11) NOT NULL AUTO_INCREMENT,
   ID_FACTURA           int,
   ID_PROVEEDOR         int,
   VALOR_PROVEEDOR      decimal(8,3) not null,
   primary key (ID_PROVEE_FACTURADOR)
);

/*==============================================================*/
/* Table: RUBRO                                                 */
/*==============================================================*/
create table RUBRO
(
   ID_RUBRO             INT(11) NOT NULL AUTO_INCREMENT,
   NOMBRE_RUBRO         char(25) not null,
   VALOR_MAXIMO         decimal(8,3) not null,
   primary key (ID_RUBRO)
);

/*==============================================================*/
/* Table: USUARIO                                               */
/*==============================================================*/
create table USUARIO
(
   ID_USUARIO           INT(11) NOT NULL AUTO_INCREMENT,
   NOMBRE_USUARIO       char(40) not null,
   CEDULA_USUARIO       char(10) not null,
   primary key (ID_USUARIO)
);

alter table FACTURA add constraint FK_REFERENCE_2 foreign key (ID_USUARIO)
      references USUARIO (ID_USUARIO) on delete restrict on update restrict;

alter table FACTURA_RUBRO add constraint FK_REFERENCE_3 foreign key (ID_FACTURA)
      references FACTURA (ID_FACTURA) on delete restrict on update restrict;

alter table FACTURA_RUBRO add constraint FK_REFERENCE_4 foreign key (ID_RUBRO)
      references RUBRO (ID_RUBRO) on delete restrict on update restrict;

alter table PROVEEDOR_FACTURA add constraint FK_REFERENCE_5 foreign key (ID_FACTURA)
      references FACTURA (ID_FACTURA) on delete restrict on update restrict;

alter table PROVEEDOR_FACTURA add constraint FK_REFERENCE_6 foreign key (ID_PROVEEDOR)
      references PROVEEDOR (ID_PROVEEDOR) on delete restrict on update restrict;

