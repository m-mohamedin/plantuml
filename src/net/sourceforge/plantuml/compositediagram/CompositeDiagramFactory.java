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
package net.sourceforge.plantuml.compositediagram;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.plantuml.ISkinSimple;
import net.sourceforge.plantuml.api.ThemeStyle;
import net.sourceforge.plantuml.command.Command;
import net.sourceforge.plantuml.command.CommonCommands;
import net.sourceforge.plantuml.command.PSystemCommandFactory;
import net.sourceforge.plantuml.compositediagram.command.CommandCreateBlock;
import net.sourceforge.plantuml.compositediagram.command.CommandCreatePackageBlock;
import net.sourceforge.plantuml.compositediagram.command.CommandEndPackageBlock;
import net.sourceforge.plantuml.compositediagram.command.CommandLinkBlock;
import net.sourceforge.plantuml.core.UmlSource;

public class CompositeDiagramFactory extends PSystemCommandFactory {

	private final ISkinSimple skinParam;

	public CompositeDiagramFactory(ISkinSimple skinParam) {
		this.skinParam = skinParam;
	}

	@Override
	protected List<Command> createCommands() {
		final List<Command> cmds = new ArrayList<>();
		cmds.add(new CommandCreateBlock());
		cmds.add(new CommandLinkBlock());
		cmds.add(new CommandCreatePackageBlock());
		cmds.add(new CommandEndPackageBlock());
		CommonCommands.addCommonCommands1(cmds);

		return cmds;
	}

	@Override
	public CompositeDiagram createEmptyDiagram(ThemeStyle style, UmlSource source, ISkinSimple skinParam) {
		return new CompositeDiagram(style, source, skinParam);
	}
}
