package sudoku.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBox;
import sudoku.model.Game;
import sudoku.view.ButtonPanel;
import sudoku.view.SudokuPanel;

/**
 * This class controls all user actions from ButtonPanel.
 *
 * @author Eric Beijer
 */
public class ButtonController implements ActionListener {

    private Game game;
    private SudokuPanel sudokuPanel;
    private ButtonPanel buttonPanel;

    /**
     * Constructor, sets game.
     *
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
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        switch (e.getActionCommand()) {
            case "Reset":
                game.newGame();
                break;
            case "Submit":
                game.checkGame();
                break;
            case "Exit":
                System.exit(0);
//            case "Hint":
//                game.setHelp(((JCheckBox) e.getSource()).isSelected());
//                break;
            case "Hint":
                sudokuPanel.setHint2(game);
                buttonPanel.setSteps2(game);
//                game.setHint2();
                break;
            case "Level 1":
                game.setLevel(70);
                game.newGame();
                break;
            case "Level 2":
                game.setLevel(60);
                game.newGame();
                break;
            case "Level 3":
                game.setLevel(50);
                game.newGame();
                break;
            case "Level 4":
                game.setLevel(40);
                game.newGame();
                break;
            case "Level 5":
                game.setLevel(30);
                game.newGame();
                break;
            default:
                game.setSelectedNumber(Integer.parseInt(e.getActionCommand()));
                break;
        }
    }
}
