import java.io.BufferedReader; 
import java.io.FileReader; 
import java.io.IOException;
import java.util.Scanner;

public class SpellChecker { 

    GTUHashSet<String> dictionary = new GTUHashSet<>();
    int numCol;

    public SpellChecker() throws IOException{
        // Read words from dictionary file and add to set
        BufferedReader reader = new BufferedReader(new FileReader("dictionary.txt"));
        String word;
        while ((word = reader.readLine()) != null) {
            dictionary.add(word.trim());
        }
        reader.close();

        numCol=0;

    }


    public int getNumCo(){
        int old = numCol;
        numCol=0;
        return old;
    }

    // Generates suggestions with edit distance ≤ 2 from the input word
    public  GTUHashSet<String> generateEditDistance2(String input ) {

        GTUHashSet<String> suggestions = new GTUHashSet<>();
        GTUHashSet<String> edit1 = new GTUHashSet<>(); // Temporary set for edit distance 1 words

        // Generate edit distance 1 words from input
        generateEditDistance1(input, edit1,  false);

        for (String candidate : edit1) {
            // If ED1 word is in dictionary, add to suggestions
            if (dictionary.contains(candidate)) {
                suggestions.add(candidate);
            }
            // Generate ED1 of ED1  total ED2 from original word
            generateEditDistance1(candidate, suggestions,  true);
        }

        return suggestions;
    }

    // Generates all words with edit distance 1 from the input word
    private  void generateEditDistance1(String word, GTUHashSet<String> resultSet, boolean isCheck) {
        int len = word.length();

        // 1. Deletions: remove each character once
        for (int i = 0; i < len; i++) {
            StringBuilder sb = new StringBuilder(len - 1);
            sb.append(word, 0, i).append(word, i + 1, len);
            String candidate = sb.toString();
            if (!isCheck || dictionary.contains(candidate)) {
                resultSet.add(candidate);
            }
        }

        // 2. Substitutions: replace each character with 'a' to 'z'
        for (int i = 0; i < len; i++) {
            char original = word.charAt(i);
            StringBuilder sb = new StringBuilder(word);
            for (char c = 'a'; c <= 'z'; c++) {
                if (word.charAt(i) != c) {
                    sb.setCharAt(i, c); // substitute
                    String candidate = sb.toString();
                    if (!isCheck || dictionary.contains(candidate)) {
                        resultSet.add(candidate);
                    }
                }
            }
            sb.setCharAt(i, original); // restore original
        }

        // 3. Insertions: insert 'a' to 'z' at every position
        for (int i = 0; i <= len; i++) {
            StringBuilder sb = new StringBuilder(len + 1);
            sb.append(word, 0, i);
            for (char c = 'a'; c <= 'z'; c++) {
                sb.append(c);
                sb.append(word, i, len);
                String candidate = sb.toString();
                if (!isCheck || dictionary.contains(candidate)) {
                    resultSet.add(candidate);
                }
                sb.setLength(i); // reset builder to position before insertion
            }
        }

        // 4. Transpositions: swap every two adjacent characters
        for (int i = 0; i < len - 1; i++) {
            if (word.charAt(i) != word.charAt(i + 1)) {
                StringBuilder sb = new StringBuilder(word);
                char temp = sb.charAt(i);
                sb.setCharAt(i, sb.charAt(i + 1));
                sb.setCharAt(i + 1, temp);
                String candidate = sb.toString();
                if (!isCheck || dictionary.contains(candidate)) {
                    resultSet.add(candidate);
                }
            }
        }
        numCol+=resultSet.numOfCollision();
        
    }

    public static void main(String[] args) throws IOException {

       

        SpellChecker spellChecker = new SpellChecker();
        int collisionInDic;

        // Print total number of words in dictionary
        System.out.print("Number of words in the dictionary: ");
        System.out.println(spellChecker.dictionary.size());
        System.out.print("Number of collisions that occurred during dictionary loading: ");
        collisionInDic = spellChecker.dictionary.numOfCollision();
        System.out.println(collisionInDic);

        Scanner scanner = new Scanner(System.in);
        boolean isCorrectInput = true;
        boolean longWord = false;

        // Infinite loop to take user input
        while(true) {
            Runtime runtime = Runtime.getRuntime();
            System.gc();
            long memBefore = runtime.totalMemory() - runtime.freeMemory();

            isCorrectInput = true;
            longWord = false; // if input size > 20

            System.out.println("======================================================");
            System.out.print("Enter a word (or type 'EXIT' to quit): ");
            String input  = scanner.nextLine().trim();

            if(input.equals("EXIT")){
                break;
            }

            // Validate that input only contains letters
            for (char c : input.toCharArray()) {
                if (!Character.isLetter(c)) {
                    System.out.println("The input you enter must contain only letters. Try again.");
                    isCorrectInput = false;
                    break;
                }
            }

            // If word is longer than 20 characters, skip suggestions
            if (input.length() > 20) {
                long startTime = System.nanoTime();
                longWord = true;
                System.out.println("No suggestions found.");
                long endTime = System.nanoTime();
                System.out.printf("Lookup and suggestion took %.2f ms.\n", (endTime - startTime) / 1e6);
            }

            // If input is valid and not too long
            if (isCorrectInput && !longWord) {
                long startTime = System.nanoTime();

                // Check if word is in dictionary
                if (spellChecker.dictionary.contains(input)) {
                    System.out.println("Correct spelling.");
                } else {
                    System.out.println("Incorrect spelling.");
                    
                    // Generate suggestions using edit distance
                    GTUHashSet<String> suggestions = spellChecker.generateEditDistance2(input);
                    if (suggestions.size() == 0) {
                        System.out.println("No suggestions found.");
                    } else {
                        System.out.print("Suggestions: ");
                        System.out.println(suggestions);

                    }
                    System.out.print("Number of collisions : ");
                    System.out.println(spellChecker.getNumCo());
                }

                long endTime = System.nanoTime();
                System.out.printf("Lookup and suggestion took %.2f ms.\n", (endTime - startTime) / 1e6);
            }
            long memAfter = runtime.totalMemory() - runtime.freeMemory();
            System.out.println("Approximate memory used : " + (memAfter - memBefore)/1_000_000.0 + " MB");

        }

        scanner.close();
    }
}
