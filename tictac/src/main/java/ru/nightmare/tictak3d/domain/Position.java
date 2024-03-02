package ru.nightmare.tictak3d.domain;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode

public class Position {
    int boardX;
    int boardY;
    int boardZ;

}
