import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;


/**
 * Created by Gabriel Howard Jadderson on 06-11-2016.
 */
public class GUI extends Sudoku
{

    public GUI()
    {
        updateLookAndFeel();

        Constants.q = new JFrame();
        Constants.q.setTitle(Constants.APP_TITLE);
        Constants.q.setPreferredSize(Constants.dimension);
        Constants.q.getContentPane().setBackground(Color.WHITE);
        Constants.q.setLocationRelativeTo(null); //center
        Constants.q.setLayout(new FlowLayout()); //Found this to fit better
        Constants.q.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Constants.q.setResizable(false);
        setIcons();
        addComponents();
        Constants.q.pack();
        Constants.q.validate();
        Constants.q.setVisible(true);
    }

    //because the java look and feel is horrible and slow...
    private void updateLookAndFeel()
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex)
        {
        }
    }

    private void addComponents()
    {
        Constants.sudokuBoard = new SudokuBoard(Constants.field);

        //====================================== solve button ======================================
        Constants.solve = new JButton("Solve");
        Constants.solve.setVisible(false);
        Constants.solve.setForeground(Color.RED);
        Constants.solve.setFocusPainted(false);
        Constants.solve.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (Constants.fileName != "")
                {
                    if (!Constants.isSolved)
                    {
                        Constants.field.fromFile(Constants.fileName);
                        try
                        {
                            Constants.SudokuRunnable = () ->
                            {
                                try
                                {
                                    Sudoku.generateRandomStartColor();
                                    solve(Constants.field, 0, 0);
                                } catch (SolvedException sse)
                                {
                                    Constants.isSolved = true;
                                }
                            };
                            Constants.sudokuThread = new Thread(Constants.SudokuRunnable);
                            Constants.sudokuThread.start();
                        } catch (Exception se)
                        {
                            Constants.isSolved = true;
                        }
                        System.out.println(Constants.field);
                        Constants.sudokuBoard.updateDigits(Constants.field);
                    } else
                    {
                        Constants.sudokuBoard.assignRandomColor();
                    }
                } else
                {
                    new JOptionPane().showMessageDialog(Constants.q, "Browse for a Sudoku file to be solved", "No Sudoku file found", JOptionPane.OK_OPTION);
                }
            }
        });

        //====================================== load button ======================================
        Constants.load = new JButton("Load");
        Constants.load.setFocusPainted(false);
        Constants.load.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (initFileChooser(true))
                {
                    Constants.isSolved = false;
                    Constants.field.reset();
                    Constants.field.fromFile(Constants.fileName);
                    Constants.sudokuBoard.updateDigits(Constants.field);
                    Constants.sudokuBoard.assignStartColor(0, 0, 0);
                    Constants.solve.setVisible(true);
                    Constants.save.setVisible(true);
                    Constants.q.validate();
                    Constants.q.repaint();
                }
            }
        });

        //====================================== save button ======================================
        Constants.save = new JButton("Save");
        Constants.save.setVisible(false);
        Constants.save.setFocusPainted(false);
        Constants.save.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (Constants.isSolved)
                {
                    if (initFileChooser(false))
                    {
                        Constants.field.fromFile(Constants.fileName);
                        Constants.sudokuBoard.updateDigits(Constants.field);
                        Constants.solve.setVisible(true);
                    }
                } else
                {
                    new JOptionPane().showMessageDialog(Constants.q, "There is no point in saving an unsolved Sudoku puzzle ;)", "Not solved", JOptionPane.OK_OPTION);
                }
            }
        });
        Constants.spinner = new SSpinner();

        Constants.q.add(Constants.sudokuBoard);
        Constants.q.add(Constants.load);
        Constants.q.add(Constants.spinner);
        Constants.q.add(Constants.save);
        Constants.q.add(Constants.solve);


    }


    private boolean initFileChooser(boolean open)
    {
        String WORKING_DIR = System.getProperty("user.dir");
        JFileChooser jfc = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Sudoku Text File (.txt)", "txt");
        jfc.setFileFilter(filter);
        jfc.setCurrentDirectory(new File(WORKING_DIR));
        if (open)
        {
            int returnVal = jfc.showOpenDialog(Constants.q);
            if (returnVal == JFileChooser.APPROVE_OPTION)
            {
                if (jfc.getSelectedFile().getName() != null && jfc.getSelectedFile().getName() != "")
                {
                    Constants.fileName = jfc.getSelectedFile().getName();
                    return true;
                }
            }
        } else
        {
            int returnVal = jfc.showSaveDialog(Constants.q);
            if (returnVal == JFileChooser.APPROVE_OPTION)
            {
                try
                {
                    FileWriter fileWriter = new FileWriter(jfc.getSelectedFile() + ".txt");
                    fileWriter.write(Constants.field.toString());
                    fileWriter.close();
                    return true;
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }

        return false;
    }

    private void setIcons()
    {
        File iconFile = new File("app_icon.png");
        if (iconFile != null && iconFile.exists())
        {
            Constants.q.setIconImage(new ImageIcon(iconFile.getAbsolutePath()).getImage());
        }
    }

}
