package archery.archers;

public class Stats {

    private int misses;
    private int tens;
    private int totalArrows;
    private int totalPoints;
    private double accuracy;

    public Stats(int totalArrows) {
        this.totalArrows = totalArrows;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void addMiss() {
        misses++;
    }

    public void addTen() {
        tens++;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public double getTensPercent() {
        return (tens * 1.0 / totalArrows) * 100;
    }

    public double getAccuracy() {
        int maxPoints = totalArrows * 10;
        accuracy = (totalPoints * 1.0 / maxPoints) * 100;
        return Math.round(accuracy * 100.0) / 100.0;
    }

    public int getMisses() {
        return misses;
    }

    public int getTens() {
        return tens;
    }

}
