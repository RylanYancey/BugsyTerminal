import java.util.Random;

public class EnemyRight 
{
    // we need random to ofind their initial location
    Random random = new Random();

    // get variables ready
    int direction;

    Position x;
    Position y;
    GameField field;
    int size;

    public EnemyRight(int direction, GameField field, int size)
    {
        // initialize variables
        this.direction = direction;

        this.x = new Position(0);
        this.y = new Position(0);

        this.field = field;
        this.size = size;

        Randomize();
    }

    // randomize the position upon start
    private void Randomize()
    {
        boolean valid = false;
        int randomx; 
        int randomy;
        while(valid == false)
        {
            randomx = random.nextInt(size);
            randomy = random.nextInt(size);

            if(field.GetFieldIndex(randomx, randomy) == " - ")
            {
                valid = true;
                field.SetFieldIndex(randomx, randomy, " @ ");
                x.PosSet(randomx);
                y.PosSet(randomy);
            }
        }
    }

    // update the position in the game to keep them moving back and forth
    public void UpdatePosition()
    {
        if(field.GetFieldIndex(x.GetPos(), y.GetPos()) != " $ " )field.SetFieldIndex(x.GetPos(), y.GetPos(), " - ");
        if(direction == 0)
        {
            if(x.GetPos() == size - 1)
            {
                direction = 1;
                MoveLeft();
            }
            else MoveRight();
        }
        else if(direction == 1)
        {
            if(x.GetPos() == 0)
            {
                direction = 0;
                MoveRight();
            }
            else MoveLeft();
        }
        if(field.GetFieldIndex(x.GetPos(), y.GetPos()) != " $ ") field.SetFieldIndex(x.GetPos(), y.GetPos(), " @ ");
    }

    // get and set methods
    public Position GetPositionX() { return x; }
    public Position GetPositionY() { return y; }

    // methods to change position
    public void MoveRight() { x.PosAdd(); }
    public void MoveLeft() { x.PosSub(); }
}
