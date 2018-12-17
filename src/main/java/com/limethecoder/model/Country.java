package com.limethecoder.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

@Getter
@Setter
@RequiredArgsConstructor
public class Country {
    private int id;
    private final String name;
    private final Position lowerLeft;
    private final Position upperRight;
    private int finishDay = -1;

    public boolean isPositionValid() {
        return lowerLeft.isValidPosition() && upperRight.isValidPosition();
    }

    @Override
    public String toString() {
        return name + StringUtils.SPACE + finishDay;
    }
}
