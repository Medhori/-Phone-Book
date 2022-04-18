package phonebook;

import java.time.Duration;


public class Main {

    public static void main(String[] args) throws InterruptedException {
        ReadFile r = new ReadFile();
        Sort sort = new Sort();
        int count;
        String[] database = r.getStringArray(r.readDatabase("D:\\Test\\Phone Book\\directory.txt"));
        String[] databaseNames = new String[database.length];
        for (int i = 0; i < database.length; i++) {
            databaseNames[i] = database[i].split(" ", 2)[1];
        }

        String[] names = r.getStringArray(r.readNames("D:\\Test\\Phone Book\\find.txt"));
        int left = 0;
        int right = databaseNames.length - 1;


        System.out.println("Start searching (linear search)...");
        long startTime = System.currentTimeMillis();
        count = sort.linearSearch(databaseNames, names);
        long endTime = System.currentTimeMillis();
        long timeDifference = endTime - startTime;
        Duration d = Duration.ofMillis(timeDifference);
        int minutes = d.toMinutesPart();
        int seconds = d.toSecondsPart();
        int milliseconds = d.toMillisPart();
        System.out.printf("Found %d / %d entries. Time taken: %d min. %d sec. %d ms.\n", count, names.length, minutes, seconds, milliseconds);

        System.out.println();

        System.out.println("Start searching (bubble sort + jump search)...");
        Thread thread = new Thread(() -> sort.bubbleSort(databaseNames));


        long startBubble = System.currentTimeMillis();
        thread.start();
        long endBubble = System.currentTimeMillis();
        long timeDifBubble;


        while (true) {

            endBubble = System.currentTimeMillis();
            timeDifBubble = endBubble - startBubble;
            Duration dBubble = Duration.ofMillis(timeDifBubble);
            int minBubble = dBubble.toMinutesPart();
            int secBubble = dBubble.toSecondsPart();
            int millisBubble = dBubble.toMillisPart();

            if (timeDifference * 10 < timeDifBubble) {
                thread.stop();


                long stratTimeLinear2 = System.currentTimeMillis();
                count = sort.linearSearch(databaseNames, names);
                long endtimeLinear2 = System.currentTimeMillis();
                long timeDiflinear2 = endtimeLinear2 - stratTimeLinear2;
                Duration dLinear2 = Duration.ofMillis(timeDiflinear2);
                int minLinear2 = dLinear2.toMinutesPart();
                int secLinear2 = dLinear2.toSecondsPart();
                int millisLin2 = dLinear2.toMillisPart();


                Duration totalDuration = Duration.ofMillis(timeDifBubble + timeDiflinear2);
                int minTotal = totalDuration.toMinutesPart();
                int secTotal = totalDuration.toSecondsPart();
                int millisTotal = totalDuration.toMillisPart();

                System.out.printf("Found %d / %d entries. Time taken: %d min. %d sec. %d ms.\n", count, names.length, minTotal, secTotal, millisTotal);
                System.out.printf("Sorting time: %d min. %d sec. %d ms. - STOPPED, moved to linear search\n", minBubble, secBubble, millisBubble);
                System.out.printf("Searching time: %d min. %d sec. %d ms.\n", minLinear2, secLinear2, millisLin2);

                break;
            }

            if (!thread.isAlive()) {

                long startJumpTime = System.currentTimeMillis();
                count = sort.searchBubbleJump(databaseNames, names);
                long endJumpTime = System.currentTimeMillis();
                long difJumpTime = endJumpTime - startJumpTime;
                Duration dJump = Duration.ofMillis(difJumpTime);
                int secJump = dJump.toSecondsPart();
                int millisJump = dJump.toMillisPart();

                Duration totalDuration = Duration.ofMillis(timeDifBubble + difJumpTime);
                int minTotal = totalDuration.toMinutesPart();
                int secTotal = totalDuration.toSecondsPart();
                int millisTotal = totalDuration.toMillisPart();

                System.out.printf("Found %d / %d entries. Time taken: %d min. %d sec. %d ms.\n", count, names.length, minTotal, secTotal, millisTotal);
                System.out.printf("Sorting time: %d min. %d sec. %d ms.\n", minBubble, secBubble, millisBubble);
                System.out.printf("Searching time: %d min. %d sec. %d ms.\n", millisJump, secJump, millisJump);


                break;
            }
        }


        System.out.println();
        System.out.println("Start searching (quick sort + binary search)...");
        Thread thread1 = new Thread(() -> sort.quickSort(databaseNames, left, right));


        long startQuickSort = System.currentTimeMillis();
        thread1.start();
        long endQuickSort = System.currentTimeMillis();
        long timeDifQuick;


        while (true) {
            endQuickSort = System.currentTimeMillis();
            timeDifQuick = endQuickSort - startQuickSort;
            Duration quickDuration = Duration.ofMillis(timeDifQuick);
            int minQuick = quickDuration.toMinutesPart();
            int secQuick = quickDuration.toSecondsPart();
            int millisQuick = quickDuration.toMillisPart();


            if (timeDifference * 10 < timeDifQuick) {
                thread1.stop();

                // linearSearch 2
                long stratTimeLinear2 = System.currentTimeMillis();
                count = sort.linearSearch(databaseNames, names);
                long endtimeLinear2 = System.currentTimeMillis();
                long timeDiflinear2 = endtimeLinear2 - stratTimeLinear2;
                Duration dLinear2 = Duration.ofMillis(timeDiflinear2);
                int minLinear2 = dLinear2.toMinutesPart();
                int secLinear2 = dLinear2.toSecondsPart();
                int millisLin2 = dLinear2.toMillisPart();

                // total duration
                Duration totalDuration = Duration.ofMillis(timeDifBubble + timeDiflinear2);
                int minTotal = totalDuration.toMinutesPart();
                int secTotal = totalDuration.toSecondsPart();
                int millisTotal = totalDuration.toMillisPart();

                System.out.printf("Found %d / %d entries. Time taken: %d min. %d sec. %d ms.\n", count, names.length, minTotal, secTotal, millisTotal);
                System.out.printf("Sorting time: %d min. %d sec. %d ms. - STOPPED, moved to linear search\n", minQuick, secQuick, millisQuick);
                System.out.printf("Searching time: %d min. %d sec. %d ms.\n", minLinear2, secLinear2, millisLin2);

                break;
            }

            if (!thread1.isAlive()) {

                long startBinaryTime = System.currentTimeMillis();
                count = sort.searchQuickBinary(databaseNames, names);
                long endBinaryTime = System.currentTimeMillis();
                long difBinaryTime = endBinaryTime - startBinaryTime;
                Duration dBinary = Duration.ofMillis(difBinaryTime);
                int minBinary = dBinary.toMinutesPart();
                int secBinary = dBinary.toSecondsPart();
                int millisBinary = dBinary.toMillisPart();


                Duration totalDuration = Duration.ofMillis(timeDifQuick + difBinaryTime);
                int minTotal = totalDuration.toMinutesPart();
                int secTotal = totalDuration.toSecondsPart();
                int millisTotal = totalDuration.toMillisPart();

                System.out.printf("Found %d / %d entries. Time taken: %d min. %d sec. %d ms.\n", count, names.length, minTotal, secTotal, millisTotal);
                System.out.printf("Sorting time: %d min. %d sec. %d ms.\n", minQuick, secQuick, millisQuick);
                System.out.printf("Searching time: %d min. %d sec. %d ms.\n", minBinary, secBinary, millisBinary);

                break;
            }


        }


        System.out.println();
        System.out.println("Start searching (hash table)...");
        Thread thread3 = new Thread(() -> sort.createTable(database));


        long startHashSort = System.currentTimeMillis();
        thread3.start();
        long endHashSort = System.currentTimeMillis();
        long timeDifHash;


        while (true) {
            endHashSort = System.currentTimeMillis();
            timeDifHash = endHashSort - startHashSort;
            Duration HashDuration = Duration.ofMillis(timeDifHash);
            int minHash = HashDuration.toMinutesPart();
            int secHash = HashDuration.toSecondsPart();
            int millisHash = HashDuration.toMillisPart();


            if (timeDifference * 10 < timeDifHash) {
                thread3.stop();

                // linearSearch 2
                long stratTimeLinear3 = System.currentTimeMillis();
                count = sort.linearSearch(databaseNames, names);
                long endtimeLinear3 = System.currentTimeMillis();
                long timeDiflinear3 = endtimeLinear3 - stratTimeLinear3;
                Duration dLinear3 = Duration.ofMillis(timeDiflinear3);
                int minLinear3 = dLinear3.toMinutesPart();
                int secLinear3 = dLinear3.toSecondsPart();
                int millisLin3 = dLinear3.toMillisPart();

                // total duration
                Duration totalDuration = Duration.ofMillis(timeDifHash + timeDiflinear3);
                int minTotal = totalDuration.toMinutesPart();
                int secTotal = totalDuration.toSecondsPart();
                int millisTotal = totalDuration.toMillisPart();

                System.out.printf("Found %d / %d entries. Time taken: %d min. %d sec. %d ms.\n", count, names.length, minTotal, secTotal, millisTotal);
                System.out.printf("Sorting time: %d min. %d sec. %d ms. - STOPPED, moved to linear search\n", minHash, secHash, millisHash);
                System.out.printf("Searching time: %d min. %d sec. %d ms.\n", minLinear3, secLinear3, millisLin3);

                break;
            }

            if (!thread3.isAlive()) {

                long startTableTime = System.currentTimeMillis();
                count = sort.searchHashtable(names);
                long endTableTime = System.currentTimeMillis();
                long difTableTime = endTableTime - startTableTime;
                Duration dTable = Duration.ofMillis(difTableTime);
                int minTable = dTable.toMinutesPart();
                int secTable = dTable.toSecondsPart();
                int millisTable = dTable.toMillisPart();


                Duration totalDuration = Duration.ofMillis(timeDifHash + difTableTime);
                int minTotal = totalDuration.toMinutesPart();
                int secTotal = totalDuration.toSecondsPart();
                int millisTotal = totalDuration.toMillisPart();

                System.out.printf("Found %d / %d entries. Time taken: %d min. %d sec. %d ms.\n", count, names.length, minTotal, secTotal, millisTotal);
                System.out.printf("Creating time: %d min. %d sec. %d ms.\n", minHash, secHash, millisHash);
                System.out.printf("Searching time: %d min. %d sec. %d ms.\n", minTable, secTable, millisTable);

                break;
            }
        }
    }
}
