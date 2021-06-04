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

--------------ASSIGNMENT 1: Node degree distrubition-------------------

For the assignment we fisrt need to count the degree of every node.

The project page hints that idenfiers should be converted to integers to take up less space I think this implemnting a hashmap.
Hashmaps is also usefull here since we can access induvidual edges of the graph quickly given a identifier.

I have not worked much with hashmaps earlier so I will use java's HashMap.

For the first assigment I have to find out have many negibours every node has.

An edge (a,b) in E implies both that a is a neigbor to b and that b is a neigbor to a.

I really only have to count the number of occurances of a node to finds it's degree.

For this i decieded to iterate each node to a list containg it's neighbors with the following algortihm

------------------------------
def mapNodesToNeighbors(G):
  
 map<key, list> map = new Hashmap()
 
 for each edge in G:
    
   if (!map.containsKey(edge.node1)):
      map.put(node1, new list)
      map(node1).add(node2)
   else:
      map(node1).add(node2)

   if (!map.containsKey(edge.node2)):
      map.put(node2, new list)
      map(node1).add(node1)
   else:
      map(node2).add(node1)
  
  
------------------------------

In the real code I use the Hashcode of the identifier as keys in my hashmap.

example:

contig_1695162".hashCode()

will return: 1865787805.

Now we get the degrees of every node by iterating entries of the map ang getting the size of every list.
I could have just used a counter when initating the hashmap instead of a list with every neighbor, but having a list made it easier to examine in test
and when we need to know every neighbor for later assignments. 

----------------------------------
def getNodeDegreeDistrubtion(map<key, listOfNeigbors>):

  for each entry in map():
   degree = entry.getValue.size();
   //do something (print/write to file etc)

---------------------------------

--------------ASSIGNMENT 2 & 3: Number of components and component size distubition-------------------

Two nodes are in the same componet if there exist a path between them. In our case this means that every node and it's neighbors will be of the same component.

we can simply find all components by looking at every line in our file and try to go as far as possible recursivly from each node and assingning every encounterd node to 
a component number. Nodes with the same component number will be in the same component. I decided to make a object that stores neghibours and a component number.

The algorithm is as follows

--------------------------------------------

def setComponent(map<key, negboursAndcomponent> neigboursAndComponents):
  
  componentNumber = 0
  
  for entry in neigboursAndComponents():
    
   current = entry.getValue()
   
   if(current.component == null):  //componet has not yet been defined if it's set to null.
      componentNumber++;
      setComponentRecursivly(componentNumber, current):
        
    
def setComponentRecursivly(componentNumber, current):
  
  if(current.component == null):
    current.setComponentNumber(componentNumber)
   
  for each neighbor in current.neighbors:
    
   if(neighbor.component == null):
    setComponentRecursivly(componentNumber, neighbor)
   
    
 --------------------------------------------   


The running time is O(V) where V is the number of vertices in the graph, since we just simply iterate all the nodes V in the graph, if the first component contains A nodes
we will have V - A expensive function calls left.












