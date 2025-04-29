package chess;

import java.util.Objects;

/**
 * A chessboard that can hold and rearrange chess pieces.
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessBoard {

    private ChessPiece[][] squares = new ChessPiece[8][8];

    public ChessBoard() {
        
    }

    /**
     * Generates a hashCode for the chess board
     *
     * @return A hashCode for the chess board
     */
    @Override
    public int hashCode() {
        int code = 0;

        for (int i = 0; i < squares.length; ++i) {
            for (int j = 0; j < squares[i].length; ++j) {
                code += (squares[i][j] != null ? squares[i][j].hashCode() : 71 ) * (i + 1) * (j + 1);
            }
        }

        return code;
    }

    /**
     * Determines whether two boards are identical
     *
     * @param obj The board to compare with
     * @return True if they are identical boards
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ChessBoard that = (ChessBoard) obj;

        // Check if all pieces are the same
        for (int i = 0; i < squares.length; ++i) {
            for (int j = 0; j < squares[i].length; ++j) {
                if (squares[i][j] == null && that.getPiece(new ChessPosition(i+1,j+1)) != null) {
                    return false;
                }

                if (squares[i][j] != null && that.getPiece(new ChessPosition(i+1,j+1)) == null) {
                    return false;
                }

                if (squares[i][j] == null && that.getPiece(new ChessPosition(i+1,j+1)) == null) {
                    continue;
                }

                if (!squares[i][j].equals(that.getPiece(new ChessPosition(i+1,j+1)))) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Outputs a string showing the board
     *
     * @return String representation of the board
     */
    @Override
    public String toString() {
        String str = "";

        for (int i = 0; i < squares.length; ++i) {
            str += "|";
            for (int j = 0; j < squares[i].length; ++j) {
                // Add empty space if null
                if (squares[i][j] == null) {
                    str += " |";
                    continue;
                }

                // Find string representation of piece
                String pieceStr = "";
                switch (squares[i][j].getPieceType()) {
                    case KING -> pieceStr = "K";
                    case QUEEN -> pieceStr = "Q";
                    case ROOK -> pieceStr = "R";
                    case BISHOP -> pieceStr = "B";
                    case KNIGHT -> pieceStr = "N";
                    case PAWN -> pieceStr = "P";
                }

                // Convert to lowercase if it is a black piece
                if (squares[i][j].getTeamColor() == ChessGame.TeamColor.BLACK) { pieceStr = pieceStr.toLowerCase(); }

                // Add piece to string
                str += pieceStr + "|";
            }
            str += "\n";
        }

        return str;
    }

    /**
     * Adds a chess piece to the chessboard
     *
     * @param position where to add the piece to
     * @param piece    the piece to add
     */
    public void addPiece(ChessPosition position, ChessPiece piece) {
        squares[position.getRow()-1][position.getColumn()-1] = piece;
    }

    /**
     * Gets a chess piece on the chessboard
     *
     * @param position The position to get the piece from
     * @return Either the piece at the position, or null if no piece is at that
     * position
     */
    public ChessPiece getPiece(ChessPosition position) {
        return squares[position.getRow()-1][position.getColumn()-1];
    }

    /**
     * Sets the board to the default starting board
     * (How the game of chess normally starts)
     */
    public void resetBoard() {
        // Add all White Pawns
        for (int i = 0; i < squares[1].length; ++i) {
            // Create a Pawn
            ChessPiece newPawn = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);

            // Add the Pawn to the board
            addPiece(new ChessPosition(2, i + 1), newPawn);
        }

        // Add all Black Pawns
        for (int i = 0; i < squares[1].length; ++i) {
            // Create a Pawn
            ChessPiece newPawn = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN);

            // Add the Pawn to the board
            addPiece(new ChessPosition(7, i + 1), newPawn);
        }

        ChessPiece.PieceType[] backRank = {ChessPiece.PieceType.ROOK, ChessPiece.PieceType.KNIGHT,
                ChessPiece.PieceType.BISHOP, ChessPiece.PieceType.QUEEN,ChessPiece.PieceType.KING,
                ChessPiece.PieceType.BISHOP, ChessPiece.PieceType.KNIGHT, ChessPiece.PieceType.ROOK};

        // Set up White's back rank
        for (int i = 0; i < backRank.length; ++i) {
            ChessPiece newPiece = new ChessPiece(ChessGame.TeamColor.WHITE, backRank[i]);
            addPiece(new ChessPosition(1, i + 1), newPiece);
        }

        // Set up Black's back rank
        for (int i = 0; i < backRank.length; ++i) {
            ChessPiece newPiece = new ChessPiece(ChessGame.TeamColor.BLACK, backRank[i]);
            addPiece(new ChessPosition(8, i + 1), newPiece);
        }
    }
}
