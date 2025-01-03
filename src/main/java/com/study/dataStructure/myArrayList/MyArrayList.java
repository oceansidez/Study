package com.study.dataStructure.myArrayList;


/**
 * 动态数组实现
 *
 * @author zzh
 * @date 2024/9/23 17:32
 */
public class MyArrayList<E> {

    /**
     * 动态数组的个数
     */
    private int size;

    /**
     * 数据数组
     */
    private E[] elements;

    /**
     * 默认容量为10
     */
    private static final int DEFAULT_CAPACITY = 5;

    public MyArrayList(int capacity) {
        capacity = Math.max(capacity, DEFAULT_CAPACITY);
        elements = (E[]) new Object[capacity];
    }

    public MyArrayList() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * 清除所有元素
     */
    public void clear() {
        // 将每个元素引用改为null
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    /**
     * 元素的数量
     *
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * 是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 查看元素的索引
     *
     * @param element
     * @return
     */
    public int indexOf(E element) {
        // null单独判断
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(elements[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * 是否包含某个元素
     *
     * @param element
     * @return
     */
    public boolean contains(E element) {
        return indexOf(element) != -1;
    }

    /**
     * 添加元素到尾部
     *
     * @param element
     */
    public void add(E element) {
        add(size, element);
    }

    /**
     * 在index位置插入一个元素
     *
     * @param index
     * @param element
     */
    public void add(int index, E element) {
        rangeCheckForAdd(index);
        // 确保capacity容量
        ensureCapacity(size + 1);
        // 将index后的元素后移，从最后一个元素开始
        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }
        size++;
        elements[index] = element;
    }

    /**
     * 确保容量扩容
     *
     * @param capacity
     */
    private void ensureCapacity(int capacity) {
        int oldCapacity = elements.length;
        // 当原有容量大于添加后的容量直接返回，否则扩容到原来的1.5倍
        if (oldCapacity > capacity) {
            return;
        }
        // 创建一个新的数组进行复制
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        E[] newElements = (E[]) new Object[newCapacity];
        for (int i = 0; i < elements.length; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;
        System.out.println(oldCapacity + "扩容为" + newCapacity);
    }

    /**
     * 获取index位置的元素
     *
     * @param index
     * @return
     */
    public E get(int index) {
        rangeCheck(index);
        return elements[index];
    }

    /**
     * 设置index位置的元素
     *
     * @param index
     * @param element
     * @return 原来的元素ֵ
     */
    public E set(int index, E element) {
        rangeCheck(index);
        E oleElement = elements[index];
        elements[index] = element;
        return oleElement;
    }


    /**
     * 删除index位置的元素
     *
     * @param index
     * @return 被删除的元素
     */
    public E remove(int index) {
        rangeCheck(index);
        E oleElement = elements[index];
        // 将index前的元素进行前移（更改指向），从index后的一个元素开始，防止移除时越界问题设置最大遍历到size而不是size+1
        for (int i = index + 1; i < size; i++) {
            elements[i - 1] = elements[i];
        }
        // 将最后一个引用指向null
        elements[size--] = null;
        return oleElement;
    }


    /**
     * 边界判定
     *
     * @return
     */
    private void outOfBounds(int index) {
        throw new IndexOutOfBoundsException("Index:" + index + ", Size:" + size);
    }

    private void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            outOfBounds(index);
        }
    }

    private void rangeCheckForAdd(int index) {
        if (index < 0 || index > size) {
            outOfBounds(index);
        }
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("size=").append(size).append(", [");
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                str.append(", ");
            }
            str.append(elements[i]);
        }
        str.append("]");
        return str.toString();
    }
}
