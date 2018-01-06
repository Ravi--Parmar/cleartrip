package cleartrip;

public class Book {
 private String title;
 private String Author;
 private String ISBN;
 private int count;
 
 public Book(String ISBN,String Authour,String title,int count){
	 this.ISBN=ISBN;
	 this.title=title;
	 this.count=count;
	 this.Author=Authour;
 }
 public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getAuthor() {
	return Author;
}
public void setAuthor(String author) {
	Author = author;
}
public String getISBN() {
	return ISBN;
}
public void setISBN(String iSBN) {
	ISBN = iSBN;
}

public int getCount() {
	return count;
}
public void setCount(int count) {
	this.count = count;
}
}
