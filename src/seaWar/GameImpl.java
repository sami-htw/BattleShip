package seaWar;

import seaWar.board.BoardException;
import seaWar.board.BoardViewAccess;
import seaWar.board.LocalBoard;
import seaWar.board.LocalBoardImpl;
import seaWar.board.RemoteBoard;
import seaWar.board.RemoteBoardImpl;
import seaWar.board.Shot;
import seaWar.board.ShotResults;

public class GameImpl implements Game, GameFactory, Shot {

  private RemoteBoardImpl remoteBoard;
  private LocalBoardImpl localBoard;

  private GameStatus status;
  private boolean hasWon = false;

  GameImpl() {
    this.status = GameStatus.PREPARE;
  }

  GameImpl(GameStatus status) {
    this.status = status;
  }

  public static Game createGame() {
    return new GameImpl();
  }

  @Override
  public RemoteBoard getRemoteBoard() {
    if (this.remoteBoard == null) {
      this.remoteBoard = new RemoteBoardImpl(this);
    }

    return this.remoteBoard;
  }

  @Override
  public BoardViewAccess getRemoteBoardAccessView() {
    // force object creation
    this.getRemoteBoard();
    return this.remoteBoard;
  }

  @Override
  public LocalBoard getLocalBoard() {
    if (this.localBoard == null) {
      this.localBoard = new LocalBoardImpl(this);
    }

    return this.localBoard;
  }

  @Override
  public BoardViewAccess getLocalBoardAccessView() {
    // force object creation
    this.getLocalBoard();
    return this.localBoard;
  }

  @Override
  public GameStatus getStatus() {
    return this.status;
  }

  @Override
  public void startGame() throws GameStatusException {
    if (this.status != GameStatus.PREPARE) {
      throw new GameStatusException("game already started or even finished");
    }

    // check if all ships are set
    if (!this.localBoard.allShipsSet()) {
      throw new GameStatusException("all ships must be set to start a game.");
    }

    this.status = GameStatus.PLAY_ACTIVE;
  }

  @Override
  public void giveUp() throws GameStatusException {
    if (this.status == GameStatus.END) {
      throw new GameStatusException("game already ended");
    }

    this.hasWon = false;
    this.status = GameStatus.END;
  }

  @Override
  public boolean won() throws GameStatusException {
    if (this.status != GameStatus.END) {
      throw new GameStatusException("game not yet ended");
    }

    return this.hasWon;
  }

  @Override
  public ShotResults shot(int column, int row) throws GameStatusException, BoardException {
    ShotResults shotResult = this.remoteBoard.shot(column, row);
    if (shotResult == ShotResults.HIT_WATER) {
      this.status = GameStatus.PLAY_PASSIVE;
    }

    return shotResult;
  }

  @Override
  public void setStatus(GameStatus status) {
    this.status = status;
  }

}
