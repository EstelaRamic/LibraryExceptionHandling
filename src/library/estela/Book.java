package library.estela;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class Book {
	private int ID;
	private String name = "";
	private boolean borrowedStatus = false;
	private String fileName = "books.txt";
	private String fileNameTMP = "books.tmp";

	public Book() {

	}

	public Book(int ID, String bookName, boolean status) {
		this.ID = ID;
		this.name = bookName;
		this.borrowedStatus = status;

	}

	/**
	 * @return the bookID
	 */
	public int getBookID() {
		return ID;
	}

	/**
	 * @param bookID
	 *            the bookID to set
	 */
	public void setBookID(int bookID) {
		this.ID = bookID;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the borrowedStatus
	 */
	public boolean getBorrowedStatus() {
		return borrowedStatus;
	}

	/**
	 * @param borrowedBooks
	 *            the borrowedBooks to set
	 */
	public void setBorrowedStatus(boolean borrowedStatus) {
		this.borrowedStatus = borrowedStatus;
	}

	// @Override
	// Konverzija objekta u String
	public String toString() {
		return "" + ID + ";" + name + ";" + borrowedStatus;
	}

	boolean changeDataInFile() {
		String thisLine = null;
		boolean empty=true;

		// upis u datotetku
		BufferedReader br = null;
		FileReader fr = null;

		File file = new File(fileName);
		File tempFile = new File(fileNameTMP);

		try {
			// provjerava da li fajl postoji, inaèe ga kreira
			if (!file.exists()) {
				file.createNewFile();
				
			}
			fr = new FileReader(file);
			br = new BufferedReader(fr); // open input stream test.txt for
											// reading purpose.

			// provjerava da li fajl.tmp postoji, inaèe ga kreira
			if (!tempFile.exists()) {
				tempFile.createNewFile();
				
			}

			// true = append file - dozvoljeno dodavanje na kraj fajla
			FileWriter fw = new FileWriter(tempFile.getAbsoluteFile(), true);
			BufferedWriter bw = new BufferedWriter(fw);

			while (((thisLine = br.readLine()) != null)) {
				empty = false;
				String[] words = thisLine.split(";");

				if ((Integer.parseInt(words[0]) == this.ID)) {
					
					bw.write(this.toString());
					bw.newLine();
					
				} else {
					Book book1 = new Book(Integer.parseInt(words[0]), words[1], Boolean.parseBoolean(words[2]));
					bw.write(book1.toString());
					bw.newLine();
					
				}
			}
			if (empty) {
				bw.write(this.toString());
				bw.newLine();
				
			}
			bw.close();
			fw.close();

			br.close();
			fr.close();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		// Delete the original file
		if (!file.delete()) {
			System.out.println("Datoteka se ne može izbrisati");
			// return false;
		} 
		// Rename the new file to the filename the original file had.
		if (!tempFile.renameTo(file)) {
			System.out.println("Datoteka se ne može preimenovati");
		}
		return true;
	}
	boolean addNewBook() {
		File file = new File(fileName);

		try {
			// provjerava da li fajl postoji, inaèe ga kreira
			if (!file.exists()) {
				file.createNewFile();
			}
			// true = append file - dozvoljeno dodavanje na kraj fajla
			FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
			BufferedWriter bw = new BufferedWriter(fw);

			bw.write(this.toString());
			bw.newLine();

			bw.close();
			fw.close();
			
			return true;
			

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}


}
