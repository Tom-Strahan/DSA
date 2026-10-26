from dynamic_array import DynamicArray
import pytest


def test_append_from_zero_capacity():
    array = DynamicArray(0)

    array.append(42)

    assert len(array) == 1
    assert array.get(0) == 42


def test_remove_from_one_capacity():
    array = DynamicArray(0)

    array.append(10)

    removed = array.remove(0)

    assert len(array) == 0
    assert removed == 10
    with pytest.raises(IndexError):
        array.get(0)


def test_insert_at_end():
    array = DynamicArray(0)

    array.append(99)
    array.append(88)
    array.insert(2, 77)

    assert len(array) == 3
    assert array.get(2) == 77
    assert array.get(1) == 88


def test_remove_middle():
    array = DynamicArray(0)

    array.append(10)
    array.append(20)
    array.append(30)

    removed = array.remove(1)
    assert removed == 20
    assert array.get(0) == 10
    assert array.get(1) == 30


def test_insert_middle():
    array = DynamicArray(3)

    array.append(5)
    array.append(6)
    array.append(8)
    array.append(9)

    array.insert(2, 7)

    assert len(array) == 5
    assert array.get(1) == 6
    assert array.get(2) == 7
    assert array.get(3) == 8
    assert array.get(4) == 9


def test_set():
    array = DynamicArray(3)

    array.append(5)
    array.append(6)

    array.set(1, 9)

    assert len(array) == 2
    assert array.get(0) == 5
    assert array.get(1) == 9
