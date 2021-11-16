public class GameField
{
    // get variables ready
    int size;
    String[][] field;

    public GameField(int size)
    {
        // initialize variables
        this.size = size;
        this.field = new String[size][size];

        InitializeField();
    }   
    
    // initialize the game field with a loop that sets them all to -
    public void InitializeField()
    {
        for(int x = 0; x < size; x++)
        {
            for(int y = 0; y < size; y++)
            {
                field[x][y] = " - ";
            }
        }
    }

    // display the field as a square
    public void DisplayField()
    {
        for(int x = 0; x < size; x++)
        {
            System.out.print("\n");
            for(int y = 0; y < size; y++)
            {
                System.out.print(field[y][x]);
            }
        }

    }

    // Get and Set methods
    public String GetFieldIndex(int x, int y) { return field[x][y]; }
    public void SetFieldIndex(int x, int y, String set) { field[x][y] = set; }
    
}
