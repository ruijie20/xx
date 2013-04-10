package Rich;


public class Land {
    private final int landNum;
    private int price;
    private int owner;
    private String mark;


    public Land(int landNum, int price, int owner, String mark) {
        this.landNum = landNum;
        this.price = price;
        this.owner = owner;
        this.mark = mark;
    }

    public int getLandNum() {
        return landNum;
    }

    public int getPrice() {
        return price;
    }

    public String getMark() {
        return mark;
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Land)) return false;

        Land land = (Land) o;

        if (landNum != land.landNum) return false;
        if (owner != land.owner) return false;
        if (price != land.price) return false;
        if (!mark.equals(land.mark)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = landNum;
        result = 31 * result + price;
        result = 31 * result + owner;
        result = 31 * result + mark.hashCode();
        return result;
    }
}
