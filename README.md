# Compression_and_String-Matching_Algorithms

Developped for an assignment on string searching and compression algorithms, this repository contains the implementation of the Boyer-More, Huffman, KMP, and Lemple-Ziv algorithms. 

## Algorithms

### Boyer-Moore Algorithm: 
#### BoyerMoore.java

The Boyer-Moore algorithm is a highly efficient string-searching technique that pre-processes the pattern to identify mismatches more quickly. It operates by skipping sections of the text, making it faster than naive methods, especially for long patterns. This algorithm uses two heuristics: the bad character rule and the good suffix rule, which together enable significant performance improvements in practical applications.

### Huffman Coding: 
#### HuffmanCoding.java

Huffman Coding is a popular algorithm used for lossless data compression. It assigns variable-length codes to input characters, with shorter codes assigned to more frequent characters. This technique is optimal for constructing prefix-free codes, ensuring that no code is a prefix of another, which is crucial for accurate data reconstruction without ambiguities.

### Knuth-Morris-Pratt (KMP) Algorithm: 
#### KMP.java

The KMP algorithm is designed for string matching, enabling efficient searches within a text by preprocessing the pattern to determine where mismatches occur. This preprocessing phase creates a partial match table (also known as the "failure function"), which guides the search to avoid redundant comparisons. By systematically skipping over parts of the text, KMP achieves linear time complexity, making it ideal for large-scale text processing tasks.

### Lempel-Ziv (LZ) Algorithm: 
#### LempleZiv.java

The Lempel-Ziv algorithm is a fundamental technique in the field of data compression, forming the basis for widely used formats like ZIP and PNG. It works by identifying and encoding repeating patterns within the data, thus reducing redundancy. The algorithm dynamically builds a dictionary of previously encountered patterns, which allows it to compress data efficiently without prior knowledge of its structure.

## Useage
1. **Compilation:** Use a Java compiler to compile the Java files.

2. **Integration:** Incorporate the compiled Java programs into your projects to leverage the algorithms.

3. **Guidelines:** Refer to the specific usage instructions within each program for detailed guidelines on each function.

## Acknowledgements

These implementations were developed as part of a project for COMP261 at Victoria University of Wellington.
