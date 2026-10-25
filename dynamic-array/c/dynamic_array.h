#ifndef DYNAMIC_ARRAY_H
#define DYNAMIC_ARRAY_H

#include <stddef.h>

typedef struct {
    int *elements;
    size_t size;
    size_t capacity;
} DynamicArray;

typedef enum {
    DA_OK,
    DA_INVALID_ARGUMENT,
    DA_OUT_OF_BOUNDS,
    DA_ALLOCATION_FAILED
} DaStatus;

DaStatus da_init(DynamicArray *array, size_t initial_capacity);
void da_destroy(DynamicArray *array);

DaStatus da_get(const DynamicArray *array, size_t index, int *out_value);
DaStatus da_set(DynamicArray *array, size_t index, int value);
DaStatus da_append(DynamicArray *array, int value);
DaStatus da_insert(DynamicArray *array, size_t index, int value);
DaStatus da_remove(DynamicArray *array, size_t index, int *out_value);

#endif