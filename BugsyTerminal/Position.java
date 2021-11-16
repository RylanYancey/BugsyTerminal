public class Position
{
    // declare variable
    int position;

    public Position(int initial)
    {
        // initialize variable
        this.position = initial;
    }

    // change variable
    public void PosAdd() { position++; }
    public void PosSub() { position--; }

    // set variable
    public void PosSet(int set) {position = set; }

    // get variable
    public int GetPos() { return position; }
}