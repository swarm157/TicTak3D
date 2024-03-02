package ru.nightmare.tictak3d.services;

import ru.nightmare.tictak3d.domain.Position;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PathStatus {
    private int friendly=0;
    private int neutral=0;
    private int enemy=0;
    private int[][][] board;
    private Position first;
    private Position second;
    private Position third;

    public PathStatus(int[][][] board, Position first,Position second,Position third){
        this.board = board;
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public void evaluate(){
        if(board[first.getBoardX()][first.getBoardY()][first.getBoardZ()] == 1){
            this.friendly++;
        }
        if(board[first.getBoardX()][first.getBoardY()][first.getBoardZ()] == 0){
            this.neutral++;
        }
        if(board[first.getBoardX()][first.getBoardY()][first.getBoardZ()] == 2){
            this.enemy++;
        }
        if(board[second.getBoardX()][second.getBoardY()][second.getBoardZ()] == 1){
            this.friendly++;
        }
        if(board[second.getBoardX()][second.getBoardY()][second.getBoardZ()] == 0){
            this.neutral++;
        }
        if(board[second.getBoardX()][second.getBoardY()][second.getBoardZ()] == 2){
            this.enemy++;
        }
        if(board[third.getBoardX()][third.getBoardY()][third.getBoardZ()] == 1){
            this.friendly++;
        }
        if(board[third.getBoardX()][third.getBoardY()][third.getBoardZ()] == 0){
            this.neutral++;
        }
        if(board[third.getBoardX()][third.getBoardY()][third.getBoardZ()] == 2){
            this.enemy++;
        }
    }
}
