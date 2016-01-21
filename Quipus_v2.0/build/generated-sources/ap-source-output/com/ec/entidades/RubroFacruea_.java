package com.ec.entidades;

import com.ec.entidades.Factura;
import com.ec.entidades.Rubro;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-01-20T21:47:51")
@StaticMetamodel(RubroFacruea.class)
public class RubroFacruea_ { 

    public static volatile SingularAttribute<RubroFacruea, Integer> idRubroFacrura;
    public static volatile SingularAttribute<RubroFacruea, BigDecimal> valorrubro;
    public static volatile CollectionAttribute<RubroFacruea, Rubro> rubroCollection;
    public static volatile CollectionAttribute<RubroFacruea, Factura> facturaCollection;

}