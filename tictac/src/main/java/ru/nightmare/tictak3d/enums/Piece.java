package ru.nightmare.tictak3d.enums;


public enum Piece {
    X{
        @Override
        public String toString(){
            return "X";
        }
    },
    O{
        @Override
        public String toString(){
            return "O";
        }
    }
}
