package library.estela;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
//import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Library {

	static LibraryData notes = new LibraryData();

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		call(input);
		input.close();
	}

	public static void call(Scanner input) {
		int choice = 0;
		boolean added;
		/*
		 * try{ notes.getMembers(); } catch (Exception e) { e.printStackTrace();
		 * } try{ notes.getBooks();
		 * 
		 * } catch (Exception e) { e.printStackTrace(); }
		 */

		do {

			choice = showMenu(input);

			switch (choice) {
			case 1:
				added = false;
				int memberID = 0;
				String memberName;
				Member acc = new Member();
				do {
					do {
						try {

							System.out.println("Enter member number(ID): ");
							memberID = input.nextInt();
							if (memberID <= 0) {
								System.out.println("-----------------------------------------------------");
								System.out.println("Number (ID) must be positive number. Tray again.");
								System.out.println("-----------------------------------------------------");
							} else {
								acc.setMemberID(memberID);
							}
						} catch (InputMismatchException ex) {
							System.out.println("Tray again. (Incorect input)");
							System.out.println("---------------------------------------------------");
							input.nextLine(); // discard input
						}
					} while (memberID <= 0);

					input.nextLine(); // discard input
					System.out.println("Enter member name: ");
					memberName = input.nextLine();
					acc.setName(memberName);

					if ((notes.memberExist(acc.getMemberID()) == null)) {

						added = acc.addNewMember();
						System.out.println(" -- "+ added);
						
						System.out.println("******************************");
						System.out.println("      New member is added.    ");
						System.out.println("******************************");
					} else {
						System.out.println("Member with tha same ID already exist.");
						added=true;
					}
				} while (!added);
				break;

			case 2:
				added = false;
				int bookID = 0;
				String bookName;
				do {
					Book accB = new Book();
					do {
						try {

							System.out.println("Enter the book number(ID): ");
							bookID = input.nextInt();
							if (bookID <= 0) {
								System.out.println("-----------------------------------------------------");
								System.out.println("Number (ID) must be positive number. Tray again.");
								System.out.println("-----------------------------------------------------");
							} else {
								accB.setBookID(bookID);
							}
						} catch (InputMismatchException ex) {
							System.out.println("Tray again. (" + "Incorect input.)");
							System.out.println("---------------------------------------------------");
							input.nextLine(); // discard input
						}
					} while (bookID <= 0);

					input.nextLine(); // discard input
					System.out.println("Enter the book name: ");
					bookName = input.nextLine();
					accB.setName(bookName);

					if (notes.bookExist(accB.getBookID()) == null) {
						
						added=accB.addNewBook();
						System.out.println(" -- "+ added);
						System.out.println("******************************");
						System.out.println("      New book is added.    ");
						System.out.println("******************************");
					}else {
						System.out.println("Book with tha same ID already exist.");
						added=true;
					}
				} while (!added);
				break;

			case 3:

				added = false;
				memberID = 0;
				bookID = 0;

				do {

					do {
						try {

							System.out.println("Enter the member number (ID):");
							memberID = input.nextInt();
							if (memberID <= 0) {
								System.out.println("-----------------------------------------------------");
								System.out.println("Number (ID) must be positive number. Tray again.");
								System.out.println("-----------------------------------------------------");
							}

						} catch (InputMismatchException ex) {
							System.out.println("Tray again. (" + "Incorect input.)");
							System.out.println("---------------------------------------------------");
							input.nextLine(); // discard input
						}
					} while (memberID <= 0);

					do {
						try {

							System.out.println("Enter the book number (ID):");
							bookID = input.nextInt();
							if (bookID <= 0) {
								System.out.println("-----------------------------------------------------");
								System.out.println("Number (ID) must be positive number. Tray again.");
								System.out.println("-----------------------------------------------------");
							}

						} catch (InputMismatchException ex) {
							System.out.println("Tray again. (" + "Incorect input.)");
							System.out.println("---------------------------------------------------");
							input.nextLine(); // discard input
						}
					} while (bookID <= 0);

					try {
						added = notes.takeBook(memberID, bookID);
						if (added){
							System.out.println("**************************************************************************");
							System.out.println("Member " + memberID + " borrow the book " + bookID + " . Record is written.");
							System.out.println("**************************************************************************");
						} else {
							added=true;
						}
					} catch (Exception e) {
						e.printStackTrace();
						added=true;
					}

				} while (!added);
				break;

			case 4:

				added = false;
				memberID = 0;
				bookID = 0;

				do {

					do {
						try {

							System.out.println("Enter the member number (ID):");
							memberID = input.nextInt();
							if (memberID <= 0) {
								System.out.println("-----------------------------------------------------");
								System.out.println("Number (ID) must be positive number. Tray again.");
								System.out.println("-----------------------------------------------------");
							}

						} catch (InputMismatchException ex) {
							System.out.println("Tray again. (" + "Incorect input.)");
							System.out.println("---------------------------------------------------");
							input.nextLine(); // discard input
						}
					} while (memberID <= 0);

					do {
						try {

							System.out.println("Enter the book number (ID):");
							bookID = input.nextInt();
							if (bookID <= 0) {
								System.out.println("-----------------------------------------------------");
								System.out.println("Number (ID) must be positive number. Tray again.");
								System.out.println("-----------------------------------------------------");
							}

						} catch (InputMismatchException ex) {
							System.out.println("Tray again. (" + "Incorect input.)");
							System.out.println("---------------------------------------------------");
							input.nextLine(); // discard input
						}
					} while (bookID <= 0);

					try {
						added = notes.returnBook(memberID, bookID);
						if (added){
							System.out.println("**************************************************************************");
							System.out.println("Member " + memberID + " return the book " + bookID + " . Record is written.");
							System.out.println("**************************************************************************");
						}else {
							added=true;
						}
					} catch (Exception e) {
						e.printStackTrace();
					}

				} while (!added);
				break;
			case 5:
				int accNum = 0;

				do {
					try {

						System.out.println("For which member number (ID) do you want information:");
						accNum = input.nextInt();
						if (accNum <= 0) {
							System.out.println("-----------------------------------------------------");
							System.out.println("Number (ID) must be positive number. Tray again.");
							System.out.println("-----------------------------------------------------");
						} else if (notes.memberExist(accNum) == null) {

							System.out.println("Member with ID: " + accNum + " does not exist");
							accNum = -1;
						} else {
							String temp = notes.memberExist(accNum).toString();
							String[] words = temp.split(";");
							System.out.print("Member ID: " + words[0] + "\nMember name: " + words[1]
									+ "\nBorrowed books: " + words[2]);
							System.out.println("\n****************************************************");
							System.out.println();

						}

					} catch (InputMismatchException ex) {
						System.out.println("Tray again. (" + "Incorect input.)");
						System.out.println("---------------------------------------------------");
						input.nextLine(); // discard input
					}
				} while (accNum <= 0);

				break;

			case 6:

				accNum = 0;
				do {
					try {

						System.out.println("For which book number (ID) do you want information:");
						accNum = input.nextInt();
						if (accNum <= 0) {
							System.out.println("-----------------------------------------------------");
							System.out.println("Number (ID) must be positive number. Tray again.");
							System.out.println("-----------------------------------------------------");
						} else if (notes.bookExist(accNum) == null) {

							System.out.println("Book with ID: " + accNum + " does not exist");
							accNum = -1;
						} else {
							String temp = notes.bookExist(accNum).toString();
							String[] words = temp.split(";");
							System.out.print("Book ID: " + words[0] + "\nBook name: " + words[1] + "\nBorrowed status: "
									+ words[2]);
							System.out.println("\n****************************************************");
							System.out.println();

						}

					} catch (InputMismatchException ex) {
						System.out.println("Tray again. (" + "Incorect input.)");
						System.out.println("---------------------------------------------------");
						input.nextLine(); // discard input
					}
				} while (accNum <= 0);

				break;

			case 7:
				System.out.println("*************************************************");
				System.out.println("You choose to exit App. You are Welcome some other time.");
				System.out.println("*************************************************");
				System.exit(0);

				break;
			}
		} while (choice != 7);

	}

	public static int showMenu(Scanner input) {

		char choice = '1';
		ArrayList<Character> validInputs = new ArrayList<>();

		validInputs.add('1');
		validInputs.add('2');
		validInputs.add('3');
		validInputs.add('4');
		validInputs.add('5');
		validInputs.add('6');
		validInputs.add('7');

		do {
			if (!validInputs.contains(choice)) {
				System.out.println("\nWrong input. Choose again.\n");
			}
			System.out.println("\nChoose an option:\n");
			System.out.println("*************************************************");
			System.out.print("1. Create new member account \n");
			System.out.print("2. Create new book account \n");
			System.out.print("3. Take a book borrowing \n");
			System.out.print("4. Make a book returning \n");
			System.out.print("5. Get member information \n");
			System.out.print("6. Get book information \n");
			System.out.print("7. Exit application \n");
			System.out.println("*************************************************");
			choice = input.next().charAt(0);

		} while (!validInputs.contains(choice));

		// ch.close();

		return ((int) choice - 48);
	}

	// provjera da li korisnik postoji
	public static boolean memberIDExist(int ID, String fileName) {
		String thisLine = null;
		boolean found = false;
		// èitanje iz datotetke
		BufferedReader br = null;
		FileReader fr = null;

		File file = new File(fileName);
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr); // open input stream test.txt for
											// reading purpose.

			while (((thisLine = br.readLine()) != null)) {
				// System.out.println(thisLine);
				String[] words = thisLine.split(";");

				if ((words[0].equals(ID))) {
					found = true;
					break;
				}
			}

			br.close();
			fr.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return found;

	}
}
