import java.util.Random;

public class EnemyUp
{
    // we need random to randomize variables
    Random random = new Random();

    // variable declaration
    int direction;

    Position x;
    Position y;
    GameField field;
    int size;

    public EnemyUp(int direction, GameField field, int size)
    {
        // initialize variables
        this.direction = direction;

        this.x = new Position(0);
        this.y = new Position(0);

        this.field = field;
        this.size = size;

        Randomize();
    }

    // randomize location on start
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
                field.SetFieldIndex(randomx, randomy, " % ");
                x.PosSet(randomx);
                y.PosSet(randomy);
            }
        }
    }

    // update position to move left and right during game
    public void UpdatePosition()
    {
        if(field.GetFieldIndex(x.GetPos(), y.GetPos()) != " $ " )field.SetFieldIndex(x.GetPos(), y.GetPos(), " - ");
        if(direction == 0)
        {
            if(y.GetPos() == size - 1)
            {
                direction = 1;
                MoveUp();
            }
            else MoveDown();
        }
        else if(direction == 1)
        {
            if(y.GetPos() == 0)
            {
                direction = 0;
                MoveDown();
            }
            else MoveUp();
        }
        if(field.GetFieldIndex(x.GetPos(), y.GetPos()) != " $ ") field.SetFieldIndex(x.GetPos(), y.GetPos(), " % ");
    }

    // get method
    public Position GetPositionX() { return x; }
    public Position GetPositionY() { return y; }

    // set method
    public void MoveDown() { y.PosAdd(); }
    public void MoveUp() { y.PosSub(); }
}
