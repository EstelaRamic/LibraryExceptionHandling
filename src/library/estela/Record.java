package library.estela;

import java.io.BufferedWriter;
import java.io.File;

import java.io.FileWriter;
import java.util.Date;

public class Record {
	private int memberID;
	private int bookID;
	private Date date;
	private int type;
	private String fileName = "logs.txt";
//	private String fileNameTMP = "logs.tmp";

	public Record() {

	}

	/**
	 * @param memberID
	 * @param bookID
	 * @param date
	 */
	public Record(int memberID, int bookID, Date date, int type) {
		super();
		this.memberID = memberID;
		this.bookID = bookID;
		this.date = date;
		this.type = type;
	}

	/**
	 * @return the memberID
	 */
	public int getMemberID() {
		return memberID;
	}

	/**
	 * @param memberID
	 *            the memberID to set
	 */
	public void setMemberID(int memberID) {
		this.memberID = memberID;
	}

	/**
	 * @return the bookID
	 */
	public int getBookID() {
		return bookID;
	}

	/**
	 * @param bookID
	 *            the bookID to set
	 */
	public void setBookID(int bookID) {
		this.bookID = bookID;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	
	public void setType(int t){
		this.type = t;
	}

	// @Override
	// Konverzija objekta u String
	public String toString() {
		return "" + memberID + ";" + bookID + ";" + date + ";" + type;
	}

	boolean addRecord() {

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
