package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.CarOwner;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-03-24T13:30:04")
@StaticMetamodel(NAW.class)
public class NAW_ { 

    public static volatile SingularAttribute<NAW, String> zipcode;
    public static volatile SingularAttribute<NAW, String> number;
    public static volatile SingularAttribute<NAW, String> firstname;
    public static volatile SingularAttribute<NAW, String> address;
    public static volatile SingularAttribute<NAW, String> city;
    public static volatile SingularAttribute<NAW, String> telephone;
    public static volatile SingularAttribute<NAW, Long> id;
    public static volatile ListAttribute<NAW, CarOwner> carowners;
    public static volatile SingularAttribute<NAW, String> lastname;

}