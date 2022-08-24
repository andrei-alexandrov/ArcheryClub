package club;

import archers.Archer;
import bows.Bow;
import util.Validator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Club {

    //Characteristics:
    private String name;
    private String address;
    private String trainer;
    private final HashSet<Archer> archers;
    private final HashMap<Integer, HashSet<Archer>> contestants;
    public static final int JUNIORS = 0;
    public static final int SENIORS = 1;
    public static final int VETERANS = 2;

    //Constructors:
    public Club(String name, String address, String trainer) {
        contestants = new HashMap<>();

        if (Validator.textValidator(name)) {
            this.name = name;
        }
        if (Validator.textValidator(address)) {
            this.address = address;
        }
        if (Validator.textValidator(trainer)) {
            this.trainer = trainer;
        }
        this.archers = new HashSet<>();
    }

    //Normal methods:
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

        ArrayList<Archer> sortedByYearsExp = new ArrayList<>();          //Not TreeSet, because archers with same years of exp won't enter
        for (HashSet<Archer> archersByType : contestants.values()) {    //Iterating through all contestants (values only)
            for (Archer a : archersByType) {                           //Iterating archersByType
                if (a.getGender() == Archer.MALE && a.getBow().getType().equals(Bow.BOW_TYPE_CARBON)) {
                    sortedByYearsExp.add(a);
                }
            }
        }
        sortedByYearsExp.sort((o1, o2) -> Integer.compare(o2.getYearsOfTraining(), o1.getYearsOfTraining()));

        System.out.println("============ MEN BY EXPERIENCE ==============");
        for (Archer archer : sortedByYearsExp) {
            System.out.println(archer.getName() + " -> " + archer.getYearsOfTraining());
        }
    }

    private void printWomenByAccuracy() {
        ArrayList<Archer> womenByAccuracy = new ArrayList<>();
        for (HashSet<Archer> archersByType : contestants.values()) {
            for (Archer archer : archersByType) {
                if (archer.getGender() == Archer.FEMALE) {
                    womenByAccuracy.add(archer);
                }
            }
        }
        womenByAccuracy.sort((o1, o2) -> Double.compare(o2.getStats().getAccuracy(), o1.getStats().getAccuracy()));

        System.out.println("============ WOMEN BY ACCURACY ==============");
        for (Archer archer : womenByAccuracy) {
            System.out.println(archer.getName() + " -> " + archer.getStats().getAccuracy() + " %");
        }
    }

    private void printWorstShooter() {
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
        int total = contestants.get(category).size();
        double sum = 0;
        for (Archer archer : contestants.get(category)) {
            sum += archer.getStats().getTotalPoints();
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
        for (Archer archer : contestants.get(category)) {
            if (winner == null) {
                winner = archer;
            } else {
                if (archer.getStats().getTotalPoints() > winner.getStats().getTotalPoints()) {
                    winner = archer;
                } else {
                    if (archer.getStats().getTotalPoints() == winner.getStats().getTotalPoints() &&
                            archer.getStats().getTens() > winner.getStats().getTens()) {
                        winner = archer;
                    }
                }
            }
        }
        return winner;
    }

    private void showSortedContestantsByName() {
        ArrayList<Archer> sortedArchersByName = new ArrayList<>();
        sortedArchersByName.addAll(archers); //From one collection to another
        sortedArchersByName.sort((o1, o2) -> o1.getName().compareTo(o2.getName())); //Sorting by name

        int position = 1;
        for (Archer a : sortedArchersByName) {
            System.out.println((position++) + ":" + a.getName());
        }
    }

    //Override methods:
    @Override
    public String toString() {
        return "Club: " + name + ", address: " + address + ", trainer: " + trainer;
    }
}
