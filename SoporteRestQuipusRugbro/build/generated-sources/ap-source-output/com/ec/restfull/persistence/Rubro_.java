package com.ec.restfull.persistence;

import com.ec.restfull.persistence.DetalleRubro;
import com.ec.restfull.persistence.Factura;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-01-18T19:12:05")
@StaticMetamodel(Rubro.class)
public class Rubro_ { 

    public static volatile CollectionAttribute<Rubro, DetalleRubro> detalleRubroCollection;
    public static volatile SingularAttribute<Rubro, BigDecimal> valorActual;
    public static volatile SingularAttribute<Rubro, DetalleRubro> idDetalleRubro;
    public static volatile SingularAttribute<Rubro, Integer> idRubro;
    public static volatile CollectionAttribute<Rubro, Factura> facturaCollection;

}