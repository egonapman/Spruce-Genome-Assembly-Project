# Spruce-Genome-Assembly-Project

Project assignment part of course DA3018

The file Spruce_fingerprint_2017-03-10_16.48.olp.m4 describes a graph G.

The assigemnt is to find the node degree distrubtion, number of components and component size distrobution of G.

Since our file is quite large we should try to preprocess as much as possible before runnig expirmantes.

Prepocess: 

1. Find all "contained" nodes and ignore them
2. Find only unique pairs of nodes a,b in (since a,b and b,a most most likley will descirbe the same edge)
3. Remove unecesary data about contigs
4. Store idenfieres as integers instead of strings

I decided to remove all contained edges using java, a while-loops that itaretes all node-pairs. If a node is equal to the overlap in the other i assume it's compltly overlapped and can therefore be removed.

Initally the graph contained a total of 64,056,772 lines after removing all conatiened nodes I was left with only 40,350,995 lines.


Then i wanted to see if there exited lines with same identifiers but in diffrent order, since node pair a,b will represnt the same edge as b,a.

Here I decided to use unix commands.

The resulting file containd 39,881,899 lines.


