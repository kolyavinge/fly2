package fly2.game.enemy;

import fly2.game.frontend.Plane;

public class NormalEnemyBrain implements EnemyBrain {

	private EnemyBrainContext context;

	public void setContext(EnemyBrainContext context) {
		if (context == null)
			throw new NullPointerException("context");
			
		this.context = context;
	}

	public StepResult activate() {
		if (context == null)
			throw new IllegalStateException("context not set");
		
		Plane player = context.getPlayer();
		Plane enemy = context.getEnemy();
		
		StepResult stepResult = new StepResult();
		
		double dx = player.getX() - enemy.getX();
		double dy = player.getY() - enemy.getY();
		if ((Math.abs(dx) <= enemy.getWidth() / 2) && (Math.abs(dy) <= 4 * enemy.getHeight())) {
			stepResult.fire();
		}

		return stepResult;
	}
}
