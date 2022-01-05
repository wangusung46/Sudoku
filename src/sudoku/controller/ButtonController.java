package sudoku.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import sudoku.model.Game;
import sudoku.view.ButtonPanel;
import sudoku.view.SudokuPanel;

/**
 * This class controls all user actions from ButtonPanel.
 *
 * @author Joshua 2021080138
 */
public class ButtonController implements ActionListener {

    private final Game game;
    private final SudokuPanel sudokuPanel;
    private final ButtonPanel buttonPanel;

    /**
     * Constructor, sets game.
     *
     * @param sudokuPanel
     * @param buttonPanel
     * @param game Game to be set.
     */
    public ButtonController(SudokuPanel sudokuPanel, ButtonPanel buttonPanel, Game game) {
        this.sudokuPanel = sudokuPanel;
        this.game = game;
        this.buttonPanel = buttonPanel;
    }

    /**
     * Performs action after user pressed button.
     *
     * @param e ActionEvent.
     */
    @Override
    @SuppressWarnings("fallthrough")
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        switch (e.getActionCommand()) {
            case "Reset":
                game.resetGame();
                game.setSteps(0);
                buttonPanel.resetSteps(game);
                break;
            case "Submit":
                game.checkGame();
                for (int y = 0; y < 9; y++) {
                    for (int x = 0; x < 9; x++) {
                        if (!game.getSubmit()[x][y]) {
                            JOptionPane.showMessageDialog(null, "Please check red boxes / empty boxes", "Answers are incorrect", JOptionPane.INFORMATION_MESSAGE);
                            return;
                        }
                    }
                }
                JOptionPane.showMessageDialog(null, "Congratulation have completed this level", "Level Complete", JOptionPane.INFORMATION_MESSAGE);
                game.newGame();
                break;
            case "Exit":
                System.exit(0);
            case "Hint":
                sudokuPanel.setHint2(game);
                buttonPanel.setSteps2(game);
                break;
            case "Level 1":
                game.setLevel(70);
                game.newGame();
                buttonPanel.resetSteps(game);
                break;
            case "Level 2":
                game.setLevel(60);
                game.newGame();
                buttonPanel.resetSteps(game);
                break;
            case "Level 3":
                game.setLevel(50);
                game.newGame();
                buttonPanel.resetSteps(game);
                break;
            case "Level 4":
                game.setLevel(40);
                game.newGame();
                buttonPanel.resetSteps(game);
                break;
            case "Level 5":
                game.setLevel(30);
                game.newGame();
                buttonPanel.resetSteps(game);
                break;
            default:
                game.setSelectedNumber(Integer.parseInt(e.getActionCommand()));
                break;
        }
    }
}
