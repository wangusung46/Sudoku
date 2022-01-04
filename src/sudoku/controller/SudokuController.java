package sudoku.controller;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import sudoku.model.Game;
import sudoku.model.UpdateAction;
import sudoku.view.ButtonPanel;
import sudoku.view.Field;
import sudoku.view.SudokuPanel;

/**
 * This class controls all user actions from SudokuPanel.
 *
 * @author Joshua 2021080138
 */
public class SudokuController implements MouseListener {
    private final SudokuPanel sudokuPanel;    // Panel to control.
    private final ButtonPanel buttonPanel;    // Panel to control.
    private final Game game;                  // Current Sudoku game.

    /**
     * Constructor, sets game.
     *
     * @param sudokuPanel
     * @param buttonPanel
     * @param game  Game to be set.
     */
    public SudokuController(SudokuPanel sudokuPanel, ButtonPanel buttonPanel, Game game) {
        this.sudokuPanel = sudokuPanel;
        this.buttonPanel = buttonPanel;
        this.game = game;
    }

    /**
     * Recovers if user clicked field in game. If so it sets the selected number
     * at clicked position in game and updates clicked field. If user clicked a
     * field and used left mouse button, number at clicked position will be
     * cleared in game and clicked field will be updated.
     *
     * @param e MouseEvent.
     */
    @Override
    public void mousePressed(MouseEvent e) {
        JPanel panel = (JPanel)e.getSource();
        Component component = panel.getComponentAt(e.getPoint());
        if (component instanceof Field) {
            Field field = (Field)component;
            int x = field.getFieldX();
            int y = field.getFieldY();

            if (e.getButton() == MouseEvent.BUTTON1 && (game.getNumber(x, y) == 0 || field.getForeground().equals(Color.BLUE))) {
                int number = game.getSelectedNumber();
                if (number == -1) {
                    return;
                }
                game.setNumber(x, y, number);
                field.setNumber(number, true);
            } else if (e.getButton() == MouseEvent.BUTTON3 && !field.getForeground().equals(Color.BLACK)) {
                game.setNumber(x, y, 0);
                field.setNumber(0, false);
            }
            sudokuPanel.update(game, UpdateAction.CANDIDATES);
            buttonPanel.update(game, UpdateAction.CANDIDATES);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) { }
    @Override
    public void mouseEntered(MouseEvent e) { }
    @Override
    public void mouseExited(MouseEvent e) { }
    @Override
    public void mouseReleased(MouseEvent e) { }
}