#include <assert.h>
#include <stdio.h>

#include "dynamic_array.h"

static void test_initialise_empty_array(void)
{
    DynamicArray array;

    assert(da_init(&array, 0) == DA_OK);
    assert(array.size == 0);
    assert(array.capacity == 0);
    assert(array.elements == NULL);

    da_destroy(&array);
}

static void test_insert_into_empty_array(void)
{
    DynamicArray array;

    assert(da_init(&array, 0) == DA_OK);
    assert(array.size == 0);
    assert(array.capacity == 0);
    assert(array.elements == NULL);

    assert(da_insert(&array, 0, 6) == DA_OK);
    int out;
    assert(da_get(&array, 0, &out) == DA_OK);
    assert(out == 6);
    assert(array.size == 1);

    da_destroy(&array);
}

static void test_multiple_inserts(void)
{
    DynamicArray array;
    assert(da_init(&array, 5) == DA_OK);

    for (int i = 0; i < 1000; i++) {
        da_append(&array, i);
    }
    assert(array.size = 1000);

    assert(da_insert(&array, 0, 9000) == DA_OK);
    assert(da_insert(&array, 500, 8000) == DA_OK);
    assert(da_insert(&array, 1002, 5000) == DA_OK);
    assert(array.size == 1003);
}

static void test_inserts_index_oob(void)
{
    DynamicArray array;
    assert(da_init(&array, 5) == DA_OK);

    for (int i = 0; i < 1000; i++) {
        da_append(&array, i);
    }
    assert(array.size = 1000);

    assert(da_insert(&array, 1002, 5000) == DA_OUT_OF_BOUNDS);
    assert(array.size == 1000);
}

static void test_appending(void)
{
    DynamicArray array;
    assert(da_init(&array, 5) == DA_OK);
    assert(array.size == 0);
    assert(array.capacity == 5);
    
    for(int i = 0; i < 10; i++) {
        assert(da_append(&array, i) == DA_OK);
        int out;
        assert(da_get(&array, (size_t) i, &out) == DA_OK);
        assert(out == i);
        assert(array.size == (size_t) i+1);
    }

    da_destroy(&array);
}

static void test_removing(void)
{
    DynamicArray array;
    da_init(&array, 2);

    for (int i = 0; i < 10; i++) {
        da_append(&array, i);
    }

    int out;
    assert(da_remove(&array, 3, &out) == DA_OK);
    assert(out == 3);
    assert(array.size == 9);

    da_destroy(&array);
}

int main(void)
{
    test_initialise_empty_array();
    test_insert_into_empty_array();
    test_multiple_inserts();
    test_inserts_index_oob();
    test_appending();
    test_removing();

    puts("All tests passed.");
    return 0;
}