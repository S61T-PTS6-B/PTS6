package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.CarTracker;
import model.NAW;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-06-06T13:53:10")
@StaticMetamodel(CarOwner.class)
public class CarOwner_ { 

    public static volatile SingularAttribute<CarOwner, NAW> nawid;
    public static volatile SingularAttribute<CarOwner, Boolean> active;
    public static volatile SingularAttribute<CarOwner, Long> id;
    public static volatile SingularAttribute<CarOwner, CarTracker> carid;

}