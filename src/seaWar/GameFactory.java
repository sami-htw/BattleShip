package seaWar;

public interface GameFactory {
  static Game getGameInStatus(GameStatus status) {
    return new GameImpl(status);
  }

  static GameFactory getGameFactory(Game game) {
    if(game instanceof GameFactory) {
      return (GameFactory) game;
    }

    return null;
  }

  void setStatus(GameStatus status);
}
