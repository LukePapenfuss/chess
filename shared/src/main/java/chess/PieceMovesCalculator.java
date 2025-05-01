package chess;

import java.util.ArrayList;

public interface PieceMovesCalculator {
    public ArrayList<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition);
}

class KingMovesCalculator implements PieceMovesCalculator {
    public ArrayList<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        ArrayList<ChessMove> possibleMoves = new ArrayList<>();

        // Find all adjacent spaces by adding these to current position
        int[][] adjacent = {
                {-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}
        };

        // Loop through all adjacent spaces
        for (int i = 0; i < adjacent.length; ++i) {
            // Create a new position for each adjacent space
            ChessPosition newPosition = new ChessPosition(myPosition.getRow() + adjacent[i][0], myPosition.getColumn() + adjacent[i][1]);

            // If the space is outside the chess board, skip it
            if (!newPosition.insideBoard()) { continue; }

            // If the space is occupied by a piece of the same color, skip it
            if (board.getPiece(newPosition) != null && board.getPiece(myPosition).getTeamColor() == board.getPiece(newPosition).getTeamColor()) { continue; }

            // Add this new position to the possible moves
            possibleMoves.add(new ChessMove(myPosition, newPosition, null));
        }

        // Castling
        if(!board.getPiece(myPosition).ifMoved()) {
            // If the king hasn't moved, check if the rooks haven't moved.
            ChessPiece leftCorner = board.getPiece(new ChessPosition(myPosition.getRow(), 1));
            if(leftCorner != null && leftCorner.getPieceType() == ChessPiece.PieceType.ROOK && !leftCorner.ifMoved()) {

                // TODO: Add a move for queen-side castling

            }

            ChessPiece rightCorner = board.getPiece(new ChessPosition(myPosition.getRow(), 8));
            if(rightCorner != null && rightCorner.getPieceType() == ChessPiece.PieceType.ROOK && !rightCorner.ifMoved()) {

                // TODO: Add a move for king-side castling

            }

        }

        return possibleMoves;
    }
}

class BishopMovesCalculator implements PieceMovesCalculator {
    public ArrayList<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        ArrayList<ChessMove> possibleMoves = new ArrayList<>();

        int[][] directions = {
                {1,1},{-1,1},{-1,-1},{1,-1}
        };

        for (int i = 0; i < directions.length; ++i) {
            ChessPosition newPosition = new ChessPosition(myPosition.getRow(), myPosition.getColumn());
            boolean capturing = false;
            do {
                // Get Position NE one square
                newPosition = new ChessPosition(newPosition.getRow() + directions[i][0], newPosition.getColumn() + directions[i][1]);

                // If the space is outside the chess board, end the diagonal
                if (!newPosition.insideBoard()) { break; }

                // If the space is occupied by a piece of the same color, end the diagonal
                if (board.getPiece(newPosition) != null) {
                    if (board.getPiece(myPosition).getTeamColor() == board.getPiece(newPosition).getTeamColor()) {
                        break;
                    } else {
                        capturing = true;
                    }
                }

                // Add this new position to the possible moves
                possibleMoves.add(new ChessMove(myPosition, newPosition, null));
            } while ( !capturing );
        }

        return possibleMoves;
    }
}

class RookMovesCalculator implements PieceMovesCalculator {
    public ArrayList<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        ArrayList<ChessMove> possibleMoves = new ArrayList<>();

        int[][] directions = {
                {1,0},{-1,0},{0,1},{0,-1}
        };

        for (int i = 0; i < directions.length; ++i) {
            ChessPosition newPosition = new ChessPosition(myPosition.getRow(), myPosition.getColumn());
            boolean capturing = false;
            do {
                // Get Position NE one square
                newPosition = new ChessPosition(newPosition.getRow() + directions[i][0], newPosition.getColumn() + directions[i][1]);

                // If the space is outside the chess board, end the diagonal
                if (!newPosition.insideBoard()) { break; }

                // If the space is occupied by a piece of the same color, end the diagonal
                if (board.getPiece(newPosition) != null) {
                    if (board.getPiece(myPosition).getTeamColor() == board.getPiece(newPosition).getTeamColor()) {
                        break;
                    } else {
                        capturing = true;
                    }
                }

                // Add this new position to the possible moves
                possibleMoves.add(new ChessMove(myPosition, newPosition, null));
            } while ( !capturing );
        }

        return possibleMoves;
    }
}

class QueenMovesCalculator implements PieceMovesCalculator {
    public ArrayList<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        ArrayList<ChessMove> possibleMoves = new ArrayList<>();

        int[][] directions = {
                {1,0},{-1,0},{0,1},{0,-1},{1,1},{-1,1},{-1,-1},{1,-1}
        };

        for (int i = 0; i < directions.length; ++i) {
            ChessPosition newPosition = new ChessPosition(myPosition.getRow(), myPosition.getColumn());
            boolean capturing = false;
            do {
                // Get Position NE one square
                newPosition = new ChessPosition(newPosition.getRow() + directions[i][0], newPosition.getColumn() + directions[i][1]);

                // If the space is outside the chess board, end the diagonal
                if (!newPosition.insideBoard()) { break; }

                // If the space is occupied by a piece of the same color, end the diagonal
                if (board.getPiece(newPosition) != null) {
                    if (board.getPiece(myPosition).getTeamColor() == board.getPiece(newPosition).getTeamColor()) {
                        break;
                    } else {
                        capturing = true;
                    }
                }

                // Add this new position to the possible moves
                possibleMoves.add(new ChessMove(myPosition, newPosition, null));
            } while ( !capturing );
        }

        return possibleMoves;
    }
}

class KnightMovesCalculator implements PieceMovesCalculator {
    public ArrayList<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        ArrayList<ChessMove> possibleMoves = new ArrayList<>();

        int[][] jumps = {
                {1,2},{1,-2},{-1,2},{-1,-2},{2,1},{2,-1},{-2,1},{-2,-1}
        };

        for (int i = 0; i < jumps.length; ++i) {
            // Create a new position for each adjacent space
            ChessPosition newPosition = new ChessPosition(myPosition.getRow() + jumps[i][0], myPosition.getColumn() + jumps[i][1]);

            // If the space is outside the chess board, skip it
            if (!newPosition.insideBoard()) { continue; }

            // If the space is occupied by a piece of the same color, skip it
            if (board.getPiece(newPosition) != null && board.getPiece(myPosition).getTeamColor() == board.getPiece(newPosition).getTeamColor()) { continue; }

            // Add this new position to the possible moves
            possibleMoves.add(new ChessMove(myPosition, newPosition, null));
        }

        return possibleMoves;
    }
}

class PawnMovesCalculator implements PieceMovesCalculator {
    public ArrayList<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        ArrayList<ChessMove> possibleMoves = new ArrayList<>();

        // Determine forward direction
        int forward = board.getPiece(myPosition).getTeamColor() == ChessGame.TeamColor.WHITE ? 1 : -1;

        // Move forward one space if unoccupied
        ChessPosition forwardPosition = new ChessPosition(myPosition.getRow() + forward, myPosition.getColumn());
        if (forwardPosition.insideBoard() && board.getPiece(forwardPosition) == null) {
            // Add this new position to the possible moves
            possibleMoves.add(new ChessMove(myPosition, forwardPosition, null));
        }

        // Move forward two spaces if possible
        ChessPosition forward2Position = new ChessPosition(myPosition.getRow() + 2*forward, myPosition.getColumn());
        if (myPosition.getRow() == (forward == 1 ? 2 : 7) && forward2Position.insideBoard() && board.getPiece(forwardPosition) == null && board.getPiece(forward2Position) == null) {
            // Add this new position to the possible moves
            possibleMoves.add(new ChessMove(myPosition, forward2Position, null));
        }

        // Diagonal Attacking
        ChessPosition leftAttackPosition = new ChessPosition(myPosition.getRow() + forward, myPosition.getColumn() - 1);
        ChessPosition rightAttackPosition = new ChessPosition(myPosition.getRow() + forward, myPosition.getColumn() + 1);
        if (leftAttackPosition.insideBoard() && board.getPiece(leftAttackPosition) != null && board.getPiece(myPosition).getTeamColor() != board.getPiece(leftAttackPosition).getTeamColor()) {
            // Add this new position to the possible moves
            possibleMoves.add(new ChessMove(myPosition, leftAttackPosition, null));
        }
        if (rightAttackPosition.insideBoard() && board.getPiece(rightAttackPosition) != null && board.getPiece(myPosition).getTeamColor() != board.getPiece(rightAttackPosition).getTeamColor()) {
            // Add this new position to the possible moves
            possibleMoves.add(new ChessMove(myPosition, rightAttackPosition, null));
        }

        // En Passant to the left
        ChessPosition leftAdj = new ChessPosition(myPosition.getRow(), myPosition.getColumn()-1);
        ChessPiece leftAdjPiece = board.getPiece(leftAdj);
        if (leftAdj.insideBoard() && myPosition.getRow() == (forward == 1 ? 6 : 4) && leftAdjPiece.getPieceType() == ChessPiece.PieceType.PAWN
                && leftAdjPiece.getTeamColor() != board.getPiece(myPosition).getTeamColor() && leftAdjPiece.isEnPassantable()) {

            // TODO: Add the move for en passant to the left

        }

        // En Passant to the right
        ChessPosition rightAdj = new ChessPosition(myPosition.getRow(), myPosition.getColumn()+1);
        ChessPiece rightAdjPiece = board.getPiece(rightAdj);
        if (leftAdj.insideBoard() && myPosition.getRow() == (forward == 1 ? 6 : 4) && rightAdjPiece.getPieceType() == ChessPiece.PieceType.PAWN
                && rightAdjPiece.getTeamColor() != board.getPiece(myPosition).getTeamColor() && rightAdjPiece.isEnPassantable()) {

            // TODO: Add the move for en passant to the right

        }


            // Promotion
        ArrayList<ChessMove> newPossibleMoves = new ArrayList<>();
        for (int i = 0; i < possibleMoves.toArray().length; ++i) {
            if(possibleMoves.get(i).getEndPosition().getRow() == (forward == 1 ? 8 : 1)) {
                newPossibleMoves.add(new ChessMove(possibleMoves.get(i).getStartPosition(), possibleMoves.get(i).getEndPosition(), ChessPiece.PieceType.QUEEN));
                newPossibleMoves.add(new ChessMove(possibleMoves.get(i).getStartPosition(), possibleMoves.get(i).getEndPosition(), ChessPiece.PieceType.ROOK));
                newPossibleMoves.add(new ChessMove(possibleMoves.get(i).getStartPosition(), possibleMoves.get(i).getEndPosition(), ChessPiece.PieceType.BISHOP));
                newPossibleMoves.add(new ChessMove(possibleMoves.get(i).getStartPosition(), possibleMoves.get(i).getEndPosition(), ChessPiece.PieceType.KNIGHT));
            } else {
                newPossibleMoves.add(possibleMoves.get(i));
            }
        }

        return newPossibleMoves;
    }
}
