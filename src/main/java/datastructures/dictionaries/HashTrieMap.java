package datastructures.dictionaries;

import cse332.exceptions.NotYetImplementedException;
import cse332.interfaces.trie.TrieMap;
import cse332.types.BString;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * See cse332/interfaces/trie/TrieMap.java
 * and cse332/interfaces/misc/Dictionary.java
 * for method specifications.
 */
public class HashTrieMap<A extends Comparable<A>, K extends BString<A>, V> extends TrieMap<A, K, V> {
    public class HashTrieNode extends TrieNode<Map<A, HashTrieNode>, HashTrieNode> {
        public HashTrieNode() {
            this(null);
        }

        public HashTrieNode(V value) {
            this.pointers = new HashMap<A, HashTrieNode>();
            this.value = value;
        }

        @Override
        public Iterator<Entry<A, HashTrieMap<A, K, V>.HashTrieNode>> iterator() {
            return pointers.entrySet().iterator();
        }
    }

    public HashTrieMap(Class<K> KClass) {
        super(KClass);
        this.root = new HashTrieNode();
    }

    @Override
    public V insert(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException();
        }
        if (this.root == null) {
            this.root = new HashTrieNode();
        }

        V temp = null;
        if (key.isEmpty()) {
            temp = this.root.value;
            this.root.value = value;
        } else {
            HashTrieNode node = (HashTrieNode) this.root;
            Iterator<A> itr = key.iterator();

            while (itr.hasNext()) {
                A piece = itr.next();
                if (!node.pointers.containsKey(piece)) {
                    node.pointers.put(piece, new HashTrieNode());
                }
                node = node.pointers.get(piece);

            }
            temp = node.value;
            node.value = value;
        }

        if (temp == null) {
            this.size++;
        }
        return temp;
    }

    @Override
    public V find(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }

        if (this.root == null) {
            return null;
        }

        HashTrieNode node = (HashTrieNode) this.root;

        Iterator<A> itr = key.iterator();

        while (itr.hasNext()) {
            A piece = itr.next();
            node = node.pointers.get(piece);
            if (node == null) {
                return null;
            }
        }

        return node.value;
    }

    @Override
    public boolean findPrefix(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }

        if (this.root == null) {
            return false;
        }

        HashTrieNode node = (HashTrieNode) this.root;

        Iterator<A> itr = key.iterator();

        while (itr.hasNext()) {
            A piece = itr.next();
            node = node.pointers.get(piece);
            if (node == null) {
                return false;
            }
        }

        return true;
    }

    @Override
    public void delete(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        HashTrieNode temp = (HashTrieNode)this.root;

        Iterator<A> itr = key.iterator();
        A tempPiece = null;

        HashTrieNode node = (HashTrieNode)this.root;

        while (itr.hasNext()) {
            A piece = itr.next();

            if (node == null) {
                return;
            }

            if (node.value != null || node.pointers.size() >= 2) {
                temp = node;
                tempPiece = piece;
            }

            if (node.pointers.size() != 0) {
                node = node.pointers.get(piece);
            } else {
                return;
            }
        }
        if (node != null && node.value != null) {
            if (node.pointers.size() != 0) {
                node.value = null;
            } else if (tempPiece != null) {
                temp.pointers.remove(tempPiece);
            }
            node.value = null;

            this.size--;
        }
    }


    @Override
    public void clear() {
        this.size = 0;
        this.root = new HashTrieNode();
    }
}
