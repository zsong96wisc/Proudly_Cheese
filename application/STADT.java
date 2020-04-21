package application;
import java.util.List;

/**
 * Defines the additional operations required of student's BST class.
 * 
 * @author Deb Deppeler (deppeler@cs.wisc.edu)
 * @param <K> A Comparable type to be used as a key to an associated value.  
 * @param <V> A value associated with the given key.
 */
public interface STADT<K extends Comparable<K>> {
    
    /**
     * Returns the height of this BST.
     * H is defined as the number of levels in the tree.
     *
     * @return the number of levels that contain keys in this BINARY SEARCH TREE
     */
    int getHeight();
    
    /**
     * Returns the keys of the data structure in sorted order.
     * In the case of binary search trees, the visit order is: L V R
     * 
     * If the SearchTree is empty, an empty list is returned.
     * 
     * @return List of Keys in-order
     */
    List<K> getInOrderTraversal();
    
    /**
     * Returns the keys of the data structure in pre-order traversal order.
     * In the case of binary search trees, the order is: V L R
     * 
     * If the SearchTree is empty, an empty list is returned.
     * 
     * @return List of Keys in pre-order
     */
    List<K> getPreOrderTraversal();

    /**
     * Returns the keys of the data structure in post-order traversal order.
     * In the case of binary search trees, the order is: L R V 
     * 
     * If the SearchTree is empty, an empty list is returned.
     * 
     * @return List of Keys in post-order
     */
    List<K> getPostOrderTraversal();

    /**
     * Returns the keys of the data structure in level-order traversal order.
     * 
     * The root is first in the list, then the keys found in the next level down,
     * and so on. 
     * 
     * If the SearchTree is empty, an empty list is returned.
     * 
     * @return List of Keys in level-order
     */
    List<K> getLevelOrderTraversal();
    
    /** 
     * Add the key,value pair to the data structure and increase the number of keys.
     * If key is null, throw IllegalNullKeyException;
     * If key is already in data structure, throw DuplicateKeyException(); 
     * Do not increase the num of keys in the structure, if key,value pair is not added.
     */
    void insert(K key) throws IllegalNullKeyException, DuplicateKeyException;
    
    /** 
     * If key is found, remove the key,value pair from the data structure 
     *                  and decrease num keys, and return true.
     * If key is not found, do not decrease the number of keys in the data structure.
     * If key is null, throw IllegalNullKeyException
     * If key is not found, return false.
     */
    boolean remove(K key) throws IllegalNullKeyException;

    /**
     *  Returns the value associated with the specified key
     *
     * Does not remove key or decrease number of keys
     * If key is null, throw IllegalNullKeyException 
     * If key is not found, throw KeyNotFoundException().
     */
    K get(K key) throws IllegalNullKeyException, KeyNotFoundException;

    /** 
     * Returns true if the key is in the data structure
     * If key is null, throw IllegalNullKeyException 
     * Returns false if key is not null and is not present 
     */
    boolean contains(K key) throws IllegalNullKeyException;

    /**
     *  Returns the number of key,value pairs in the data structure
     */
    int numKeys();
    
} // deppeler@cs.wisc.edu      

