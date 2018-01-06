package cleartrip;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LibraryManagement {
	static int allowedNumberofBooksPerUser = 5;
	private HashMap<String, User> Users = new HashMap<String, User>();

	private HashMap<String, Book> Library = new HashMap<String, Book>();
	private HashMap<String, List<Book>> IssuedBookMap = new HashMap<String, List<Book>>();

	public void addBook(Book b) {
		System.out.println("Book count before adding :" + Library.size());
		if (Library.get(b.getISBN()) != null) {
			Book old = Library.get(b.getISBN());
			old.setCount(old.getCount() + b.getCount());
			Library.put(old.getISBN(), old);
		} else {
			Library.put(b.getISBN(), b);
			System.out.println("Book added in library Book Name: " + b.getTitle());
		}
		System.out.println("Book count after adding :" + Library.size());
	}

	public String issueBook(String ISBN, User U) {
		int totalBooks = 0;
		for (Book c : Library.values()) {
			totalBooks = totalBooks + c.getCount();
		}
		System.out.println("Number of books available before issue: " + totalBooks);
		Book b = Library.get(ISBN);
		if (b != null && b.getCount() > 0) {
			if (IssuedBookMap.containsKey(U.getIdentifier())) {
				List<Book> oldIssuedList = IssuedBookMap.get(U.getIdentifier());
				if (oldIssuedList.size() < allowedNumberofBooksPerUser) {
					oldIssuedList.add(b);
					IssuedBookMap.put(U.getIdentifier(), oldIssuedList);
					b.setCount(b.getCount() - 1);
					Library.put(ISBN, b);
					System.out.println(b.getTitle() + ": Book Issued to user: " + U.getName());
					totalBooks = 0;
					for (Book c : Library.values()) {
						totalBooks = totalBooks + c.getCount();
					}
					System.out.println("Number of books available after issue: " + totalBooks);
					return "issued";
				} else {
					System.out.println("maximum allowed book limt reache for user" + U.getName());
					return "notissued";
				}

			} else {
				List<Book> currentList = new ArrayList<Book>();
				currentList.add(Library.get(ISBN));
				IssuedBookMap.put(U.getIdentifier(), currentList);
				b.setCount(b.getCount() - 1);
				Library.put(ISBN, b);
				System.out.println(b.getTitle() + ": Book Issued to user: " + U.getName());
				totalBooks = 0;
				for (Book c : Library.values()) {
					totalBooks = totalBooks + c.getCount();
				}
				System.out.println("Number of books available after issue: " + totalBooks);
				return "issued";
			}

		}

		return "";
	}

	public String returnBook(Book B, User U) {
		int totalBooks = 0;
		for (Book c : Library.values()) {
			totalBooks = totalBooks + c.getCount();
		}
		System.out.println("Number of books available before return: " + totalBooks);
		B.setCount(Library.get(B.getISBN()).getCount());
		Library.put(B.getISBN(), B);
		List<Book> oldListOfIssuedBook = IssuedBookMap.get(U.getIdentifier());
		oldListOfIssuedBook.remove(B);
		IssuedBookMap.put(U.getIdentifier(), oldListOfIssuedBook);
		System.out.println(B.getTitle() + ": book returned by user : " + U.getName());
		B = Library.get(B.getISBN());
		B.setCount(B.getCount() + 1);
		Library.put(B.getISBN(), B);
		totalBooks = 0;
		for (Book c : Library.values()) {
			totalBooks = totalBooks + c.getCount();
		}
		System.out.println("Number of books available after return: " + totalBooks);
		return "returned";
	}

	public void addUser(User u) {
		System.out.println("userCount before add :" + Users.size());
		if (!Users.containsKey(u.getIdentifier())) {
			Users.put(u.getIdentifier(), u);
			System.out.println("User Added :" + u.getName());
		} else

			System.out.println("User Already Exist");
		System.out.println("userCount after add :" + Users.size());
	}

	public List<User> SearchUser(String name) {
		List<User> searchedList = new ArrayList<User>();
		for (User u : Users.values()) {
			if (u.getName().toLowerCase().contains(name.toLowerCase())) {
				searchedList.add(u);
			}
		}
		System.out.println("user found with this match");
		for (User u : searchedList) {
			System.out.print(u.getName() + " ");
		}
		return searchedList;
	}

	public List<Book> searchBook(String byParameter, String value) {
		List<Book> searchedList = new ArrayList<Book>();
		if (byParameter.equalsIgnoreCase("name")) {
			for (Book b : Library.values()) {
				if (b.getTitle().toLowerCase().contains(value.toLowerCase()) && b.getCount() > 0) {
					searchedList.add(b);
				}
			}
		}
		if (byParameter.equalsIgnoreCase("authour")) {
			for (Book b : Library.values()) {
				if (b.getAuthor().toLowerCase().contains(value.toLowerCase()) && b.getCount() > 0) {
					searchedList.add(b);
				}
			}
		}
		for (Book b : searchedList) {
			System.out.println(b.getTitle());
		}
		return searchedList;
	}

	public static int getAllowedNumberofBooksPerUser() {
		return allowedNumberofBooksPerUser;
	}

	public static void setAllowedNumberofBooksPerUser(int allowedNumberofBooksPerUser) {
		LibraryManagement.allowedNumberofBooksPerUser = allowedNumberofBooksPerUser;
	}

	public HashMap<String, User> getUser() {
		return Users;
	}

	public void setUsers(HashMap<String, User> users) {
		Users = users;
	}

	public HashMap<String, Book> getLibrary() {
		return Library;
	}

	public void setLibrary(HashMap<String, Book> library) {
		Library = library;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LibraryManagement l = new LibraryManagement();
		User U = new User("1", "Ravi");
		l.addUser(U);
		Book b = new Book("1", "kanetkar", "c++", 1);
		l.addBook(b);
		l.issueBook(b.getISBN(), U);
		l.returnBook(b, U);
		System.out.print("serching book by name c :");
		l.searchBook("name", "c");
		System.out.print("serching book by authour kan :");
		l.searchBook("authour", "kan");
		System.out.print("searching user rav: ");
		l.SearchUser("rav");
	}

}
