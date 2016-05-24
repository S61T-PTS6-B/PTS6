package model;

import java.util.List;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Cordon;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-05-24T13:17:22")
@StaticMetamodel(Invoice.class)
public class Invoice_ { 

    public static volatile SingularAttribute<Invoice, Double> totalAmount;
    public static volatile SingularAttribute<Invoice, List> seriesOfLocationsOnRoad;
    public static volatile SingularAttribute<Invoice, Long> id;
    public static volatile SingularAttribute<Invoice, Double> totalDistance;
    public static volatile ListAttribute<Invoice, Cordon> cordonOccurrences;

}