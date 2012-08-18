package fly2.common;

public class Geometry {

	/**
	 * Проверяет пересечение прямоугольников
	 * @param x0 Координата x первого прямоугольника
	 * @param y0 Координата y первого прямоугольника
	 * @param width0 Ширина первого прямоугольника
	 * @param height0 Высота первого прямоугольника
	 * @param x1 Координата x второго прямоугольника
	 * @param y1 Координата y второго прямоугольника
	 * @param width1 Ширина второго прямоугольника
	 * @param height1 Высота второго прямоугольника
	 * @return true - прямоугольники пересекаются, false - не пересекаются
	 */
	public static boolean impactRect(
			double x0, double y0, double width0, double height0,
			double x1, double y1, double width1, double height1) {

		// сначала проверяются все точки второго прямоугольника на пересечение с первым
		// а потом наоборот
		
		return  impactPoint(x0, y0, width0, height0, x1, y1) ||
				impactPoint(x0, y0, width0, height0, x1 + width1, y1) ||
				impactPoint(x0, y0, width0, height0, x1, y1 + height1) ||
				impactPoint(x0, y0, width0, height0, x1 + width1, y1 + height1) ||

				impactPoint(x1, y1, width1, height1, x0, y0) ||
				impactPoint(x1, y1, width1, height1, x0 + width0, y0) ||
				impactPoint(x1, y1, width1, height1, x0, y0 + height0) ||
				impactPoint(x1, y1, width1, height1, x0 + width0, y0 + height0);
	}

	/**
	 * Проверяет полное попадание внутреннего прямоугольника (inner) в родительский (parent)
	 * @param parentX Координата x родительского прямоугольника
	 * @param parentY Координата y родительского прямоугольника
	 * @param parentWidth Ширина родительского прямоугольника
	 * @param parentHeight Высота родительского прямоугольника
	 * @param innerX Координата x внутреннего прямоугольника
	 * @param innerY Координата y внутреннего прямоугольника
	 * @param innerWidth Ширина внутреннего прямоугольника
	 * @param innerHeight Высота внутреннего прямоугольника
	 * @return true - внутренний прямоугольник полностью попадает в родительский, false - в противном случае
	 */
	public static boolean innerRect(
			double parentX, double parentY, double parentWidth, double parentHeight,
			double innerX, double innerY, double innerWidth, double innerHeight) {
		
		return  impactPoint(parentX, parentY, parentWidth, parentHeight, innerX, innerY) &&
				impactPoint(parentX, parentY, parentWidth, parentHeight, innerX + innerWidth, innerY) &&
				impactPoint(parentX, parentY, parentWidth, parentHeight, innerX, innerY + innerHeight) &&
				impactPoint(parentX, parentY, parentWidth, parentHeight, innerX + innerWidth, innerY + innerHeight);
	}
	
	/**
	 * Проверяет попадание точки в прямоугольник
	 * @param rectX Координата x прямоугольника
	 * @param rectY Координата y прямоугольника
	 * @param rectWidth Ширина прямоугольника
	 * @param rectHeight Высота прямоугольника
	 * @param pointX Координата x точки
	 * @param pointY Координата y точки
	 * @return true - точка попала в прямоугольник, false - не попала
	 */
	public static boolean impactPoint(
			double rectX, double rectY, double rectWidth, double rectHeight,
			double pointX, double pointY) {

		return  (rectX <= pointX) && (pointX <= rectX + rectWidth) &&
				(rectY <= pointY) && (pointY <= rectY + rectHeight);
	}
	
	/**
	 * Проверяет, попадает исходное значение в заданный интервал или нет.
	 * Обе граничные точки включаются в интервал [left; right].
	 * @param value Исходное значение
	 * @param left Левая граница интервала
	 * @param right Правая граница интервала
	 * @return true - значение попало в интервал, false - не попало
	 */
	public static boolean between(double value, double left, double right) {
		return (left <= value) && (value <= right);
	}
}
