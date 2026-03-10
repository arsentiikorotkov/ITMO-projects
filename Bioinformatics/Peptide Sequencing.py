import numpy as np
from sys import stdin


alphabet = ['A','C','D','E','F','G','H','I','K','L','M','N','P','Q','R','S','T','V','W','Y'] 
masses = [71, 103, 115, 129, 147, 57, 137, 113, 128, 113, 131, 114, 97, 128, 156, 87, 101, 99, 186, 163]


mass_from_letter = dict(zip(alphabet, masses))

def spectrum(peptide):
    peptide_mass = [mass_from_letter[letter] for letter in list(peptide)]

    prefixes = np.cumsum(peptide_mass)
    suffixes = np.full(shape=len(peptide_mass), fill_value=prefixes[-1]) - prefixes

    return np.sort(np.unique(np.concatenate((prefixes, suffixes))))
           

peptide = stdin.readline().strip()
    
print(*spectrum(peptide))
