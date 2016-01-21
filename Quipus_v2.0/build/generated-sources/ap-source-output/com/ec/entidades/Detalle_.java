package com.ec.entidades;

import com.ec.entidades.Factura;
import com.ec.entidades.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-01-20T21:47:51")
@StaticMetamodel(Detalle.class)
public class Detalle_ { 

    public static volatile SingularAttribute<Detalle, Integer> idDetalle;
    public static volatile SingularAttribute<Detalle, Factura> idFactu;
    public static volatile SingularAttribute<Detalle, Integer> idFactura;
    public static volatile SingularAttribute<Detalle, Usuario> idUsuario;

}