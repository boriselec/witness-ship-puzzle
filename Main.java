public class Main {
    private static int count = 0;

    public static void main(String[] args) {
        find(Field.empty());
    }

    private static void find(Field field) {
        if (checkWithDotsSameColor(field)) {
            System.out.println(++count);
            System.out.println(field);
        }
        if (field.canDown()) {
            find(field.copy().down());
        }
        if (field.canLeft()) {
            find(field.copy().left());
        }
        if (field.canRight()) {
            find(field.copy().right());
        }
        if (field.canUp()) {
            find(field.copy().up());
        }
    }

    private static boolean check(Field field) {
        return (field.i == 0 && field.j == 6) || (field.j == 0 && field.i == 6);
    }

    private static boolean checkWithDots(Field field) {
        if (!check(field)) {
            return false;
        }
        return
            (field.cells[1][0].state != Field.CellState.EMPTY)
                && (field.cells[0][4].state != Field.CellState.EMPTY)
                && (field.cells[3][4].state != Field.CellState.EMPTY)
                && (field.cells[1][5].state != Field.CellState.EMPTY)
                && (field.cells[5][5].state != Field.CellState.EMPTY)
                && (field.cells[6][1].state != Field.CellState.EMPTY)
                && (field.cells[2][2].to == Field.Direction.UP || field.cells[2][2].from == Field.Direction.UP)
            ;
    }

    private static boolean checkWithDotsSameColor(Field field) {
        if (!checkWithDots(field)) {
            return false;
        }
        return
            (
                (
                    (field.cells[1][5].state == Field.CellState.THIS)
                        && (field.cells[3][4].state == Field.CellState.THIS)
                        && (field.cells[5][5].state == Field.CellState.THIS)
                )
                    ||
                    (
                        (field.cells[1][5].state == Field.CellState.OTHER)
                            && (field.cells[3][4].state == Field.CellState.OTHER)
                            && (field.cells[5][5].state == Field.CellState.OTHER)
                    )
            )
                &&
            (
                (

                    (field.cells[1][0].state == Field.CellState.THIS)
                        && (field.cells[2][2].state == Field.CellState.THIS)
                )
                    ||
                    (
                        (field.cells[1][0].state == Field.CellState.OTHER)
                            && (field.cells[2][2].state == Field.CellState.OTHER)
                    )
            )
            ;
    }

}
