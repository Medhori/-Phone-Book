package phonebook;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadFile {

    public ArrayList<String> readDatabase(String filePath) {
        File database = new File(filePath);
        ArrayList<String> listDatabase = new ArrayList<>();
        try (Scanner scanner = new Scanner(database)) {
            while (scanner.hasNext()) {
                listDatabase.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return listDatabase;
    }

    public ArrayList<String> readNames(String filePath) {
        File file = new File(filePath);
        ArrayList<String> namesList = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                namesList.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return namesList;
    }


    public String[] getStringArray(ArrayList<String> arr) {
        String[] str = new String[arr.size()];
        for (int j = 0; j < arr.size(); j++) {
            str[j] = arr.get(j);
        }
        return str;
    }
}
