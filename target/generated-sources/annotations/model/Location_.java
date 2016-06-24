package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-06-24T15:35:14")
@StaticMetamodel(Location.class)
public class Location_ { 

    public static volatile SingularAttribute<Location, Date> date;
    public static volatile SingularAttribute<Location, String> road;
    public static volatile SingularAttribute<Location, String> city;
    public static volatile SingularAttribute<Location, Double> latitude;
    public static volatile SingularAttribute<Location, Double> longitude;

}