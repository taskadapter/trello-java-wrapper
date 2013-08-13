trello-java-wrapper
===================

Java Wrapper for Trello API

[![Build Status](https://travis-ci.org/julienvey/trello-java-wrapper.png?branch=master)](https://travis-ci.org/julienvey/trello-java-wrapper)

## Release

### Config

Use [maven-jgitflow-plugin](https://bitbucket.org/atlassian/maven-jgitflow-plugin/wiki/Home)

You may also need to provide a plugin group in your /.m2/settings.xml to be able to use the short name of the plugin on the command line.

To do so, simply add the following to your settings.xml:
```xml
<pluginGroups>
  <pluginGroup>com.atlassian.maven.plugins</pluginGroup>
</pluginGroups>
```

### Workflow

Create the release branch and set the release version. Release-start creates a release branch and sets the pom version.

	mvn jgitflow:release-start

Finish the release and set the next snapshot version. Release-finish creates the release tag, merges the release branch into master, deletes the release branch and push everything to the scm.

	mvn jgitflow:release-finish
