package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.CarTracker;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-06-01T10:16:44")
@StaticMetamodel(Invoice.class)
public class Invoice_ { 

    public static volatile SingularAttribute<Invoice, Double> totalAmount;
    public static volatile SingularAttribute<Invoice, Integer> month;
    public static volatile SingularAttribute<Invoice, CarTracker> car;
    public static volatile SingularAttribute<Invoice, Integer> year;
    public static volatile SingularAttribute<Invoice, Boolean> paid;
    public static volatile SingularAttribute<Invoice, Long> id;
    public static volatile SingularAttribute<Invoice, Double> totalDistance;

}