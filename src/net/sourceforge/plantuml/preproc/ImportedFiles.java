/* ========================================================================
 * PlantUML : a free UML diagram generator
 * ========================================================================
 *
 * (C) Copyright 2009-2020, Arnaud Roques
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
 * Modified by: Nicolas Jouanin
 * 
 *
 */
package net.sourceforge.plantuml.preproc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.sourceforge.plantuml.AFile;
import net.sourceforge.plantuml.AFileRegular;
import net.sourceforge.plantuml.AFileZipEntry;
import net.sourceforge.plantuml.AParentFolder;
import net.sourceforge.plantuml.Log;
import net.sourceforge.plantuml.OptionFlags;
import net.sourceforge.plantuml.security.SFile;
import net.sourceforge.plantuml.security.SecurityUtils;

public class ImportedFiles {

	private final List<SFile> imported;
	private final AParentFolder currentDir;

	private ImportedFiles(List<SFile> imported, AParentFolder currentDir) {
		this.imported = imported;
		this.currentDir = currentDir;
	}

	public ImportedFiles withCurrentDir(AParentFolder newCurrentDir) {
		if (newCurrentDir == null) {
			return this;
		}
		return new ImportedFiles(imported, newCurrentDir);
	}

	public static ImportedFiles createImportedFiles(AParentFolder newCurrentDir) {
		return new ImportedFiles(new ArrayList<SFile>(), newCurrentDir);
	}

	@Override
	public String toString() {
		return "ImportedFiles=" + imported + " currentDir=" + currentDir;
	}

	public AFile getAFile(String nameOrPath) throws IOException {
		// Log.info("ImportedFiles::getAFile nameOrPath = " + nameOrPath);
		// Log.info("ImportedFiles::getAFile currentDir = " + currentDir);
		final AParentFolder dir = currentDir;
		if (dir == null || isAbsolute(nameOrPath)) {
			return new AFileRegular(new SFile(nameOrPath).getCanonicalFile());
		}
		// final File filecurrent = SecurityUtils.File(dir.getAbsoluteFile(),
		// nameOrPath);
		final AFile filecurrent = dir.getAFile(nameOrPath);
		Log.info("ImportedFiles::getAFile filecurrent = " + filecurrent);
		if (filecurrent != null && filecurrent.isOk()) {
			return filecurrent;
		}
		for (SFile d : getPath()) {
			if (d.isDirectory()) {
				final SFile file = d.file(nameOrPath);
				if (file.exists()) {
					return new AFileRegular(file.getCanonicalFile());
				}
			} else if (d.isFile()) {
				final AFileZipEntry zipEntry = new AFileZipEntry(d, nameOrPath);
				if (zipEntry.isOk()) {
					return zipEntry;
				}
			}
		}
		return filecurrent;
	}

	public List<SFile> getPath() {
		final List<SFile> result = new ArrayList<>(imported);
		result.addAll(includePath());
		result.addAll(SecurityUtils.getPath("java.class.path"));
		return result;
	}

	private List<SFile> includePath() {
		return SecurityUtils.getPath("plantuml.include.path");
	}

	private boolean isAbsolute(String nameOrPath) {
		final SFile f = new SFile(nameOrPath);
		return f.isAbsolute();
	}

	public void add(SFile file) {
		this.imported.add(file);
	}

	public AParentFolder getCurrentDir() {
		return currentDir;
	}

	public FileWithSuffix getFile(String filename, String suffix) throws IOException {
		final int idx = filename.indexOf('~');
		final AFile file;
		final String entry;
		if (idx == -1) {
			file = getAFile(filename);
			entry = null;
		} else {
			file = getAFile(filename.substring(0, idx));
			entry = filename.substring(idx + 1);
		}
		if (isAllowed(file) == false) {
			return FileWithSuffix.none();
		}
		return new FileWithSuffix(filename, suffix, file, entry);
	}

	private boolean isAllowed(AFile file) throws IOException {
		if (OptionFlags.ALLOW_INCLUDE) {
			return true;
		}
		if (file != null) {
			final SFile folder = file.getSystemFolder();
			// System.err.println("canonicalPath=" + path + " " + folder + " " +
			// INCLUDE_PATH);
			if (includePath().contains(folder)) {
				return true;
			}
		}
		return false;
	}

}
