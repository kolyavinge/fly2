/*
 * Глобальные объекты:
 *   world      - игровой мир
 *   player     - самолет игрока
 *   enemy      - самолет текущего бота
 *   enemies    - все самолеты ботов (вместе с самолетом текущего бота)
 *   bullets    - все пули в игровом мире
 *   stepResult - результат хода текущего бота
 */

function main() {
	fire();
}

function fire() {
	var dx = player.getX() - enemy.getX();
	var dy = player.getY() - enemy.getY();
	if ((Math.abs(dx) <= enemy.getWidth() / 2) && (Math.abs(dy) <= 4 * enemy.getHeight())) {
		stepResult.fire();
	}
}

// запуск ыскуственного интилекта
main();
