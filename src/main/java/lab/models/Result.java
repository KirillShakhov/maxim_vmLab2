package lab.models;

public class Result {
    private Point point;
    private StringBuilder table = new StringBuilder();
    private int i;

    public Result(int i) {
        this.i = i;
    }

    public void addCol(String ... v){
        table.append(i++);
        for(String i : v){
            table.append(" | ").append(i);
        }
        table.append("\n");
    }
    public void addHeader(String ... v){
        for(String i : v){
            table.append(i).append(" | ");
        }
        table.append("\n");
    }
    public void printTable(){
        System.out.println(table.toString());
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }
}
