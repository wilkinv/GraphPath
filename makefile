JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class: 
	$(JC) $(JFLAGS) $*.java

CLASSES = \
        GraphSearch.java \
        Pair.java \
        ShortestDistance.java \
        Structure.java \
	StructureQueue.java \
	StructureStack.java 

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
