package seaWar.board;

import seaWar.Game;

/**
 * This interface can help wit remoteBoard Testing
 */
public interface RemoteBoard extends Board {
    static RemoteBoard getBoard(Game game){
      return new RemoteBoardImpl(game);
    }
}
