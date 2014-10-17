package cn.sspku.zx.aad.chapterTwo;

/**
 * 归并排序
 * 
 * @author zhangxu
 * 
 */
public class Es_2_7 {
	public static abstract class MergeSorter {
		protected int[] in, array;

		public abstract MergeSorter Sort();

		public MergeSorter(int[] _in) {
			in = _in;
			array = new int[_in.length];
		}

		protected final void merge(int start, int mid, int end) {
			int _mid = mid, p = start;
			while (start < mid && _mid < end) {
				if (in[start] < in[_mid]) {
					array[p++] = in[start++];
				} else {
					array[p++] = in[_mid++];
				}
			}
			while (start < mid)
				array[p++] = in[start++];
			while (_mid < end)
				array[p++] = in[_mid++];
		}

		protected final void copy(int start, int end) {
			while (start < end)
				in[start] = array[start++];
		}

		public final void Print() {
			System.out.printf("\nThe final result is ：");
			for (int idx = 0; idx < in.length; ++idx) {
				System.out.printf("%8d", in[idx]);
			}

		}
	}

	public static class GeneralMergeSorter extends MergeSorter {

		public GeneralMergeSorter(int[] in) {
			super(in);
		}

		private GeneralMergeSorter MergeSort(int start, int end) {
			if (end <= start + 1)
				return this;
			int mid = (start + end) / 2;
			MergeSort(start, mid);
			MergeSort(mid, end);

			merge(start, mid, end);
			copy(start, end);

			return this;
		}

		public GeneralMergeSorter Sort() {
			return MergeSort(0, in.length);
		}

	}

	public static class NaturalMergeSorter extends MergeSorter {
		public NaturalMergeSorter(int[] in) {
			super(in);
		}

		private NaturalMergeSorter naturalMergeSort(int start, int end) {
			int _start = start, _mid, _end;

			while (true) {
				//确定第一个有序子数组
				_mid = _start;
				while (_mid < end - 1 && in[_mid] <= in[_mid + 1]) {
					++_mid;
				}
				_end = ++_mid;

				//确定第二个有序子数组
				if (_mid != end) {
					while (_end < end - 1 && in[_end] <= in[_end + 1]) {
						++_end;
					}
					++_end;
				}

				//合并两个子数组
				merge(_start, _mid, _end);
				copy(_start, _end);

				if (_end != end) {
					_start = _end;
				} else if (_start != start) {
					_start = start;
				} else
					return this;
			}
		}

		@Override
		public MergeSorter Sort() {
			return naturalMergeSort(0, in.length);
		}
	}

	public static void main(String[] args) {
		int[] in = new int[] { 34, 435, 223, 45, 34, 23, 59, 82, 93, 84, 75 };

		// new GeneralMergeSorter(in).Sort().Print();

		new NaturalMergeSorter(in).Sort().Print();
	}

}
