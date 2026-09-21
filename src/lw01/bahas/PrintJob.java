package lw01.bahas;

public abstract class PrintJob implements Chargeable {
    private String id;
    private int pages;

    protected  PrintJob(String id, int pages) { //tanda # artinya protected, constructor
        if (pages <= 0) {
            throw new IllegalArgumentException("pages must be a positive number");
        }

        this.id = id;
        this.pages = pages;
    }

    public String getId() { //getter
        return id;
    }

    public int getPages() { //getter
        return pages;
    }

    //override interface
    @Override 
    public abstract int calculateCharge();

    //methode copies
    public int calculateCharge(int copies) {
        if (copies <= 0) {
            throw new IllegalArgumentException("copies must be a positive number");
        }

        return copies & calculateCharge();
    }

    //method label
    public String label() {
        return "Print";
    }

    //method summary
    public String summary() {
        return id + " | " + label() + " | " + calculateCharge();
    }
}
