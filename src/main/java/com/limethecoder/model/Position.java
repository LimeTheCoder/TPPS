package com.limethecoder.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.Wither;

@EqualsAndHashCode
@AllArgsConstructor
@Getter
@Wither
public class Position {
    private int x;
    private int y;

    public Position north() {
        return withY(y + 1);
    }

    public Position south() {
        return withY(y - 1);
    }

    public Position east() {
        return withX(x - 1);
    }

    public Position west() {
        return withX(x + 1);
    }

    public boolean isValidPosition() {
        return x >= ConstantsHolder.MIN_POS && x <= ConstantsHolder.MAX_POS && y >= ConstantsHolder.MIN_POS && y <= ConstantsHolder.MAX_POS;
    }
}
