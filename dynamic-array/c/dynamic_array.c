#include "dynamic_array.h"

#include <stdint.h>
#include <stdlib.h>

DaStatus da_init(DynamicArray *array, size_t initial_capacity)
{
    if (array == NULL) {
        return DA_INVALID_ARGUMENT;
    }

    array->elements = NULL;
    array->size = 0;
    array->capacity = 0;

    if (initial_capacity == 0) {
        return DA_OK;
    }

    if (initial_capacity > SIZE_MAX / sizeof *array->elements) {
        return DA_ALLOCATION_FAILED;
    }

    int *elements = malloc(initial_capacity * sizeof *elements);

    if (elements == NULL) {
        return DA_ALLOCATION_FAILED;
    }

    array->elements = elements;
    array->capacity = initial_capacity;
    return DA_OK;
}

void da_destroy(DynamicArray *array)
{
    if (array == NULL) {
        return;
    }

    free(array->elements);
    
    array->elements = NULL;
    array->size = 0;
    array->capacity = 0;
}

DaStatus da_get(const DynamicArray *array, size_t index, int *out_value)
{
    if (array == NULL || out_value == NULL) {
        return DA_INVALID_ARGUMENT;
    }

    if (index >= array->size) {
        return DA_OUT_OF_BOUNDS;
    }

    *out_value = array->elements[index];
    return DA_OK;
}

DaStatus da_set(DynamicArray *array, size_t index, int value)
{
    if (array == NULL) return DA_INVALID_ARGUMENT;

    if (index >= array->size) return DA_OUT_OF_BOUNDS;

    array->elements[index] = value;
    return DA_OK;
}

static DaStatus ensure_capacity(DynamicArray *array)
{
    if (array->size < array->capacity) return DA_OK;

    size_t max_capacity = SIZE_MAX / sizeof *array->elements;
    size_t new_capacity;

    if (array->capacity == 0) {
        new_capacity = 1;
    } else {
        if (array->capacity > max_capacity / 2) {
            return DA_ALLOCATION_FAILED;
        }
        new_capacity = array->capacity * 2;
    }

    int *replacement = realloc(
        array->elements, 
        new_capacity * sizeof *array->elements
    );

    if (replacement == NULL) return DA_ALLOCATION_FAILED;

    array->elements = replacement;
    array->capacity = new_capacity;
    return DA_OK;
}

DaStatus da_append(DynamicArray *array, int value)
{
    if (array == NULL) return DA_INVALID_ARGUMENT;

    DaStatus result = ensure_capacity(array);
    if (result != DA_OK) return result;

    array->elements[array->size] = value;
    array->size += 1;
    return DA_OK;
}

DaStatus da_insert(DynamicArray *array, size_t index, int value)
{ 
    if (array == NULL) return DA_INVALID_ARGUMENT;
    if (index > array->size) return DA_OUT_OF_BOUNDS;

    DaStatus result = ensure_capacity(array);
    if (result != DA_OK) return result;

    // Move all elements from last down to insert pos over by one
    for (size_t i = array->size; i > index; i--) {
        array->elements[i] = array->elements[i - 1];
    }
    array->elements[index] = value;
    array->size += 1;
    return DA_OK;
}

DaStatus da_remove(DynamicArray *array, size_t index, int *out_value)
{
    if (array == NULL || out_value == NULL) return DA_INVALID_ARGUMENT;

    if(index >= array->size) return DA_OUT_OF_BOUNDS;

    *out_value = array->elements[index];

    // Overwrite each element from index to end with its successor
    for (size_t i = index; i < array->size - 1; i++) { // Dont care about last elem
        array->elements[i] = array->elements[i + 1];
    }
    array->size -= 1;
    return DA_OK;
}

