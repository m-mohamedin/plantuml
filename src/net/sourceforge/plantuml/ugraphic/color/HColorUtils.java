/* ========================================================================
 * PlantUML : a free UML diagram generator
 * ========================================================================
 *
 * (C) Copyright 2009-2023, Arnaud Roques
 *
 * Project Info:  http://plantuml.com
 * 
 * If you like this project or if you find it useful, you can support us at:
 *
 * This file is part of PlantUML.
 *
 * PlantUML is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * PlantUML distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public
 * License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301,
 * USA.
 *
 *
 * Original Author:  Arnaud Roques
 * 
 *
 */
package net.sourceforge.plantuml.ugraphic.color;

import java.awt.Color;

import net.sourceforge.plantuml.ugraphic.UChange;
import net.sourceforge.plantuml.ugraphic.UGraphic;

public class HColorUtils {

	public static final HColorSimple BLACK;
	public static final HColorSimple WHITE;
	public static final HColor RED_LIGHT;
	public static final HColor RED_DARK;
	public static final HColor RED;
	public static final HColor GREEN;
	public static final HColor BLUE;
	public static final HColor GRAY;
	public static final HColor LIGHT_GRAY;
	public static final HColor MY_YELLOW;
	public static final HColor MY_RED;
	public static final HColor MY_GREEN;

	public static final HColor COL_C82930;
	public static final HColor COL_F24D5C;
	public static final HColor COL_1963A0;
	public static final HColor COL_4177AF;
	public static final HColor COL_B38D22;
	public static final HColor COL_FFFF44;
	public static final HColor COL_038048;
	public static final HColor COL_84BE84;
	public static final HColor COL_DDDDDD;
	public static final HColor COL_EEEEEE;
	public static final HColor COL_FBFB77;
	public static final HColor COL_ADD1B2;
	public static final HColor COL_A9DCDF;
	public static final HColor COL_E3664A;
	public static final HColor COL_EB937F;
	public static final HColor COL_B4A7E5;
	public static final HColor COL_527BC6;
	public static final HColor COL_D1DBEF;
	public static final HColor COL_D7E0F2;
	public static final HColor COL_989898;
	public static final HColor COL_BBBBBB;

	static {

		final HColorSet set = HColorSet.instance();

		BLACK = (HColorSimple) set.getColorOrWhite("black");
		WHITE = (HColorSimple) set.getColorOrWhite("white");
		RED_LIGHT = set.getColorOrWhite("#FEF6F3");
		RED_DARK = set.getColorOrWhite("#CD0A0A");
		RED = set.getColorOrWhite("#FF0000");
		GREEN = set.getColorOrWhite("#00FF00");
		BLUE = set.getColorOrWhite("#0000FF");
		GRAY = set.getColorOrWhite("#808080");
		LIGHT_GRAY = set.getColorOrWhite("#C0C0C0");
		MY_YELLOW = set.getColorOrWhite("#FEFECE");
		MY_RED = set.getColorOrWhite("#A80036");
		MY_GREEN = set.getColorOrWhite("#33FF02");

		COL_C82930 = set.getColorOrWhite("#C82930");
		COL_F24D5C = set.getColorOrWhite("#F24D5C");
		COL_1963A0 = set.getColorOrWhite("#1963A0");
		COL_4177AF = set.getColorOrWhite("#4177AF");
		COL_B38D22 = set.getColorOrWhite("#B38D22");
		COL_FFFF44 = set.getColorOrWhite("#FFFF44");
		COL_038048 = set.getColorOrWhite("#038048");
		COL_84BE84 = set.getColorOrWhite("#84BE84");
		COL_DDDDDD = set.getColorOrWhite("#DDDDDD");
		COL_EEEEEE = set.getColorOrWhite("#EEEEEE");
		COL_FBFB77 = set.getColorOrWhite("#FBFB77");
		COL_ADD1B2 = set.getColorOrWhite("#ADD1B2");
		COL_A9DCDF = set.getColorOrWhite("#A9DCDF");
		COL_E3664A = set.getColorOrWhite("#E3664A");
		COL_EB937F = set.getColorOrWhite("#EB937F");
		COL_B4A7E5 = set.getColorOrWhite("#B4A7E5");
		COL_527BC6 = set.getColorOrWhite("#527BC6");
		COL_D1DBEF = set.getColorOrWhite("#D1DBEF");
		COL_D7E0F2 = set.getColorOrWhite("#D7E0F2");
		COL_989898 = set.getColorOrWhite("#989898");
		COL_BBBBBB = set.getColorOrWhite("#BBBBBB");

	}

	public static HColor noGradient(HColor color) {
		if (color instanceof HColorGradient) {
			return ((HColorGradient) color).getColor1();
		}
		return color;
	}

	public static UChange changeBack(UGraphic ug) {
		final HColor color = ug.getParam().getColor();
		if (color == null) {
			return new HColorNone().bg();
		}
		return color.bg();
	}

	final private static HColorSimple TRANSPARENT = new HColorSimple(new Color(0, 0, 0, 0), false);

	public static HColor transparent() {
		return TRANSPARENT;
	}

	public static boolean isTransparent(HColor back) {
		if (back == TRANSPARENT) {
			return true;
		}
		if (back instanceof HColorBackground && ((HColorBackground) back).getBack() == TRANSPARENT) {
			return true;
		}
		if (back instanceof HColorSimple && ((HColorSimple) back).isTransparent()) {
			return true;
		}
		return false;
	}

	public static HColor unlinear(HColor color1, HColor color2, int completion) {
		if (completion == 0) {
			return color1;
		}
		if (completion == 100) {
			return color2;
		}
		if (color1 instanceof HColorSimple && color2 instanceof HColorSimple) {
			return HColorSimple.unlinear((HColorSimple) color1, (HColorSimple) color2, completion);
		}
		return color1;
	}

}
