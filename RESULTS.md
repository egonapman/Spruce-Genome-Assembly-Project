I was given a file Spruce_fingerprint_2017-03-10_16.48.olp.m4 that represents a graph that I need to find the Node degree distrobution, number of components and component size distrobution.

preprocess

To get the result I got, the first step is to remove contained nodes with graph then on the reulting file we want only one of each edge with the unix command found in the scripts directory.

The resulting file UniqueEdges.txt only contains the first identfier names since thats all thats needed for this assingment part.

Get the node degree distobution and component size distrobution from the dnaLinks class assuming you have run all the preprocess

(I had to change to run configrations when running in eclipse beacuse of how big the dataset is)

def main():

  dnaLinks links = new dnaLinks():
  
  file preporcessedFile = new preporcessedFile(file)
  
  for each line in file
  
    links.add(col[0], col[1])
    
    
    
  links.setKomponent();
			
	links.setHistogram();
			
	links.setHistogramcomponrt();
			
	links.printDegreeDistubition();
			
	links.printComponentSizeDistrubution();
  
 
 this creates two files NodeDegreeDistrubition.txt and ComponentSizeDistrubtion.txt
 
 
 
 
  
  
  
