SOURCEDIR = src
SOURCES := $(shell find $(SOURCEDIR) -name '*.java')

all:
	$(shell mkdir bin &>/dev/null)
	javac -cp "src/names/*" $(SOURCES) -d bin

run:
	java -cp "src/names/*:bin/" Main $(arg)

.PHONY: run
