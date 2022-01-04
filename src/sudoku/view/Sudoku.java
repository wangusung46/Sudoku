package sudoku.view;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import sudoku.controller.ButtonController;
import sudoku.controller.SudokuController;
import sudoku.model.Game;

/**
 * Main class of program.
 *
 * @author Joshua 2021080138
 */
public class Sudoku extends JFrame {

    private static final long serialVersionUID = 1L;

    public Sudoku() {
        super("Sudoku - Author by 林世友2021080138");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        Game game = new Game();
        SudokuPanel sudokuPanel = new SudokuPanel();
        ButtonPanel buttonPanel = new ButtonPanel();

        ButtonController buttonController = new ButtonController(sudokuPanel, buttonPanel, game);
        buttonPanel.setController(buttonController);
        add(buttonPanel, BorderLayout.EAST);

        SudokuController sudokuController = new SudokuController(sudokuPanel, buttonPanel, game);
        sudokuPanel.setGame(game);
        sudokuPanel.setController(sudokuController);
        add(sudokuPanel, BorderLayout.CENTER);

        game.addObserver(buttonPanel);
        game.addObserver(sudokuPanel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        buttonPanel.setTimer(game);
    }

    /**
     * Main entry point of program.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        // Use System Look and Feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException ex) {
        }
        new Sudoku();
    }
}
