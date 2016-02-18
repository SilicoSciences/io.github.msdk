/*
 * (C) Copyright 2015-2016 by MSDK Development Team
 *
 * This software is dual-licensed under either
 *
 * (a) the terms of the GNU Lesser General Public License version 2.1
 * as published by the Free Software Foundation
 *
 * or (per the licensee's choosing)
 *
 * (b) the terms of the Eclipse Public License v1.0 as published by
 * the Eclipse Foundation.
 */

package io.github.msdk.io.mzml;

import java.io.File;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.common.collect.ImmutableList;

import io.github.msdk.datamodel.chromatograms.Chromatogram;
import io.github.msdk.datamodel.featuretables.Sample;
import io.github.msdk.datamodel.files.FileType;
import io.github.msdk.datamodel.rawdata.MsFunction;
import io.github.msdk.datamodel.rawdata.MsScan;
import io.github.msdk.datamodel.rawdata.RawDataFile;
import uk.ac.ebi.jmzml.xml.io.MzMLUnmarshaller;

class MzMLRawDataFile implements RawDataFile {

    private static final @Nonnull FileType fileType = FileType.MZML;

    private final @Nonnull List<Chromatogram> chromatograms;
    private final @Nonnull List<MsFunction> msFunctions;

    private final @Nonnull List<MsScan> msScans;
    private @Nonnull String name;
    private @Nullable MzMLUnmarshaller parser;

    private @Nullable Sample sample;

    private final @Nonnull File sourceFile;

    /**
     * <p>
     * Constructor for MzMLRawDataFile.
     * </p>
     *
     * @param sourceFile
     *            a {@link java.io.File} object.
     * @param parser
     *            a {@link uk.ac.ebi.jmzml.xml.io.MzMLUnmarshaller} object.
     * @param msFunctions
     *            a {@link java.util.List} object.
     * @param msScans
     *            a {@link java.util.List} object.
     * @param chromatograms
     *            a {@link java.util.List} object.
     */
    @SuppressWarnings("null")
    public MzMLRawDataFile(@Nonnull final File sourceFile, @Nonnull final MzMLUnmarshaller parser,
	    final List<MsFunction> msFunctions, final List<MsScan> msScans, final List<Chromatogram> chromatograms) {
	this.sourceFile = sourceFile;
	this.parser = parser;
	name = sourceFile.getName();
	this.msFunctions = msFunctions;
	this.msScans = msScans;
	this.chromatograms = chromatograms;
    }

    /** {@inheritDoc} */
    @Override
    public void addChromatogram(@Nonnull final Chromatogram chromatogram) {
	throw new UnsupportedOperationException();
    }

    /** {@inheritDoc} */
    @Override
    public void addScan(@Nonnull final MsScan scan) {
	throw new UnsupportedOperationException();
    }

    /** {@inheritDoc} */
    @Override
    public void dispose() {
	parser = null;
    }

    /** {@inheritDoc} */
    @SuppressWarnings("null")
    @Override
    @Nonnull
    public List<Chromatogram> getChromatograms() {
	return ImmutableList.copyOf(chromatograms);
    }

    /** {@inheritDoc} */
    @SuppressWarnings("null")
    @Override
    @Nonnull
    public List<MsFunction> getMsFunctions() {
	return ImmutableList.copyOf(msFunctions);
    }

    /** {@inheritDoc} */
    @Override
    @Nonnull
    public String getName() {
	return name;
    }

    /** {@inheritDoc} */
    @Override
    @Nullable
    public File getOriginalFile() {
	return sourceFile;
    }

    @Nullable
    MzMLUnmarshaller getParser() {
	return parser;
    }

    /** {@inheritDoc} */
    @Override
    @Nonnull
    public FileType getRawDataFileType() {
	return fileType;
    }

    /** {@inheritDoc} */
    @Nullable
    @Override
    public Sample getSample() {
	return sample;
    }

    /** {@inheritDoc} */
    @SuppressWarnings("null")
    @Override
    @Nonnull
    public List<MsScan> getScans() {
	return ImmutableList.copyOf(msScans);
    }

    /*
     * Unsupported set-operations
     */

    /** {@inheritDoc} */
    @Override
    public void removeChromatogram(@Nonnull final Chromatogram chromatogram) {
	throw new UnsupportedOperationException();
    }

    /** {@inheritDoc} */
    @Override
    public void removeScan(@Nonnull final MsScan scan) {
	throw new UnsupportedOperationException();
    }

    /** {@inheritDoc} */
    @Override
    public void setName(@Nonnull final String name) {
	this.name = name;
    }

    /** {@inheritDoc} */
    @Override
    public void setOriginalFile(@Nullable final File newOriginalFile) {
	throw new UnsupportedOperationException();
    }

    /** {@inheritDoc} */
    @Override
    public void setRawDataFileType(@Nonnull final FileType rawDataFileType) {
	throw new UnsupportedOperationException();
    }

    /** {@inheritDoc} */
    @Override
    public void setSample(final @Nullable Sample sample) {
	this.sample = sample;
    }

}
