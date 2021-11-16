public class Bug
{
    // get our position variables ready
    Position x;
    Position y;

    // getting the size
    int size;

    public Bug(int initialx, int initialy, int size)
    {
        // initializing the position
        x = new Position(initialx);
        y = new Position(initialy);

        // initializing the size
        this.size = size;
    }

    // so the position can be grabbed
    public Position GetPositionX() { return x; }
    public Position GetPositionY() { return y; }

    // so the position can be changed
    public void MoveUp() { if(y.GetPos() != size - 1) y.PosAdd(); }
    public void MoveDown() { if(y.GetPos() != 0) y.PosSub(); }
    public void MoveRight() { if(x.GetPos() != size - 1) x.PosAdd(); }
    public void MoveLeft() { if(x.GetPos() != 0) x.PosSub(); }
}
