public enum UserAction {
    ADD_EXPENSE(new Action(1 , "Add a new Expense")),
    VIEW_EXPENSES(new Action(2 , "View all expenses")),
    VIEW_MONTHLY_SUMMARY(new Action(3 , "View monthly summary")),
    EXPORT_TO_TXT(new Action(4 , "Export to a text file")),
    EXIT(new Action(5 , "Exit"))
    ;

    private final Action action ;
    UserAction(Action action){this.action=action;}

    public Action getAction(){return action;}

}

record Action(int code , String message){}
