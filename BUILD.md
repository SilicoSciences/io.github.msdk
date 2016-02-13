## Prerequisities

A sucessful build depends on the `org.openscience.cdk` framework.

Clone `org.openscience.cdk` into the same directory as MSDK. E.g.:

    git clone git@github.com:SilicoSciences/org.openscience.cdk.git
    git clone git@github.com:SilicoSciences/msdk.git

# How to build MSDK using Maven Tycho

Eclipse Plug-ins can be found unter `plugins` directory, features in the `features` directory. An additional folder, `build` contains projects that are needed to build MSDK using Tycho.

The `build` directory contains the `parent` project as well as an `aggregator` project.

To start the build, cd to `build\io.github.msdk.aggregator` and execute e.g. `mvn install`.
