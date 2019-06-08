package com.yousoff.crud.main;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;

import com.yousoff.crud.model.Item;

/**
 * 
 * Ref: https://www.mkyong.com/java/java-read-a-file-from-resources-folder/
 * 
 * @author yousoffeffendy
 *
 */
public class MainApplication {

	private static List<Item> items;

	public static void main(String[] args) {

		System.out.println("[MainApplication] Started..");

		MainApplication mainApp = new MainApplication();
		Scanner input = new Scanner(System.in);

		// Initialize items
		items = getItems();

		try {

			// 1. Display option
			String options = FileUtils.readFileToString(mainApp.getFileFromResources("Options.txt"), "UTF-8");

			// 2. Key in option
			int selectedOption = 0;
			boolean isExit = false;

			while (!isExit) {

				System.out.println();
				System.out.println(options);

				while (!input.hasNextInt()) {
					System.out.println("Invalid input " + input.next());
				}
				selectedOption = input.nextInt();

				switch (selectedOption) {
				case 1:
					create(input);
					break;
				case 2:
					update(input);
					break;
				case 3:
					read();
					break;
				case 4:
					delete(input);
					break;
				case -1:
					isExit = true;
					break;
				default:
					System.out.println("Invalid option " + selectedOption);
					break;
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			input.close();
		}

		System.out.println("[MainApplication] End..");
	}

	private static List<Item> getItems() {
		List<Item> items = new ArrayList<Item>();
		items.add(new Item("ID1", "Item 1", "This is item 1", new Date(), "SYSTEM"));
		items.add(new Item("ID2", "Item 2", "This is item 2", new Date(), "SYSTEM"));
		items.add(new Item("ID3", "Item 3", "This is item 3", new Date(), "SYSTEM"));
		return items;
	}

	private static void delete(Scanner input) {
		System.out.println("[MainApplication] Delete Start");
		System.out.print("Enter Item ID to delete : ");
		String id = input.next();

		Item selectedItem = new Item();
		selectedItem.setId(id);

		if (!items.contains(selectedItem)) {
			System.out.println("Item with ID " + id + " does not exist.");
		} else {
			items.remove(selectedItem);
			System.out.println("Successfully deleted " + id);
		}

		System.out.println("[MainApplication] Delete End");
	}

	private static void update(Scanner input) {
		System.out.println("[MainApplication] Update Start");
		System.out.print("Enter Item ID to update : ");
		String id = input.next();

		Item selectedItem = new Item();
		selectedItem.setId(id);

		if (!items.contains(selectedItem)) {
			System.out.println("Item with ID " + id + " does not exist.");
		} else {

			selectedItem = items.get(items.indexOf(selectedItem));
			input.nextLine();
			System.out.print("Name : ");
			selectedItem.setName(input.nextLine());

			System.out.print("Description : ");
			selectedItem.setDescription(input.nextLine());

			selectedItem.setUpdatedDate(new Date());
			selectedItem.setUpdatedBy("CURRENT_USER");

			System.out.println("Successfully updated " + id);
		}

		System.out.println("[MainApplication] Update End");
	}

	private static void read() {
		System.out.println("[MainApplication] Read Start");
		if (items != null) {
			for (Item item : items) {
				System.out.println(item.toString());
			}
		}
		System.out.println("[MainApplication] Read End");
	}

	private static void create(Scanner input) {
		System.out.println("[MainApplication] Create Start");
		System.out.println("Creating new item");

		Item newItem = new Item();
		while (true) {
			System.out.print("ID : ");
			String id = input.next();
			newItem.setId(id);

			if (!items.contains(newItem)) {
				break;
			}
			System.out.println("Item with ID " + id + " already exist. Try again.");
		}

		input.nextLine();
		System.out.print("Name : ");
		newItem.setName(input.nextLine());

		System.out.print("Description : ");
		newItem.setDescription(input.nextLine());

		System.out.println("Successfully create new item " + newItem.toString());
		newItem.setCreatedDate(new Date());
		newItem.setCreatedBy("CURRENT_USER");
		items.add(newItem);
		System.out.println("[MainApplication] Create End");
	}

	/**
	 * Get file from classpath, resources folder
	 * 
	 * @param fileName
	 * @return
	 */
	private File getFileFromResources(String fileName) {

		ClassLoader classLoader = getClass().getClassLoader();

		URL resource = classLoader.getResource(fileName);
		if (resource == null) {
			throw new IllegalArgumentException("[getFileFromResources ] File is not found!");
		} else {
			return new File(resource.getFile());
		}

	}

}
