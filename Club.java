package archery;

import archery.archers.Archer;
import archery.bows.Bow;
import archery.util.Validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Club {

    private String name;
    private String address;
    private String trainer;
    private HashSet<Archer> archers;
    private HashMap<Integer, HashSet<Archer>> contestants;
    public static final int JUNIORS = 0;
    public static final int SENIORS = 1;
    public static final int VETERANS = 2;

    public Club(String name, String address, String trainer) {
        contestants = new HashMap<>();

        /* We can create the keys here, only if we know that the type will never change in the future, but we don't know that
        contestants.put(JUNIORS, new HashSet<>());
        contestants.put(SENIORS, new HashSet<>());
        contestants.put(VETERANS, new HashSet<>());
        */

        if (Validator.isValidString(name)) {
            this.name = name;
        }
        if (Validator.isValidString(address)) {
            this.address = address;
        }
        if (Validator.isValidString(trainer)) {
            this.trainer = trainer;
        }
        this.archers = new HashSet<>();
    }

    public void addArcher(Archer archer) {
        archers.add(archer);
    }

    public void startTournament() {

        showSortedContestantsByName();

        for (Archer a : archers) {
            if (!contestants.containsKey(a.getType())) {        //Check if there is a key...
                contestants.put(a.getType(), new HashSet<>());  //...if not -> create one + value
            } else {
                contestants.get(a.getType()).add(a);            //Add an archer in the correct key type
            }
            a.attend();
            a.shootArrows();
        }

        printWinner();
        System.out.println("====================== Average scores ==================");
        printAvgScore();
        printSharpShooter();
        printWorstShooter();
        printWomenByAccuracy();
        printMenWithCarbonByExp();
    }

    private void printMenWithCarbonByExp() {

        /* Another possibility using streams:
         System.out.println("============ MEN BY EXPERIENCE ==============");
         archers.stream()
              .filter(a -> a.getGender() == Archer.MALE && a.getBow().getType().equals(Bow.BOW_TYPE_CARBON))
              .sorted((o1, o2) -> o1.getYearsOfTraining() - o2.getYearsOfTraining())
              .forEach(a -> System.out.println(a.getName() + " ->" + a.getYearsOfTraining()));
        */

        ArrayList<Archer> sortedByYearsExp = new ArrayList<>();          //Not TreeSet, because archers with same years of exp won't enter
        for (HashSet<Archer> archersByType : contestants.values()) {    //Iterating through all contestants (values only)
            for (Archer a : archersByType) {                           //Iterating archersByType
                if (a.getGender() == Archer.MALE && a.getBow().getType().equals(Bow.BOW_TYPE_CARBON)) {
                    sortedByYearsExp.add(a);
                }
            }
        }
        sortedByYearsExp.sort((o1, o2) -> Integer.compare(o2.getYearsOfTraining(), o1.getYearsOfTraining())); //Can use also: o1.getYearsOfTraining() - o2.getYearsOfTraining()

        System.out.println("============ MEN BY EXPERIENCE ==============");
        for (Archer a : sortedByYearsExp) {
            System.out.println(a.getName() + " -> " + a.getYearsOfTraining());
        }
    }

    private void printWomenByAccuracy() {

    /* Another possibility using streams:
    System.out.println("============ WOMEN BY ACCURACY ==============");
    archers.stream()
                .filter(a -> a.getGender() == Archer.FEMALE)
                .sorted((o1, o2) -> Double.compare(o1.getStats().getAccuracy(), o2.getStats().getAccuracy()))
                .forEach(a -> System.out.println(a.getName() + " -> " + a.getStats().getAccuracy() + "%"));
     */

        ArrayList<Archer> womenByAccuracy = new ArrayList<>();
        for (HashSet<Archer> archersByType : contestants.values()) {
            for (Archer a : archersByType) {
                if (a.getGender() == Archer.FEMALE) {
                    womenByAccuracy.add(a);
                }
            }
        }
        womenByAccuracy.sort((o1, o2) -> Double.compare(o2.getStats().getAccuracy(), o1.getStats().getAccuracy()));

        System.out.println("============ WOMEN BY ACCURACY ==============");
        for (Archer a : womenByAccuracy) {
            System.out.println(a.getName() + " -> " + a.getStats().getAccuracy() + " %");
        }
    }

    private void printWorstShooter() { //Taking the guy with most misses
        Archer worstShooter = archers.stream()
                .max((o1, o2) -> o1.getStats().getMisses() - o2.getStats().getMisses())
                .get();
        System.out.println("============ Worst shooter of the tournament ===========");
        System.out.println(worstShooter);
    }

    private void printSharpShooter() {
        Archer sharpShooter = archers.stream()
                .max((o1, o2) -> Double.compare(o1.getStats().getTensPercent(), o2.getStats().getTensPercent()))
                .get();
        System.out.println("============ Sharpshooter of the tournament ============");
        System.out.println(sharpShooter);
    }

    private void printAvgScore() {
        System.out.println("Juniors avg:");
        System.out.println(getAvg(JUNIORS));
        System.out.println("Seniors avg:");
        System.out.println(getAvg(SENIORS));
        System.out.println("Veterans avg:");
        System.out.println(getAvg(VETERANS));
    }

    private double getAvg(int category) {

        /* With stream:
        return contestants.get(category).stream()
                .mapToInt(value -> value.getStats().getTotalPoints())
                .summaryStatistics().getAverage();
         */

        int total = contestants.get(category).size();
        double sum = 0;
        for (Archer a : contestants.get(category)) {
            sum += a.getStats().getTotalPoints();
        }
        return Math.round((sum / total) * 100.0) / 100.0;
    }

    private void printWinner() {
        System.out.println("============ Winners of the tournament =================");
        System.out.println("Juniors winner:");
        System.out.println(getWinner(JUNIORS));
        System.out.println("Seniors winner:");
        System.out.println(getWinner(SENIORS));
        System.out.println("Veterans winner:");
        System.out.println(getWinner(VETERANS));
    }

    private Archer getWinner(int category) {
        Archer winner = null;
        for (Archer a : contestants.get(category)) {
            if (winner == null) {
                winner = a;
            } else {
                if (a.getStats().getTotalPoints() > winner.getStats().getTotalPoints()) {
                    winner = a;
                } else {
                    if (a.getStats().getTotalPoints() == winner.getStats().getTotalPoints() &&
                            a.getStats().getTens() > winner.getStats().getTens()) {
                        winner = a;
                    }
                }
            }
        }
        return winner;
    }

//      ========== THIS IS AN EXAMPLE OF HOW TO MAKE A TREESET TO ALLOW DUPLICATE ELEMENTS IN IT ==========
//  private void showSortedContestantsByName() {
//        TreeSet<Archer> sorted = new TreeSet<>((o1, o2) -> {
//            if (o1.getName().compareTo(o2.getName()) == 0) {
//                return 1; --> Everything different from 0 works.
//            } else {
//                return o1.getName().compareTo(o2.getName());
//            }
//        });
//        sorted.addAll(archers);

    private void showSortedContestantsByName() {
        ArrayList<Archer> sortedArchersByName = new ArrayList<>();
        sortedArchersByName.addAll(archers); //From one collection to another
        sortedArchersByName.sort((o1, o2) -> o1.getName().compareTo(o2.getName())); //Sorting by name

        int position = 1;
        for (Archer a : sortedArchersByName) {
            System.out.println((position++) + ":" + a.getName());
        }
    }

    @Override
    public String toString() {
        return "Club: " + name + ", address: " + address + ", trainer: " + trainer;
    }
}

