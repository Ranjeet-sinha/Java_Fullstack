package com.lockme;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Application {
	static Scanner sc = new Scanner(System.in);

	public static boolean welcome() {

		System.out.println("*********************************************\n");
		System.out.println("*********************************************\n");
		System.out.println("\tWELCOME TO LOCKME.COM\n");
		System.out.println("*********************************************\n");
		System.out.println("*********************************************\n\n");
		System.out.println("........... DEVELOPERS DETAILS .............\n");
		System.out.println("----------------Ranjeet Kumar Sinha-----------------\n");
		System.out.println("-----Organisation:XYZ Consultancy Services-------\n");

		System.out.println(
				"-----To Inquire, Contact:+91-8124010289----------\n\n--------MailTo:ranjeetkumarshn0@gmail.com---------\n");
		System.out.println(
				("This Application is useful in:\n\n#Sorting files inside directory and its subdirectory \n#Adding file to a directory\n#Deleteing a file in a directory\n#Searching a file in a directory\n"));
		System.out.println("\n\n\n Press YES to proceed  ->->");
		String str = sc.next();
		while (true) {
			if (str.equalsIgnoreCase("yes")) {
				return true;
			} else {
				System.out.println("Invalid Entry!! Please press YES to continue");
				str = sc.next();
			}
		}
	}

	public static void choices() throws IOException {
		while (true) {
			System.out.println("\n\n***Please Enter your choice*****\n");
			System.out.println("Press 1: List current file names in ascending order");
			System.out.println("Press 2: List user interfaces ");
			System.out.println("Press 3: Close Application\n");
			int n = sc.nextInt();
			switch (n) {
			case 1:
				List<String> list = new ArrayList<>();
				list = listFiles();

				System.out.println("\n*****List of Files in Rootfolder and its subfolder*******\n");
				for (int i = 0; i < list.size(); i++) {
					System.out.println(list.get(i));
				}
				break;
			case 2:
				userInterfaces();
				break;

			case 3:
				System.out.println("***Application Closed****");
				System.exit(0);
				break;

			default:
				System.out.println("Wrong choice!!! Please select the listed choices!");
			}
		}
	}

	public static ArrayList<String> listFiles() {

		System.out.println("Enter Root Directory path");
		String location = sc.next();
		File file = null;
		try {
			file = new File(location);
		} catch (NullPointerException e) {
			System.out.println("Please enter correct Root directory");
			
		}
		File[] fs = file.listFiles();
		ArrayList<String> list = new ArrayList<>();
		list = read(fs, list);
		Collections.sort(list);
		return list;

	}

	public static ArrayList<String> read(File file[], ArrayList<String> list) {
		for (File eachfile : file) {
			list.add(eachfile.getName());
			if (eachfile.isDirectory()) {
				File fs[] = eachfile.listFiles();
				read(fs, list);
			}
		}
		return list;
	}

	public static void userInterfaces() throws IOException {
		System.out.println("\n\n***Please Enter your choice*****\n");
		System.out.println("Press 1: Add a file to the existing directory list");
		System.out.println("Press 2: Delete a user specified file from the existing directory list");
		System.out.println("Press 3: Search a user specified file from the main directory");
		System.out.println("Press 4: Navigate back to the main context\n");
		int n = sc.nextInt();
		switch (n) {
		case 1:
			addFile();
			userInterfaces();
			break;
		case 2:
			deleteFile();
			userInterfaces();
			break;
		case 3:
			searchFile();
			userInterfaces();
			break;
		case 4:
			choices();
			break;
		default:
			System.out.println("Wrong choice entered!");
			break;
		}
	}

	public static void addFile() {
		System.out.println("Enter Existing Directory Path with new file name\n Example: F:\\temp\\abc.txt \n");
		Path path = Paths.get(sc.next());
		List<String> list = new ArrayList<>();
		try {
			Files.write(path, list, StandardOpenOption.CREATE_NEW);
			System.out.println("\nFile Created!");
		} catch (IOException e) {
			System.out.println("\nFile Exists!");
		}

	}

	public static void deleteFile() throws IOException {
		System.out.println("Enter the file to be deleted with absolute path\n");
		Path path = Paths.get(sc.next());
		
	boolean filedeleted	=	Files.deleteIfExists(path);
	if 	(filedeleted){
		System.out.println("File Deleted!");	
	}
	else
		System.out.println("File not found");
		
			
		
	}

	public static void searchFile() {
		System.out.println("\nEnter the file to be searched with extension Ex:test.txt");
		{
			String string = sc.next();
			ArrayList<String> list = new ArrayList<>();
			list = listFiles();
			if (list.contains(string)) {
				System.out.println("\nFile Exist!");
			} else
				System.out.println("\nFile do not exist!");
		}
	}

	public static void main(String[] args) throws IOException {
		boolean check = welcome();
		if (check) {
			choices();
		}
	}

}
