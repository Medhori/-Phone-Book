package phonebook;

import java.util.Hashtable;

public class Sort {

    Hashtable<String, String> table = new Hashtable<>();

    public void bubbleSort(String[] arr) {
        String temp;
        for (int j = 0; j < arr.length - 1; j++) {
            for (int i = j + 1; i < arr.length; i++) {
                if (arr[j].compareTo(arr[i]) > 0) {
                    temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                }
            }
        }
    }

    public int linearSearch(String[] database, String[] listFile) {
        int count = 0;
        for (String s : listFile) {
            for (String value : database) {
                if (s.equals(value)) {
                    count++;
                }
            }
        }
        return count;
    }

    public int searchBubbleJump(String[] database, String[] names) {
        int count = 0;
        for (String s : names) {
            if (jumpSearch(database, s) != -1) {
                count++;
                System.out.println(count);
            }
        }
        return count;
    }


    public int jumpSearch(String[] array, String target) {
        int currentRight = 0;
        int prevRight = 0;
        if (array.length == 0) {
            return -1;
        }
        if (array[currentRight].equals(target)) {
            return 0;
        }
        int jumpLength = (int) Math.sqrt(array.length);
        while (currentRight < array.length - 1) {
            currentRight = Math.min(array.length - 1, currentRight + jumpLength);
            if (array[currentRight].compareTo(target) >= 0) {
                break;
            }
            prevRight = currentRight;
        }
        if ((currentRight == array.length - 1) && target.compareTo(array[currentRight]) > 0) {
            return -1;
        }
        return backwardSearch(array, target, prevRight, currentRight);
    }

    private int backwardSearch(String[] array, String target, int letExcl, int rightIncl) {
        for (int i = rightIncl; i > letExcl; i--) {
            if (array[i].equals(target)) {
                return i;
            }
        }
        return -1;
    }

    public void quickSort(String[] array, int left, int right) {
        if (left < right) {
            int pivotIndex = partition(array, left, right);
            quickSort(array, left, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, right);
        }
    }

    public int partition(String[] array, int left, int right) {
        String pivot = array[right];
        int partitionIndex = left;


        for (int i = left; i < right; i++) {
            if (array[i].compareTo(pivot) <= 0) {
                swap(array, i, partitionIndex);
                partitionIndex++;
            }
        }

        swap(array, partitionIndex, right);

        return partitionIndex;
    }

    public void swap(String[] array, int i, int j) {
        String temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public int searchQuickBinary(String[] database, String[] names) {
        int count = 0;
        int left = 0;
        int right = database.length - 1;
        for (String s :
                names) {
            if (binarySearch(database, s, left, right) != -1) {
                count++;
            }
        }
        return count;
    }

    public int binarySearch(String[] array, String elem, int left, int right) {
        if (left > right) {
            return -1;
        }

        int mid = left + (right - left) / 2;

        if (elem.equals(array[mid])) {
            return mid;
        } else if (elem.compareTo(array[mid]) < 0) {
            return binarySearch(array, elem, left, mid - 1);
        } else {
            return binarySearch(array, elem, mid + 1, right);
        }
    }

    public void createTable(String[] array) {
        if (array.length > 0) {
            for (String s : array) {
                String number = s.split(" ")[0];
                String name = s.split(" ", 2)[1];
                table.put(name, number);
            }
        }
    }

    public int searchHashtable(String[] array) {
        int count = 0;
        for (String s : array) {
            if (table.containsKey(s)) {
                count++;
            }
        }
        return count;
    }
}
