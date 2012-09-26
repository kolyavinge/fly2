package fly2.game.enemy;

import fly2.game.frontend.Plane;

import java.util.ArrayList;
import java.util.Collection;

public class EnemyBrainController {

	private EnemyBrainContainer brainContainer;
	private Collection<EnemyBrainContext> contextCollection;

	public EnemyBrainController(EnemyBrainContainer brainContainer, Collection<EnemyBrainContext> contextCollection) {
		if (brainContainer == null)
			throw new NullPointerException("brainContainer");

		if (contextCollection == null)
			throw new NullPointerException("contextCollection");

		this.brainContainer = brainContainer;
		this.contextCollection = new ArrayList<EnemyBrainContext>(contextCollection);
	}

	public void activate() {
		for (EnemyBrainContext context : contextCollection) {
			activateContext(context);
		}
	}

	private void activateContext(EnemyBrainContext context) {
		Plane enemy = context.getEnemy();
		EnemyBrain brain = brainContainer.getBrain(enemy.getKind());
		brain.setContext(context);
		brain.activate();
	}
}
