package ru.nightmare.tictak3d.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class CreateMoveResponseDTO {
    private String message;
    private int[][][] gameBoard;

    public CreateMoveResponseDTO(int[][][] gameBoard){
        this.gameBoard = gameBoard;
    }

}
