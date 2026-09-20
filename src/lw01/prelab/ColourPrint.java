package lw01.prelab;

public class ColourPrint extends PrintJob {

    public ColourPrint(String id, int pages) {
        super(id, pages);
    }
    
    @Override 
    public String label() {
        return "Colour";
    }

    @Override 
    public int calculateCharge() {
        int price = 0;

        if (getPages() <= 10) {
            price = (getPages() * 1500) + 2000;
        } else {
            price = (10 * 1500) + ((getPages() - 10) * 1000) + 2000;
        }

        return price;
    }
}
