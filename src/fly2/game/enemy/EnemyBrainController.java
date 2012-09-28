package fly2.game.enemy;

import fly2.game.frontend.Plane;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class EnemyBrainController {

	private EnemyBrainContainer brainContainer;
	private Collection<EnemyBrainContext> contextCollection;

	public EnemyBrainController() {
		this.brainContainer = new NullEnemyBrainContainer();
		this.contextCollection = new ArrayList<EnemyBrainContext>();
	}
	
	public EnemyBrainContainer getBrainContainer() {
		return brainContainer;
	}

	public void setBrainContainer(EnemyBrainContainer brainContainer) {
		if (brainContainer == null)
			throw new NullPointerException("brainContainer");
		
		this.brainContainer = brainContainer;
	}

	public Collection<EnemyBrainContext> getContextCollection() {
		return contextCollection;
	}

	public void setContextCollection(Collection<EnemyBrainContext> contextCollection) {
		if (contextCollection == null)
			throw new NullPointerException("contextCollection");

		this.contextCollection.addAll(contextCollection);
	}

	public void activate() {
		Iterator<EnemyBrainContext> contextIter = contextCollection.iterator();
		// бежим по всем контекстам
		while (contextIter.hasNext()) {
			EnemyBrainContext context = contextIter.next();
			// получем бота, которому принадлежит этот контекст
			Plane enemy = context.getEnemy();
			// если бот еще живой
			if (enemy.isDestroyed() == false) {
				// включаем ему мозги
				EnemyBrain brain = brainContainer.getBrain(enemy.getKind());
				brain.setContext(context);
				brain.activate();
			} else {
				// если бот уже дохлый - удаляем его контекст
				contextIter.remove();
			}
		}
	}
}
