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

