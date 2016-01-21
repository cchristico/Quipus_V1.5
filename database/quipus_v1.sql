/*nombre base de datos quipus*/
/*==============================================================*/
/* Table: DETALLE                                               */
/*==============================================================*/
create table DETALLE
(
   ID_DETALLE INT(11) NOT NULL AUTO_INCREMENT,
   ID_USUARIO           int,
   ID_FACTU             int,
   ID_FACTURA           int not null,
   primary key (ID_DETALLE)
);

/*==============================================================*/
/* Table: DETALLE_RUBRO                                         */
/*==============================================================*/
create table DETALLE_RUBRO
(
   ID_DETALLE_RUBRO     INT(11) NOT NULL AUTO_INCREMENT,
   NOMBRE_RUBRO         char(20) not null,
   VALOR_MAXIMO         decimal(10,3) not null,
   TIPO                 char(20),
   primary key (ID_DETALLE_RUBRO)
);

/*==============================================================*/
/* Table: FACTURA                                               */
/*==============================================================*/
create table FACTURA
(
   ID_FACTU             INT(11) NOT NULL AUTO_INCREMENT,
   ID_RUBRO_FACRURA     int,
   FECHA                date not null,
   primary key (ID_FACTU)
);

/*==============================================================*/
/* Table: PROVEEDOR                                             */
/*==============================================================*/
create table PROVEEDOR
(
   ID_PROVEEDOR         INT(11) NOT NULL AUTO_INCREMENT,
   ID_FACTU             int,
   NOMBRE_PROVEEDOR     char(20) not null,
   RUC_PROOVEDOR        char(13) not null,
   primary key (ID_PROVEEDOR)
);

/*==============================================================*/
/* Table: RUBRO                                                 */
/*==============================================================*/
create table RUBRO
(
   ID_RUBRO             INT(11) NOT NULL AUTO_INCREMENT,
   ID_RUBRO_FACRURA     int,
   ID_DETALLE_RUBRO     int,
   VALOR_ACTUAL         decimal(10,3),
   primary key (ID_RUBRO)
);

/*==============================================================*/
/* Table: RUBRO_FACRUEA                                         */
/*==============================================================*/
create table RUBRO_FACRUEA
(
   ID_RUBRO_FACRURA     INT(11) NOT NULL AUTO_INCREMENT,
   VALORRUBRO           decimal(12,3) not null,
   primary key (ID_RUBRO_FACRURA)
);

/*==============================================================*/
/* Table: USUARIO                                               */
/*==============================================================*/
create table USUARIO
(
   ID_USUARIO           INT(11) NOT NULL AUTO_INCREMENT,
   NOMBRE_USUARIO       char(25) not null,
   CONTRASENA_USUARIO   char(10) not null,
   CEDULA_USUARIO       char(13),
   primary key (ID_USUARIO)
);

alter table DETALLE add constraint FK_FACTURA_DETALLE foreign key (ID_FACTU)
      references FACTURA (ID_FACTU) on delete restrict on update restrict;

alter table DETALLE add constraint FK_USUARIO_DETALLE foreign key (ID_USUARIO)
      references USUARIO (ID_USUARIO) on delete restrict on update restrict;

alter table FACTURA add constraint FK_RELATIONSHIP_6 foreign key (ID_RUBRO_FACRURA)
      references RUBRO_FACRUEA (ID_RUBRO_FACRURA) on delete restrict on update restrict;

alter table PROVEEDOR add constraint FK_FACTURA_PROVEEDOR foreign key (ID_FACTU)
      references FACTURA (ID_FACTU) on delete restrict on update restrict;

alter table RUBRO add constraint FK_RELATIONSHIP_5 foreign key (ID_DETALLE_RUBRO)
      references DETALLE_RUBRO (ID_DETALLE_RUBRO) on delete restrict on update restrict;

alter table RUBRO add constraint FK_RELATIONSHIP_7 foreign key (ID_RUBRO_FACRURA)
      references RUBRO_FACRUEA (ID_RUBRO_FACRURA) on delete restrict on update restrict;
