public class Position {
    private int x;
    private int y;

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Position){
            return ((Position) obj).getX() == this.x && ((Position) obj).getY() == this.y;

        }
        return false;
    }
}
