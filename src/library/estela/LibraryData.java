package library.estela;

import java.io.BufferedReader;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class LibraryData {
	private ArrayList<Member> memberData = new ArrayList<>();
	private ArrayList<Book> bookData = new ArrayList<>();
	private Path memberPath = Paths.get("members.txt");
	private Path bookPath = Paths.get("books.txt");
	
	//Constructors
	LibraryData(){}

	public ArrayList<Member> getMembers() throws Exception {
		memberData.clear();
		File file = new File("members.txt");
		// provjerava da li fajl postoji, inaèe ga kreira
		if (!file.exists()) {
			file.createNewFile();
		}
		BufferedReader reader = Files.newBufferedReader(memberPath);
		String temp = null;
		while ((temp = reader.readLine()) != null) {
			String words[] = temp.split(";");
			Member member = new Member(Integer.parseInt(words[0]), words[1], Short.parseShort(words[2]));
			memberData.add(member);
		}
		reader.close();
		return memberData;
	}
	
	public ArrayList<Book> getBooks() throws Exception {
		bookData.clear();
		File file = new File("books.txt");
		// provjerava da li fajl postoji, inaèe ga kreira
		if (!file.exists()) {
			file.createNewFile();
		}
		BufferedReader reader = Files.newBufferedReader(bookPath);
		String temp;
		while ((temp = reader.readLine()) != null) {
			String words[] = temp.split(";");
			Book book = new Book(Integer.parseInt(words[0]), words[1], Boolean.parseBoolean(words[2]));
			bookData.add(book);
		}
		reader.close();
		return bookData;
	}
	
	public Member memberExist(int number) {

		Member memb = new Member();
		try {
			this.getMembers();
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (int i = 0; i < memberData.size(); i++) {
			memb = memberData.get(i);

			if (memb.getMemberID() == number) {
				return memb;
			}
		}
		return null;
	}

	public Book bookExist(int number) {

		Book book = new Book();
		try {
			this.getBooks();
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (int i = 0; i < bookData.size(); i++) {
			book = bookData.get(i);
			if (book.getBookID() == number)
				return book;
		}
		return null;
	}
	
	
	public boolean takeBook(int memberID, int bookID) throws Exception {
		bookData = getBooks();
		memberData = getMembers();
		
		Member member = memberExist(memberID);
		Book book = bookExist(bookID);
		
		boolean memberOK = (member != null) && (member.getBorrowedBooks() < 3);
		boolean bookOK = (book != null) && (book.getBorrowedStatus() == false);

		Record record = new Record();
		Date date = new Date();

		if (memberOK && bookOK) {
			record.setMemberID(memberID);
			record.setBookID(bookID);
			record.setDate(date);
			record.setType(1);

			record.addRecord();

			member.setBorrowedBooks((short) (member.getBorrowedBooks() + 1));
			member.changeDataInFile();

			book.setBorrowedStatus(!book.getBorrowedStatus());
			book.changeDataInFile();

		} else if (member == null) {
			System.out.println("-------------------------------------------------");
			System.out.println("That member with ID " + memberID + " doesn't exist!");
			System.out.println("-------------------------------------------------");
			return false;
		} else if (book == null) {
			System.out.println("-------------------------------------------------");
			System.out.println("That book with ID " + bookID + "doesn't exist!");
			System.out.println("-------------------------------------------------");
			return false;
		} else if (member.getBorrowedBooks() == 3) {
			System.out.println("----------------------------------------------------------");
			System.out.println("Member with ID " + memberID + " allready has 3 borrowed books");
			System.out.println("----------------------------------------------------------");
			return false;
		} else if (book.getBorrowedStatus() == true) {
			System.out.println("-------------------------------------------------");
			System.out.println("Book with ID " + bookID + " is allready borrowed.");
			System.out.println("-------------------------------------------------");
			return false;
		}
		return true;
	}
	
	public boolean returnBook(int memberID, int bookID) throws Exception{
		bookData=getBooks();
		memberData=getMembers();
		
		Member member=memberExist(memberID);
		Book book =bookExist(bookID);
		
		
		boolean memberOK = (member!=null);
		boolean bookOK = (book!=null) && (book.getBorrowedStatus()==true);
		
		Record record = new Record();
		Date date = new Date();
		
				
		if(memberOK && bookOK) {
			record.setMemberID(memberID);
			record.setBookID(bookID);
			record.setDate(date);
			record.setType(-1);
			
			record.addRecord();
				
			member.setBorrowedBooks((short)(member.getBorrowedBooks()-1));
			member.changeDataInFile();
			
			book.setBorrowedStatus(!book.getBorrowedStatus());
			book.changeDataInFile();
			
		}else if(member==null) {
			System.out.println("-------------------------------------------------");
			System.out.println("That member with ID "+ memberID +" doesn't exist!");
			System.out.println("-------------------------------------------------");
			return false;
		}else if(book==null) {
			System.out.println("-------------------------------------------------");
			System.out.println("That book with ID "+ bookID +"doesn't exist!");
			System.out.println("-------------------------------------------------");
			return false;
		}else if (member.getBorrowedBooks() == 0) {
			System.out.println("----------------------------------------------------------");
			System.out.println("Member with ID " + memberID + " has no more borrowed books.");
			System.out.println("----------------------------------------------------------");
			return false;
		} else if (book.getBorrowedStatus()==false){
			System.out.println("-------------------------------------------------");
			System.out.println("Book with ID "+bookID+ " was not borrowed at all." );
			System.out.println("-------------------------------------------------");
			return false;
		}

		return true;
	}
}
