class Field {
    enum CellState {
        THIS,
        OTHER,
        EMPTY,
    }

    enum Direction {
        LEFT,
        UP,
        RIGHT,
        DOWN,
        NONE,
    }

    Cell[][] cells;
    int i;
    int j;

    static Field empty() {
        Field field = new Field();
        field.cells = new Cell[7][7];
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                field.cells[i][j] = new Cell(CellState.EMPTY, Direction.NONE, Direction.NONE);
            }
        }
        field.cells[0][0] = new Cell(CellState.THIS, Direction.NONE, Direction.NONE);
        field.cells[6][6] = new Cell(CellState.OTHER, Direction.NONE, Direction.NONE);
        field.i = 0;
        field.j = 0;
        return field;
    }

    Field copy() {
        Field field = new Field();
        field.cells = new Cell[7][7];
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                Cell fromCell = this.cells[i][j];
                field.cells[i][j] = new Cell(fromCell.state, fromCell.from, fromCell.to);
            }
        }
        field.i = this.i;
        field.j = this.j;
        return field;
    }

    boolean canUp() {
        if (j == 6) {
            return false;
        }
        if (cells[i][j + 1].state != CellState.EMPTY) {
            return false;
        }
        if (i == 3 && j + 1 == 3) {
            return false;
        }
        return true;
    }

    Field up() {
        cells[i][j].to = Direction.UP;
        cells[6 - i][6 - j].to = Direction.DOWN;
        j++;
        cells[i][j] = new Cell(CellState.THIS, Direction.DOWN, Direction.NONE);
        cells[6 - i][6 - j] = new Cell(CellState.OTHER, Direction.UP, Direction.NONE);
        return this;
    }

    boolean canDown() {
        if (j == 0) {
            return false;
        }
        if (cells[i][j - 1].state != CellState.EMPTY) {
            return false;
        }
        if (i == 3 && j - 1 == 3) {
            return false;
        }
        return true;
    }

    Field down() {
        cells[i][j].to = Direction.DOWN;
        cells[6 - i][6 - j].to = Direction.UP;
        j--;
        cells[i][j] = new Cell(CellState.THIS, Direction.UP, Direction.NONE);
        cells[6 - i][6 - j] = new Cell(CellState.OTHER, Direction.DOWN, Direction.NONE);
        return this;
    }

    boolean canLeft() {
        if (i == 0) {
            return false;
        }
        if (cells[i - 1][j].state != CellState.EMPTY) {
            return false;
        }
        if (i - 1 == 3 && j == 3) {
            return false;
        }
        return true;
    }

    Field left() {
        cells[i][j].to = Direction.LEFT;
        cells[6 - i][6 - j].to = Direction.RIGHT;
        i--;
        cells[i][j] = new Cell(CellState.THIS, Direction.RIGHT, Direction.NONE);
        cells[6 - i][6 - j] = new Cell(CellState.OTHER, Direction.LEFT, Direction.NONE);
        return this;
    }

    boolean canRight() {
        if (i == 6) {
            return false;
        }
        if (cells[i + 1][j].state != CellState.EMPTY) {
            return false;
        }
        if (i + 1 == 3 && j == 3) {
            return false;
        }
        return true;
    }

    Field right() {
        cells[i][j].to = Direction.RIGHT;
        cells[6 - i][6 - j].to = Direction.LEFT;
        i++;
        cells[i][j] = new Cell(CellState.THIS, Direction.LEFT, Direction.NONE);
        cells[6 - i][6 - j] = new Cell(CellState.OTHER, Direction.RIGHT, Direction.NONE);
        return this;
    }

    public String toString() {
        StringBuilder view = new StringBuilder();
        for (int j = 6; j >= 0; j--) {
            for (int i = 0; i < 7; i++) {
                view.append(cells[i][j].toString());
            }
            view.append('\n');
        }
        return view.toString();
    }

    static class Cell {
        CellState state;
        Direction from;
        Direction to;

        public Cell(CellState state, Direction from, Direction to) {
            this.state = state;
            this.from = from;
            this.to = to;
        }

        @Override
        public String toString() {
            switch (state) {
                case THIS:
                case OTHER:
                    switch (from) {
                        case LEFT:
                            switch (to) {
                                case UP:
                                    return "┛";
                                case RIGHT:
                                    return "━";
                                case DOWN:
                                    return "┓";
                                case NONE:
                                    return "╸";
                            }
                        case UP:
                            switch (to) {
                                case LEFT:
                                    return "┛";
                                case RIGHT:
                                    return "┗";
                                case DOWN:
                                    return "┃";
                                case NONE:
                                    return "╹";
                            }
                        case RIGHT:
                            switch (to) {
                                case LEFT:
                                    return "━";
                                case UP:
                                    return "┗";
                                case DOWN:
                                    return "┏";
                                case NONE:
                                    return "╺";
                            }
                        case DOWN:
                            switch (to) {
                                case LEFT:
                                    return "┓";
                                case UP:
                                    return "┃";
                                case RIGHT:
                                    return "┏";
                                case NONE:
                                    return "╻";
                            }
                        case NONE:
                            switch (to) {
                                case LEFT:
                                    return "╸";
                                case UP:
                                    return "╹";
                                case RIGHT:
                                    return "╺";
                                case DOWN:
                                    return "╻";
                                case NONE:
                                    return "X";
                            }
                    }
                case EMPTY:
                    return " ";
            }
            return "?";
        }
    }
}
