/**
 * Project name: P2_Project
 * <p>
 * Course: COMP SCI 400, Spring, 2020
 * Lecture number: 002
 * Lecturer's Name: Debra Deppeler
 * <p>
 * Name: Xiaoxi Sun
 * E-mail: xsun279@wisc.edu
 * <p>
 * Description: This class implements a binary search tree data structure
 */

package application;

import java.util.LinkedList;
import java.util.List;

/**
 * Defines the operations required of student's BST class.
 *
 * NOTE: There are many methods in this interface 
 * that are required solely to support gray-box testing 
 * of the internal tree structure.  They must be implemented
 * as described to pass all grading tests.
 * 
 * @author Deb Deppeler (deppeler@cs.wisc.edu)
 * @param <K> A Comparable type to be used as a key to an associated value.  
 * @param <V> A value associated with the given key.
 */
public class BST<K extends Comparable<K>> implements STADT<K> {
  private TreeNode root;
  private int size;

  /**
   * Node class for binary search tree
   * 
   * Class variable: private K key, private V value, private Node left, 
   * private Node right;
   * 
   * Class method: private Node(K, V), private boolean isLeaf()
   * 
   * @author Xiaoxi Sun
   *
   */
  private class TreeNode {
    private K data; 
    private TreeNode left; // left child of this node
    private TreeNode right; // right child of this node

    /**
     * Constructor of Node
     * 
     * @param dt - of the pair
     */
    private TreeNode(K dt) {
      data = dt;
      left = null;
    }

    /**
     * Constructor of the TreeNode
     * 
     * @param dt - data of the pair
     * @param l - left node
     * @param r - right node
     */
    private TreeNode(K dt, TreeNode l, TreeNode r) {
      data = dt;
      left = l;
      right = r;
    }

    /**
     * Determine whether this node is a leaf
     * 
     * @return true if it is. Otherwise, false.
     */
    private boolean isLeaf() {
      if (left == null && right == null)
        return true;
      else
        return false;
    }
  }

  /**
   * Returns the height of this BST. H is defined as the number of levels 
   * in the tree.
   * 
   * @return the number of levels that contain keys in this BINARY SEARCH TREE
   */
  public int getHeight() {
    return getHeightHelper(root);
  }

  /**
   * get the height of the current tree
   * 
   * @param node - node of current tree
   * @return the height of the current tree
   */
  private int getHeightHelper(TreeNode node) {
    if (node == null) // Base case
      return 0;
    else {
      // Compute the sub tree's height
      int leftHeight = getHeightHelper(node.left);
      int rightHeight = getHeightHelper(node.right); 
      
      // return the height of the highest sub tree.
      return 1 + (leftHeight > rightHeight ? leftHeight : rightHeight); 
    }
  }

  /**
   * Returns the keys of the data structure in sorted order. In the case of 
   * binary search trees, the
   * visit order is: L V R
   * 
   * If the SearchTree is empty, an empty list is returned.
   * 
   * @return List of Keys in-order
   */
  public List<K> getInOrderTraversal() {
    return getInOrderTraversalHelper(root);
  }

  /**
   * Return the list containing keys of in-order traversal of sub tree whose
   * root is node
   * 
   * @param node - current node that is traversed
   * @return the list containing keys of current traversal
   */
  private List<K> getInOrderTraversalHelper(TreeNode node) {
    if (node == null)
      return null;
    else {
      List<K> tempList = new LinkedList<K>();

      if(node.left != null) // Traverse the left sub tree
        tempList.addAll(getInOrderTraversalHelper(node.left));

      tempList.add(node.data);  // Add current node second

      if(node.right != null) // Traverse the right sub tree
        tempList.addAll(getInOrderTraversalHelper(node.right));

      return tempList;
    }
  }

  /**
   * Returns the keys of the data structure in pre-order traversal order. In the 
   * case of binary search trees, the order is: V L R
   * 
   * If the SearchTree is empty, an empty list is returned.
   * 
   * @return List of Keys in pre-order
   */
  public List<K> getPreOrderTraversal() {
    return getPreOrderTraversalHelper(root);
  }

  /**
   * Return the list containing keys of pre-order traversal of sub tree whose
   * root is node
   * 
   * @param node - current node that is traversed
   * @return the list containing keys of current traversal
   */
  private List<K> getPreOrderTraversalHelper(TreeNode node) {
    if (node == null)
      return null;
    else {
      List<K> tempList = new LinkedList<K>();

      tempList.add(node.data); // Add current node first

      if(node.left != null) // Traverse the left sub tree
        tempList.addAll(getPreOrderTraversalHelper(node.left));

      if(node.right != null) // Traverse the right sub tree
        tempList.addAll(getPreOrderTraversalHelper(node.right));

      return tempList;
    }
  }

  /**
   * Returns the keys of the data structure in post-order traversal order. 
   * In the case of binary search trees, the order is: L R V
   * 
   * If the SearchTree is empty, an empty list is returned.
   * 
   * @return List of Keys in post-order
   */
  public List<K> getPostOrderTraversal() {
    return getPostOrderTraversalHelper(root);
  }

  /**
   * Return the list containing keys of post-order traversal of sub tree whose
   * root is node
   * 
   * @param node - current node that is traversed
   * @return the list containing keys of current traversal
   */
  private List<K> getPostOrderTraversalHelper(TreeNode node) {
    if (node == null)
      return null;
    else {
      List<K> tempList = new LinkedList<K>();
      
      if(node.left != null) // Traverse the left sub tree
        tempList.addAll(getPostOrderTraversalHelper(node.left));

      if(node.right != null) // Traverse the right sub tree
        tempList.addAll(getPostOrderTraversalHelper(node.right));

      tempList.add(node.data);  // Add current node last
      
      return tempList;
    }
  }

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
  public List<K> getLevelOrderTraversal() {
    if (root == null)
      return null;

    List<K> traversalList = new LinkedList<K>(); // list to be returned
    // queue for level traversal
    LinkedList<TreeNode> queue = new LinkedList<TreeNode>();

    queue.add(root);
    TreeNode current = null; // current temporary node
    while (!queue.isEmpty()) {
      // Only when the queue is empty, the traversal is over
      current = queue.removeFirst();
      traversalList.add(current.data); // Traversal

      if (current.left != null)
        queue.add(current.left);
      if (current.right != null)
        queue.add(current.right);
    }

    return traversalList;
  }

  /**
   * Add the key,value pair to the data structure and increase the number of 
   * keys. If key is null, throw IllegalNullKeyException; If key is already 
   * in data structure, throw DuplicateKeyException(); Do not increase the 
   * num of keys in the structure, if key,value pair is not added.
   * 
   * @param key - the key to be inserted
   * @param value - the value to be inserted
   * @throws DuplicateKeyException - if there is duplicate key
   * @throws IllegalNullKeyException - if the key is null
   */
  public void insert(K key)
      throws IllegalNullKeyException, DuplicateKeyException {
    
    if(key == null) // null key
      throw new IllegalNullKeyException("null key");
    
    if(root == null)
      root = new TreeNode(key); // new node is the root
    else // The tree is not empty
      insertHelper(new TreeNode(key), root); // call helper to help
    
    size ++;
    return;
  }

  /**
   * Help to insert the targeted key, value pair
   * 
   * @param nodeToInsert - node to be inserted
   * @param current - root of the current sub-tree
   * @throws DuplicateKeyException if there is duplicate key
   */
  private void insertHelper(TreeNode nodeToInsert, TreeNode current) 
      throws DuplicateKeyException {
    int compareResult = current.data.compareTo(nodeToInsert.data);
    if (compareResult == 0) // Meet with the same key
      throw new DuplicateKeyException("duplicate key");
    
    else if(compareResult > 0) { // current node is bigger
      if (current.left == null) // Find a place to insert
        current.left = nodeToInsert;
      else // should continue to find the place to insert
        insertHelper(nodeToInsert, current.left);
    }
    else { // current node is smaller
      if (current.right == null) // Find a place to insert
        current.right = nodeToInsert;
      else // should continue to find the place to insert
        insertHelper(nodeToInsert, current.right);
    }
    return;
  }

  /**
   * If key is found, remove the key,value pair from the data structure and 
   * decrease num keys, and return true. 
   * 
   * If key is not found, do not decrease the number of keys in the data 
   * structure, return false. 
   * 
   * If key is null, throw IllegalNullKeyException
   * 
   * @param key - the key to be removed
   * @return true if the key is successfully removed
   * @throws IllegalNullKeyException if the key is null
   */
  public boolean remove(K key) throws IllegalNullKeyException {
    if (key == null)
      throw new IllegalNullKeyException("null key");

    if (!contains(key)) // Key is not and key is not found
      return false;

    root = removeHelper(root, key);
    return true;
  }

  /**
   * helps remove to remove the targeted node with a certain key
   * 
   * @param current is the sub-tree's current node
   * @param key - of the target
   * @return the sub-tree after remove
   */
  private TreeNode removeHelper(TreeNode current, K key) {
    if(current == null)
      return current;

    int compareResult = current.data.compareTo(key);

    if(compareResult == 0) { // Find the node to be removed
      if (current.isLeaf()) { // Node is a leaf
        size --;
        return null;
      } else if(current.left == null) { // There is something in the right
        size --;
        return current.right;
      } else if (current.right == null) { // There is something in the left
        size --;
        return current.left;
      } else { // There are two children for this node
        TreeNode predeccessor = findBiggest(current.left);
        current.left = removeHelper(current.left, predeccessor.data); 
        // Size is already deducted
        current.data = predeccessor.data;
        return current;
      }
    }

    else if(compareResult > 0) // should look to the left
      current.left = removeHelper(current.left, key);
    else  // should look to the right
      current.right = removeHelper(current.right, key);

    return current;
  }

  /**
   * find the most right (biggest) node of the sub tree
   * 
   * @param current is the sub-tree's root
   * @return the node with the biggest key
   */
  private TreeNode findBiggest(TreeNode current) {

    while(current.right != null) { 
      // find the most right (biggest) node of the sub tree
      current = current.right;
    }

    return current;
  }

  /**
   * Returns the value associated with the specified key.
   *
   * Does not remove key or decrease number of keys If key is null, 
   * throw IllegalNullKeyException 
   * If key is not found, throw KeyNotFoundException().
   * 
   * @param key - the key to be removed
   * @return the value associated with the key
   * @throws KeyNotFoundException if the key is not found
   * @throws IllegalNullKeyException if the key is null
   */
  public K get(K key) throws IllegalNullKeyException, KeyNotFoundException {
    if(key == null)
      throw new IllegalNullKeyException("null key");

    return getHelper(root, key);
  }

  /**
   * Get the value according to key
   * 
   * @param current root of current sub tree
   * @param key of the pair to be found
   * @return the value if key is in the tree
   * @throws KeyNotFoundException if there is no such key
   */
  private K getHelper(TreeNode current, K key) throws KeyNotFoundException {
    if (current == null)
      throw new KeyNotFoundException("key not found");

    int compareResult = current.data.compareTo(key);
    if (compareResult == 0) // Find it!!
      return current.data;
    else if (compareResult > 0) // Continue to find in the left sub tree
      return getHelper(current.left, key);
    else // Continue to find in the right sub tree
      return getHelper(current.right, key);
  }

  /**
   * Returns true if the key is in the data structure If key is null, 
   * throw IllegalNullKeyException
   * Returns false if key is not null and is not present
   * 
   * @key - key to be detected
   * @returns true if the key is in the data structure If key is null. Otherwise
   *          false
   * @throws IllegalNullKeyException if the key is null
   */
  public boolean contains(K key) throws IllegalNullKeyException {
    if (key == null)
      throw new IllegalNullKeyException("null key");

    TreeNode current = root;
    while (current != null) {
      // If current is null, it means there is no such key
      int compareResult = current.data.compareTo(key);
      if (compareResult == 0) // Found the key
        return true;
      else if (compareResult > 0)
        current = current.left;
      else
        current = current.right;
    }

    return false;
  }

  /**
   * Returns the number of key,value pairs in the data structure
   */
  public int numKeys() {
    return size;
  }

    /**
     * Print the tree. 
     *
     * For our testing purposes of your print method: 
     * all keys that we insert in the tree will have 
     * a string length of exactly 2 characters.
     * example: numbers 10-99, or strings aa - zz, or AA to ZZ
     *
     * This makes it easier for you to not worry about spacing issues.
     *
     * You can display a binary search in any of a variety of ways, 
     * but we must see a tree that we can identify left and right children 
     * of each node
     *
     * For example: 
     
           30
           /\
          /  \
         20  40
         /   /\
        /   /  \
       10  35  50 

       Look from bottom to top. Inorder traversal of above tree (10,20,30,35,40,50)
       
       Or, you can display a tree of this kind.

       |       |-------50
       |-------40
       |       |-------35
       30
       |-------20
       |       |-------10
       
       Or, you can come up with your own orientation pattern, like this.

       10                 
               20
                       30
       35                
               40
       50                  

       The connecting lines are not required if we can interpret your tree.

     */
  public void print() {
    System.out.println("********** PRINT TREE ********");
    printHelper(root, 0);
    System.out.println("******* PRINT TREE OVER *******");
  }
  
  /**
   * Given the root and current, print the tree with certain indent associated 
   * with the current height. 
   * 
   * The result is like this:
   * 
       |       |-------50
       |-------40
       |       |-------35
       30
       |-------20
       |       |-------10
   * 
   * @param current - current root
   * @param currentHeight - current height
   */
  private void printHelper(TreeNode current, int currentHeight) {
    if(current == null)
      return;
    else {
      printHelper(current.right, currentHeight + 1);  // Print right first 
     
      String temp = new String();
      for(int i = 0; i<currentHeight - 1; i++) {
        temp = temp.concat("       ");
      }
      if (currentHeight > 0)
        temp = temp.concat(" |------");
      System.out.println(temp + current.data); // print mid second
      
      printHelper(current.left, currentHeight + 1); // Print left last 
      
    }
  }
} // copyrighted material, students do not have permission to post on public sites
//deppeler@cs.wisc.edu