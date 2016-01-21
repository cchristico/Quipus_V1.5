package com.ec.entidades;

import com.ec.entidades.DetalleRubro;
import com.ec.entidades.Factura;
import com.ec.entidades.RubroFacruea;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-01-20T21:47:51")
@StaticMetamodel(Rubro.class)
public class Rubro_ { 

    public static volatile SingularAttribute<Rubro, RubroFacruea> idRubroFacrura;
    public static volatile SingularAttribute<Rubro, BigDecimal> valorActual;
    public static volatile SingularAttribute<Rubro, DetalleRubro> idDetalleRubro;
    public static volatile SingularAttribute<Rubro, Integer> idRubro;
    public static volatile CollectionAttribute<Rubro, Factura> facturaCollection;

}