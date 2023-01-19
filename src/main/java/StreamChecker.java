public class StreamChecker {

    class TrieNode{
        public boolean isWord;
        public TrieNode childs [];
        TrieNode(){
            isWord = false;
            childs = new TrieNode[26];
        }
    }

    public TrieNode root = new TrieNode();
    public int maxsize;
    public StringBuilder sb = new StringBuilder();

    public StreamChecker(String[] words) {
        createTrie(words);
    }

    public boolean query(char letter) {
        sb.append(letter);
        if(sb.length() > maxsize)
            sb.deleteCharAt(0);

        TrieNode curr = root;
        for(int i = sb.length()-1;i>=0;i--){
            int ch = sb.charAt(i) - 'a';
            curr = curr.childs[ch];
            if(curr == null) break;
            if(curr.isWord == true) return true;
        }
        return false;
    }

    public void createTrie(String [] words){
        for(String s : words){
            maxsize = Math.max(maxsize,s.length());
            TrieNode current = root;
            for(int i = s.length() -1 ; i>=0 ; i--){
                int ch = s.charAt(i) - 'a';
                if(current.childs[ch] == null){
                    current.childs[ch] = new TrieNode();
                }
                current = current.childs[ch];
            }
            current.isWord = true;
        }
    }

    public static void main(String args []){
        StreamChecker streamChecker = new StreamChecker(new String[]{"cd", "f", "kl"});
        System.out.println(streamChecker.query('a')); // return False
        System.out.println(streamChecker.query('b')); // return False
        System.out.println(streamChecker.query('c')); // return False
        System.out.println(streamChecker.query('d')); // return True, because 'cd' is in the wordlist
        System.out.println(streamChecker.query('e')); // return False
        System.out.println(streamChecker.query('f')); // return True, because 'f' is in the wordlist
        System.out.println(streamChecker.query('g')); // return False
        System.out.println(streamChecker.query('h')); // return False
        System.out.println(streamChecker.query('i')); // return False
        System.out.println( streamChecker.query('j')); // return False
        System.out.println(streamChecker.query('k')); // return False
        System.out.println(streamChecker.query('l')); // return True, because 'kl' is in the wordlist

    }
}

