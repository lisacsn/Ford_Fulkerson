PACKAGE = fordfulkerson
MAIN = Flow

CP = classes
LIB = lib
SRC = src

compil: $(MAIN).java

$(MAIN).java: src/**/*.java
	javac --source-path $(SRC) --class-path $(CP):$(LIB)/* -d $(CP) ./src/$(PACKAGE)/$(MAIN).java

run: classes/$(PACKAGE)/Flow.class
	java -classpath $(CP):$(LIB)/* $(PACKAGE).$(MAIN)

clean:
   	rm -rf classes/

all : 
	rm -rf classes/
