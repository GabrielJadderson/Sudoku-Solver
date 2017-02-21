import javax.swing.*;
import java.awt.*;

/**
 * Created by GabrielHoward on 08/11/16.
 */
public final class Constants //singleton
{
    public static Thread sudokuThread;
    public static Runnable SudokuRunnable;
    public static Field field = new Field(); //The Field class represents the Sudoku puzzle
    public static JFrame q = new JFrame();
    public static String fileName = ""; //the name of the file to solve, used by JFileChooser.
    public static final String APP_TITLE = "Sudoku Solver"; //the name of the application
    public static final Dimension dimension = new Dimension(185, 280); //the dimension which holds the width and height of our app.
    public static SudokuBoard sudokuBoard; //the sudoku board container, responsible for graphically displaying the board.
    public static JButton solve; //Jbutton declared statically. Not safe, but if used correctly can be edited during runtime.
    public static JButton load;
    public static JButton save;
    public static SSpinner spinner;
    public static boolean isSolved = false; // isSolved is true when the board is solved and false when a new file is loaded.
}
