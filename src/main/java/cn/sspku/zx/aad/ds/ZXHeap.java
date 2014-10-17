package cn.sspku.zx.aad.ds;

public class ZXHeap {
	private int[] data;

	public ZXHeap(int[] data) {
		this.data = data;
	}

	/**
	 * 调整下标为elementI的元素，从而使得父节点大于左右子节点
	 * 
	 * @param elementIdx
	 *            所需调整元素的下标
	 * @param size
	 *            调整元素的区间
	 */
	private void adjust(int elementIdx, int size) {
		// 检查实参是否符合要求：可以调整的元素必须是非叶子节点
		if (size > 0 && elementIdx >= 0 && elementIdx < size / 2) {
			int maxIdx = elementIdx, leftChildIdx = elementIdx * 2 + 1, rightChildIdx = elementIdx * 2 + 2;
			// 非叶子节点一定有左子节点，所以无需判断其是否存在
			if (data[maxIdx] < data[leftChildIdx])
				maxIdx = leftChildIdx;
			// 非叶子节点不一定有右子节点，所以需要先判断其是否存在
			if (rightChildIdx < size && data[maxIdx] < data[rightChildIdx])
				maxIdx = rightChildIdx;

			/*
			 * 如果子节点大于父节点，则父子节点数据必须进行交换 同时查看交换后的结果是否满足堆的要求， 不满足则继续调整
			 */
			if (maxIdx != elementIdx) {
				swap(elementIdx, maxIdx);
				adjust(maxIdx, size);
			}
		}
		// else
		// throw new
		// ArrayIndexOutOfBoundsException("parameter elementIdx is out of array bounds!");
	}

	/**
	 * 交换两个元素
	 * 
	 * @param first
	 * @param second
	 */
	private void swap(int first, int second) {
		if (first != second) {
			int tpr = data[first];
			data[first] = data[second];
			data[second] = tpr;
		}
	}

	/**
	 * 构建一个堆
	 */
	public void BuildHeap() {
		int size = data.length - 1;
		for (int idx = data.length / 2 - 1; idx >= 0; --idx) {
			adjust(idx, size);
		}
	}

	/**
	 * 进行堆排序
	 */
	public void Sort() {
		BuildHeap();

		for (int idx = data.length - 1; idx >= 0; --idx) {
			// 将堆顶的值交换至末尾
			swap(0, idx);
			// 调整大小为（size-1）的堆的堆顶元素，此时idx=size-1
			adjust(0, idx);
		}
	}

	/**
	 * 将数据以串的形式输出到标准输出设备
	 */
	public void Print() {
		for (int idx = 0; idx < data.length; ++idx) {
			System.out.printf("%5d", data[idx]);
		}
	}

	public static void main(String[] args) {
		int[] data = new int[] { 3, 543, 645, 245, 45, 24, 23, 67 };

		ZXHeap heap = new ZXHeap(data);

		heap.Sort();
		heap.Print();
	}
}
