from typing import cast


class DynamicArray[T]:
    def __init__(self, initial_capacity: int = 0):
        if initial_capacity < 0:
            raise ValueError("Capacity must not be negative")

        self._elements: list[None | T] = [None] * initial_capacity
        self._size = 0

    def __len__(self) -> int:
        return self._size

    def get(self, index: int) -> T:
        if index < 0 or index >= self._size:
            raise IndexError(f"Index {index} out of bounds for length {self._size}")
        return cast(T, self._elements[index])

    def set(self, index: int, value: T) -> None:
        if index < 0 or index >= self._size:
            raise IndexError(f"Index {index} out of bounds for length {self._size}")
        self._elements[index] = value

    def append(self, value: T) -> None:
        self._ensure_capacity()
        self._elements[self._size] = value
        self._size += 1

    def insert(self, index: int, value: T) -> None:
        if index < 0 or index > self._size:  # Allow insert at end
            raise IndexError(
                f"Index {index} out of bounds for insert on length {self._size}"
            )
        self._ensure_capacity()

        # Shift all elements after index up by one
        for i in range(self._size, index, -1):  # Start end + 1 (size), finish index + 1
            self._elements[i] = self._elements[i - 1]
        self._elements[index] = value
        self._size += 1

    def remove(self, index: int) -> T:
        if index < 0 or index >= self._size:
            raise IndexError(f"Index {index} out of bounds for length {self._size}")
        removed = self.get(index)
        # Shift all elements after index down by one
        for i in range(index, self._size - 1):
            self._elements[i] = self._elements[i + 1]
        self._size -= 1
        self._elements[self._size] = None
        return removed

    def _ensure_capacity(self) -> None:
        if self._size < len(self._elements):
            return

        new_capacity = max(1, len(self._elements) * 2)  # Handles growing len 0

        new_array: list[None | T] = [None] * new_capacity
        for i, elem in enumerate(self._elements):
            new_array[i] = elem

        self._elements = new_array
