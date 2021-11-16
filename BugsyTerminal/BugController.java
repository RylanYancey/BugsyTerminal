import java.util.Scanner;
import java.util.Random;

// Rylan Yancey // CGW512
// 11/ 15/ 21/ 

// Bugsy the Game
// This is a simple game that plays in your Terminal
// Read the HowToPlayBugsy.txt to learn how to play

public class BugController
{
    public static void main(String[] args)
    {
        // Get our imports so we can use them
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        
        // set the size of the game field
        int size = 7;

        // initialize the game field
        GameField field = new GameField(size);

        // initialize the score
        int score = 0;

        // Get the player ready
        Bug player = new Bug(random.nextInt(size), random.nextInt(size), size);
        Position x = player.GetPositionX();
        Position y = player.GetPositionY();
        field.SetFieldIndex(x.GetPos(), y.GetPos(), " # ");

        // place the first food
        boolean valid = false;
        int randomx;
        int randomy;
        // we use a while loop because we dont want it to spawn
        // on an existing object (like the player)
        while(valid == false)
        {
            randomx = random.nextInt(size);
            randomy = random.nextInt(size);

            if(field.GetFieldIndex(randomx, randomy) == " - ")
            {
                valid = true;
                field.SetFieldIndex(randomx, randomy, " $ ");
            }
        }

        // initialize enemies that travel left to right
        EnemyRight Er1 = new EnemyRight(1, field, size);
        EnemyRight Er2 = new EnemyRight(0, field, size);

        // initialize enemies that travel up and down
        EnemyUp Eu1 = new EnemyUp(1, field, size);
        EnemyUp Eu2 = new EnemyUp(0, field, size);

        // variables for the loop
        boolean gameOver = false;
        String input;

        while(gameOver == false)
        {
            // iterate the score down to increase pressure
            if(score > 0) score -= 1;

            // print out the score, then display the game field
            System.out.println("Score: " + score);
            field.DisplayField();

            // Rread inputs from user
            System.out.println("\n Input one of the following to move: w, a, s, or d.  Input e to exit: ");
            input = scanner.nextLine();

            // Set the player's position to be - 
            field.SetFieldIndex(x.GetPos(), y.GetPos(), " - ");

            // move the player
            if(input.equals("w")) player.MoveDown();
            if(input.equals("a")) player.MoveLeft();
            if(input.equals("s")) player.MoveUp();
            if(input.equals("d")) player.MoveRight();
            if(input.equals("e")) gameOver = true;

            // check if the player collected money, if so, move the money elsewhere and add score
            if(field.GetFieldIndex(x.GetPos(), y.GetPos()) == " $ ")
            {
                score += 20;

                valid = false;
                while(valid == false)
                {
                    randomx = random.nextInt(size);
                    randomy = random.nextInt(size);
        
                    if(randomx != x.GetPos() && randomy != y.GetPos())
                    {
                        valid = true;
                        field.SetFieldIndex(randomx, randomy, " $ ");
                    }
                }
            }

            // check for death by walking into enemy
            if((field.GetFieldIndex(x.GetPos(), y.GetPos()) != " % " ) && (field.GetFieldIndex(x.GetPos(), y.GetPos()) != " @ " ))
            {
                field.SetFieldIndex(x.GetPos(), y.GetPos(), " # ");
            }
            else
            {
                // if dead, end game
                System.out.println("You got stepped on by an enemy!");
                gameOver = true;
            }

            // update positions of enemies
            Er1.UpdatePosition();
            Er2.UpdatePosition();
            
            Eu1.UpdatePosition();
            Eu2.UpdatePosition();

            // check for death *again* this time to check if the player was standing in the way
            if((field.GetFieldIndex(x.GetPos(), y.GetPos()) == " % " ) || (field.GetFieldIndex(x.GetPos(), y.GetPos()) == " @ " ))
            {
                System.out.println("You got stepped on by an enemy!");
                gameOver = true;
            }
        }

        // let the player know the game is over and their score
        System.out.println(" Game Over! Your Score: " + score);

        // close the scanner
        scanner.close();
    }
}