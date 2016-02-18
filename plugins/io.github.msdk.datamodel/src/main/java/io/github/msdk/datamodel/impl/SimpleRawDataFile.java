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

package io.github.msdk.datamodel.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;

import io.github.msdk.datamodel.chromatograms.Chromatogram;
import io.github.msdk.datamodel.datastore.DataPointStore;
import io.github.msdk.datamodel.featuretables.Sample;
import io.github.msdk.datamodel.files.FileType;
import io.github.msdk.datamodel.rawdata.MsFunction;
import io.github.msdk.datamodel.rawdata.MsScan;
import io.github.msdk.datamodel.rawdata.RawDataFile;

/**
 * Implementation of the RawDataFile interface.
 */
class SimpleRawDataFile implements RawDataFile {

    private final @Nonnull ArrayList<Chromatogram> chromatograms;
    private final @Nonnull DataPointStore dataPointStore;
    private @Nullable File originalRawDataFile;
    private @Nonnull String rawDataFileName;
    private @Nonnull FileType rawDataFileType;
    private @Nullable Sample sample;
    private final @Nonnull ArrayList<MsScan> scans;

    SimpleRawDataFile(@Nonnull final String rawDataFileName, @Nullable final File originalRawDataFile,
	    @Nonnull final FileType rawDataFileType, @Nonnull final DataPointStore dataPointStore) {
	Preconditions.checkNotNull(rawDataFileType);
	Preconditions.checkNotNull(dataPointStore);
	this.rawDataFileName = rawDataFileName;
	this.originalRawDataFile = originalRawDataFile;
	this.rawDataFileType = rawDataFileType;
	this.dataPointStore = dataPointStore;
	scans = new ArrayList<>();
	chromatograms = new ArrayList<>();
    }

    /** {@inheritDoc} */
    @Override
    public void addChromatogram(@Nonnull final Chromatogram chromatogram) {
	Preconditions.checkNotNull(chromatogram);
	synchronized (chromatograms) {
	    chromatograms.add(chromatogram);
	}
    }

    /** {@inheritDoc} */
    @Override
    public void addScan(@Nonnull final MsScan scan) {
	Preconditions.checkNotNull(scan);
	synchronized (scans) {
	    scans.add(scan);
	}
	scan.setRawDataFile(this);
    }

    /** {@inheritDoc} */
    @Override
    public void dispose() {
	dataPointStore.dispose();
    }

    /** {@inheritDoc} */
    @SuppressWarnings("null")
    @Override
    @Nonnull
    public List<Chromatogram> getChromatograms() {
	return ImmutableList.copyOf(chromatograms);
    }

    /** {@inheritDoc} */
    @Override
    @Nonnull
    public List<MsFunction> getMsFunctions() {
	final ArrayList<MsFunction> msFunctionList = new ArrayList<>();
	synchronized (scans) {
	    for (final MsScan scan : scans) {
		final MsFunction f = scan.getMsFunction();
		if (f != null) {
		    msFunctionList.add(f);
		}
	    }
	}
	return msFunctionList;
    }

    /** {@inheritDoc} */
    @Override
    public @Nonnull String getName() {
	return rawDataFileName;
    }

    /** {@inheritDoc} */
    @Override
    @Nullable
    public File getOriginalFile() {
	return originalRawDataFile;
    }

    /** {@inheritDoc} */
    @Override
    public @Nonnull FileType getRawDataFileType() {
	return rawDataFileType;
    }

    /** {@inheritDoc} */
    @Override
    public Sample getSample() {
	return sample;
    }

    /** {@inheritDoc} */
    @SuppressWarnings("null")
    @Override
    public @Nonnull List<MsScan> getScans() {
	return ImmutableList.copyOf(scans);
    }

    /** {@inheritDoc} */
    @Override
    public void removeChromatogram(@Nonnull final Chromatogram chromatogram) {
	Preconditions.checkNotNull(chromatogram);
	synchronized (chromatograms) {
	    chromatograms.remove(chromatogram);
	}
    }

    /** {@inheritDoc} */
    @Override
    public void removeScan(@Nonnull final MsScan scan) {
	Preconditions.checkNotNull(scan);
	synchronized (scans) {
	    scans.remove(scan);
	}
    }

    /** {@inheritDoc} */
    @Override
    public void setName(@Nonnull final String name) {
	Preconditions.checkNotNull(name);
	rawDataFileName = name;
    }

    /** {@inheritDoc} */
    @Override
    public void setOriginalFile(@Nullable final File newOriginalFile) {
	originalRawDataFile = newOriginalFile;
    }

    /** {@inheritDoc} */
    @Override
    public void setRawDataFileType(@Nonnull final FileType rawDataFileType) {
	Preconditions.checkNotNull(rawDataFileType);
	this.rawDataFileType = rawDataFileType;
    }

    /** {@inheritDoc} */
    @Override
    public void setSample(final Sample sample) {
	this.sample = sample;
    }

}
