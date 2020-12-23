package seaWar.board;


import seaWar.Game;
import seaWar.GameStatus;
import seaWar.GameStatusException;

public class RemoteBoardImpl extends BoardImpl implements RemoteBoard {
  public RemoteBoardImpl(Game game) {
    super(game, FieldStatus.UNKNOWN);
  }

  @Override
  public ShotResults shot(int column, int row) throws GameStatusException,
      BoardException {

    if(this.game.getStatus() != GameStatus.PLAY_ACTIVE) {
      throw new GameStatusException("cannot fire - not active player now");
    }

    return super.shot(column, row);
  }
}
