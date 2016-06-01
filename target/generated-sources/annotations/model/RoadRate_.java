package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Road;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-06-01T13:00:24")
@StaticMetamodel(RoadRate.class)
public class RoadRate_ { 

    public static volatile SingularAttribute<RoadRate, Road> road;
    public static volatile SingularAttribute<RoadRate, Date> time_start;
    public static volatile SingularAttribute<RoadRate, Double> rate;
    public static volatile SingularAttribute<RoadRate, Date> timestamp_out;
    public static volatile SingularAttribute<RoadRate, Long> id;
    public static volatile SingularAttribute<RoadRate, Date> timestamp_in;
    public static volatile SingularAttribute<RoadRate, Date> time_end;

}