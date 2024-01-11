package labb6;

import java.util.*;

public class MySparseVec<E extends Comparable<E>> implements SparseVec<E>{

    private TreeMap<Integer,E> fooMap;

    public MySparseVec() {
        fooMap = new TreeMap<>();
    }
    
    @Override
    public void add(E elem) {
        Integer minIndexBeingUsed = minIndex();
        Integer maxIndexBeingUsed = maxIndex();
        if (minIndexBeingUsed==-1){
            fooMap.put(0,elem);
            return;
        }
        for (int i = minIndexBeingUsed; i <= maxIndexBeingUsed+1; i++) {
            if (fooMap.get(i)==null){
                fooMap.put(i, elem);
                return;
            }
        }
    }

    @Override
    public void add(int pos, E elem) {
        fooMap.put(pos,elem);
    }

    @Override
    public E get(int pos) {
        E itemAtPos = fooMap.get(pos);
        return itemAtPos;
    }

    @Override
    public int indexOf(E elem) {

        if (!fooMap.containsValue(elem)){
            System.err.println("This element doesn't exist in the sparse vec");
            return 0;
        }

        for (Integer index : fooMap.keySet()) {
            if (fooMap.get(index).equals(elem)){
                return index;
            }
        }
        return 0;
    }

    @Override
    public int maxIndex() {
        if (fooMap.size()==0){
            return -1;
        }
        int lastIndex = fooMap.lastKey();        
        return lastIndex;
    }

    @Override
    public int minIndex() {
        if (fooMap.size()==0){
            return -1;
        }
        int firstIndex = fooMap.firstKey();

        return firstIndex;
    }

    @Override
    public void removeAt(int pos) {
        fooMap.remove(pos);
    }

    @Override
    public void removeElem(E elem) {
        Integer indexOfElem = indexOf(elem);
        fooMap.remove(indexOfElem);
    }

    @Override
    public int size() {
        return fooMap.size();
    }

    @Override
    public List<E> sortedValues() {
        Collection<E> values = fooMap.values();
        List<E> listOfVals = new ArrayList<>();
        listOfVals.addAll(values);
        Collections.sort(listOfVals);
        return listOfVals;
    }

    @Override
    public Object[] toArray() {
        Integer maxIndex = maxIndex();
        Object[] result = new Object[maxIndex+1];
        for (int i = 0; i <= maxIndex(); i++) {
            
            result[i] = fooMap.get(i);
            // System.out.println(fooMap.get(i));
        }
        return result;
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        E nextElement;
        String nextLine;
        for (Integer index : fooMap.keySet()) {
            builder.append("\n");            
            nextElement = fooMap.get(index);
            nextLine = "Index: "+index.toString()+" Value: "+nextElement.toString();
            builder.append(nextLine);
        }
        builder.deleteCharAt(0);
        String result = builder.toString();
        return result;
    }
    
    // I SparseVec-implementationens main-metod, skapa en vektor, 
    // lägg in minst fem värden och testa toString(), toArray() och sortedValues() 
    // genom att anropa dem och skriva ut resultaten. 
    // Använd alltså inte JUnit och assert för dessa tester.  

    public static void main(String[] args) {
        SparseVec<Integer> myVec = new MySparseVec<>();
        myVec.add(1);
        myVec.add(1,7);
        myVec.add(2,2);
        myVec.add(3,9);
        myVec.add(7, 9);

        System.out.println("\n String representation of myVec");
        System.out.println(myVec.toString());
        
        System.out.println("\n Array representation of myVec");
        Object[] obj = myVec.toArray();
        for (int i =0; i<obj.length;i++) {
            System.out.println(obj[i]);
        }

        System.out.println("\n Sorted values");

        System.out.println(myVec.sortedValues());
    }
    
}
