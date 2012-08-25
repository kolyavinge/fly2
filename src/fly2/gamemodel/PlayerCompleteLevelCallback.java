package fly2.gamemodel;

/**
 * Вызывается, когда самолетик игрока прилетает к концу мира, 
 * кароче, когда заканчивается раунд.
 */
public interface PlayerCompleteLevelCallback {

	void execute();
}
