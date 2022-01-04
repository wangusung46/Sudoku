package sudoku.model;

/**
 * Enumeration used to inform observers what to update.
 *
 * @author Joshua 2021080138
 */
public enum UpdateAction {
    NEW_GAME,
    CHECK,
    SELECTED_NUMBER,
    CANDIDATES,
    HELP,
    HELP2,
    STEPS
}