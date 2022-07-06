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
package net.sourceforge.plantuml.api;

import net.sourceforge.plantuml.awt.geom.Dimension2D;

import net.sourceforge.plantuml.CMapData;
import net.sourceforge.plantuml.annotation.HaxeIgnored;

public class ImageDataComplex extends ImageDataAbstract {

	private final CMapData cmap;
	private final String warningOrError;

	@SuppressWarnings("unused")  // available publicly so retained for backwards compatibility
	@HaxeIgnored
	public ImageDataComplex(Dimension2D info, CMapData cmap, String warningOrError) {
		super(info);
		this.cmap = cmap;
		this.warningOrError = warningOrError;
	}

	public ImageDataComplex(Dimension2D info, CMapData cmap, String warningOrError, int status) {
		super(info);
		this.cmap = cmap;
		this.warningOrError = warningOrError;
		setStatus(status);
	}

	public boolean containsCMapData() {
		return cmap != null && cmap.containsData();
	}

	public String getCMapData(String nameId) {
		return cmap.asString(nameId);
	}

	public String getWarningOrError() {
		return warningOrError;
	}

}
