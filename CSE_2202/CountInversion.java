import java.util.*;

class InversionCount{
	private List<Integer> data;
	private List<Integer> sorted;
	private int inversionCount = 0;

	InversionCount(List<Integer> a){
		this.data = a;
		this.sorted = a;
	} 

	void sort(){
		inversionCount = mergeSort(0, data.size() - 1);
		System.out.println(sorted);
		System.out.println("Inversion Count: " + inversionCount);
	}

	private int merge(List<Integer> arr1, List<Integer> arr2, List<Integer> finalArr)
	{
		int idx1 = 0, idx2 = 0, idxFinal = 0, localInvCnt = 0;
		while(idx1 < arr1.size() && idx2 < arr2.size())
		{
			if(arr1.get(idx1) < arr2.get(idx2))
				finalArr.set(idxFinal++, arr1.get(idx1++));
			else{
				finalArr.set(idxFinal++, arr2.get(idx2++)); 
				localInvCnt += (arr1.size() - idx1);
			}
		}

		while(idx1 < arr1.size())
			finalArr.set(idxFinal++, arr1.get(idx1++));
		while(idx2 < arr2.size())
			finalArr.set(idxFinal++, arr2.get(idx2++));

		return localInvCnt;
	}

	private int mergeSort(int low, int high){
		int localInvCnt = 0;

		if (low >= high)
            return localInvCnt;

        int mid = (low + high) / 2;

        localInvCnt += mergeSort(low, mid);
    	localInvCnt += mergeSort(mid + 1, high);

        List<Integer> leftPart = new ArrayList<>(data.subList(low, mid + 1));
        List<Integer> rightPart = new ArrayList<>(data.subList(mid + 1, high + 1));

        List<Integer> merged = new ArrayList<>(leftPart.size() + rightPart.size());
        for (int i = 0; i < leftPart.size() + rightPart.size(); i++)
            merged.add(0);
        localInvCnt += merge(leftPart, rightPart, merged);

        for (int i = 0; i < merged.size(); i++)
            sorted.set(low + i, merged.get(i));
        return localInvCnt;
	}

}

public class CountInversion{
	public static void main(String [] args){
		List<Integer> arr = new ArrayList<>();
		arr.add(4);
		arr.add(9);
		arr.add(1);
		arr.add(5);
		arr.add(6);
		arr.add(2);
		arr.add(0);
		arr.add(7);
		arr.add(3);
		arr.add(8);
		InversionCount cnt = new InversionCount(arr);
		cnt.sort();
	}
}