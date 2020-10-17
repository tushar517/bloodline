package com.DataModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Donor {

@SerializedName("name")
@Expose
private String name;
@SerializedName("number")
@Expose
private String number;
@SerializedName("city")
@Expose
private String city;
@SerializedName("Area")
@Expose
private String area;
public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public String getNumber() {
return number;
}

public void setNumber(String number) {
this.number = number;
}

public String getCity() {
return city;
}

public void setCity(String city) {
this.city = city;
}
 public  String getArea(){return area;}
 public void setArea(String area){this.area=area;}
}