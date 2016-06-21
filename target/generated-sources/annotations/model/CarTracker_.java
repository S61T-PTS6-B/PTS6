package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.CarOwner;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-06-21T15:05:21")
@StaticMetamodel(CarTracker.class)
public class CarTracker_ { 

    public static volatile SingularAttribute<CarTracker, String> brandCar;
    public static volatile SingularAttribute<CarTracker, String> modelCar;
    public static volatile SingularAttribute<CarTracker, String> licensePlate;
    public static volatile SingularAttribute<CarTracker, String> priceCategory;
    public static volatile SingularAttribute<CarTracker, Boolean> websiteSubscription;
    public static volatile SingularAttribute<CarTracker, Long> id;
    public static volatile ListAttribute<CarTracker, CarOwner> carowners;

}