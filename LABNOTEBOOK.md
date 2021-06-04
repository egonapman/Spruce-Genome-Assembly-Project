# Spruce-Genome-Assembly-Project

Project assignment part of course DA3018

The file Spruce_fingerprint_2017-03-10_16.48.olp.m4 describes a graph G.

The assigemnt is to find the node degree distrubtion, number of components and component size distrobution of G.

Since our file is quite large we should try to preprocess as much as possible before runnig expirmantes.

----------------------PREPROCESS-----------------

1. Find all "contained" nodes and ignore them
2. Find only unique pairs of nodes a,b in (since a,b and b,a most most likley will descirbe the same edge)
3. Remove unecesary data about contigs
4. Store idenfieres as integers instead of strings

I decided to remove all contained edges using java, a while-loops that itaretes all node-pairs. If a node is equal to the overlap in the other i assume it's compltly overlapped and can therefore be removed.

------------------------------------------------------------

def removeContainedNodes(inputFile):
  
  outputFile = new file()
  
  reader = new Scanner(file)
  
  while(reader.hasNextLine){
    
   edge = reader.nextLine
    
   if((edge.node1.overlapEnd - edge.node1.overlapStart) == edge.node2.length){
    
       //node2 is contained in node1
     
   }else if((edge.node2.overlapEnd - edge.node2.overlapStart) == edge.node1.length){
     
     //node2 is contained in node1
     
   }else{
     
   outputFile.write(edge)
     
   }
 }
 
 ---------------------------------------------------------
 
 removeContainedNodes, assuming reading and writing files is in constant time, runs in constant time O(n) where n is the number of edges in G (represnted as lines in the input file).
      

Initally the graph contained a total of 64,056,772 lines after removing all conatiened nodes I was left with only 40,350,995 lines.


Then I wanted to see if there exists lines with same identifiers but in diffrent order, since node pair a,b will represnt the same edge as b,a.

Here I decided to use unix commands.

$awk '{if($1 < $2) print $2" "$1; else print $1" "$2}' inputFile | sort | uniq > outputFile

Example:

input file:

ctg.ctg01 ctg.ctg02<br/>
ctg.ctg03 ctg.ctg01<br/>
ctg.ctg01 ctg.ctg04<br/>
ctg.ctg02 ctg.ctg01<br/>
ctg.ctg04 ctg.ctg01

$awk '{if($1 < $2) print $2" "$1; else print $1" "$2}' inputFile gives

ctg.ctg02 ctg.ctg01<br/>
ctg.ctg03 ctg.ctg01<br/>
ctg.ctg04 ctg.ctg01<br/>
ctg.ctg02 ctg.ctg01<br/>
ctg.ctg04 ctg.ctg01

$sort gives
  
ctg.ctg02 ctg.ctg01<br/>
ctg.ctg02 ctg.ctg01<br/>
ctg.ctg03 ctg.ctg01<br/>
ctg.ctg04 ctg.ctg01<br/>
ctg.ctg04 ctg.ctg01
  
$uniq gives
  
ctg.ctg02 ctg.ctg01<br/>
ctg.ctg03 ctg.ctg01<br/>
ctg.ctg04 ctg.ctg01

All edges are now only reapeted once.

The resulting file containd 39,881,899 lines.

--------------ASSIGNMENT-------------------

For the assignment we fisrt need to count the derggree of every node.

The project page hints that idenfiers should be converted to integers to take up less space I think this implemnting a hashmap.
Hashmaps is also usefull since we can hopfully access induvidual edges of the graph quickly given a identifier.

I will map every identifiers hashcode with a list of it's connected neighbors, the length of the list will then be the degree of the node is mapped with.

The algorithms i used for this

iterates all edges and adds the first identifer to a hashmap and adds the second identifer to it's list of negibours

then it adds the second identifier to the hashmap with the first identifer to it's list of neighbors.

If a idenfier is already in the hashmap we add it's other idenfier to it's list of neigbours

Example:

node pairs:

ctg.ctg01 ctg.ctg02
ctg.ctg01 ctg.ctg03
ctg.ctg02 ctg.ctg03

map (in the real code the keys are hashcode and not idenfiers strings):

first iteration

ctg.ctg01 : [ctg.ctg02]

ctg.ctg02 : [ctg.ctg01]

second iteration

ctg.ctg01 : [ctg.ctg02, ctg.ctg03]

ctg.ctg02 : [ctg.ctg01]

ctg.ctg03 : [ctg.ctg01]

third iteration

ctg.ctg01 : [ctg.ctg02 , ctg.ctg03]

ctg.ctg02 : [ctg.ctg01 , ctg.ctg03]

ctg.ctg03 : [ctg.ctg01 , ctg.ctg02]

here every node will have degree two since all node have 2 neigbours.

For the assigmnet we also want to know how many components G contains. For this I made my hashamp map every node to it's neibors and also a component number.

Two nodes are part of the same componet if theres a path between them.

I can set component by iterating every entry in my hashmap

for each entry i first need to check if they already have a assiend component if not we will add it to the first component (1) then i iterate it's neighbors
and for each neighor we use recursion to get set it and all of it's neigbors.

It's similar to graph traversal with the assumption that all nodes cant be reached from a node, and all visted nodes will share component number with it's neigbors.

I decided to map every component number to the number of nodes with that component number so it will be east to find the size distrubiton and number of components. 











