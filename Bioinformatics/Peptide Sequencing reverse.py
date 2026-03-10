import numpy as np
from sys import stdin


alphabet = ['A','C','D','E','F','G','H','I','K','L','M','N','P','Q','R','S','T','V','W','Y'] 
masses = [71, 103, 115, 129, 147, 57, 137, 113, 128, 113, 131, 114, 97, 128, 156, 87, 101, 99, 186, 163]


mass_from_letter = dict(zip(alphabet, masses))

def Ideal_Spectrum(peptide):
    peptide_mass = [mass_from_letter[letter] for letter in list(peptide)]

    prefixes = np.cumsum(peptide_mass)
    suffixes = np.full(shape=len(peptide_mass), fill_value=prefixes[-1]) - prefixes

    return np.sort(np.unique(np.concatenate((prefixes, suffixes))))


letter_from_mass = dict(zip(masses, alphabet))

def Graph(ideal_spectrum):
    graph = []

    for i in range(len(ideal_spectrum)):
        i_neighbors = []

        for j in range(i + 1, len(ideal_spectrum)):
            if (ideal_spectrum[j] - ideal_spectrum[i]) in letter_from_mass: i_neighbors.append(j)
        
        graph.append(i_neighbors)

    return graph

def find_peptide(graph, v, peptide):
    if (v == len(graph) - 1) and np.array_equal(Ideal_Spectrum(peptide), ideal_spectrum): return peptide

    for x in graph[v]:
        new_peptide = peptide + letter_from_mass[ideal_spectrum[x] - ideal_spectrum[v]]

        if (result := find_peptide(graph, x, new_peptide)) != "unluck": return result

    return "unluck"

def Peptide(ideal_spectrum):
    graph = Graph(ideal_spectrum)

    return find_peptide(graph, 0, "")


ideal_spectrum = list(map(int, stdin.readline().strip().split()))
    
print(Peptide(ideal_spectrum))
