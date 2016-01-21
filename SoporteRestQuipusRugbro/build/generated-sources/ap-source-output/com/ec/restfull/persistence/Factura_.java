package com.ec.restfull.persistence;

import com.ec.restfull.persistence.Proveedor;
import com.ec.restfull.persistence.Rubro;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-01-18T19:12:05")
@StaticMetamodel(Factura.class)
public class Factura_ { 

    public static volatile SingularAttribute<Factura, Date> fecha;
    public static volatile SingularAttribute<Factura, Integer> idFactu;
    public static volatile SingularAttribute<Factura, Proveedor> idProveedor;
    public static volatile CollectionAttribute<Factura, Rubro> rubroCollection;

}