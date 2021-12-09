package InterfaceAdapter.Search;

import InterfaceAdapter.InstanceMain;

import java.util.List;

@SuppressWarnings("AccessStaticViaInstance")
public class Search {

    /**
     * return all recommendations to auto-complete the client's input word based on the strings stored in the Trie，
     * return an empty
     * @param word the String that client enter
     * @return return a list of suggested movieNames
     */
    public static List<String> suggestionSearch(String word) {
        List<String> words = InstanceMain.getMovieManager().getMovieNames();
        Trie trie = new Trie(words);
        return trie.suggest(word);
    }




}
