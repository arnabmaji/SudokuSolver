public class Position {
    private int x;
    private int y;

    Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    int getX(){
        return this.x;
    }

    int getY(){
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
