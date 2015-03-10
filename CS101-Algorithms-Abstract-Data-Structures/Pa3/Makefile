#------------------------------------------------------------------------------
#  Makefile for CMPS 101 pa3
#  Creates an executable jar file called Sparse.  This makefile will compile 
#  all .java files in your current working directory.  
#------------------------------------------------------------------------------

MAINCLASS  = Sparse
JAVAC      = javac 
MATRIXTEST = MatrixTest
LISTTEST = ListTest
JAVASRC    = $(wildcard *.java)
SOURCES    = $(JAVASRC) Makefile README
CLASSES    = $(patsubst %.java, %.class, $(JAVASRC))
JARCLASSES = $(patsubst %.class, %*.class, $(CLASSES))
JARFILE    = $(MAINCLASS)
JARFILE2    = $(MATRIXTEST) 
JARFILE3    = $(LISTTEST) 

all: $(JARFILE)
	
$(JARFILE): $(CLASSES)
	echo Main-class: $(MAINCLASS) > Manifest
	jar cvfm $(JARFILE) Manifest $(JARCLASSES)
	chmod +x $(JARFILE)
	rm Manifest
	
MatrixTest: $(MATRIXTEST) 

 $(MATRIXTEST): $(CLASSES)
	echo Main-class:  $(MATRIXTEST) > Manifest
	jar cvfm $(JARFILE2)  Manifest $(JARCLASSES)
	chmod +x $(JARFILE2)
	rm Manifest

ListTest: $(LISTTEST)

$(LISTTEST) : $(CLASSES)
	echo Main-class:  $(LISTTEST)  > Manifest
	jar cvfm $(JARFILE3)  Manifest $(JARCLASSES)
	chmod +x $(JARFILE3) 
	rm Manifest
	
%.class: %.java
	$(JAVAC) $<

clean:
	rm -f *.class $(JARFILE) 
