package library.estela;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class Member {
	private int ID;
	private String name = "";
	private short borrowedBooks = 0;

	private String fileName = "members.txt";
	private String fileNameTMP = "members.tmp";

	public Member() {

	}

	public Member(int ID, String memberName, short bBooks) {
		this.ID = ID;
		this.name = memberName;
		this.borrowedBooks = bBooks;

	}

	/**
	 * @return the memberID
	 */
	public int getMemberID() {
		return ID;
	}

	/**
	 * @param memberID
	 *            the memberID to set
	 */
	public void setMemberID(int memberID) {
		this.ID = memberID;
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
	 * @return the borrowedBooks
	 */
	public short getBorrowedBooks() {
		return borrowedBooks;
	}

	/**
	 * @param borrowedBooks
	 *            the borrowedBooks to set
	 */
	public void setBorrowedBooks(short borrowedBooks) {
		this.borrowedBooks = borrowedBooks;
	}

	// @Override
	// Konverzija objekta u String
	public String toString() {
		return "" + ID + ";" + name + ";" + borrowedBooks;
	}

	boolean changeDataInFile() {
		String thisLine = null;
		boolean empty = true;

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

			while ((thisLine = br.readLine()) != null) {

				empty = false;
				String[] words = thisLine.split(";");

				if ((Integer.parseInt(words[0]) == this.ID)) {

					bw.write(this.toString());
					bw.newLine();
				} else {
					Member member1 = new Member(Integer.parseInt(words[0]), words[1], Short.parseShort(words[2]));
					bw.write(member1.toString());
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
			//return false;
		}
		// Rename the new file to the filename the original file had.
		if (!tempFile.renameTo(file)) {
			System.out.println("Datoteka se ne može preimenovati");
		}
		return true;
	}
	boolean addNewMember() {
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
