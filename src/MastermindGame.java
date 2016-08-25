import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.SynchronousQueue;

/**
 * Created by madmann on 8/22/16.
 */
public class MastermindGame {
    ArrayList<Integer> mPegs = new ArrayList<>();
    int mNumColors = 12;
    int mNumPegs = 12;

    public MastermindGame() {
        genNumColors();
        genNumPegs();
        makeList();
    }

    private void genNumPegs() {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Number of pegs: %n");
        int numPegs = scanner.nextInt();
        mNumPegs = numPegs;
        return;
    }


    private void genNumColors() {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Number of colors: %n");
        int numColors = scanner.nextInt();
        mNumColors = numColors;
    }

    private int genColor() {
        Random rand = new Random();
        int n = rand.nextInt(mNumColors) + 1;
        return n;
    }

    private void makeList() {
        int i;
        for (i = 0; i < mNumPegs; i++) {
            mPegs.add(i, genColor());
        }
    }

    public ArrayList<Integer> getPegs() {
        return mPegs;
    }

    private ArrayList<Integer> promptForGuess() throws IOException {
        ArrayList<Integer> list = new ArrayList<>();
        String guess = prompt();
        for (char character : guess.toCharArray()) {
            int number = Character.getNumericValue(character);
            list.add(number);

        }
        return list;
    }

    private String prompt() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.printf("Enter Guess, %d characters: %n", mNumPegs);
        String guess= br.readLine();
        if (guess.length()!=mNumPegs){
            prompt();
        }
        else {
            return guess;
        }
        return guess;
    }


    private boolean checkGuess() throws IOException {
        int numCorrectPegs = 0;
        int numCorrectColors = 0;
        ArrayList<Integer> list = mPegs;
        int i;
        ArrayList<Integer> guess = promptForGuess();
        for (i = 0; i < mPegs.size(); i++) {
            if (guess.get(i).equals
                    (mPegs.get(i))) {
                numCorrectPegs++;
            }
        }
        HashSet<Integer> mPegSet = new HashSet<>();
        int a;
        for (a = 0; a < mPegs.size(); a++) {
            if (!mPegSet.contains(mPegs.get(a))) {
                mPegSet.add(mPegs.get(a));
            }
        }
        HashMap<Integer, Integer> mColorsMap = new HashMap<>();
        int e;
        for (e = 0; e < mPegs.size(); e++) {
            if (mPegSet.contains(guess.get(e))) {
                if (!mColorsMap.containsKey(e)) {
                    mColorsMap.put(e, 1);
                }
                if (mColorsMap.containsKey(e)) {
                    mColorsMap.put(e, (mColorsMap.get(e) + 1));
                }
            }
        }
        numCorrectColors = mColorsMap.size();
        System.out.printf("%d correct pegs", numCorrectPegs);
        System.out.printf(", %d correct colors %n", numCorrectColors);
        if (numCorrectPegs != mNumPegs) {
            return false;
        }
        else {
            System.out.println("You Win!!!!");
            return true;
        }
    }

    public void run() throws IOException {
        boolean done = false;
        while (!done) {
            done = checkGuess();
        }
    }
}
