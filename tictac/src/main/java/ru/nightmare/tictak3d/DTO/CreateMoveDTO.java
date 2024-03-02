package ru.nightmare.tictak3d.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CreateMoveDTO {

    @NotNull
    String gameId;

    @NotNull
    int boardX;

    @NotNull
    int boardY;

    @NotNull
    int boardZ;
}
