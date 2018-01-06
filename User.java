package cleartrip;

public class User {
 private String identifier;
 private String name;
 public User(String identifier,String name){
	 this.identifier=identifier;
	 this.name=name;
 }
public String getIdentifier() {
	return identifier;
}
public void setIdentifier(String identifier) {
	this.identifier = identifier;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}

 
}
