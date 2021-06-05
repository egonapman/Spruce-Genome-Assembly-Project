I was given a file Spruce_fingerprint_2017-03-10_16.48.olp.m4 that represents a graph that I need to find the Node degree distrobution, number of components and component size distrobution.

preprocess

To get the result I got, the first step is to remove contained nodes with graph then on the reulting file we want only one of each edge with the unix command found in the scripts directory.

The resulting file UniqueEdges.txt only contains the first identfier names since thats all thats needed for this assingment part.

Get the node degree distobution and component size distrobution from the GraphCalculator class assuming you have run all the preprocess.



psudo-code for my main method

    def main():

       GraphCalculator calculator=new GraphCalculator();
  
       file preporcessedFile = preprocessedFile
  
       for each line in preprocessedFile:
  
    	  links.add(col[0], col[1])

        links.setKomponent();
			
        calculator.assembleContigKeyIntoComponents();
		
        calculator.setDegreeDistribution();
		
        calculator.setComponentSizeDistribution();
		
        calculator.printDegreeDistubition("C:\\temp\\NodeDegreeDistrubition.txt");
		 
        calculator.printComponentSizeDistrubution("C:\\temp\\ComponentSizeDistrubtion.txt");
  
 
 All my java scripts was ran in eclipse, for it to work on the big data set I had to change run configuration by adding -Xms1048m -Xmx12288m -Xss512m to argumnets or
 I would end up with a stackoverflow error.
 
 This creates two files NodeDegreeDistrubition.txt and ComponentSizeDistrubtion.txt (first line in this file contains number of components).
 
 
 
 
 
  
  
  
