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
package net.sourceforge.plantuml.skin.rose;

import net.sourceforge.plantuml.ISkinSimple;
import net.sourceforge.plantuml.LineBreakStrategy;
import net.sourceforge.plantuml.awt.geom.Dimension2D;
import net.sourceforge.plantuml.cucadiagram.Display;
import net.sourceforge.plantuml.graphic.StringBounder;
import net.sourceforge.plantuml.graphic.TextBlock;
import net.sourceforge.plantuml.skin.AbstractTextualComponent;
import net.sourceforge.plantuml.skin.Area;
import net.sourceforge.plantuml.style.PName;
import net.sourceforge.plantuml.style.Style;
import net.sourceforge.plantuml.ugraphic.UGraphic;
import net.sourceforge.plantuml.ugraphic.ULine;
import net.sourceforge.plantuml.ugraphic.URectangle;
import net.sourceforge.plantuml.ugraphic.UStroke;
import net.sourceforge.plantuml.ugraphic.UTranslate;
import net.sourceforge.plantuml.ugraphic.color.HColor;

public class ComponentRoseDivider extends AbstractTextualComponent {

	// private final int outMargin = 5;
	private final HColor borderColor;
	private final HColor background;
	private final boolean empty;

	private final double shadow;
	private final UStroke stroke;
	private final double roundCorner;

	public ComponentRoseDivider(Style style, Display stringsToDisplay, ISkinSimple spriteContainer) {
		super(style, LineBreakStrategy.NONE, 4, 4, 4, spriteContainer, stringsToDisplay, false);

		this.background = style.value(PName.BackGroundColor).asColor(spriteContainer.getThemeStyle(),
				getIHtmlColorSet());
		this.borderColor = style.value(PName.LineColor).asColor(spriteContainer.getThemeStyle(), getIHtmlColorSet());
		this.stroke = style.getStroke();
		this.roundCorner = style.value(PName.RoundCorner).asInt();
		this.shadow = style.value(PName.Shadowing).asDouble();

		this.empty = stringsToDisplay.get(0).length() == 0;
	}

	@Override
	protected void drawInternalU(UGraphic ug, Area area) {
		final Dimension2D dimensionToUse = area.getDimensionToUse();

		ug = ug.apply(background.bg());
		if (empty) {
			drawSep(ug.apply(UTranslate.dy(dimensionToUse.getHeight() / 2)), dimensionToUse.getWidth());
		} else {
			final TextBlock textBlock = getTextBlock();
			final StringBounder stringBounder = ug.getStringBounder();
			final double textWidth = getTextWidth(stringBounder);
			final double textHeight = getTextHeight(stringBounder);
			final double deltaX = 6;
			final double xpos = (dimensionToUse.getWidth() - textWidth - deltaX) / 2;
			final double ypos = (dimensionToUse.getHeight() - textHeight) / 2;

			drawSep(ug.apply(UTranslate.dy(dimensionToUse.getHeight() / 2)), dimensionToUse.getWidth());

			ug = ug.apply(borderColor);
			ug = ug.apply(stroke);
			final URectangle rect = new URectangle(textWidth + deltaX, textHeight).rounded(roundCorner);
			rect.setDeltaShadow(shadow);

			ug.apply(new UTranslate(xpos, ypos)).draw(rect);
			textBlock.drawU(ug.apply(new UTranslate(xpos + deltaX, ypos + getMarginY())));
		}
	}

	private void drawSep(UGraphic ug, double width) {
		ug = ug.apply(background);
		drawRectLong(ug.apply(UTranslate.dy(-1)), width);
		drawDoubleLine(ug, width);
	}

	private void drawRectLong(UGraphic ug, double width) {
		final URectangle rectLong = new URectangle(width, 3).rounded(roundCorner);
		rectLong.setDeltaShadow(shadow);

		ug = ug.apply(new UStroke());
		ug.draw(rectLong);
	}

	private void drawDoubleLine(UGraphic ug, final double width) {
		ug = ug.apply(new UStroke(stroke.getThickness() / 2)).apply(borderColor);
		final ULine line = ULine.hline(width);
		ug.apply(UTranslate.dy(-1)).draw(line);
		ug.apply(UTranslate.dy(2)).draw(line);
	}

	@Override
	public double getPreferredHeight(StringBounder stringBounder) {
		return getTextHeight(stringBounder) + 20;
	}

	@Override
	public double getPreferredWidth(StringBounder stringBounder) {
		return getTextWidth(stringBounder) + 30;
	}

}
